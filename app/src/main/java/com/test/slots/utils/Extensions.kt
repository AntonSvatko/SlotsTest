package com.test.slots.utils

operator fun <T> MutableList<T>.times(increment: Int) {
    val len = this.size
    repeat(increment - 1) {
        this += this.subList(0, len)
    }
}