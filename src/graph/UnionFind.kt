package graph

class UnionFind (val size: Int) {

    private val parent = IntArray(size) { it }
    private val groupSize = IntArray(size) { 1 }

    fun find(elem: Int): Int {
        if (parent[elem] != elem) {
            parent[elem] = find(parent[elem])
        }
        return parent[elem]
    }

    fun union(first: Int,  second: Int) {
        val parentOfFirst = find(first)
        val parentOfSecond = find(second)
        if (parentOfFirst != parentOfSecond) {
            if (groupSize[parentOfFirst] >= groupSize[parentOfSecond]) {
                parent[parentOfSecond] = parentOfFirst
                groupSize[parentOfFirst] += groupSize[parentOfSecond]
            } else {
                parent[parentOfFirst] = parentOfSecond
                groupSize[parentOfSecond] += groupSize[parentOfFirst]
            }
        }
    }
}