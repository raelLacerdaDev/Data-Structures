package stack


fun main() {
    val bookStack = BookStack()
    bookStack.push("Estruturas de Dados")
    bookStack.push("Harry potter e a pedra filosofal")
    bookStack.forEach {
        println(it.title)
    }
    println()
    print("pop: ${bookStack.pop()}\n")

    println()
    bookStack.forEach {
        println(it.title)
    }
}
// node
data class Book (
    val title: String,
    var next : Book? = null
)
// stack
class BookStack : Iterable<Book> {
    private var head: Book? = null
    var size = 0
        private set
    val first get() = head?.title ?: "Unknown"

    override fun iterator(): Iterator<Book> {
        return object : Iterator<Book> {

            private var current = head

            override fun next(): Book {
                if(!hasNext()) throw NoSuchElementException()
                val elementToReturn = current!!
                current = current?.next
                return elementToReturn
            }

            override fun hasNext(): Boolean {
                return current != null
            }

        }
    }

    fun push(title: String) {
        val newBook = Book(title)
        newBook.next = head
        head = newBook
        size += 1
    }

    fun pop() : String {
        if (size == 0) return "Size is empty"

        val elementToReturn = head
        head = head?.next
        elementToReturn?.next = null
        size -= 1
        return elementToReturn?.title ?: ""
    }


}

