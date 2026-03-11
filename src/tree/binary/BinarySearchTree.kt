package tree.binary

import java.lang.IllegalStateException


class BinarySearchTreeSet<T : Comparable<T>> {
    private var _root: Node<T>? = null
    val root: T? get() = _root?.key
    var size = 0
        private set

    private data class Node<T>(
        var key: T,
        var left: Node<T>? = null,
        var right: Node<T>? = null,
        var parent: Node<T>? = null
    )

    private fun find(node: Node<T>, key: T): Node<T>? {
        var current : Node<T>? = node
        while(current != null) {
            if (key == current.key) {
                return current
            }
            current = if(key < current.key) { // essa comparação só é possivel por causa do Comparable
                current.left
            } else {
                current.right
            }
        }
        return null
    }

    fun add(key: T) {
        if(size == 0) {
            val newNode = Node(key, null, null, null)
            _root = newNode
            size += 1
            return
        }
        val node = find(_root ?: return, key) // O(log N)
        if(node != null) { // element already exists
            return
        }
        var current = _root
        while(current != null) {
            when {
                key < current.key -> {
                    if(current.left != null) {
                        current = current.left
                        continue
                    }
                    val newLeft = Node(key,null,null,current)
                    current.left = newLeft
                    size += 1
                    return
                }
                key > current.key -> {
                    if(current.right != null) {
                        current = current.right
                        continue
                    }
                    val newRight = Node(key,null,null,current)
                    current.right = newRight
                    size += 1
                    return
                }
            }
        }
    }

    fun inOrder (action: (T) -> Unit) {
        inOrderRecursive(_root ?: return, action)
    }

    private fun inOrderRecursive(node: Node<T>, action: (T) -> Unit) {
        if(node.left != null) {
            inOrderRecursive(node.left ?: return, action)
        }
        action(node.key)
        if(node.right != null) {
            inOrderRecursive(node.right ?: return, action)
        }
    }

    fun keys () : List<T> {
        val list = mutableListOf<T>()
        inOrder {
            list.add(it)
        }
        return list.toList()
    }

    fun contains(key: T): Boolean {
        return find(_root ?: return false, key)?.key == key
    }

    fun printTree() {
        if (_root == null) {
            println("Empty tree")
        } else {
            printNode(_root, "", true, "root")
        }
    }
    private fun printNode(node: Node<T>?, prefix: String, isTail: Boolean, side: String) {
        if (node == null) return
        print(prefix)
        print(if (isTail) "└── " else "├── ")
        println("$side: ${node.key}")
        val childPrefix = prefix + if (isTail) "    " else "│   "
        val hasLeft = node.left != null
        val hasRight = node.right != null
        if (hasLeft) {
            printNode(node.left, childPrefix, !hasRight, "L")
        }
        if (hasRight) {
            printNode(node.right, childPrefix, true, "R")
        }
    }

    fun remove(key: T) : Boolean {
        var nodeToRemove = find(_root ?: return false, key)
        if(nodeToRemove == null) {
            return false
        }
        if (nodeToRemove.left != null && nodeToRemove.right != null) {
            val successor = findMinSuccessor(nodeToRemove.right ?: throw NoSuchElementException())
            nodeToRemove.key = successor.key
            nodeToRemove = successor
        }
        val child = if(nodeToRemove.left != null) nodeToRemove.left else nodeToRemove.right
        val parent = nodeToRemove.parent
        when {
            parent == null -> {
                _root = child
            }
            else -> {
                if(parent.left == nodeToRemove) {
                    parent.left = child
                } else {
                    parent.right = child
                }
            }
        }
        child?.parent = parent
        size -= 1
        return true
    }

    private fun findMinSuccessor(node: Node<T>): Node<T> {
        var current = node
        while(current.left != null) {
            current = current.left ?: throw IllegalStateException()
        }
        return current
    }

    fun union (other: BinarySearchTreeSet<T>) : BinarySearchTreeSet<T> {
        val result = BinarySearchTreeSet<T>()
        this.keys().forEach { key ->
            result.add(key)
        }
        other.keys().forEach { key ->
            result.add(key)
        }
        return result
    }


    fun intersection (other: BinarySearchTreeSet<T>) : BinarySearchTreeSet<T> {
        val result = BinarySearchTreeSet<T>()
        this.keys().forEach { key ->
            if (other.contains(key)) {
                result.add(key)
            }
        }
        return result
    }

    fun difference (other: BinarySearchTreeSet<T>) : BinarySearchTreeSet<T> {
        val result = BinarySearchTreeSet<T>()
        this.keys().forEach { key ->
            if (!other.contains(key)) {
                result.add(key)
            }
        }
        return result
    }



}



