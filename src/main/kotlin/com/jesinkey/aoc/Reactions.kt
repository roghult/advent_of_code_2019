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

    fun fuelFromOre(input: List<String>): Int {
        val amountOfOre = 1000000000000
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

        val restChemicals = restAmountByName.map { (name, amount) ->
            Chemical(name, amount)
        }

        val oreConsumedPerRunDisregardingRest = chemicals.map { chemical ->
            val chemicalFunction = chemicalFunction(functions, chemical)
            val functionAmount = ceil(numberOfTimesFunctionIsNeeded(chemical.amount, chemicalFunction)).toInt()
            val oreRequired = functionAmount * chemicalFunction.chemicals.single().amount
            oreRequired
        }.sum()

        val restWorthPerRun = restChemicals.map { chemical ->
            val chemicalFunction = chemicalFunction(functions, chemical)
            val savedPerRun = chemical.amount / chemicalFunction.result.amount.toDouble()
            savedPerRun
        }.sumByDouble { it }

        val oreConsumedPerRun = oreConsumedPerRunDisregardingRest - restWorthPerRun
        val result = 1 + ((amountOfOre - oreConsumedPerRun) / oreConsumedPerRun).toInt()

        return result
    }

    private fun expand(
        accumulatedRestChemicals: MutableList<Chemical>,
        functions: List<Function>
    ): List<Chemical> {
        var changeOccurred = false
        var foo = accumulatedRestChemicals.toList()
        do {
            foo = foo.flatMap { restChemical ->
                val chemicalFunction = chemicalFunction(functions, restChemical)
                if (restChemical.amount % chemicalFunction.result.amount == 0) {
                    changeOccurred = true
                    chemicalFunction.chemicals
                } else {
                    listOf(restChemical)
                }
            }
        } while (changeOccurred)
        return mergeChemicals(foo)
    }

    fun minimumOreForFuel(input: List<String>): Int {
        val functions = createFunctions(input)
        val fuelFunction = functions.single { function -> function.result.name == "FUEL" }
        var chemicals = fuelFunction.chemicals
        chemicals = replaceWithProducers(chemicals, functions)

        val oreAmounts = chemicals.associateBy { it.name }.map { (name, chemical) ->
            val chemicalFunction = functions.single { function -> function.result.name == name}
            val chemicalAmount = chemical.amount
            val functionAmount = ceil(numberOfTimesFunctionIsNeeded(chemicalAmount, chemicalFunction)).toInt()
            chemicalFunction.chemicals.single().amount * functionAmount
        }

        return oreAmounts.sum()
    }

    private fun replaceWithProducers(
        chemicals: List<Chemical>,
        functions: List<Function>
    ): List<Chemical> {
        var producerChemicals = chemicals
        var allChemicalsAreProducers = false
        while (!allChemicalsAreProducers) {
            producerChemicals = replaceNonProducers(producerChemicals, functions)
            producerChemicals = mergeChemicals(producerChemicals)
            allChemicalsAreProducers = allChemicalsAreProducers(producerChemicals, functions)
            producerChemicals = removeRest(producerChemicals)
        }
        return producerChemicals
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