package com.jesinkey.aoc

import kotlin.math.ceil
import kotlin.math.floor

data class Function(val chemicals: List<Chemical>, val result: Chemical) {

    fun isProducer(): Boolean {
        return chemicals.none { it.name != "ORE" }
    }

    companion object {
        fun from(chemicalString: String, resultString: String): Function {
            val chemicals = chemicalString.split(",").map {
                Chemical.from(it)
            }
            val result = Chemical.from(resultString)
            return Function(chemicals, result)
        }
    }
}

data class Chemical(val name: String, val amount: Int) {
    companion object {
        fun from(chemicalString: String): Chemical {
            val foo = chemicalString.trim().split(" ")
            val amount = foo[0].toInt()
            val name = foo[1]
            return Chemical(name, amount)
        }
    }
}

class Reactions {

    private val restAmountByName = mutableMapOf<String, Int>()

    private fun createFunctions(input: List<String>): List<Function> {
        return input.map { each ->
            val foo = each.split(" => ")
            val chemicalsString = foo[0]
            val resultString = foo[1]
            Function.from(chemicalsString, resultString)
        }
    }

    fun minimumOreForFuel(input: List<String>): Int {
        val functions = createFunctions(input)
        val fuelFunction = functions.single { function -> function.result.name == "FUEL" }
        var chemicals = fuelFunction.chemicals
        var allChemicalsAreProducers = false
        while (!allChemicalsAreProducers) {
            chemicals = replaceNonProducers(chemicals, functions)
            chemicals = mergeChemicals(chemicals)
            allChemicalsAreProducers = allChemicalsAreProducers(chemicals, functions)
            chemicals = removeRest(chemicals)
        }

        val oreAmounts = chemicals.associateBy { it.name }.map { (name, chemical) ->
            val chemicalFunction = functions.single { function -> function.result.name == name}
            val chemicalAmount = chemical.amount
            val functionAmount = ceil(numberOfTimesFunctionIsNeeded(chemicalAmount, chemicalFunction)).toInt()
            chemicalFunction.chemicals.single().amount * functionAmount
        }

        return oreAmounts.sum()
    }

    private fun removeRest(chemicals: List<Chemical>): List<Chemical> {
        return chemicals.map {
            val restAmount = restAmountByName[it.name]?: 0
            restAmountByName[it.name] = 0
            it.copy(amount = it.amount - restAmount)
        }
    }

    private fun replaceNonProducers(
        chemicals: List<Chemical>,
        functions: List<Function>
    ): List<Chemical> {
        return chemicals.flatMap { chemical ->
            val chemicalFunction = chemicalFunction(functions, chemical)
            if (chemicalFunction.isProducer()) {
                listOf(chemical)
            } else {
                val functionAmount = numberOfTimesFunctionIsNeeded(chemical.amount, chemicalFunction)
                val functionAmountRoundedUp = ceil(functionAmount).toInt()
                val resultAmount = chemicalFunction.result.amount
                val rest = floor(resultAmount * functionAmountRoundedUp - resultAmount * functionAmount).toInt()
                addRest(chemicalFunction.result.name, rest)
                chemicalFunction.chemicals.map {
                    val amount = it.amount * functionAmountRoundedUp
                    it.copy(amount = amount)
                }
            }
        }
    }

    private fun addRest(name: String, amount: Int) {
        restAmountByName[name] = restAmountByName[name]?.plus(amount) ?: amount
    }

    private fun mergeChemicals(chemicals: List<Chemical>): List<Chemical> {
        return chemicals
            .groupBy { it.name }
            .map { (name, chemicals) ->
                Chemical(name, chemicals.sumBy { chemical ->  chemical.amount })
            }
    }

    private fun numberOfTimesFunctionIsNeeded(
        chemicalAmount: Int,
        chemicalsFunction: Function
    ) = chemicalAmount / chemicalsFunction.result.amount.toDouble()

    private fun allChemicalsAreProducers(
        chemicals: List<Chemical>,
        functions: List<Function>
    ): Boolean {
        return chemicals.all { chemical ->
            val chemicalsFunction = chemicalFunction(functions, chemical)
            chemicalsFunction.isProducer()
        }
    }

    private fun chemicalFunction(
        functions: List<Function>,
        chemical: Chemical
    ) = functions.single { function -> function.result.name == chemical.name }

}