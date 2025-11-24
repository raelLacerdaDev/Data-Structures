package queue.exercises

import queue.QueueWithDescendingOrder

fun main() {

    val queueWithDescendingOrder = QueueWithDescendingOrder<Char>()

    queueWithDescendingOrder.add('A', 'A'.code)
    queueWithDescendingOrder.add('B', 'B'.code)
    queueWithDescendingOrder.add('C', 'C'.code)
    queueWithDescendingOrder.add('D', 'D'.code)


    queueWithDescendingOrder.forEach { item ->
        print("${item.value} ") // D C B A
    }

    queueWithDescendingOrder.remove()

    println()

    queueWithDescendingOrder.forEach { item ->
        print("${item.value} ") // C B A
    }



}