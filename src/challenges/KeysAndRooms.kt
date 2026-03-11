package challenges

import java.util.ArrayDeque

fun canVisitAllRooms(rooms: List<List<Int>>): Boolean {

    val stack = ArrayDeque<Int>()
    val visited = BooleanArray(rooms.size) { false }

    stack.addLast(0)
    visited[0] = true

    while (stack.isNotEmpty()) {
        val current = stack.removeLast()
        rooms[current].forEach { room ->
            if(!visited[room]) {
                stack.addLast(room)
                visited[room] = true
            }
        }
    }

    return visited.all { it }
}