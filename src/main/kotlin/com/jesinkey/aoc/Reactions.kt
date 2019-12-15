package com.jesinkey.aoc

import kotlin.math.ceil

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

data class Chemical(val name: String, val amount: Double) {
    companion object {
        fun from(chemicalString: String): Chemical {
            val foo = chemicalString.trim().split(" ")
            val amount = foo[0].toDouble()
            val name = foo[1]
            return Chemical(name, amount)
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
        val fuelFunction = functions.single { function -> function.result.name == "FUEL" }
        var chemicals = fuelFunction.chemicals
        var keepGoing = true
        while (keepGoing) {
            chemicals = replaceNonProducers(chemicals, functions)
            chemicals = mergeChemicals(chemicals)
            keepGoing = !allChemicalsAreProducers(chemicals, functions)
        }

        val oreAmounts = chemicals.associateBy { it.name }.map { (name, chemical) ->
            val chemicalFunction = functions.single { function -> function.result.name == name}
            val chemicalAmount = ceil(chemical.amount)
            val functionAmount = numberOfTimesFunctionIsNeeded(chemicalAmount, chemicalFunction)
            val asd = chemicalFunction.chemicals.single().amount * functionAmount
            asd.toInt()
        }

        return oreAmounts.sum()
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
//                val functionAmount = chemical.amount / chemicalFunction.result.amount
                val functionAmount = numberOfTimesFunctionIsNeeded(chemical.amount, chemicalFunction)
                chemicalFunction.chemicals.map {
                    it.copy(amount = it.amount * functionAmount)
                }
            }
        }
    }

    private fun mergeChemicals(chemicals: List<Chemical>): List<Chemical> {
        return chemicals
            .groupBy { it.name }
            .map { (name, chemicals) ->
                Chemical(name, chemicals.sumByDouble { chemical ->  chemical.amount })
            }
    }

    private fun numberOfTimesFunctionIsNeeded(
        chemicalAmount: Double,
        chemicalsFunction: Function
    ) = ceil(chemicalAmount / chemicalsFunction.result.amount).toInt()

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