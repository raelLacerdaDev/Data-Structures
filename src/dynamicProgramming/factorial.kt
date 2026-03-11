package dynamicProgramming

import java.math.BigInteger

// 5! = 5 * 4 * 3 * 2 * 1



tailrec fun factorial(n: BigInteger, acc: BigInteger = BigInteger.ONE): BigInteger {
    if (n == BigInteger.ZERO) return acc
    if (n == BigInteger.ONE) return acc
    return factorial(n = n - BigInteger.ONE , acc = acc * n)
}


fun main() {
    println(
        factorial(10000.toBigInteger())
    )
}