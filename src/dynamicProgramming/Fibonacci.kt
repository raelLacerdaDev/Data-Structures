package dynamicProgramming

import java.math.BigInteger

//bottom-up
tailrec fun fibonacci(
    n: Int,
    left: BigInteger = BigInteger.ZERO,
    right: BigInteger = BigInteger.ONE
): BigInteger {
    if (n == 0) return left
    if (n == 1) return right
    return fibonacci(n - 1, left = right, right = left + right)
}

