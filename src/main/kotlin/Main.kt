fun main() {
    println("program starts")

    println("=======Using Join=======")
    RxMergeEquivalent().mergeTwoAsyncsAndRunAfterTheyAreAllDoneUsingExplicitJoin()
    println("========================")

    println("=======Using Structured Concurrency=======")
    RxMergeEquivalent().mergeTwoAsyncsAndRunAfterTheyAreAllDoneUsingStructuredConcurrency()
    println("==========================================")

    println("program ends")
}