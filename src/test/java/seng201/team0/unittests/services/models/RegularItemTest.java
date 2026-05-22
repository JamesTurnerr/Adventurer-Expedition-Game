package seng201.team0.unittests.services.models;

import org.junit.jupiter.api.Test;
import seng201.team0.models.RegularItem;

import static org.junit.jupiter.api.Assertions.*;

public class RegularItemTest {
    @Test
    public void testToStringReturnsTheName() {
        assertEquals("Medium Health", RegularItem.HEALTH_POTION.toString());
        assertEquals("Rusty Sword", RegularItem.RUSTY_SWORD.toString());
    }
    @Test
    public void testGetDescription() {
        assertEquals("Moderately restores stamina", RegularItem.STAMINA_POTION.getDescription());
        assertEquals("Pretty useless", RegularItem.RUSTY_SWORD.getDescription());
    }
    @Test
    public void testIncreaseCost() {
        RegularItem regularItem = RegularItem.HEALTH_POTION;
        int original = regularItem.getCost();
        regularItem.increaseCost(5);
        assertEquals(original + 5, regularItem.getCost());
    }

    @Test
    public void testLowerCost() {
        RegularItem regularItem = RegularItem.HEALTH_POTION;
        int original = regularItem.getCost();
        regularItem.decreaseCost(2);
        assertEquals(original - 2, regularItem.getCost());
    }

    @Test
    public void testGetRandomItemReturnsValidEnum() {
        RegularItem regularItem = RegularItem.getRandomItem();
        assertNotNull(regularItem);
    }

    @Test
    public void testGetAdjustedModifierNeverNegative() {
        int result = RegularItem.HEALTH_POTION.getAdjustedModifier(50, 100);

        assertTrue(result <= 10);
        assertTrue(result >= 0);
    }

    @Test
    public void testGetAdjustedModifierCapsAtMaxDifference() {
        // health potion gives 10 health
        int result = RegularItem.HEALTH_POTION.getAdjustedModifier(100, 95);
        assertEquals(5, result);
    }
}
