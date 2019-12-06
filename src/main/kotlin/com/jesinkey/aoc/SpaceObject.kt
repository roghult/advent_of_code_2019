package com.jesinkey.aoc

val COM = "COM"

data class SpaceObject (val name: String, var orbits: SpaceObject? = null) {

    fun numberOfOrbits(): Int {
        if (name == COM) {
            return 0
        }
        var orbitingSpaceObject = orbits!!
        var counter = 1
        while (orbitingSpaceObject.name != COM) {
            counter++
            orbitingSpaceObject = orbitingSpaceObject.orbits!!
        }
        return counter
    }
}

class SpaceObjectFactory {
    fun fromInputText(input: List<String>): List<SpaceObject> {
        val spaceObjectNameToSpaceObject = mutableMapOf<String, SpaceObject>()
        input.forEach { it ->
            val spaceObjectNames = it.split(")")
            spaceObjectNameToSpaceObject[spaceObjectNames[0]] = SpaceObject(spaceObjectNames[0])
            spaceObjectNameToSpaceObject[spaceObjectNames[1]] = SpaceObject(spaceObjectNames[1])
        }
        input.forEach { it ->
            val spaceObjectNames = it.split(")")
            val orbitingSpaceObject = spaceObjectNameToSpaceObject[spaceObjectNames[1]]!!
            val orbitsAround = spaceObjectNameToSpaceObject[spaceObjectNames[0]]!!
            orbitingSpaceObject.orbits = orbitsAround
        }

        return spaceObjectNameToSpaceObject.values.toList()
    }
}

fun totalNumberOfOrbits(spaceObjects: List<SpaceObject>): Int {
    return spaceObjects.sumBy { it.numberOfOrbits() }
}
