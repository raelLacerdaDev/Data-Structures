package maps.exercises.dataBaseWithoutOrm

data class Department(
    val id: Int,
    val name: String
) {
    val employees: MutableList<Employee> = emptyList<Employee>().toMutableList()

    fun addEmployee(employee: Employee) {
        if (employee.departmentId != id){
            throw IllegalArgumentException("The Department Id of employee does not match the id of the department")
        }
        employees.add(employee)
    }
}

