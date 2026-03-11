package challenges

fun main() {

}


fun findJudge(n: Int, trust: Array<IntArray>): Int {

    val trustworthy = IntArray(n + 1) { 0 }

    trust.forEach { (a, b) ->
        trustworthy[a] -= 1
        trustworthy[b] += 1
    }

    for (person in 1..n) {
        if (trustworthy[person] == n - 1) {
            return person
        }
    }

    return -1
}
