fun d05(input: String) {
    var initialState: MutableList<ArrayDeque<Char>>
    val instructions: List<List<Int>>

    input.split("\n\n").let {
        val stackInput = it[0].split("\n")
        val stackCount = stackInput.last().trim().split("\\s+".toRegex()).last().toInt()

        initialState = MutableList<ArrayDeque<Char>>(stackCount) { ArrayDeque<Char>() }

        stackInput.subList(0, stackInput.size - 1).reversed().forEach { layer ->
            layer.chunked(4).forEachIndexed { index, crate ->
                val crateChar = crate.replace("[\\[\\] ]".toRegex(), "")
                if (crateChar != "") {
                    initialState[index].add(crateChar.toCharArray().first())
                }
            }
        }

        instructions = it[1].split("\n").map { instruction ->
            instruction.replace("[a-z]+ ".toRegex(), "").trim().split(" ").map { num ->
                num.toInt()
            }
        }
    }

    fun cloneState(): MutableList<ArrayDeque<Char>> {
        val clone = initialState.map {
            val list = ArrayDeque<Char>()
            list.addAll(it)
            list
        }
        return clone.toMutableList()
    }

    val p1State = cloneState()
    val p2State = cloneState()

    instructions.forEach { instruction ->
        val p2Changes = mutableListOf<Char>()
        repeat(instruction[0]) {
            p1State[instruction[2] - 1].add(p1State[instruction[1] - 1].removeLast())
            p2Changes.add(p2State[instruction[1] - 1].removeLast())
        }
        p2State[instruction[2] - 1].addAll(p2Changes.reversed())
    }

    println("P1: ${p1State.map { it.last() }.joinToString("")}")
    println("P2: ${p2State.map { it.last() }.joinToString("")}")
}