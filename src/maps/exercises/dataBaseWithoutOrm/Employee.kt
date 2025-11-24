package maps.exercises.dataBaseWithoutOrm

data class Employee(
    val id: Int,
    val name: String,
    val salary: Double,
    val departmentId: Int
) {
    override fun toString(): String {
        return "$id: $name, $ $salary"
    }
}