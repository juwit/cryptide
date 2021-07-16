package io.github.juwit.cryptide

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class ThreeCellsAwayFromColoredStructRuleTest {

    @Test
    fun testAppliesToSelf() {
        val greenMonolithMountainCell =
            Cell(CellType.MOUNTAIN, AnimalType.NONE, Struct(StructType.MONOLITH, StructColor.GREEN))

        Assertions.assertThat(ThreeCellsAwayFromColoredStructRule(StructColor.GREEN).appliesTo(greenMonolithMountainCell)).isTrue
    }

    @Test
    fun testDoesNotAppliesToOtherType() {
        val whiteMonolithMountainCell =
            Cell(CellType.MOUNTAIN, AnimalType.NONE, Struct(StructType.MONOLITH, StructColor.WHITE))

        Assertions.assertThat(ThreeCellsAwayFromColoredStructRule(StructColor.GREEN).appliesTo(whiteMonolithMountainCell)).isFalse
    }

    @Test
    fun testAppliesToOneNeighbor() {
        val mountainCell = Cell(CellType.MOUNTAIN)
        mountainCell.neighbors =
            listOf(Cell(CellType.DESERT, AnimalType.NONE, Struct(StructType.MONOLITH, StructColor.GREEN)))

        Assertions.assertThat(ThreeCellsAwayFromColoredStructRule(StructColor.GREEN).appliesTo(mountainCell)).isTrue
    }

    @Test
    fun testAppliesToAnyNeighbor() {
        val mountainCell = Cell(CellType.MOUNTAIN)
        mountainCell.neighbors = listOf(
            Cell(CellType.SWAMP),
            Cell(CellType.DESERT, AnimalType.PUMA, Struct(StructType.CABAN, StructColor.GREEN)),
            Cell(CellType.LAKE)
        )

        Assertions.assertThat(ThreeCellsAwayFromColoredStructRule(StructColor.GREEN).appliesTo(mountainCell)).isTrue
    }

    @Test
    fun testAppliesToTwoCellsAwayNeighbor() {
        val mountainCell = Cell(CellType.MOUNTAIN)
        val desertCell = Cell(CellType.DESERT)
        val cabanCell = Cell(CellType.SWAMP, AnimalType.NONE, Struct(StructType.CABAN, StructColor.GREEN))

        mountainCell.neighbors = listOf(desertCell)
        desertCell.neighbors = listOf(mountainCell, cabanCell)

        Assertions.assertThat(ThreeCellsAwayFromColoredStructRule(StructColor.GREEN).appliesTo(mountainCell)).isTrue
    }

    @Test
    fun testAppliesToThreeCellsAwayNeighbor() {
        val mountainCell = Cell(CellType.MOUNTAIN)
        val desertCell = Cell(CellType.DESERT)
        val lakeCell = Cell(CellType.LAKE)
        val cabanCell = Cell(CellType.SWAMP, AnimalType.NONE, Struct(StructType.CABAN, StructColor.GREEN))

        mountainCell.neighbors = listOf(desertCell)
        desertCell.neighbors = listOf(lakeCell)
        lakeCell.neighbors = listOf(cabanCell)

        Assertions.assertThat(ThreeCellsAwayFromColoredStructRule(StructColor.GREEN).appliesTo(mountainCell)).isTrue
    }

    @Test
    fun testDoesNotAppliesToTooFarAwayNeighbor() {
        val mountainCell = Cell(CellType.MOUNTAIN)
        val desertCell = Cell(CellType.DESERT)
        val lakeCell = Cell(CellType.LAKE)
        val anotherLakeCell = Cell(CellType.LAKE)
        val cabanCell = Cell(CellType.SWAMP, AnimalType.NONE, Struct(StructType.CABAN, StructColor.GREEN))

        mountainCell.neighbors = listOf(desertCell)
        desertCell.neighbors = listOf(mountainCell, lakeCell)
        lakeCell.neighbors = listOf(desertCell,anotherLakeCell)
        anotherLakeCell.neighbors = listOf(cabanCell)

        Assertions.assertThat(ThreeCellsAwayFromColoredStructRule(StructColor.GREEN).appliesTo(mountainCell)).isFalse
    }
}