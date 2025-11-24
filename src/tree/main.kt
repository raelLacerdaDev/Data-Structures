package tree

fun main() {

    val tree = GenericTree<String>()

    val pRoot = tree.addRoot("bem vindo!")
    tree.add("Quem sou eu?", pRoot)
    tree.add("para quem e este curso?", pRoot)
    tree.add("O que esperar?", pRoot)

    tree.postOrder {
        println(it.element)
    }

    val elements = tree.elements()
    println(elements)

    val target = tree.find("O que esperar?")
    println(target?.element)
    println(target is Position<*>)


    val target2 = tree.find("alalalall blblab,slblkbkflb")
    println(target2?.element)
    println(target2 is Position<*>)

}
