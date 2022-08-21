fun main() {
    BuildSequenceLearning().testIteratingIntegerSequenceWhenSequenceIsReturned()
}

class BuildSequenceLearning {

    fun testIteratingIntegerSequenceWhenSequenceIsReturned() {
        val numberIterator = getIntegerSequence().iterator()
        for (i in 0..10) {
            try {
                println(numberIterator.next())
            } catch (e: NoSuchElementException) {
                println("NoSuchElementException")
            }
        }
    }

    private fun getIntegerSequence(): Sequence<Int> {
        var x = 0
        return sequence {
            while(true) {
                if (x >= 10) return@sequence
                yield(x)
                x = x.inc()
            }
        }
    }

    fun getIntegerSequenceWithSuspendFuncInside(start: Int): Sequence<Int> {
        var x = start
        return sequence {
            while(true) {
                if (x >= start + 10) return@sequence
                /*
                  delay(2000)
                  This suspension function is invalid inside restricted suspending functions (i.e. sequence {...})

                  Compile error is induced saying
                  "Restricted suspending functions can only invoke member or extension suspending functions on their restricted coroutine scope"
                 */
                yield(x)
                x = x.inc()
            }
        }
    }
}