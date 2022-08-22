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
}

class MultipleAsyncLearning {
    fun checkIfAwaitIsRunOneAfterOneAnother() {
        runBlocking {
            val deferred1 = async {
                println("Deferred1 starting")
                delay(5000)
                println("Deferred1 ending")
                return@async "first"
            }
            val deferred2 = async {
                println("Deferred2 starting")
                delay(3000)
                println("Deferred2 ending")
                return@async 1234
            }
            println("Is this piece of code run before or after two async declaration?")
            showResultForDeferredAwait(deferred1.await(), deferred2.await())
            println("Is this piece of code run before or after both deferred are complete?")
        }
    }

    private fun showResultForDeferredAwait(result1: String, result2: Int) {
        println("1: $result1, 2:$result2")
    }
}