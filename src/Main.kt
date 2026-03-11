import java.math.BigInteger
import kotlin.math.min

fun main() {

}


fun coinChange(coins: IntArray, amount: Int): Int {
    if (amount == 0) return 0

    val memory = IntArray(amount + 1) { amount + 1 }
    memory[0] = 0

    for (i in 1..amount) {
        for (coin in coins) {
            if (i - coin >= 0) {
                memory[i] = min(memory[i], 1 + memory[i - coin])
            }
        }
    }
    return if (memory[amount] > amount) -1 else memory[amount]
}




