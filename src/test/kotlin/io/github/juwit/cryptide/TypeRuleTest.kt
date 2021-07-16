package io.github.juwit.cryptide

import org.assertj.core.api.Assertions.assertThat

import org.junit.jupiter.api.Test

internal class TypeRuleTest {

    @Test
    fun testAppliesToFirstType(){
        val mountainCell = Cell(CellType.MOUNTAIN)

        val mountainOrDesert = TypeRule(CellType.MOUNTAIN to CellType.DESERT)

        assertThat(mountainOrDesert.appliesTo(mountainCell)).isTrue
    }

    @Test
    fun testAppliesToSecondType(){
        val mountainCell = Cell(CellType.MOUNTAIN)

        val mountainOrDesert = TypeRule(CellType.SWAMP to CellType.MOUNTAIN)

        assertThat(mountainOrDesert.appliesTo(mountainCell)).isTrue
    }

    @Test
    fun testDoesNotApplies(){
        val mountainCell = Cell(CellType.MOUNTAIN)

        val mountainOrDesert = TypeRule(CellType.SWAMP to CellType.FOREST)

        assertThat(mountainOrDesert.appliesTo(mountainCell)).isFalse
    }

}