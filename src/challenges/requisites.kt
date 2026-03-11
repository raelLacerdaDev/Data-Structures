package challenges



fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
    val status = IntArray(numCourses) { -1 }

    val adjacencyList = List(numCourses) {
        mutableSetOf<Int>()
    }

    prerequisites.forEach { (a, b) ->
        adjacencyList[b].add(a)
    }

    for (v in 0..<numCourses) {
        if(status[v] == -1) {
            val result = canFinishWay(source = v, status, list = adjacencyList)
            if (!result) {
                return false
            }
        }
    }

    return true
}


fun canFinishWay(source: Int, status: IntArray, list: List<Set<Int>>): Boolean {

    status[source] = 0

    for(neighbour in list[source]) {
        if (status[neighbour] == 1) {
            continue
        }
        if (status[neighbour] == 0) {
            return false
        }
        val result = canFinishWay(neighbour, status, list)
        if (!result) {
            return false
        }
    }

    status[source] = 1
    return true
}


