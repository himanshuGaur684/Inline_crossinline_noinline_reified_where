package com.example.keywordsinkotlin

fun main() {

    message {
        print("this is message")
        return@message
    }

    message { print("this is second message") }

}

inline fun message(a: () -> Unit) {
    a.invoke()
}

fun a(){
    return
}