fun d02(input: String) {
    val rounds = input.split("\n").map { it.split(" ").map { it1 -> it1.first().code } }

    var p1Points = 0
    var p2Points = 0

    rounds.forEach {
        val shapeA = it[0] - 64
        val shapeB = it[1] - 87

        p1Points += shapeB // P1 shape choice

        if ((shapeA < shapeB && !(shapeA == 1 && shapeB == 3)) || (shapeA == 3 && shapeB == 1)) { // P1 win
            p1Points += 6
        } else if (shapeA == shapeB) { // P1 draw
            p1Points += 3
        }

        p2Points += (shapeB - 1) * 3 // P2 round result

        p2Points += when (shapeB) {
            3 -> { // P2 win
                when (shapeA == 3) {
                    true -> 1
                    false -> shapeA + 1
                }
            }
            2 -> { // P2 draw
                shapeA
            }
            else -> { // P2 loss
                when (shapeA == 1) {
                    true -> 3
                    false -> shapeA - 1
                }
            }
        }
    }

    println("P1: $p1Points")
    println("P1: $p2Points")
}