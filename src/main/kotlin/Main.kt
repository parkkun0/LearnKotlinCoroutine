fun main() {
    println("program starts")

    println("=======Using Join=======")
    RxMergeEquivalent().mergeTwoAsyncsAndRunAfterTheyAreAllDoneUsingExplicitJoin()
    println("========================")

    println("=======Using Structured Concurrency=======")
    RxMergeEquivalent().mergeTwoAsyncsAndRunAfterTheyAreAllDoneUsingStructuredConcurrency()
    println("==========================================")

    println("""
        Discovered that Join and Structured Concurrency are different in the way they handle after second coroutine launch.
        
        Using join, println after second job was blocked until both jobs are done.
        
        On the other hand, using structured concurrency, println following the second job was immediately run.
        The parent coroutine was just not done because all its children weren't done.
        
        Thus, to use structured concurrency another explicit coroutine launch is necessary to execute what is intended 
        
    """.trimIndent())

    println("program ends")
}