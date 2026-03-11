package tree.avl

import kotlin.math.max


class AvlTree <T: Comparable<T>> { // in progress

    private var _root: Node<T>? = null
    val root : T? get() = _root?.value

    var size = 0
        private set

    private data class Node<T>(
        val value: T,
        var left: Node<T>? = null,
        var right: Node<T>? = null,
        var parent: Node<T>? = null,
        var height: Int = 0
    )

    // in order way
    fun inOrder(callAction: (T) -> Unit) {
        inOrderRecursive(_root ?: return, callAction)
    }
    private fun inOrderRecursive(node: Node<T>,callAction: (T) -> Unit) {
        if (node.left != null) {
            inOrderRecursive(node.left ?: return, callAction)
        }
        callAction(node.value)
        if (node.right != null) {
            inOrderRecursive(node.right ?: return, callAction)
        }
    }

    private fun find(value: T): Node<T>? {
        var current = _root
        while (current != null) {
            if (current.value == value) {
                return current
            }
            current = if(value < current.value) {
                current.left
            } else {
                current.right
            }
        }
        return null
    }


}