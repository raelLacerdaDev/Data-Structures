package stack

import Element


class Stack<T> : Iterable<Element<T>> {
    var size = 0
        private set
    private var head : Element<T>? = null

    val top get() = head?.value

    override fun iterator(): Iterator<Element<T>> {
        return object : Iterator<Element<T>> {
            private var current = head
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

    fun push(item: T) {
        val newItem = Element(value = item)
        newItem.next = head
        head = newItem
        size += 1
    }
    fun pop() : Element<T>? {
        if (size == 0) return null
        val itemToReturn = head
        head = head?.next
        itemToReturn?.next = null
        size -= 1
        return itemToReturn
    }




}