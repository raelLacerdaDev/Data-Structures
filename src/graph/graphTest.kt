package graph



fun main() {
    val graph = MyGraph(10)
    graph.addEdge(0, 1)
    graph.addEdge(0, 2)
    graph.addEdge(0, 3)
    graph.addEdge(0, 5)
    graph.addEdge(1, 2)
    graph.addEdge(2, 3)
    graph.addEdge(2, 4)
    graph.addEdge(4, 6)
    graph.addEdge(5, 4)
    graph.addEdge(5, 6)
    graph.addEdge(6, 7)
    graph.addEdge(6, 8)
    graph.addEdge(9, 6)

    val result = graph.topologicalSort()
    println(result)

}