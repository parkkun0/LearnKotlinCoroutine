import kotlinx.coroutines.*

fun main() {
    MultipleAsyncLearning().checkIfAwaitIsRunOneAfterOneAnother()
    println("""
        ========Result============
        I expected deferred2.await() would start after deferred1.await() is completed,
        since that is how code executes in sequential programming handles calculating two parameters
        
        But it turns out that the code ran deferred 1 and two in parallel and waited for both to complete.
        ==========================
    """.trimIndent())


    MultipleAsyncLearning().checkIfDeferred1SecondAwaitCallsAsyncAgain()
    println("""
        ========Result============
        Observed that any awaits following after first completion does not trigger async again.
        Seems like it is using cached(completed) value
        ==========================
    """.trimIndent())
}

class MultipleAsyncLearning {
    fun checkIfAwaitIsRunOneAfterOneAnother() {
        runBlocking {
            val deferred1 = getAsync1()
            val deferred2 = getAsync2()
            println("Is this piece of code run before or after two async declaration?")
            showResultForDeferredAwait(deferred1.await(), deferred2.await())
            println("Is this piece of code run before or after both deferred are complete?")
        }
    }

    private fun CoroutineScope.getAsync2() = async {
        println("Deferred2 starting")
        delay(3000)
        println("Deferred2 ending")
        return@async 1234
    }

    private fun CoroutineScope.getAsync1() = async {
        println("Deferred1 starting")
        delay(5000)
        println("Deferred1 ending")
        return@async "first"
    }

    private fun showResultForDeferredAwait(result1: String, result2: Int) {
        println("1: $result1, 2:$result2")
    }

    fun checkIfDeferred1SecondAwaitCallsAsyncAgain() {
        runBlocking {
            val deferred1 = getAsync1()
            val deferred2 = getAsync2()
            println("Is this piece of code run before or after two async declaration?")
            showResultForDeferredAwait(deferred1.await(), deferred2.await())
            println("Does await on already completed async trigger async again?")
            println("Deferred1: ${deferred1.await()}")
            println("Is this piece of code run before or after both deferred are complete?")
        }
    }
}