fun d04(input: String) {
    val pairs = input.split("\n").map { pair ->
        pair.split(",").map { range ->
            range.split("-").map { it.toInt() }
        }
    }

    var p1sum = 0
    var p2sum = 0

    fun rangeContained(r1: List<Int>, r2: List<Int>): Boolean {
        return r1[0] <= r2[0] && r1[1] >= r2[1]
    }

    fun rangeOverlaps(r1: List<Int>, r2: List<Int>): Boolean {
        return (r1[0] >= r2[0] && r1[0] <= r2[1]) || (r1[1] >= r2[0] && r1[1] <= r2[1])
    }

    pairs.forEach {
        if (rangeContained(it[0], it[1]) || rangeContained(it[1], it[0])) {
            p1sum++
        }

        if (rangeOverlaps(it[0], it[1]) || rangeOverlaps(it[1], it[0])) {
            p2sum++
        }
    }

    println("P1: $p1sum")
    println("P2: $p2sum")
}