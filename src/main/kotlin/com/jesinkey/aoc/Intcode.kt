package com.jesinkey.aoc

enum class ParameterMode(val code: Int) {
    POSITION(0),
    IMMEDIATE(1),
    RELATIVE(2);

    companion object {
        private val values = values()
        fun getByValue(value: Int) = values.first { it.code == value }
        fun getByValue(value: Char) = getByValue(Character.getNumericValue(value))
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

    private val OPCODE_LENGTH = 2
    private var instructionPointer: Long = 0
    private var relativeBase: Long = 0

    fun run(inputs: MutableList<Long> = mutableListOf()): Long? {
        while (true) { // ?
            val opcode = Opcode.getByValue(opcode())
            if (opcode == Opcode.EXIT) {
                return null // ?
            }

            when (opcode) {
                Opcode.ADD -> {
                    val parameter1Value = readValue(1)
                    val parameter2Value = readValue(2)
                    val outputPosition = writeValue(3)
                    val newValue = parameter1Value + parameter2Value
                    setValue(outputPosition, newValue)
                    instructionPointer += 4
                }
                Opcode.MULTIPLY -> {
                    val parameter1Value = readValue(1)
                    val parameter2Value = readValue(2)
                    val outputPosition = writeValue(3)
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
                    val outputPosition = writeValue(1)
                    setValue(outputPosition, input)
                    instructionPointer += 2
                }
                Opcode.OUTPUT -> {
                    val output = readValue(1)
//                    println(output)
                    instructionPointer += 2
                    return output
                }
                Opcode.JUMP_IF_TRUE -> {
                    val parameter1Value = readValue(1)
                    val parameter2Value = readValue(2)
                    if (parameter1Value != 0L) {
                        instructionPointer = parameter2Value
                    } else {
                        instructionPointer += 3
                    }
                }
                Opcode.JUMP_IF_FALSE -> {
                    val parameter1Value = readValue(1)
                    val parameter2Value = readValue(2)
                    if (parameter1Value == 0L) {
                        instructionPointer = parameter2Value
                    } else {
                        instructionPointer += 3
                    }
                }
                Opcode.LESS_THAN -> {
                    val parameter1Value = readValue(1)
                    val parameter2Value = readValue(2)
                    val outputPosition = writeValue(3)
                    val value: Long = if (parameter1Value < parameter2Value) { 1 } else { 0 }
                    setValue(outputPosition, value)
                    instructionPointer += 4
                }
                Opcode.EQUALS -> {
                    val parameter1Value = readValue(1)
                    val parameter2Value = readValue(2)
                    val outputPosition = writeValue(3)
                    val value: Long = if (parameter1Value == parameter2Value) { 1 } else { 0 }
                    setValue(outputPosition, value)
                    instructionPointer += 4
                }
                Opcode.ADJUST_RELATIVE_BASE -> {
                    val parameterValue1 = readValue(1)
                    relativeBase += parameterValue1
                    instructionPointer += 2
                }
                Opcode.EXIT -> return null
            }
        }
    }

    private fun opcode(): Int {
        return currentInstruction().takeLast(OPCODE_LENGTH).toInt()
    }

    private fun parameterMode(number: Long): ParameterMode {
        val instruction = currentInstruction()
        val positionFromRight = number + OPCODE_LENGTH
        val positionFromLeft = (instruction.length - positionFromRight).toInt()
        val parameterValue = when {
            positionFromLeft >= 0 -> {
                instruction[positionFromLeft]
            }
            else -> {
                '0'
            }
        }
        return ParameterMode.getByValue(parameterValue)
    }

    private fun currentInstruction(): String {
        return sequence[instructionPointer].toString()
    }

    private fun readValue(parameterNumber: Long): Long {
        val positionValue = value(instructionPointer + parameterNumber)
        return when (parameterMode(parameterNumber)) {
            ParameterMode.POSITION -> value(positionValue)
            ParameterMode.IMMEDIATE -> positionValue
            ParameterMode.RELATIVE -> value(positionValue + relativeBase)
        }
    }

    private fun writeValue(parameterNumber: Long): Long {
        val positionValue = value(instructionPointer + parameterNumber)
        return when (parameterMode(parameterNumber)) {
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
}

fun sequenceListToMap(sequence: List<Long>): MutableMap<Long, Long> {
    return sequence.mapIndexed { index, value -> index.toLong() to value }.toMap() as MutableMap<Long, Long>
}
