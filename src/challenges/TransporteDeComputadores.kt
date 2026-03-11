package challenges

import java.util.PriorityQueue


fun totalDeliveryCost(n : Int, connections: Array<IntArray>, locations: IntArray): Int {

    val distance = IntArray(n) { Integer.MAX_VALUE }
    val previous = IntArray(n) { -1 }
    val queue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })

    val adjList = Array(n) {
        mutableSetOf<Pair<Int, Int>>()
    }

    for((v1,v2,w) in connections) { 
        adjList[v1].add(v2 to w)
        adjList[v2].add(v1 to w)
    }

    distance[0] = 0
    previous[0] = 0
    queue.add(0 to 0)

    while (queue.isNotEmpty()) {
        val (currentVertex, currentWeight) = queue.poll()

        if(currentWeight > distance[currentVertex]) {
            continue
        }

        for ((neighbour , neighbourWeight) in adjList[currentVertex]) {

            val newDistance = neighbourWeight + currentWeight

            if (newDistance < distance[neighbour]) {
                distance[neighbour] = newDistance
                previous[neighbour] = currentVertex
                queue.add(neighbour to newDistance)
            }

        }
    }

    var response = 0
    locations.forEach { location ->
        response += distance[location]
    }

    return response
}