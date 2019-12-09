package com.jesinkey.aoc

fun heapsPermutations(list: List<Long>): List<List<Long>> {
    return heapLoop(list.toMutableList())
}

private fun heapLoop(list: List<Long>): List<List<Long>> {
    val c = list.indices.map { 0 }.toMutableList()
    val permutation = list.toMutableList()
    val permutations = emptyList<List<Long>>().toMutableList()
    permutations.add(permutation.toList())
    var i = 0
    while (i < list.size) {
        if (c[i] < i) {
            if (i % 2 == 0) {
                permutation[0] = permutation[i].also { permutation[i] = permutation[0] }
            } else {
                permutation[c[i]] = permutation[i].also { permutation[i] = permutation[c[i]] }
            }
            permutations.add(permutation.toList())
            c[i] += 1
            i = 0
        } else {
            c[i] = 0
            i += 1
        }
    }

    return permutations
}
