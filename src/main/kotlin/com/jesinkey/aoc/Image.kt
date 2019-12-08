package com.jesinkey.aoc

private const val TRANSPARENT = 2

class Image(
    private val input: String,
    private val width: Int,
    private val height: Int
) {

    private val layers = createLayers()

    private fun createLayers(): List<Layer> {
        val chunks = input.chunked(width * height)
        val rows = chunks.map { row -> row.map { it.toString().toInt() } }
        val layers = rows.map { Layer(it.chunked(width)) }
        return layers
    }

    fun layerWithLeast(value: Int): Int {
        val layerIndexToCount = layers.mapIndexed { index, layer ->
            index to layer.occurrences(value)
        }
        val layerIndex = layerIndexToCount.minBy { it.second }!!.first
        return layerIndex + 1
    }

    fun occurrencesInLayer(layer: Int, value: Int): Int {
        val occurrences = layers[layer - 1].occurrences(value)
        return occurrences
    }

    fun finalImage(): String {
        var image = ""
        for (y in 0 until height) {
            for (x in 0 until width) {
                val colorPixels = layers.filter { it.pixelAt(x, y) != TRANSPARENT }.map { it.pixelAt(x, y) }
                image += colorPixels.first()
            }
            image += "\n"
        }
        return image
    }
}

class Layer(val rows: List<List<Int>>) {

    fun occurrences(value: Int): Int {
        return rows.sumBy { row -> row.count { it == value } }
    }

    fun pixelAt(x: Int, y: Int): Int {
        return rows[y][x]
    }
}
