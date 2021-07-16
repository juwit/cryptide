package io.github.juwit.cryptide

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class TwoCellsAwayFromStructureRuleTest {

    @Test
    fun testAppliesToSelf() {
        val monolithMountainCell = Cell(CellType.MOUNTAIN, AnimalType.NONE, StructType.MONOLITH)

        Assertions.assertThat(TwoCellsAwayFromStructureRule(StructType.MONOLITH).appliesTo(monolithMountainCell)).isTrue
    }

    @Test
    fun testDoesNotAppliesToOtherType() {
        val monolithMountainCell = Cell(CellType.MOUNTAIN, AnimalType.NONE, StructType.MONOLITH)

        Assertions.assertThat(TwoCellsAwayFromStructureRule(StructType.CABAN).appliesTo(monolithMountainCell)).isFalse
    }

    @Test
    fun testAppliesToOneNeighbor() {
        val mountainCell = Cell(CellType.MOUNTAIN)
        mountainCell.neighbors = listOf(Cell(CellType.DESERT, AnimalType.NONE, StructType.CABAN))

        Assertions.assertThat(TwoCellsAwayFromStructureRule(StructType.CABAN).appliesTo(mountainCell)).isTrue
    }

    @Test
    fun testAppliesToAnyNeighbor() {
        val mountainCell = Cell(CellType.MOUNTAIN)
        mountainCell.neighbors = listOf(
            Cell(CellType.SWAMP),
            Cell(CellType.DESERT, AnimalType.PUMA, StructType.CABAN),
            Cell(CellType.LAKE)
        )

        Assertions.assertThat(TwoCellsAwayFromStructureRule(StructType.CABAN).appliesTo(mountainCell)).isTrue
    }

    @Test
    fun testAppliesToTwoCellsAwayNeighbor() {
        val mountainCell = Cell(CellType.MOUNTAIN)
        val desertCell = Cell(CellType.DESERT)
        val cabanCell = Cell(CellType.SWAMP, AnimalType.NONE, StructType.CABAN)

        mountainCell.neighbors = listOf(desertCell)
        desertCell.neighbors = listOf(mountainCell, cabanCell)

        Assertions.assertThat(TwoCellsAwayFromStructureRule(StructType.CABAN).appliesTo(mountainCell)).isTrue
    }

    @Test
    fun testDoesNotAppliesToFarAwayNeighbor() {
        val mountainCell = Cell(CellType.MOUNTAIN)
        val desertCell = Cell(CellType.DESERT)
        val lakeCell = Cell(CellType.LAKE)
        val cabanCell = Cell(CellType.SWAMP, AnimalType.NONE, StructType.CABAN)

        mountainCell.neighbors = listOf(desertCell)
        desertCell.neighbors = listOf(lakeCell)
        lakeCell.neighbors = listOf(cabanCell)

        Assertions.assertThat(TwoCellsAwayFromStructureRule(StructType.CABAN).appliesTo(mountainCell)).isFalse
    }
}