fun d06(input: String) {
    var p1marker = 0
    var p2marker = 0
    for (i in 3 until input.length) {
        if (input.subSequence(i-3, i+1).toSet().size == 4) {
            p1marker = i+1
            break
        }
    }
    for (i in 13 until input.length) {
        if (input.subSequence(i-13, i+1).toSet().size == 14) {
            p2marker = i+1
            break
        }
    }

    println("P1: $p1marker")
    println("P2: $p2marker")
}