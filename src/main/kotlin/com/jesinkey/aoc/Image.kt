package com.jesinkey.aoc

class Image(
    private val input: String,
    private val width: Int,
    private val height: Int
) {

    private val layers = createLayers()

    private fun createLayers(): List<List<String>> {
        val foo = input.chunked(width)
        val bar = foo.chunked(height)
        return bar
    }

    fun layerWithLeast(value: Int): Int {
        val layerIndexToCount = layers.mapIndexed { index, layer ->
            index to layer.sumBy { row -> row.count { char -> char.toString() == value.toString() } }
        }
        val layerIndex = layerIndexToCount.minBy { it.second }!!.first
        return layerIndex + 1
    }

    fun occurrencesInLayer(layer: Int, value: Int): Int {
        val occurrences = layers[layer - 1].sumBy { row ->
            row.count { it.toString() == value.toString() }
        }
        return occurrences
    }
}