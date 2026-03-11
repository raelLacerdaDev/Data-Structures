package challenges

import graph.UnionFind

fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
    val disjointSet = UnionFind(n)

    edges.forEach { (v1,v2) ->
        disjointSet.union(v1, v2)
    }

    val rootSource = disjointSet.find(source)
    val rootDestination = disjointSet.find(destination)

    return rootSource == rootDestination
}