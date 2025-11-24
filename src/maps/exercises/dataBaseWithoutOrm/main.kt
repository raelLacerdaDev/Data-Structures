package maps.exercises.dataBaseWithoutOrm

fun main() {
   val sample1 =  convertRecords(
        arrayOf(
            "57,Vendas,8032,Meire Silva,8000.0,57",
            "32,Estoque,4368,Dom Dias,7000.0,32",
            "57,Vendas,3298,Pedro Neto,8500.0,57",
            "57,Vendas,8639,Carol Souza,9000.0,57",
            "18,Marketing,6421,Davi Souto,7500.0,18",
            "32,Estoque,7523,Lara Matos,8000.0,32",
            "18,Marketing,2732,Bob Costa,6500.0,18"
        )
   )
    val sample2 =  convertRecords(
        arrayOf(
            "57,Vendas,8032,Meire Silva,8000.0,57",
            "18,Marketing,6421,Davi Souto,7500.0,18",
            "18,Marketing,2732,Bob Costa,6500.0,18"
        )
    )

    sample1.forEach { department ->
        println("${department.name}: ")
        department.employees.forEach { employee ->
            println("\t$employee")
        }
    }
    println()
    sample2.forEach { department ->
        println("${department.name}: ")
        department.employees.forEach { employee ->
            println("\t$employee")
        }
    }



}

fun convertRecords(records: Array<String>): List<Department> {
    val employeesAndDepartments = records.map { item ->
        val parts = item.split(",")
        Pair(
            Department(
                parts[0].toInt(),
                parts[1]
            ),
            Employee(
                parts[2].toInt(),
                parts[3],
                parts[4].toDouble(),
                parts[5].toInt()
            )
        )
    }
    val departmentWithAllEmployees : Map<Department, List<Employee>> = employeesAndDepartments
        .groupBy{ it.first }
        .mapValues { entry ->
            entry.value.map { 
                it.second
            }
        }
    return departmentWithAllEmployees.map { (department, employees) ->
        employees.forEach {
            department.addEmployee(it)
        }
        department
    }
}