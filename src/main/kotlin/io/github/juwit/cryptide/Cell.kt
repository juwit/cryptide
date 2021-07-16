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

enum class StructColor {
    BLUE, WHITE, GREEN, BLACK
}

data class Struct (val type: StructType, val color: StructColor)

class Cell(
    val type: CellType,
    val animalType: AnimalType = AnimalType.NONE,
    val struct: Struct? = null
) {
    var neighbors = listOf<Cell>()
}