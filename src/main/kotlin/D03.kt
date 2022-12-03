fun d03(input: String) {
    val rucksacks = input.split("\n").map(String::toCharArray)

    fun itemPriority(item: Char): Int {
        return when (item > 'Z') {
            true -> item - 'a'
            false -> item - 'A' + 26
        } + 1
    }

    var p1Sum = 0
    rucksacks.forEach { rucksack ->
        val compartments = rucksack.toList().chunked(rucksack.size / 2)
        val p1Intersection = compartments[0].toSet().intersect(compartments[1].toSet())
        p1Intersection.forEach {
            p1Sum += itemPriority(it)
        }
    }

    var p2Sum = 0
    rucksacks.chunked(3).forEach { group ->
        group[0].toSet().intersect(group[1].toSet()).intersect(group[2].toSet()).forEach {
            p2Sum += itemPriority(it)
        }
    }

    println("P1: $p1Sum")
    println("P2: $p2Sum")
}