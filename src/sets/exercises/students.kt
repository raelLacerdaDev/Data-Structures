package sets.exercises

fun main() {

    println(
        studentsCount(
            listOf(
                listOf(15,21,80,42),
                listOf(21, 80, 47),
                listOf(12, 21, 47, 35),
            )
        )
    )


}



fun studentsCount(courses: List<List<Int>>): Int {
    val control = mutableSetOf<Int>()
    courses.forEach { students ->
        students.forEach { student ->
            control.add(student)
        }
    }
    return control.size
}