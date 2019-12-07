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
    JUMP_IF_TRUE(5),
    JUMP_IF_FALSE(6),
    LESS_THAN(7),
    EQUALS(8),
    EXIT(99);

    companion object {
        private val values = values();
        fun getByValue(value: Int) = values.first { it.code == value }
    }
}

class Intcode(private val sequence: MutableList<Int>) {

    private var latestOutput = 0
    private var instructionPointer = 0

    fun run(inputs: MutableList<Int> = mutableListOf()): Int? {
        while (instructionPointer != sequence.lastIndex) {
            val instruction = sequence[instructionPointer].toString()
            val opcode = Opcode.getByValue(opcode(instruction))
            if (opcode == Opcode.EXIT) {
                return null // ?
            }

            val parameterMode1 = parameterMode(instruction, 1)
            val parameterMode2 = parameterMode(instruction, 2)
            val outputParameterMode = parameterMode(instruction, 3)

            when (opcode) {
                Opcode.ADD -> {
                    val parameter1Value = value(sequence, instructionPointer + 1, parameterMode1)
                    val parameter2Value = value(sequence, instructionPointer + 2, parameterMode2)
                    val outputPosition = value(sequence, instructionPointer + 3, outputParameterMode)
                    sequence[outputPosition] = parameter1Value + parameter2Value
                    instructionPointer += 4
                }
                Opcode.MULTIPLY -> {
                    val parameter1Value = value(sequence, instructionPointer + 1, parameterMode1)
                    val parameter2Value = value(sequence, instructionPointer + 2, parameterMode2)
                    val outputPosition = value(sequence, instructionPointer + 3, outputParameterMode)
                    sequence[outputPosition] = parameter1Value * parameter2Value
                    instructionPointer += 4
                }
                Opcode.INPUT -> {
                    val input = if (inputs.isEmpty()) {
                        return null
                    } else {
                        inputs.removeAt(0)
                    }
                    val outputPosition = value(sequence, instructionPointer + 1, outputParameterMode)
                    sequence[outputPosition] = input
                    instructionPointer += 2
                }
                Opcode.OUTPUT -> {
                    val output = value(sequence, instructionPointer + 1, parameterMode1)
                    println(output)
                    latestOutput = output
                    instructionPointer += 2
                    return latestOutput
                }
                Opcode.JUMP_IF_TRUE -> {
                    val parameter1Value = value(sequence, instructionPointer + 1, parameterMode1)
                    val parameter2Value = value(sequence, instructionPointer + 2, parameterMode2)
                    if (parameter1Value != 0) {
                        instructionPointer = parameter2Value
                    } else {
                        instructionPointer += 3
                    }
                }
                Opcode.JUMP_IF_FALSE -> {
                    val parameter1Value = value(sequence, instructionPointer + 1, parameterMode1)
                    val parameter2Value = value(sequence, instructionPointer + 2, parameterMode2)
                    if (parameter1Value == 0) {
                        instructionPointer = parameter2Value
                    } else {
                        instructionPointer += 3
                    }
                }
                Opcode.LESS_THAN -> {
                    val parameter1Value = value(sequence, instructionPointer + 1, parameterMode1)
                    val parameter2Value = value(sequence, instructionPointer + 2, parameterMode2)
                    val outputPosition = value(sequence, instructionPointer + 3, outputParameterMode)
                    sequence[outputPosition] = if (parameter1Value < parameter2Value) {
                        1
                    } else {
                        0
                    }
                    instructionPointer += 4
                }
                Opcode.EQUALS -> {
                    val parameter1Value = value(sequence, instructionPointer + 1, parameterMode1)
                    val parameter2Value = value(sequence, instructionPointer + 2, parameterMode2)
                    val outputPosition = value(sequence, instructionPointer + 3, outputParameterMode)
                    sequence[outputPosition] = if (parameter1Value == parameter2Value) {
                        1
                    } else {
                        0
                    }
                    instructionPointer += 4
                }
                Opcode.EXIT -> return null
            }
        }
        return null
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
    /*
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
     */
    return null
}
