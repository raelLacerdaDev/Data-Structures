package tree

class GenericTree<T> {

    private var _root: Node<T>? = null
    val root: Position<T>? get() = _root

    var size = 0
        private set

    // nested class
    private data class Node<T>(override var element: T) : Position<T> {
        val children = mutableListOf<Node<T>>()
        var parent: Node<T>? = null
    }

    fun addRoot(element: T): Position<T> {
        if (_root != null) {
            throw IllegalStateException("Root already exists.")
        }
        val newNode = Node(element)
        _root = newNode
        size += 1
        return newNode
    }

    fun add(element: T, parent: Position<T>): Position<T> {
        if(_root == null) {
            throw IllegalStateException("You must add root!")
        }

        val newNode = Node(element)
        val parentNode = validatePosition(parent)

        newNode.parent = parentNode
        parentNode.children.add(newNode)
        size += 1

        return newNode
    }

    private fun validatePosition(position: Position<T>): Node<T> {
        val node = position as? Node<T> ?: throw IllegalStateException("Position invalid.")
        return node
    }

    fun children(position: Position<T>): List<Position<T>> {
        val node = validatePosition(position)
        return node.children
    }

    fun elements() : List<T> {
        val list = mutableListOf<T>()
        preOrder {
            list.add(it.element)
        }
        return list.toList()
    }

    fun positions(): List<Position<T>> {
        val list = mutableListOf<Position<T>>()
        preOrder {
            list.add(it)
        }
        return list.toList() // O(n) but safety
    }

    fun find(target: T): Position<T>? {
        return findRecursive(_root ?: return null, target)
    }
    private fun findRecursive(p: Position<T>,target: T): Position<T>? {
        if (p.element == target) {
            return p
        }
        for (child in children(p)) {
            val result = findRecursive(child, target)
            if (result != null) {
                return result
            }
        }
        return null
    }



    fun printRecursive(node: Position<T>, depth: Int = 0) {
        val spaces = "    ".repeat(depth)
        println("$spaces${node.element}")
        val children = children(node)
        for (child in children) {
            printRecursive(child, depth + 1)
        }
    }

    // dfs
    fun preOrder(callAction: (Position<T>) -> Unit) {
        preOrderRecursive(_root ?: throw IllegalArgumentException("Root is null!"), callAction)
    }
    private fun preOrderRecursive(node: Position<T>, callAction: (Position<T>) -> Unit) {
        callAction(node)
        for(child in children(node)) {
            preOrderRecursive(child, callAction)
        }
    }

    fun postOrder(callAction: (Position<T>) -> Unit) {
        postOrderRecursive(_root ?: throw IllegalArgumentException("Root is null!"), callAction)
    }
    private fun postOrderRecursive(node: Position<T>, callAction: (Position<T>) -> Unit) {
        for (child in children(node)) {
            postOrderRecursive(child, callAction)
        }
        callAction(node)
    }


}