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

data class Chemical(val name: String, val amount: Long) {
    companion object {
        fun from(chemicalString: String): Chemical {
            val foo = chemicalString.trim().split(" ")
            val amount = foo[0].toLong()
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

    fun oreNeededForFuel(fuelAmount: Long, input: List<String>): Long {
        val functions = createFunctions(input)
        val fuelFunction = functions.single { it.result.name == "FUEL" }
        val amountNeeded = fuelAmount / fuelFunction.result.amount
        var previous = mutableMapOf<String, Long>()
        val needToProduce = fuelFunction.chemicals.map { it.name to it.amount * amountNeeded }.toMap().toMutableMap()
        val rest = mutableMapOf<String, Long>()

        while (needToProduce != previous) {
            previous = needToProduce.toMutableMap()
            for ((chemical, amount) in needToProduce.filterValues { it > 0 }) {
                val chemicalFunction = functions.single { it.result.name == chemical }
                if (chemicalFunction.isProducer()) continue

                val requiredProductionAmount = ceil(amount / chemicalFunction.result.amount.toDouble()).toInt()

                for (newChemical in chemicalFunction.chemicals) {
                    val newChemicalAmount = newChemical.amount * requiredProductionAmount
                    needToProduce[newChemical.name] = needToProduce[newChemical.name]?.plus(newChemicalAmount) ?: newChemicalAmount
                }

                val restAmount = amount - requiredProductionAmount
                rest[chemical] = rest[chemical]?.plus(restAmount) ?: restAmount
                for ((restName, restAmount) in rest) {
                    if (needToProduce.containsKey(restName)) {
                        val needToProduceValue = needToProduce[restName]
                        val newProduceValue = when {
                            needToProduceValue == null -> 0
                            needToProduceValue >= restAmount -> {
                                rest[restName] = 0
                                needToProduceValue - restAmount
                            }
                            needToProduceValue < restAmount -> {
                                rest[restName] = restAmount - needToProduceValue
                                0
                            }
                            else -> TODO()
                        }
                        needToProduce[restName] = newProduceValue
                    }
                }

                needToProduce[chemicalFunction.result.name] = 0
            }
        }

        val amountOre = needToProduce.filterValues { it > 0 }.map { (name, amount) ->
            val chemicalFunction = functions.single { it.result.name == name }
            val requiredProductionAmount = ceil(amount / chemicalFunction.result.amount.toDouble()).toInt()
            chemicalFunction.chemicals.single().amount * requiredProductionAmount
        }.sum()

        return amountOre
    }
}