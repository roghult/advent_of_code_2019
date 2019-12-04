package com.jesinkey.aoc

class PasswordVerification {
    fun verify(password: String): Boolean {
        return foo(password) && digitsOnlyIncrease(password)
    }

    private fun digitsOnlyIncrease(password: String): Boolean {
        for (index in 0 until password.length - 1) {
            if (password[index] > password[index + 1]) {
                return false
            }
        }
        return true
    }

    private fun hasDoubleDigit(password: String): Boolean {
        for (index in 0 until password.length - 1) {
            if (password[index] == password[index + 1]) {
                return true
            }
        }
        return false
    }

    private fun foo(password: String): Boolean {
        val asd = password.groupBy { it }.map { (a, b) ->
            a to b.size
        }.toMap()
        return asd.values.any { it == 2 }
    }
}