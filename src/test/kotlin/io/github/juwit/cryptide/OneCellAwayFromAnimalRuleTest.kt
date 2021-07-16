package io.github.juwit.cryptide

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class OneCellAwayFromAnimalRuleTest {

    @Test
    fun testAppliesToSelf() {
        val bearMountainCell = Cell(CellType.MOUNTAIN, AnimalType.BEAR)

        Assertions.assertThat(OneCellAwayFromAnimalRule().appliesTo(bearMountainCell)).isTrue
    }

    @Test
    fun testAppliesToAnyAnimalType() {
        val pumaMountainCell = Cell(CellType.MOUNTAIN, AnimalType.PUMA)

        Assertions.assertThat(OneCellAwayFromAnimalRule().appliesTo(pumaMountainCell)).isTrue
    }

    @Test
    fun testDoesNotAppliesToSelf() {
        val mountainCell = Cell(CellType.MOUNTAIN)

        Assertions.assertThat(OneCellAwayFromAnimalRule().appliesTo(mountainCell)).isFalse
    }

    @Test
    fun testAppliesToOneNeighbor() {
        val mountainCell = Cell(CellType.MOUNTAIN)
        mountainCell.neighbors = listOf(Cell(CellType.DESERT, AnimalType.PUMA))

        Assertions.assertThat(OneCellAwayFromAnimalRule().appliesTo(mountainCell)).isTrue
    }

    @Test
    fun testAppliesToAnyNeighbor() {
        val mountainCell = Cell(CellType.MOUNTAIN)
        mountainCell.neighbors = listOf(
            Cell(CellType.SWAMP),
            Cell(CellType.DESERT, AnimalType.PUMA),
            Cell(CellType.LAKE)
        )

        Assertions.assertThat(OneCellAwayFromAnimalRule().appliesTo(mountainCell)).isTrue
    }

    @Test
    fun testDoesNotAppliesToFarAwayNeighbor() {
        val mountainCell = Cell(CellType.MOUNTAIN)

        val swampNeighbor = Cell(CellType.SWAMP)
        swampNeighbor.neighbors = listOf(Cell(CellType.DESERT, AnimalType.PUMA))

        mountainCell.neighbors = listOf(swampNeighbor)

        Assertions.assertThat(OneCellAwayFromAnimalRule().appliesTo(mountainCell)).isFalse
    }
}