package queue



class CircleQueue<T>(capacity: Int) {

    @Suppress("UNCHECKED_CAST")
    private val array: Array<T?> = arrayOfNulls<Any?>(capacity) as Array<T?>

    private var start = 0
    private var end = 0

    var size = 0
        private set

    val isEmpty: Boolean get() = size == 0
    val isFull: Boolean get() = size == array.size

    fun add(value: T) {
        if (isFull) {
            throw IllegalStateException("Stack is full")
        }
        array[end] = value
        end = (end + 1) % array.size
        size += 1
    }

    fun remove(): T {
        if (isEmpty) {
            throw NoSuchElementException("Stack is empty")
        }
        val elementToRemove = array[start]!!
        array[start] = null
        start = (start + 1) % array.size
        size -= 1
        return elementToRemove
    }
}

