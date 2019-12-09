package com.jesinkey.aoc

class Amplifier(private val sequence: List<Long>) {

    fun run(phaseSettings: LongRange): Pair<List<Long>, Long> {
        var largestThrusterSignal = Long.MIN_VALUE
        var largestPhaseSettings = emptyList<Long>()
        var possiblePhaseSettings = heapsPermutations(phaseSettings.toList())
        for (phaseSetting in possiblePhaseSettings) {
            val ampA = Intcode(sequenceListToMap(sequence))
            val ampB = Intcode(sequenceListToMap(sequence))
            val ampC = Intcode(sequenceListToMap(sequence))
            val ampD = Intcode(sequenceListToMap(sequence))
            val ampE = Intcode(sequenceListToMap(sequence))

            var ampEOutput: Long? = 0

            val ampAInput = mutableListOf(phaseSetting[0], ampEOutput)
            val ampBInput = mutableListOf<Long?>(phaseSetting[1])
            val ampCInput = mutableListOf<Long?>(phaseSetting[2])
            val ampDInput = mutableListOf<Long?>(phaseSetting[3])
            val ampEInput = mutableListOf<Long?>(phaseSetting[4])

            while (ampEOutput != null) {
                val ampAOutput = ampA.run(ampAInput.filterNotNull().toMutableList())
                ampAInput.clear()
                ampBInput.add(ampAOutput)
                val ampBOutput = ampB.run(ampBInput.filterNotNull().toMutableList())
                ampBInput.clear()
                ampCInput.add(ampBOutput)
                val ampCOutput = ampC.run(ampCInput.filterNotNull().toMutableList())
                ampCInput.clear()
                ampDInput.add(ampCOutput)
                val ampDOutput = ampD.run(ampDInput.filterNotNull().toMutableList())
                ampDInput.clear()
                ampEInput.add(ampDOutput)
                ampEOutput = ampE.run(ampEInput.filterNotNull().toMutableList())
                ampEInput.clear()
                ampAInput.add(ampEOutput)
                if (ampEOutput != null) {
                    val thrusterSignal = ampEOutput!!
                    if (thrusterSignal > largestThrusterSignal) {
                        largestThrusterSignal = thrusterSignal
                        largestPhaseSettings = phaseSetting
                    }
                }
            }
        }

        return largestPhaseSettings to largestThrusterSignal
    }

}
