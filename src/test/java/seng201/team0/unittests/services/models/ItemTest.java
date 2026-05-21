package seng201.team0.unittests.services.models;

import org.junit.jupiter.api.Test;
import seng201.team0.models.Item;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    @Test
    public void testToStringReturnsTheName() {
        assertEquals("Health Potion", Item.HEALTH_POTION.toString());
        assertEquals("Rusty Sword", Item.RUSTY_SWORD.toString());
    }
    @Test
    public void testGetDescription() {
        assertEquals("Moderately restores stamina", Item.STAMINA_POTION.getDescription());
        assertEquals("Pretty useless", Item.RUSTY_SWORD.getDescription());
    }
    @Test
    public void testIncreaseCost() {
        Item item = Item.HEALTH_POTION;
        int original = item.getCost();
        item.increaseCost(5);
        assertEquals(original + 5, item.getCost());
    }

    @Test
    public void testLowerCost() {
        Item item = Item.HEALTH_POTION;
        int original = item.getCost();
        item.decreaseCost(2);
        assertEquals(original - 2, item.getCost());
    }

    @Test
    public void testGetRandomItemReturnsValidEnum() {
        Item item = Item.getRandomItem();
        assertNotNull(item);
    }

    @Test
    public void testGetAdjustedModifierNeverNegative() {
        int result = Item.HEALTH_POTION.getAdjustedModifier(50, 100);

        assertTrue(result <= 10);
        assertTrue(result >= 0);
    }

    @Test
    public void testGetAdjustedModifierCapsAtMaxDifference() {
        // health potion gives 10 health
        int result = Item.HEALTH_POTION.getAdjustedModifier(100, 95);
        assertEquals(5, result);
    }
}
