package maps.exercises

fun main() {

}


fun twoSum(nums: IntArray, target: Int) : IntArray {
    val map  = HashMap<Int, Int>()
    nums.forEachIndexed { index, value ->
        val need = target - value
        if (map.containsKey(need)) {
            return intArrayOf(
                map.getValue(need),
                index
            )
        } else {
            map[value] = index
        }
    }
    return intArrayOf()
}

