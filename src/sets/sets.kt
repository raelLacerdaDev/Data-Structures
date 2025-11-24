package sets

fun main() {

    val set = setOf(1,2,3,4,5)
    val hashSet = hashSetOf(1,2,3,6,7,8)
    val mutableSet = mutableSetOf<Int>()
    mutableSet.add(1)
    mutableSet.add(2)
    mutableSet.add(3)
    mutableSet.add(4)
    mutableSet.add(5)
    val treeSet = sortedSetOf<Int>(1,2,3,4,5) // binary tree

    val union = set.union(hashSet)
    val intersection = set.intersect(treeSet)
    val difference = set.subtract(hashSet)

    println(union)
    println(intersection)
    println(difference)


}




