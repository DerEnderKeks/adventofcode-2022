fun d07(input: String) {
    class Node(
        val name: String,
        val isFile: Boolean,
        var size: Int,
        var children: MutableList<Node>?,
        var parent: Node?
    )

    val tree = Node("/", false, 0, mutableListOf(), null)

    var currentNode = tree

    fun increaseSize(node: Node, size: Int) {
        node.size += size
        if (node.parent != null) {
            increaseSize(node.parent!!, size)
        }
    }

    input.split("\n").forEach { line ->
        if (line.startsWith("$ ")) {
            val (cmd, param) = line.replace("$ ", "").split(" ").toMutableList().apply {
                if (size < 2) add("")
            }

            if (cmd != "cd" || param == "/") {
                return@forEach
            }

            if (param == "..") {
                currentNode = currentNode.parent!!
                return@forEach
            }

            val subDir = Node(param, false, 0, mutableListOf(), currentNode)
            currentNode.children?.add(subDir)
            currentNode = subDir
        } else {
            val (size, name) = line.split(" ")
            if (size == "dir") {
                return@forEach
            }
            currentNode.children?.add(Node(name, true, size.toInt(), null, currentNode))
            increaseSize(currentNode, size.toInt())
        }
    }

    fun printTree(level: Int, node: Node) {
        var padding = ""
        repeat(level * 2) {
            padding += " "
        }
        println("$padding- ${node.name} (${if (node.isFile) "file" else "dir"}, ${node.size})")
        node.children?.sortedWith(compareBy({ it.isFile }, { it.name }))?.forEach {
            printTree(level + 1, it)
        }
    }
    println("Directory tree:")
    printTree(0, tree)

    fun findP1dirs(node: Node): List<Node> {
        val list = mutableListOf<Node>()
        if (!node.isFile && node.size <= 100000) {
            list.add(node)
        }
        node.children?.forEach {
            list.addAll(findP1dirs(it))
        }
        return list
    }

    fun findP2dirs(node: Node, minSize: Int): List<Node> {
        val list = mutableListOf<Node>()
        if (!node.isFile && node.size >= minSize) {
            list.add(node)
        }
        node.children?.forEach {
            list.addAll(findP2dirs(it, minSize))
        }
        return list
    }

    val p2RequiredSpace = 30000000 - (70000000 - tree.size) // update size - (fs size - used space)

    println()
    println("P1: ${findP1dirs(tree).sumOf { it.size }}")
    println("P2: ${findP2dirs(tree, p2RequiredSpace).minBy { it.size }.size}")
}