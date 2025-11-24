package sets.exercises

import kotlin.system.measureTimeMillis

fun main() {
    val time = measureTimeMillis {
        total(
            arrayOf(
                "ana,2024-07-04T21:42:40.353283800Z,https://blog.com/login",
                "bob,2024-07-04T21:42:44.571283800Z,https://blog.com/news",
                "maria,2024-07-04T21:42:46.394283800Z,https://blog.com/shop",
                "ana,2024-07-04T21:42:50.026283800Z,https://blog.com/news"

            )
        )
    }
    println(time)

}


fun total(visitors: Array<String>): Int {
    val control = mutableSetOf<String>()
    visitors.forEach { element ->
        val newArray = element.split(",").toTypedArray()
        control.add(newArray.first())
    }
    return control.size
}
