package maps.exercises


fun main() {

    val list =
        wordCount("O vento sussurra sons entre as árvores, sons que fazem animais correrem. A floresta e a natureza vibram com segredos e sons.")

    list.forEach {
        println(it)
    }
}


data class Rank(val str: String, val count: Int) {
    override fun toString(): String = "$str : $count"
}

fun wordCount(text: String): List<Rank> {

    val textNormalized = normalize(text)

    val parts = textNormalized.split(" ")
        .filter { it.isNotEmpty() }

    // groupingBy agrupa os elementos
    val groups = parts.groupingBy { it }
        .eachCount() // faz a contagem

    val result = groups.map { (key, value) ->
        Rank(key, value)
    }

    return result.sortedWith(
        compareByDescending<Rank> { it.count }
            .thenBy { it.str }
    )
}


fun normalize(text: String): String {
    val nonAlphanumeric = Regex("[^\\p{L}\\p{N}\\s]")
    val extraWhitespace = Regex("\\s+")
    return text
        .replace(nonAlphanumeric, " ")
        .replace(extraWhitespace, " ")
        .trim()
        .lowercase()
}