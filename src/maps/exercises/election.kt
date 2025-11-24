package maps.exercises

fun main() {
    println(
        electionResult(
            listOf(
                "Alex Blue,15",
                "Maria Green,22",
                "Bob Brown,21",
                "Alex Blue,30",
                "Bob Brown,15",
                "Maria Green,27",
                "Maria Green,22",
                "Bob Brown,25",
                "Alex Blue,31"
            )
        )
    )
    println(
        electionResultIdiomatic(
            listOf(
                "Alex Blue,15",
                "Maria Green,22",
                "Bob Brown,21",
                "Alex Blue,30",
                "Bob Brown,15",
                "Maria Green,27",
                "Maria Green,22",
                "Bob Brown,25",
                "Alex Blue,31"
            )
        )
    )
}

fun electionResult(votes: List<String>) : List<String> {
    val control = mutableMapOf<String,Int>()
    votes.forEach { vote ->
        val split = vote.split(',')
        if (control.containsKey(split[0])) {
            control[split[0]] = control.getValue((split[0])) + (split[1].toIntOrNull() ?: 0)
        } else {
            control[split[0]] = split[1].toIntOrNull() ?: 0
        }
    }
    return control.map { (key, value) -> "$key,$value" }
}
fun electionResultIdiomatic(votes: List<String>): List<String> {
    return votes
        .map { item ->
            val parts = item.split(',')
            val name = parts.getOrNull(0) ?: ""
            val count = parts.getOrNull(1)?.toIntOrNull() ?: 0
            name to count
        }
        .filter { it.first.isNotEmpty()}
        .groupBy { it.first }
        .mapValues { entry ->
            entry.value.sumOf { it.second }
        }
        .map { (name, count) -> "$name,$count" }
}


