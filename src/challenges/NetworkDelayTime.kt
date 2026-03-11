package challenges

import java.util.PriorityQueue
import kotlin.math.max


fun networkDelayTime(times: Array<IntArray>, n: Int, k: Int): Int {

    val distance = IntArray(n + 1) { Int.MAX_VALUE }
    val previous = IntArray(n + 1) { -1 }
    val priorityQ = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })

    val adjacencyList = List(n + 1) {
        mutableSetOf<Pair<Int, Int>>()
    }

    times.forEach { (v1, v2, weight) ->
        adjacencyList[v1].add(v2 to weight)
    }

    distance[k] = 0
    previous[k] = k
    priorityQ.add(Pair(k, 0))

    while (priorityQ.isNotEmpty()) {
        val (currentElem, currentWeight) = priorityQ.poll()

        if (currentWeight > distance[currentElem]) {
            continue
        }

        for((neighbour, neighbourWeight) in adjacencyList[currentElem]) {
            val newWeight = currentWeight + neighbourWeight
            if(distance[neighbour] > newWeight) {
                distance[neighbour] = newWeight
                previous[neighbour] = currentElem
                priorityQ.add(neighbour to newWeight)
            }
        }

    }

    var response = 0
    for (i in 1..n) {
        response = max(distance[i], response)
    }

    return if(response == Int.MAX_VALUE) -1 else response
}



