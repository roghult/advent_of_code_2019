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

    fun orbitPath(): List<SpaceObject> {
        val orbitPath = mutableListOf<SpaceObject>()
        var spaceObject = orbits!!
        while (spaceObject.name != "COM") {
            orbitPath.add(spaceObject)
            spaceObject = spaceObject.orbits!!
        }
        orbitPath.add(spaceObject)
        return orbitPath
    }

    fun distanceTo(spaceObject: SpaceObject): Int {
        if (spaceObject.name == name) {
            return 0
        }
        var orbitingSpaceObject = orbits!!
        var counter = 0
        while (orbitingSpaceObject.name != spaceObject.name) {
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

fun minimumTransfersRequired(spaceObjects: List<SpaceObject>, fromName: String, toName: String): Int {
    val from = spaceObjects.single { it.name == fromName }
    val to = spaceObjects.single { it.name == toName }
    val fromOrbitPath = from.orbitPath()
    val toOrbitPath = to.orbitPath()
    val firstCommonSpaceObject = fromOrbitPath.intersect(toOrbitPath).first()
    return from.distanceTo(firstCommonSpaceObject) + to.distanceTo(firstCommonSpaceObject)
}


