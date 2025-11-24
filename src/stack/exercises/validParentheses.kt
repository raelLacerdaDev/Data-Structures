package stack.exercises

import stack.Stack

fun main() {
    println(
        validParentheses("()([]{})")
    )
    println(
        validParentheses("(){]{}")
    )
    println(
        validParentheses("()(){}[][][][]")
    )
    println(
        validParentheses("[][][]{}{}{}")
    )
}

fun validParentheses(s: String): Boolean {
    val st = Stack<Char>()
    s.forEach { char ->
        if (char == '(' || char == '[' || char == '{') {
            st.push(char)
        } else {
            if(st.size == 0) {
                return false
            }
            when {
                char == ')' && st.top == '(' -> st.pop()
                char == ']' && st.top== '[' -> st.pop()
                char == '}' && st.top== '{' -> st.pop()
                else -> return false
            }
        }
    }
    return st.size == 0
}

