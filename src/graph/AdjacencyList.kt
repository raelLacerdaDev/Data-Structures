package graph

import java.util.PriorityQueue

class AdjacencyList (private val numOfVertex : Int) {

    init {
        if (numOfVertex < 1) {
            throw IllegalArgumentException("The vertex needs to be at least 1.")
        }
    }

    private val _list = List(numOfVertex) {
        mutableSetOf<Int>()
    }
    val list: List<Set<Int>> get() = _list

    fun addEdge(v1: Int, v2: Int) {

        if( v1 !in 0..list.lastIndex || v2 !in 0..list.lastIndex) {
            throw IllegalArgumentException("Invalid Vertex.")
        }

        if (v1 == v2) {
            throw IllegalArgumentException("cannot add link. ")
        }
        _list[v1].add(v2)
        _list[v2].add(v1)
    }

    fun removeEdge(v1: Int, v2: Int) {
        if( v1 !in 0..list.lastIndex || v2 !in 0..list.lastIndex) {
            throw IllegalArgumentException("Invalid Vertex.")
        }
        _list[v1].remove(v2)
        _list[v2].remove(v1)
    }

    fun degree(vertex: Int): Int {
        if(vertex !in 0..list.lastIndex) {
            throw IllegalArgumentException("Invalid Vertex.")
        }
        return list[vertex].size
    }

    fun printGraph() {
        _list.forEachIndexed { index , edges ->
            println("$index : $edges")
        }
    }

    fun listByDegree() {
        val map = _list.indices.groupBy { _list[it].size }.toSortedMap()
        map.forEach { (key, value) ->
            println("Degree {$key} : $value")
        }
    }

    fun neighbours (vertex: Int) : List<Int> {
        if (vertex !in 0.._list.lastIndex) {
            throw IllegalArgumentException("Invalid Vertex.")
        }
        return _list[vertex].toList()
    }

    fun bfs (
        vertex: Int,
        visitedVertices: BooleanArray = BooleanArray(_list.size) { false },
        processing: (Int) -> Unit,
    ) {
        val neighboursQueue = ArrayDeque<Int>()
        neighboursQueue.add(vertex)
        visitedVertices[vertex] = true
        while(neighboursQueue.isNotEmpty()) {
            val current = neighboursQueue.removeFirst()
            processing(current)

            val neighbours = this.neighbours(current)

            neighbours.forEach { vertex ->
                if (!visitedVertices[vertex]) {
                    neighboursQueue.add(vertex)
                    visitedVertices[vertex] = true
                }
            }
        }
    }

    fun connectedComponents () : Int {
        var count = 0
        val visited = BooleanArray(_list.size) { false }

        _list.forEachIndexed { index, vertex ->
            if(!visited[index]) {
                visited[index] = true
                bfs(index, visited) {}
                count++
            }
        }
        return count
    }

    fun dfs (
        vertex: Int,
        visitedVertices: BooleanArray = BooleanArray(_list.size) { false },
        processing: (Int) -> Unit,
    ) {
        visitedVertices[vertex] = true
        processing(vertex)
        val neighbours = this.neighbours(vertex)
        neighbours.forEach { neighbour ->
            if(!visitedVertices[neighbour]) {
                dfs(neighbour, visitedVertices, processing)
            }
        }
    }


    fun dfsIterative (
        vertex: Int,
        visitedVertices: BooleanArray = BooleanArray(_list.size) { false },
        processing: (Int) -> Unit
    ) {
        val neighboursStack = ArrayDeque<Int>()

        neighboursStack.addLast(vertex)
        visitedVertices[vertex] = true

        while(neighboursStack.isNotEmpty()) {
            val current = neighboursStack.removeLast()
            processing(current)

            val neighbours = this.neighbours(current)

            neighbours.forEach { neighbour ->
                if (!visitedVertices[neighbour]) {
                    neighboursStack.addLast(neighbour)
                    visitedVertices[neighbour] = true
                }
            }
        }
    }

    fun dfsRecursiveWithDiscoveryAndFinishTime (
        vertex: Int,
        visitedVertices: BooleanArray = BooleanArray(_list.size) { false },
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
                dfsRecursiveWithDiscoveryAndFinishTime(neighbour, visitedVertices, time, result, processing)
            }
        }
        time[0] +=  1
        val finishTime = time[0]
        result.add(Triple(vertex,discoveryTime,finishTime))

        return result.toList()
    }

}
