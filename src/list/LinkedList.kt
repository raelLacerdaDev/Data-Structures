package list

import Element


class LinkedList<T> : Iterable<Element<T>> {
    private var start: Element<T>? = null
    private var end: Element<T>? = null
    var size: Int = 0
        private set
    val first get() = start?.value
    val last get() = end?.value
    val isEmpty get() = size == 0
    fun addAtStart(element: T) {
        val newElement = Element(value = element)
        if (isEmpty) {
            start = newElement
            end = newElement
            size += 1
        }
        newElement.next = start
        start = newElement
        size += 1
    }
    fun addAtEnd(element: T) {
        val newElement = Element(value = element)
        if (isEmpty) {
            start = newElement
            end = newElement
            size += 1
        }
        end?.next = newElement
        end = newElement
        size += 1
    }
    fun addAtPosition(element: T, index: Int) {
        if (isEmpty || index !in 0 ..< size || index == size - 1) {
            addAtEnd(element)
            return
        }
        if (index == 0) {
            addAtStart(element)
            return
        }
        val newElement = Element(value = element)
        val previous = getElement(index - 1)
        val next = previous.next
        previous.next = newElement
        newElement.next = next
        size += 1
    }
    fun getElement (index: Int) : Element<T> {
        if (isEmpty) throw NoSuchElementException("List is empty!!!")
        if (index !in 0 ..< size) throw IndexOutOfBoundsException("Index: $index isn't valid!!!")
        var currentElement = start
        var currentIndex = 0

        while (currentIndex < index) {
            currentElement = currentElement?.next
            currentIndex += 1
        }
        return currentElement!! // because index is always valid
    }
    override fun iterator(): Iterator<Element<T>> {
        return object : Iterator<Element<T>> {
            private var current = start
            override fun next(): Element<T> {
               if (!hasNext()) throw NoSuchElementException()
               val element = current!!
                current = current?.next
                return element
            }
            override fun hasNext(): Boolean {
                return current != null
            }

        }
    }
}