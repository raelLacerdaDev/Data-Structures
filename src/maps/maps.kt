package maps

fun main() {

    val map = mapOf(
        1 to "One",
        2 to "Two",
        3 to "Three",
        4 to "Four",
        5 to "Five"
    )

    val mutableMap = mutableMapOf(
        'a'.code to 'a',
        'b'.code to 'b',
        'c'.code to 'c',
    )
    // mapName.put(key,value) == mapName[key] = value
    mutableMap['d'.code] = 'd'
    mutableMap['e'.code] = 'e'


    // mapName.replace(key,value) == mapName[key] = value
    mutableMap['a'.code] = 'x'

    mutableMap.forEach { (key, value) -> println("$key -> $value") }


}