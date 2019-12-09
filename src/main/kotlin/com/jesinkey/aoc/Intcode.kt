package com.jesinkey.aoc

const val ZERO: Long = 0

enum class ParameterMode(val code: Int) {
    POSITION(0),
    IMMEDIATE(1),
    RELATIVE(2);

    companion object {
        private val values = values()
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
    ADJUST_RELATIVE_BASE(9),
    EXIT(99);

    companion object {
        private val values = values()
        fun getByValue(value: Int) = values.first { it.code == value }
    }
}

class Intcode(val sequence: MutableMap<Long, Long>) {

    private var instructionPointer: Long = 0
    private var relativeBase: Long = 0

    fun run(inputs: MutableList<Int> = mutableListOf()): Long? {
        while (true) { // ?
            val instruction = sequence[instructionPointer].toString()
            val opcode = Opcode.getByValue(opcode(instruction))
            if (opcode == Opcode.EXIT) {
                return null // ?
            }

            val parameterMode1 = parameterMode(instruction, 1)
            val parameterMode2 = parameterMode(instruction, 2)
            val parameterMode3 = parameterMode(instruction, 3)

            when (opcode) {
                Opcode.ADD -> {
                    val parameter1Value = readValue(instructionPointer + 1, parameterMode1)
                    val parameter2Value = readValue(instructionPointer + 2, parameterMode2)
                    val outputPosition = writeValue(instructionPointer + 3, parameterMode3)
                    val newValue = parameter1Value + parameter2Value
                    setValue(outputPosition, newValue)
                    instructionPointer += 4
                }
                Opcode.MULTIPLY -> {
                    val parameter1Value = readValue(instructionPointer + 1, parameterMode1)
                    val parameter2Value = readValue(instructionPointer + 2, parameterMode2)
                    val outputPosition = writeValue(instructionPointer + 3, parameterMode3)
                    val newValue = parameter1Value * parameter2Value
                    setValue(outputPosition, newValue)
                    instructionPointer += 4
                }
                Opcode.INPUT -> {
                    val input = if (inputs.isEmpty()) {
                        return null
                    } else {
                        inputs.removeAt(0)
                    }
                    val outputPosition = writeValue(instructionPointer + 1, parameterMode1)
                    setValue(outputPosition, input.toLong())
                    instructionPointer += 2
                }
                Opcode.OUTPUT -> {
                    val output = readValue(instructionPointer + 1, parameterMode1)
                    println(output)
                    instructionPointer += 2
                    return output
                }
                Opcode.JUMP_IF_TRUE -> {
                    val parameter1Value = readValue(instructionPointer + 1, parameterMode1)
                    val parameter2Value = readValue(instructionPointer + 2, parameterMode2)
                    if (parameter1Value != ZERO) {
                        instructionPointer = parameter2Value
                    } else {
                        instructionPointer += 3
                    }
                }
                Opcode.JUMP_IF_FALSE -> {
                    val parameter1Value = readValue(instructionPointer + 1, parameterMode1)
                    val parameter2Value = readValue(instructionPointer + 2, parameterMode2)
                    if (parameter1Value == ZERO) {
                        instructionPointer = parameter2Value
                    } else {
                        instructionPointer += 3
                    }
                }
                Opcode.LESS_THAN -> {
                    val parameter1Value = readValue(instructionPointer + 1, parameterMode1)
                    val parameter2Value = readValue(instructionPointer + 2, parameterMode2)
                    val outputPosition = writeValue(instructionPointer + 3, parameterMode3)
                    val value: Long = if (parameter1Value < parameter2Value) { 1 } else { 0 }
                    setValue(outputPosition, value)
                    instructionPointer += 4
                }
                Opcode.EQUALS -> {
                    val parameter1Value = readValue(instructionPointer + 1, parameterMode1)
                    val parameter2Value = readValue(instructionPointer + 2, parameterMode2)
                    val outputPosition = writeValue(instructionPointer + 3, parameterMode3)
                    val value: Long = if (parameter1Value == parameter2Value) { 1 } else { 0 }
                    setValue(outputPosition, value)
                    instructionPointer += 4
                }
                Opcode.ADJUST_RELATIVE_BASE -> {
                    val parameterValue1 = readValue(instructionPointer + 1, parameterMode1)
                    relativeBase += parameterValue1
                    instructionPointer += 2
                }
                Opcode.EXIT -> return null
            }
        }
    }

    private fun opcode(instruction: String): Int {
        return instruction.takeLast(2).toInt()
    }

    private fun parameterMode(instruction: String, number: Int): ParameterMode {
        val positionFromRight = number + 2
        val positionFromLeft = instruction.length - positionFromRight
        val parameterValue = when {
            positionFromLeft >= 0 -> {
                instruction[positionFromLeft].toString().toInt()
            }
            else -> {
                0
            }
        }
        return ParameterMode.getByValue(parameterValue)
    }

    private fun readValue(position: Long, parameterMode: ParameterMode): Long {
        val positionValue = value(position)
        return when (parameterMode) {
            ParameterMode.POSITION -> value(positionValue)
            ParameterMode.IMMEDIATE -> positionValue
            ParameterMode.RELATIVE -> value(positionValue + relativeBase)
        }
    }

    private fun writeValue(position: Long, parameterMode: ParameterMode): Long {
        val positionValue = value(position)
        return when (parameterMode) {
            ParameterMode.POSITION -> positionValue
            ParameterMode.IMMEDIATE -> positionValue
            ParameterMode.RELATIVE -> positionValue + relativeBase
        }
    }

    private fun value(position: Long): Long {
        return sequence.getOrDefault(position, 0)
    }

    private fun setValue(position: Long, value: Long) {
        sequence[position] = value
    }

//    private fun increaseSequence(to: Int) {
//        for (x in sequence.size..to) {
//            sequence.add(0)
//        }
//    }
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

fun sequenceListToMap(sequence: List<Long>): MutableMap<Long, Long> {
    return sequence.mapIndexed { index, value -> index.toLong() to value }.toMap() as MutableMap<Long, Long>
}
