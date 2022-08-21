import kotlinx.coroutines.*

class RxMergeEquivalent {
    val scope = CoroutineScope(Dispatchers.IO + CoroutineName("AfterMerge"))

    fun mergeTwoAsyncsAndRunAfterTheyAreAllDoneUsingExplicitJoin() {
        runBlocking {
            println("startBlocking")
            val job1 = scope.launch {
                delay(2000)
                println("Runs first")
            }
            val job2 = scope.launch {
                delay(3000)
                println("Runs second")
            }
            val list = listOf(job1, job2)
            list.joinAll()

            println("running after job1 and job2 are done. Job1 and Job2 are launched in parallel")
            println("doneBlocking")
        }
    }

    fun mergeTwoAsyncsAndRunAfterTheyAreAllDoneUsingStructuredConcurrency() {
        runBlocking {
            println("startBlocking")
            launch {
                delay(2000)
                println("Runs first")
            }
            launch {
                delay(3000)
                println("Runs Second")
            }
            println("doneBlocking")
        }

        println("running after job1 and job2 are done. Job1 and Job2 are launched in parallel")
    }
}