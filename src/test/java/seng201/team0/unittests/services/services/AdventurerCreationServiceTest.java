package seng201.team0.unittests.services.services;

import org.junit.jupiter.api.Test;
import seng201.team0.models.Adventurer;
import seng201.team0.services.AdventurerCreationService;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class AdventurerCreationServiceTest {

    @Test
    void createAdventurerWithSpecificStats(){
        Adventurer adventurer = AdventurerCreationService.createAdventurer(
                80, 120, 50, 25, 15, 10);

        assertNotNull(adventurer);
        assertNotNull(adventurer.getName());

        // checks if all stats are right
        assertEquals(80, adventurer.getStamina());
        assertEquals(120, adventurer.getHealth());
        assertEquals(50, adventurer.getPerception());
        assertEquals(25, adventurer.getHiringCost());
        assertEquals(15, adventurer.getPay());
        assertEquals(10, adventurer.getDamage());
    }

    @Test
    void createRandomAdventurer(){
        Adventurer adventurer = AdventurerCreationService.createRandomAdventurer();

        assertNotNull(adventurer);
        assertNotNull(adventurer.getName());

        int averageStat = AdventurerCreationService.getStatValue();
        int upperLimit = averageStat + averageStat/3;
        int lowerLimit = averageStat - averageStat/3;

        assertTrue(adventurer.getHealth() >= lowerLimit && adventurer.getHealth() <= upperLimit);
        assertTrue(adventurer.getStamina() >= lowerLimit && adventurer.getStamina() <= upperLimit);
        assertTrue(adventurer.getPerception() >= lowerLimit && adventurer.getPerception() <= upperLimit);
    }

    @Test
    void uniqueNamesUsedUp() {
        Set<String> generatedNames = new HashSet<>();
    }
}
