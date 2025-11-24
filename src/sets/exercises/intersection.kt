package sets.exercises

fun main() {
    val result1 = intersection(
        nums1 = arrayOf(1,2,2,1),
        nums2 = arrayOf(2,2)
    )

    print("{")
    result1.forEach { print("$it ") }
    print("}")
    val result2 = intersection(
        nums1 = arrayOf(4,9,5),
        nums2 = arrayOf(9,4,9,8,4)
    )
    println()
    print("{")
    result2.forEach { print("$it ") }
    print("}")
}



fun intersection (nums1 : Array<Int>, nums2 : Array<Int>) : Array<Int> {
    val set1 = nums1.toSet()
    val set2 = nums2.toSet()
    return set1.intersect(set2).toTypedArray()
}