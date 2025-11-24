package queue

import Element



class Queue<T> : Iterable<Element<T>> {
    var size = 0
        private set
    private var start : Element<T>? = null
    private var end : Element<T>? = null
    val first get() = start?.value
    val last get() = end?.value

    override fun iterator(): Iterator<Element<T>> {
        return object : Iterator<Element<T>> {
            var current = start
            override fun next(): Element<T> {
                if (!hasNext()) throw NoSuchElementException()
                val elementToReturn = current
                current = current?.next
                return elementToReturn!!
            }
            override fun hasNext(): Boolean {
                return current != null
            }

        }
    }

    fun add(item: T) {
        val newItem = Element(value = item)
        if (size == 0) {
            start = newItem
            end = newItem
            size += 1
            return
        }
        end?.next = newItem
        end = newItem
        size += 1
    }

    fun remove() : Element<T>? {
        if (size == 0) return null
        val elementToReturn = start
        start = start?.next
        elementToReturn?.next = null
        size -= 1
        return elementToReturn
    }
}