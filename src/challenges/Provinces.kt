package challenges

import java.util.ArrayDeque

fun findCircleNum(isConnected: Array<IntArray>): Int {

    val visited = BooleanArray(isConnected.size) { false }
    val neighbours = ArrayDeque<Int>()

    var count = 0

    isConnected.forEachIndexed { i, _ ->

        if (visited[i]) {
            return@forEachIndexed
        }

        count += 1

        neighbours.addLast(i)
        visited[i] = true

        while (neighbours.isNotEmpty()) {

            val city = neighbours.removeLast()

            for (neighbour in isConnected[city].indices) {
                if (isConnected[city][neighbour] == 1 && !visited[neighbour]) {
                    neighbours.addLast(neighbour)
                    visited[neighbour] = true
                }
            }
        }
    }


    return count
}

