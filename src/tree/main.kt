package tree

fun main() {

    val tree = GenericTree<Char>()
    val root = tree.addRoot('M')
    val a = tree.add('A', root)
    val u = tree.add('U', root)
    val c = tree.add('C', root)
    tree.add('H', a)
    tree.add('T', a)
    tree.add('E', u)
    tree.add('J', u)
    val b = tree.add('B', c)
    tree.add('W', c)
    tree.add('K', c)
    tree.add('N', b)
    tree.add('L', b)

  tree.bfs {
     print(it.element)
     print(" ")
  }


}
