package challenges

fun main() {
    println(
        totalDeliveryCost( // 18
            n = 6,
            connections = arrayOf(
                intArrayOf(0, 1, 1),
                intArrayOf(0, 2, 2),
                intArrayOf(0, 3,3),
                intArrayOf(1, 4,3),
                intArrayOf(2, 4,2),
                intArrayOf(3, 4,3),
                intArrayOf(4, 5,4),
            ),
            locations = intArrayOf(1, 2, 3, 4, 5)
        )
    )

    println(
        totalDeliveryCost( // 22
            n = 6,
            connections = arrayOf(
                intArrayOf(0, 1, 1),
                intArrayOf(0, 2, 2),
                intArrayOf(0, 3,3),
                intArrayOf(1, 4,3),
                intArrayOf(2, 4,2),
                intArrayOf(3, 4,3),
                intArrayOf(4, 5,4),
            ),
            locations = intArrayOf(2,4,5,5)
        )
    )

}