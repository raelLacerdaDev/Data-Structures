package graph


class  AdjacencyMatrix (private val numberOfVertex: Int) {

    init {
        if (numberOfVertex < 1) {
            throw IllegalArgumentException("The number of vertex must be at least 1.")
        }
    }
    private val nestedArray = Array(numberOfVertex) {
        Array(numberOfVertex) { 0 }
    }

    fun addEdge(firstVertex: Int, secondVertex: Int) {

        if (firstVertex !in 0..<numberOfVertex  || secondVertex !in 0..<numberOfVertex) {
            throw IllegalArgumentException("Invalid Vertex.")
        }

        if(firstVertex == secondVertex) {
            throw IllegalArgumentException("cannot add edge link")
        }

        nestedArray[firstVertex][secondVertex] = 1
        nestedArray[secondVertex][firstVertex] = 1

    }

    fun removeEdge(firstVertex: Int, secondVertex: Int) {

        if (firstVertex !in 0..<numberOfVertex  || secondVertex !in 0..<numberOfVertex) {
            throw IllegalArgumentException("Invalid Vertex.")
        }

        nestedArray[firstVertex][secondVertex] = 0
        nestedArray[secondVertex][firstVertex] = 0

    }

    fun degree(vertex: Int) : Int {
        var degree = 0

        if (vertex !in 0..<numberOfVertex) {
            throw IllegalArgumentException("Invalid Vertex.")
        }

        val edges = nestedArray[vertex]

        edges.forEach { edge ->
            if (edge == 1) {
                degree += 1
            } // == degree += edge (because edge always is 0 or 1)

        }

        return degree
    }

    fun printGraph() {
        nestedArray.forEach { edges ->
            println(edges.contentDeepToString())
        }
    }

}