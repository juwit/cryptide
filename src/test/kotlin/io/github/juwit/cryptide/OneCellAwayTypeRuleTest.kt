package io.github.juwit.cryptide

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class OneCellAwayTypeRuleTest {

    @Test
    fun testAppliesToSelf(){
        val mountainCell = Cell(CellType.MOUNTAIN)

        val mountainPlus1 = OneCellAwayTypeRule(CellType.MOUNTAIN)

        Assertions.assertThat(mountainPlus1.appliesTo(mountainCell)).isTrue
    }

    @Test
    fun testDoesNotAppliesToSelf(){
        val mountainCell = Cell(CellType.MOUNTAIN)

        val mountainOrDesert = OneCellAwayTypeRule(CellType.SWAMP)

        Assertions.assertThat(mountainOrDesert.appliesTo(mountainCell)).isFalse
    }

    @Test
    fun testAppliesToOneNeighbor(){
        val mountainCell = Cell(CellType.MOUNTAIN)
        mountainCell.neighbors = listOf(Cell(CellType.DESERT))

        val desertPlus1 = OneCellAwayTypeRule(CellType.DESERT)

        Assertions.assertThat(desertPlus1.appliesTo(mountainCell)).isTrue
    }

    @Test
    fun testAppliesToAnyNeighbor(){
        val mountainCell = Cell(CellType.MOUNTAIN)
        mountainCell.neighbors = listOf(
            Cell(CellType.SWAMP),
            Cell(CellType.DESERT),
            Cell(CellType.LAKE)
        )

        val desertPlus1 = OneCellAwayTypeRule(CellType.DESERT)

        Assertions.assertThat(desertPlus1.appliesTo(mountainCell)).isTrue
    }

    @Test
    fun testDoesNotAppliesToFarAwayNeighbor(){
        val mountainCell = Cell(CellType.MOUNTAIN)

        val swampNeighbor = Cell(CellType.SWAMP)
        swampNeighbor.neighbors = listOf(Cell(CellType.DESERT))

        mountainCell.neighbors = listOf(swampNeighbor)

        val desertPlus1 = OneCellAwayTypeRule(CellType.DESERT)

        Assertions.assertThat(desertPlus1.appliesTo(mountainCell)).isFalse
    }

}