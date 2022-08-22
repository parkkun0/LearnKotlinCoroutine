import kotlinx.coroutines.*

class RxMergeEquivalent {
    private val scope = CoroutineScope(Dispatchers.IO + CoroutineName("AfterMerge"))

    fun mergeTwoAsyncsAndRunAfterTheyAreAllDoneUsingExplicitJoin() {
        runBlocking {
            val job1 = scope.launch {
                runFirst()
            }
            val job2 = scope.launch {
                runSecond()
            }
            val list = listOf(job1, job2)
            list.joinAll()

            println("running after job1 and job2 are done. Job1 and Job2 are launched in parallel")
        }
    }

    fun mergeTwoAsyncsAndRunAfterTheyAreAllDoneUsingStructuredConcurrency() {
        runBlocking {
            launch(Dispatchers.IO) {
                runFirst()
            }
            launch(Dispatchers.IO) {
                runSecond()
            }
        }

        println("running after job1 and job2 are done. Job1 and Job2 are launched in parallel")
    }

    private suspend fun runFirst() {
        delay(2000)
        println("Runs first")
    }

    private suspend fun runSecond() {
        delay(3000)
        println("Runs second")
    }
}