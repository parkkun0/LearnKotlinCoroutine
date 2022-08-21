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
}