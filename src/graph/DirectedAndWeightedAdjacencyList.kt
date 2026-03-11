package graph

import java.util.PriorityQueue

class DirectedAndWeightedAdjacencyList (numOfVertex: Int) {

    init {
        if (numOfVertex < 1) {
            throw IllegalArgumentException("The vertex needs to be at least 1.")
        }
    }

    private val _list = List(numOfVertex) {
        mutableSetOf<Pair<Int, Int>>()
    }
    val list: List<Set<Pair<Int, Int>>> get() = _list

    fun addEdge(v1: Int, v2: Int, weight: Int) {

        if (v1 !in list.indices || v2 !in list.indices) throw IllegalArgumentException("Invalid Vertex.")

        if (v1 == v2) throw IllegalArgumentException("Cannot add edge link")

        val contains = _list[v1].find {
            it.first == v2
        }
        if (contains != null) {
            _list[v1].removeIf { it.first == v2 }
        }

        _list[v1].add(v2 to weight)
    }

    fun removeEdge(v1: Int, v2: Int) {
        if (v1 !in list.indices || v2 !in list.indices) {
            throw IllegalArgumentException("Invalid Vertex.")
        }
        _list[v1].removeIf { it.first == v2 }
    }

    fun printGraph() {
        _list.forEachIndexed { index , edges ->
            println("$index : $edges")
        }
    }

    fun lowestWeight(): Triple<Int, Int, Int>? {
        return _list.mapIndexedNotNull { index, edges ->
            val minPair = edges.minByOrNull { it.second }
            minPair?.let {
                Triple(first = index, second = minPair.first, third = minPair.second)
            }
        }.minByOrNull { it.third }
    }

    fun neighbours(vertex: Int) : List<Int> {
        if (vertex !in list.indices ) {
            throw IllegalArgumentException("Invalid Vertex.")
        }
        return _list[vertex].map {
            it.first
        }
    }

    fun dijkstra(startVertex: Int, endVertex: Int): List<Int> {

        val distance = IntArray(_list.size) { Int.MAX_VALUE }
        val previous = IntArray(_list.size) { -1 }
        val priorityQueue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second})
        val path = mutableListOf<Int>()

        distance[startVertex] = 0
        previous[startVertex] = startVertex
        priorityQueue.add(Pair(startVertex, 0))

        while (priorityQueue.isNotEmpty()) {
           val (currentVertex, currentDist) = priorityQueue.poll()

            if (currentDist > distance[currentVertex]) {
                continue
            }

           _list[currentVertex].forEach { neighbour ->
               val newDist = currentDist + neighbour.second
               if (newDist < distance[neighbour.first]) {
                   distance[neighbour.first] = newDist
                   previous[neighbour.first] = currentVertex
                   priorityQueue.add(Pair(neighbour.first, newDist))
               }
           }
        }

        var current = endVertex

        if (distance[current] == Int.MAX_VALUE) {
            return emptyList()
        }
        // create path
        while (current != startVertex) {
            path.add(current)
            current = previous[current]
        }
        path.add(startVertex)

        return path.reversed()
    }

}