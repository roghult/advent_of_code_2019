package com.jesinkey.aoc

import kotlin.math.ceil

data class Function(val chemicals: List<Chemical>, val result: List<Chemical>) {

    fun isProducer(): Boolean {
        return chemicals.none { it.name != "ORE" }
    }

    companion object {
        fun from(chemicalString: String, resultString: String): Function {
            val chemicals = chemicalString.split(",").flatMap {
                Chemical.from(it)
            }
            val result = Chemical.from(resultString)
            return Function(chemicals, result)
        }
    }
}

data class Chemical(val name: String) {
    companion object {
        fun from(chemicalString: String): List<Chemical> {
            val foo = chemicalString.trim().split(" ")
            val amount = foo[0].toInt()
            val name = foo[1]
            return (0 until amount).map { Chemical(name) }
        }
    }
}

class Reactions {
    fun createFunctions(input: List<String>): List<Function> {
        val functions = input.map { each ->
            val foo = each.split(" => ")
            val chemicalsString = foo[0]
            val resultString = foo[1]
            Function.from(chemicalsString, resultString)
        }
        return functions
    }

    fun minimumOreForFuel(input: List<String>): Int {
        val functions = createFunctions(input)
        val fuelFunction = functions.single { function -> function.result.first().name == "FUEL" }
        val result = expandFunctionToProducers(functions, fuelFunction)
        val asd = result.groupBy { it.name }.map { (name, chemicals) ->
            val chemicalsFunction = functions.single { it.result.first().name == name }
            val oresRequired = chemicalsFunction.chemicals.size
            val foo = ceil(chemicals.size / chemicalsFunction.result.size.toDouble()).toInt() * oresRequired
            foo
        }
        val qwe = asd.sum()
        return qwe
    }

    private fun expandFunctionToProducers(functions: List<Function>, function: Function): List<Chemical> {
        if (function.isProducer()) {
            return listOf(function.result.first())
        }

        val result = function.chemicals.groupBy { it.name }.flatMap { (name, chemicals) ->
            val chemicalsFunction = functions.single { function -> function.result.first().name == name }
//            val numberOfTimes = ceil(chemicals.size / chemicalsFunction.result.size.toDouble()).toInt()
            (chemicals.indices).flatMap {
                val asd = expandFunctionToProducers(functions, chemicalsFunction)
                asd
            }
        }

        return result
    }

}