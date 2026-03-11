package graph

import kotlin.collections.MutableList

class MyGraph (numVertex: Int) {

    init {
        if (numVertex < 0) throw IllegalArgumentException("The vertex needs to be at least 1.")
    }

    private val _graph = List(numVertex) {
        mutableSetOf<Int>()
    }
    val graph : List<Set<Int>> get() = _graph


    private fun hasCycle(
        vertex: Int,
        target: Int,
        visited: BooleanArray = BooleanArray(_graph.size) { false }
    ) : Boolean {

        if (vertex == target) return true

        visited[vertex] = true

        this.neighbours(vertex).forEach { neighbour ->
            if (!visited[neighbour]) {

               val found = hasCycle(neighbour, target, visited)

                if (found) {
                    return true
                }
            }
        }

        return false
    }

    fun addEdge(start: Int, end: Int) {
        if (start == end) throw IllegalArgumentException("Cannot have edge link.")
        _graph[start].add(end)
    }

    fun neighbours(vertex: Int): List<Int> {
        if (vertex !in 0.._graph.lastIndex) {
            throw IllegalArgumentException("Invalid Vertex: $vertex")
        }
        return _graph[vertex].toList()
    }


    fun dfs(
        vertex: Int,
        visited: BooleanArray = BooleanArray(_graph.size) { false },
        processing: (Int) -> Unit = {}
    ){
        visited[vertex] = true
        processing(vertex)
        this.neighbours(vertex).forEach { neighbour ->
            if(!visited[neighbour]) {
                dfs(neighbour, visited, processing)
            }
        }
    }

    fun dfs (
        vertex: Int,
        visitedVertices: BooleanArray = BooleanArray(_graph.size) { false },
        time: IntArray = IntArray(1) {0},
        result: MutableList<Triple<Int,Int, Int>> = mutableListOf(),
        processing: (Int) -> Unit,
    ) : List<Triple<Int,Int, Int>> {

        visitedVertices[vertex] = true
        time[0] += 1
        val discoveryTime = time[0]
        processing(vertex)

        this.neighbours(vertex).forEach { neighbour ->
            if (!visitedVertices[neighbour]) {
                dfs(neighbour, visitedVertices, time, result, processing)
            }
        }
        time[0] +=  1
        val finishTime = time[0]
        result.add(Triple(vertex,discoveryTime,finishTime))

        return result.toList()
    }

    fun topologicalSort() : List<Triple<Int,Int, Int>> {
        val result : MutableList<Triple<Int,Int, Int>> = mutableListOf()
        val visited = BooleanArray(_graph.size) { false }
        val time = IntArray(1) { 0 }

        _graph.forEachIndexed { vertex, neighbours ->
            if (!visited[vertex]) {
                dfs(vertex = vertex, visitedVertices = visited, time = time, result = result) {}
            }
        }

        return result.reversed().toList()
    }


}