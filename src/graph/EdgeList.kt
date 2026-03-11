package graph

class EdgeList (private val numVertices: Int) {

    private val _edges = mutableListOf<Triple<Int, Int, Int>>()
    val edges: List<Triple<Int, Int, Int>> get() = _edges

    fun addEdge(v1: Int, v2: Int, weight: Int) {

        if (v1 !in 0 until numVertices || v2 !in 0 until numVertices) {
            throw IllegalArgumentException("Invalid vertex")
        }

        _edges.add(Triple(v1, v2, weight))
    }


    fun bellmanFord(vertex: Int) : IntArray {
        val dist = IntArray(numVertices) { Int.MAX_VALUE }
        dist[vertex] = 0

        for(i in 0 until numVertices - 1 ) {
            _edges.forEach { (u,v,w) ->
               if (dist[u] != Int.MAX_VALUE && dist[u] + w < dist[v]) {
                   dist[v] = dist[u] + w
               }
            }
        }

        _edges.forEach { (u,v,w) ->
            if (dist[u] != Int.MAX_VALUE && dist[u] + w < dist[v]) {
                return intArrayOf(-Int.MAX_VALUE)
            }
        }

        return dist
    }

    fun kruskal() : Pair<Int, List<Triple<Int, Int, Int>>> {
        val unionFind = UnionFind(numVertices)
        val result = mutableListOf<Triple<Int, Int, Int>>()
        var totalWeight = 0

        edges.sortedBy { it.third }.forEach { (v1,v2,w) ->
            if(unionFind.find(v1) != unionFind.find(v2)) {
                unionFind.union(v1, v2)
                result.add(Triple(v1, v2, w))
                totalWeight += w
            }
        }

        return Pair(totalWeight, result)
    }


}