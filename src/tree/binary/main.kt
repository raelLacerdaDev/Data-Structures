package tree.binary

import jdk.internal.org.jline.keymap.KeyMap.key

fun main() {


    val setA = BinarySearchTreeSet<Int>()
    setA.add(1)
    setA.add(2)
    setA.add(3)
    setA.add(4)
    setA.add(5)
    val setB = BinarySearchTreeSet<Int>()
    setB.add(4)
    setB.add(5)
    setB.add(6)
    setB.add(7)
    setB.add(8)

    val difference = setA.difference(setB)
    difference.printTree()

}