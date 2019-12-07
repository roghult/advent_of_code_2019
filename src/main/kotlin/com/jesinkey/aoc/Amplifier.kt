package com.jesinkey.aoc

class Amplifier(private val sequence: List<Int>) {
    
    fun run(phaseSettings: IntRange): Pair<List<Int>, Int> {
        var largestThrusterSignal = Int.MIN_VALUE
        var largestPhaseSettings = emptyList<Int>()
        for (phaseSettingA in phaseSettings) {
            for (phaseSettingB in phaseSettings) {
                for (phaseSettingC in phaseSettings) {
                    for (phaseSettingD in phaseSettings) {
                        for (phaseSettingE in phaseSettings) {
                            val currentPhaseSettings = listOf(
                                phaseSettingA,
                                phaseSettingB,
                                phaseSettingC,
                                phaseSettingD,
                                phaseSettingE
                            )

                            val isPermutation = currentPhaseSettings.toSet().size == currentPhaseSettings.size
                            if (isPermutation) {
                                val ampA = Intcode(sequence.toMutableList())
                                val ampB = Intcode(sequence.toMutableList())
                                val ampC = Intcode(sequence.toMutableList())
                                val ampD = Intcode(sequence.toMutableList())
                                val ampE = Intcode(sequence.toMutableList())

                                var ampEOutput: Int? = 0

                                val ampAInput = mutableListOf(phaseSettingA, ampEOutput)
                                val ampBInput = mutableListOf<Int?>(phaseSettingB)
                                val ampCInput = mutableListOf<Int?>(phaseSettingC)
                                val ampDInput = mutableListOf<Int?>(phaseSettingD)
                                val ampEInput = mutableListOf<Int?>(phaseSettingE)

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
                                            largestPhaseSettings = currentPhaseSettings
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return largestPhaseSettings to largestThrusterSignal
    }

}
