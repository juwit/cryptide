package io.github.juwit.cryptide

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class TwoCellsAwayFromAnimalRuleTest {
    
    @Test
    fun testAppliesToSelf() {
        val bearMountainCell = Cell(CellType.MOUNTAIN, AnimalType.BEAR)

        Assertions.assertThat(TwoCellsAwayFromAnimalRule(AnimalType.BEAR).appliesTo(bearMountainCell)).isTrue
    }

    @Test
    fun testDoesNotAppliesToOtherType() {
        val pumaMountainCell = Cell(CellType.MOUNTAIN, AnimalType.PUMA)

        Assertions.assertThat(TwoCellsAwayFromAnimalRule(AnimalType.BEAR).appliesTo(pumaMountainCell)).isFalse
    }

    @Test
    fun testAppliesToOneNeighbor() {
        val bearMountainCell = Cell(CellType.MOUNTAIN)
        bearMountainCell.neighbors = listOf(Cell(CellType.DESERT, AnimalType.BEAR))

        Assertions.assertThat(TwoCellsAwayFromAnimalRule(AnimalType.BEAR).appliesTo(bearMountainCell)).isTrue
    }

    @Test
    fun testAppliesToAnyNeighbor() {
        val mountainCell = Cell(CellType.MOUNTAIN)
        mountainCell.neighbors = listOf(
            Cell(CellType.SWAMP),
            Cell(CellType.DESERT, AnimalType.BEAR),
            Cell(CellType.LAKE)
        )

        Assertions.assertThat(TwoCellsAwayFromAnimalRule(AnimalType.BEAR).appliesTo(mountainCell)).isTrue
    }

    @Test
    fun testAppliesToTwoCellsAwayNeighbor() {
        val mountainCell = Cell(CellType.MOUNTAIN)
        val desertCell = Cell(CellType.DESERT)
        val bearCell = Cell(CellType.SWAMP, AnimalType.BEAR)

        mountainCell.neighbors = listOf(desertCell)
        desertCell.neighbors = listOf(mountainCell, bearCell)

        Assertions.assertThat(TwoCellsAwayFromAnimalRule(AnimalType.BEAR).appliesTo(mountainCell)).isTrue
    }

    @Test
    fun testDoesNotAppliesToFarAwayNeighbor() {
        val mountainCell = Cell(CellType.MOUNTAIN)
        val desertCell = Cell(CellType.DESERT)
        val lakeCell = Cell(CellType.LAKE)
        val cabanCell = Cell(CellType.SWAMP, AnimalType.BEAR)

        mountainCell.neighbors = listOf(desertCell)
        desertCell.neighbors = listOf(lakeCell)
        lakeCell.neighbors = listOf(cabanCell)

        Assertions.assertThat(TwoCellsAwayFromAnimalRule(AnimalType.BEAR).appliesTo(mountainCell)).isFalse
    }
}