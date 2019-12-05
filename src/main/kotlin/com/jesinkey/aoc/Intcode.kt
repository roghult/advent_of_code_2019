package com.jesinkey.aoc

enum class ParameterMode(val code: Int) {
    POSITION(0),
    IMMEDIATE(1);

    companion object {
        private val values = values();
        fun getByValue(value: Int) = values.first { it.code == value }
    }
}

enum class Opcode(val code: Int) {
    ADD(1),
    MULTIPLY(2),
    INPUT(3),
    OUTPUT(4),
    EXIT(99);

    companion object {
        private val values = values();
        fun getByValue(value: Int) = values.first { it.code == value }
    }
}

class Intcode {

    fun run(sequence: List<Int>): List<Int> {
        val newSequence = sequence.toMutableList()
        var pos = 0
        while (pos != sequence.lastIndex) {
            val instruction = newSequence[pos].toString()
            val opcode = Opcode.getByValue(opcode(instruction))
            if (opcode == Opcode.EXIT) {
                return newSequence
            }

            val parameterMode1 = parameterMode(instruction, 1)
            val parameterMode2 = parameterMode(instruction, 2)
            val outputParameterMode = parameterMode(instruction, 3)

            pos += when (opcode) {
                Opcode.ADD -> {
                    val parameter1Value = value(newSequence, pos + 1, parameterMode1)
                    val parameter2Value = value(newSequence, pos + 2, parameterMode2)
                    val outputPosition = value(newSequence, pos + 3, outputParameterMode)
                    newSequence[outputPosition] = parameter1Value + parameter2Value
                    4
                }
                Opcode.MULTIPLY -> {
                    val parameter1Value = value(newSequence, pos + 1, parameterMode1)
                    val parameter2Value = value(newSequence, pos + 2, parameterMode2)
                    val outputPosition = value(newSequence, pos + 3, outputParameterMode)
                    newSequence[outputPosition] = parameter1Value * parameter2Value
                    4
                }
                Opcode.INPUT -> {
                    val input = readLine()
                    val outputPosition = value(newSequence, pos + 1, outputParameterMode)
                    newSequence[outputPosition] = input!!.toInt()
                    2
                }
                Opcode.OUTPUT -> {
                    val output = value(newSequence, pos + 1, parameterMode1)
                    println(output)
                    2
                }
                Opcode.EXIT -> return newSequence
            }
        }
        return newSequence
    }

    private fun opcode(instruction: String): Int {
        return instruction.takeLast(2).toInt()
    }

    private fun parameterMode(instruction: String, number: Int): ParameterMode {
        val instructionPosition = number + 2
        val parameterValue = when {
            number == 3 -> {
                1
            }
            instruction.length >= instructionPosition -> {
                instruction[instruction.length - instructionPosition].toString().toInt()
            }
            else -> {
                0
            }
        }
        return ParameterMode.getByValue(parameterValue)
    }

    private fun value(sequence: List<Int>, position: Int, parameterMode: ParameterMode): Int {
        return when (parameterMode) {
            ParameterMode.POSITION -> sequence[sequence[position]]
            ParameterMode.IMMEDIATE -> sequence[position]
        }
    }
}

data class NounAndVerb(
    val noun: Int,
    val verb: Int
)

fun nounAndVerbFinder(input: List<Int>, desiredOutput: Int, nouns: List<Int>, verbs: List<Int>): NounAndVerb? {
    val intcode = Intcode()
    for (noun in nouns) {
        for (verb in verbs) {
            val input = INTCODE_INPUT.toMutableList()
            input[1] = noun
            input[2] = verb
            val result = intcode.run(input)
            if (result[0] == desiredOutput) {
                return NounAndVerb(noun, verb)
            }
        }
    }
    return null
}
