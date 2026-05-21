package seng201.team0.unittests.services.models;

import org.junit.jupiter.api.Test;
import seng201.team0.models.Adventurer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdventurerTest {
    @Test
    public void testAdventurerConstructorSetsValuesCorrectly() {
        Adventurer adventurer = new Adventurer(
                "Maxwell", 1, 1, 1, 1, 1, 1);
        assertEquals("Maxwell", adventurer.getName());
        assertEquals(1, adventurer.getStamina());
        assertEquals(1, adventurer.getPerception());
        assertEquals(1, adventurer.getHiringCost());
        assertEquals(1, adventurer.getPay());
    }

    @Test
    public void testAdventurerIncreaseHealthAtMaxHealth() {
        Adventurer adventurer = new Adventurer(
                "Maxwell", 1, 1, 1, 1, 1, 1);
        adventurer.increaseHealth(1);
        assertEquals(adventurer.getMaxHealth(), adventurer.getHealth());
    }

    @Test
    public void testAdventurerDecreaseHealthBelowMinimum() {
        Adventurer adventurer = new Adventurer(
                "Maxwell", 1, 1, 1, 1, 1, 1);
        adventurer.takeHealthDamage(2);
        assertEquals(0, adventurer.getHealth());
    }

    @Test
    public void testAdventurerIncreaseStaminaAtMaxStamina() {
        Adventurer adventurer = new Adventurer(
                "Maxwell", 1, 1, 1, 1, 1, 1);
        adventurer.increaseStamina(1);
        assertEquals(adventurer.getMaxStamina(), adventurer.getStamina());
    }

    @Test
    public void testAdventurerDecreaseStaminaBelowMinimum() {
        Adventurer adventurer = new Adventurer(
                "Maxwell", 1, 1, 1, 1, 1, 1);
        adventurer.takeStaminaDamage(2);
        assertEquals(0, adventurer.getStamina());
    }

    @Test
    public void testToString() {
        Adventurer adventurer = new Adventurer(
                "Bob", 40, 80, 10, 8, 3, 1
        );
        assertEquals("Bob", adventurer.toString());
    }
}
