package com.jesinkey.aoc

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PasswordVerificationTest {

    @Test
    fun `test 111111 is ok`() {
        val subject = PasswordVerification()
        val expected = true
        val result = subject.verify("111111")
        assertEquals(expected, result)
    }

    @Test
    fun `test 223450 is not ok`() {
        val subject = PasswordVerification()
        val expected = false
        val result = subject.verify("223450")
        assertEquals(expected, result)
    }

    @Test
    fun `test 123789 is not ok`() {
        val subject = PasswordVerification()
        val expected = false
        val result = subject.verify("123789")
        assertEquals(expected, result)
    }

    @Test
    fun `test 112233 is ok`() {
        val subject = PasswordVerification()
        val expected = true
        val result = subject.verify("112233")
        assertEquals(expected, result)
    }

    @Test
    fun `test 123444 is ok`() {
        val subject = PasswordVerification()
        val expected = false
        val result = subject.verify("123444")
        assertEquals(expected, result)
    }
}