package io.github.juwit.cryptide

enum class CellType {
    LAKE, MOUNTAIN, FOREST, DESERT, SWAMP
}

enum class AnimalType {
    NONE, PUMA, BEAR
}

enum class StructType {
    NONE, CABAN, MONOLITH
}

class Cell(
    val type: CellType,
    val animalType: AnimalType = AnimalType.NONE,
    val structType: StructType = StructType.NONE
) {
    var neighbors = listOf<Cell>()
}