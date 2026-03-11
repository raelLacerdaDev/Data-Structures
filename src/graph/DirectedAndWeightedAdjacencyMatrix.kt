package graph

class DirectedAndWeightedAdjacencyMatrix(private val numberOfVertices: Int) {

    init {
        if (numberOfVertices < 1) {
            throw IllegalArgumentException("The number of vertex must be at least 1.")
        }
    }

    private val nestedArray = Array(numberOfVertices) {
        Array(numberOfVertices) { Int.MAX_VALUE }
    }

    fun addEdge(v1: Int, v2: Int, weight: Int) {
        if (v1 !in 0..<numberOfVertices || v2 !in 0..<numberOfVertices) {
            throw IllegalArgumentException("Invalid vertex index.")
        }

        if(v1 == v2) {
            throw IllegalArgumentException("cannot add edge link")
        }

        nestedArray[v1][v2] = weight
    }

    fun removeEdge(v1: Int, v2: Int) {
        if (v1 !in 0..<numberOfVertices || v2 !in 0..<numberOfVertices) {
            throw IllegalArgumentException("Invalid vertex index.")
        }
        nestedArray[v1][v2] = Int.MAX_VALUE
    }

    fun printGraph() {
        nestedArray.forEach { edges ->
            println(edges.contentDeepToString())
        }
    }

    fun neighbours(vertex: Int): List<Int> {
        if (vertex !in 0..<numberOfVertices) {
            throw IllegalArgumentException("Invalid vertex index.")
        }
        return nestedArray[vertex].indices.filter {
            nestedArray[vertex][it] != Int.MAX_VALUE
        }
    }

    fun lowestWeight() : Pair<Int, Int> {
        var v = 0
        var neighbour = 0
        var minLatestValue = Int.MAX_VALUE

        nestedArray.forEachIndexed { vertex, edges ->
            val min = edges.filter { it != Int.MAX_VALUE }.minOrNull()
            min?.let {
                if (min < minLatestValue) {
                    minLatestValue = it
                    val column = edges.indexOf(min)
                    v = vertex
                    neighbour = column
                }
            }
        }
        return if (minLatestValue != Int.MAX_VALUE) Pair(v,neighbour) else Pair(-1,-1)
    }
}