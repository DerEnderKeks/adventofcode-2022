fun d01(input: String) {
    val elves = input.split("\n\n").map { it.split("\n").map(String::toInt).sum() }

    val maxCals = elves.max()
    val top3Cals = elves.sortedDescending().subList(0, 3).sum()

    println("P1: $maxCals")
    println("P2: $top3Cals")
}