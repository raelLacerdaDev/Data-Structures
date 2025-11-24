package queue


data class ItemWithPriority<T> (
    val value: T,
    val priority: Int,
    var next: ItemWithPriority<T>? = null
)


class QueueWithDescendingOrder<T> : Iterable<ItemWithPriority<T>> {
    var size = 0
        private set
    private var start : ItemWithPriority<T>? = null
    private var end : ItemWithPriority<T>? = null

    val first get() = start?.value
    val last get() = end?.value

    override fun iterator(): Iterator<ItemWithPriority<T>> {
        return object : Iterator<ItemWithPriority<T>> {
            var current = start
            override fun next(): ItemWithPriority<T> {
                if(!hasNext()) throw NoSuchElementException()
                val elementToReturn = current
                current = current?.next
                return elementToReturn!!
            }
            override fun hasNext(): Boolean {
                return current != null
            }

        }
    }

    fun add(value: T, priority: Int) {
        val newItem = ItemWithPriority(value, priority)

        // empty queue
        if (size == 0) {
            start = newItem
            end = newItem
            size += 1
            return
        }
        // item with priority bigger than start
        if (newItem.priority > start!!.priority) {
            newItem.next = start
            start = newItem
            size += 1
            return
        }
        // item with priority smaller than end
        if (newItem.priority <= end!!.priority) {
            end?.next = newItem
            end = newItem
            size += 1
            return
        }

        var current: ItemWithPriority<T>? = start
        var previous: ItemWithPriority<T>? = null

        while (current != null) {
            if (newItem.priority > current.priority) {
                newItem.next = current
                previous?.next = newItem
                size += 1
                return
            }
            previous = current
            current = current.next
        }

    }

    fun remove() : ItemWithPriority<T>? {
        if (size == 0) return null
        val itemToReturn = start
        start = start?.next
        itemToReturn?.next = null
        size -= 1

        if (size == 0) {
            end = null
        }

        return itemToReturn
    }
}