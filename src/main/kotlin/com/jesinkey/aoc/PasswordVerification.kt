package com.jesinkey.aoc

class PasswordVerification {
    fun verify(password: String): Boolean {
        return digitsOnlyIncrease(password) && hasDoubleDigit(password)
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

}
