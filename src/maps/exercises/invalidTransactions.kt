package maps.exercises

import kotlin.math.abs

fun main() {
    println(
        invalidTransactions(
            arrayOf("alice,20,800,mtv","alice,50,100,beijing")
        )
    )
    println(
        invalidTransactions(
            arrayOf("alice,20,800,mtv","alice,50,1200,mtv")
        )
    )
    println(
        invalidTransactions(
            arrayOf("alice,20,800,mtv","bob,50,1200,mtv")
        )
    )
    println(
        invalidTransactions(
            arrayOf("alice,20,1220,mtv","alice,20,1220,mtv")
        )
    )

}


fun invalidTransactions(transactions: Array<String>): List<String> {

    val allTransactions = transactions.map { item ->
        val parts = item.split(",")
        Transaction(
            name = parts[0],
            time = parts[1].toInt(),
            amount = parts[2].toInt(),
            city = parts[3]
        )
    }

    val transactionByName: Map<String, List<Transaction>> = allTransactions.groupBy { it.name }

    val invalidTransactionObjects: List<Transaction> = allTransactions.filter { currentTransaction ->

        val isAmountInvalid = currentTransaction.amount > 1000

        val allTransactionsForThisPerson = transactionByName.getValue(currentTransaction.name)

        val hasConflict = allTransactionsForThisPerson.any { otherTransaction ->
            val isWithin60Minutes = abs(currentTransaction.time - otherTransaction.time) <= 60
            val isDifferentCity = currentTransaction.city != otherTransaction.city
            isWithin60Minutes && isDifferentCity
        }

        isAmountInvalid || hasConflict
    }

    return invalidTransactionObjects.map { it.toString() }
}


data class Transaction(
    val name: String,
    val time: Int,
    val amount: Int,
    val city: String
) {
    override fun toString(): String {
        return "$name,$time,$amount,$city"
    }
}


