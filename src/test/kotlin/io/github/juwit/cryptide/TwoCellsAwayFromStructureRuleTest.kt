package io.github.juwit.cryptide

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class TwoCellsAwayFromStructureRuleTest {

    @Test
    fun testAppliesToSelf() {
        val monolithMountainCell = Cell(CellType.MOUNTAIN, AnimalType.NONE, Struct(StructType.MONOLITH, StructColor.BLACK))

        Assertions.assertThat(TwoCellsAwayFromStructureRule(StructType.MONOLITH).appliesTo(monolithMountainCell)).isTrue
    }

    @Test
    fun testDoesNotAppliesToOtherType() {
        val monolithMountainCell = Cell(CellType.MOUNTAIN, AnimalType.NONE, Struct(StructType.MONOLITH, StructColor.BLACK))

        Assertions.assertThat(TwoCellsAwayFromStructureRule(StructType.CABAN).appliesTo(monolithMountainCell)).isFalse
    }

    @Test
    fun testAppliesToOneNeighbor() {
        val mountainCell = Cell(CellType.MOUNTAIN)
        mountainCell.neighbors = listOf(Cell(CellType.DESERT, AnimalType.NONE, Struct(StructType.MONOLITH, StructColor.BLACK)))

        Assertions.assertThat(TwoCellsAwayFromStructureRule(StructType.MONOLITH).appliesTo(mountainCell)).isTrue
    }

    @Test
    fun testAppliesToAnyNeighbor() {
        val mountainCell = Cell(CellType.MOUNTAIN)
        mountainCell.neighbors = listOf(
            Cell(CellType.SWAMP),
            Cell(CellType.DESERT, AnimalType.PUMA, Struct(StructType.CABAN, StructColor.BLACK)),
            Cell(CellType.LAKE)
        )

        Assertions.assertThat(TwoCellsAwayFromStructureRule(StructType.CABAN).appliesTo(mountainCell)).isTrue
    }

    @Test
    fun testAppliesToTwoCellsAwayNeighbor() {
        val mountainCell = Cell(CellType.MOUNTAIN)
        val desertCell = Cell(CellType.DESERT)
        val cabanCell = Cell(CellType.SWAMP, AnimalType.NONE, Struct(StructType.CABAN, StructColor.BLACK))

        mountainCell.neighbors = listOf(desertCell)
        desertCell.neighbors = listOf(mountainCell, cabanCell)

        Assertions.assertThat(TwoCellsAwayFromStructureRule(StructType.CABAN).appliesTo(mountainCell)).isTrue
    }

    @Test
    fun testDoesNotAppliesToFarAwayNeighbor() {
        val mountainCell = Cell(CellType.MOUNTAIN)
        val desertCell = Cell(CellType.DESERT)
        val lakeCell = Cell(CellType.LAKE)
        val cabanCell = Cell(CellType.SWAMP, AnimalType.NONE, Struct(StructType.CABAN, StructColor.BLACK))

        mountainCell.neighbors = listOf(desertCell)
        desertCell.neighbors = listOf(lakeCell)
        lakeCell.neighbors = listOf(cabanCell)

        Assertions.assertThat(TwoCellsAwayFromStructureRule(StructType.CABAN).appliesTo(mountainCell)).isFalse
    }
}