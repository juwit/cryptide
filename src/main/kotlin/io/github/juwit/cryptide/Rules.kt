package io.github.juwit.cryptide

/**
 * Represents a rule of the game ("Indice" in french)
 */
interface Rule {
    /**
     * Tells if the current rule applies to the given cell.
     */
    fun appliesTo(cell: Cell): Boolean
}

class TypeRule(val types: Pair<CellType, CellType>) : Rule {
    override fun appliesTo(cell: Cell): Boolean = this.types.first == cell.type || this.types.second == cell.type
}

class OneCellAwayTypeRule(val type: CellType) : Rule {
    override fun appliesTo(cell: Cell): Boolean {
        return cell.type == this.type || cell.neighbors.any { it.type == this.type }
    }
}

class OneCellAwayFromAnimalRule() : Rule {
    override fun appliesTo(cell: Cell): Boolean {
        return cell.animalType != AnimalType.NONE || cell.neighbors.any { it.animalType != AnimalType.NONE }
    }
}

class TwoCellsAwayFromStructureRule(val structType: StructType) : Rule {
    override fun appliesTo(cell: Cell): Boolean {
        return cell.struct?.type == structType
                || cell.neighbors.any { it.struct?.type == structType }
                || cell.neighbors.flatMap { it.neighbors }.any { it.struct?.type == structType }
    }
}

class TwoCellsAwayFromAnimalRule(val animalType: AnimalType) : Rule {
    override fun appliesTo(cell: Cell): Boolean {
        return cell.animalType == animalType
                || cell.neighbors.any { it.animalType == animalType }
                || cell.neighbors.flatMap { it.neighbors }.any { it.animalType == animalType }
    }
}

class ThreeCellsAwayFromColoredStructRule(val structColor: StructColor) : Rule {
    override fun appliesTo(cell: Cell): Boolean {
        return cell.struct?.color == structColor
                || cell.neighbors.any { it.struct?.color == structColor }
                || cell.neighbors.flatMap { it.neighbors }.any { it.struct?.color == structColor }
                || cell.neighbors.flatMap { it.neighbors.flatMap { cell -> cell.neighbors  } }.any { it.struct?.color == structColor }
    }
}