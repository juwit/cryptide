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