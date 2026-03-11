package challenges

import graph.UnionFind
import kotlin.math.min

fun minimumCost(n: Int,connections: Array<IntArray>): Int {

    if (n == 1) return 0

    val sortedConnections = connections.sortedBy { it[2] }
    val dsu = UnionFind(n)
    var totalWeight = 0
    var ways = 0

    for ((v1,v2,w) in sortedConnections) {
        val rootV1 = dsu.find(v1)
        val rootV2 = dsu.find(v2)
        if (rootV1 != rootV2) {
            dsu.union(v1, v2)
            totalWeight += w
            ways += 1
            if (ways == n - 1) return totalWeight
        }
    }
    return  -1
}