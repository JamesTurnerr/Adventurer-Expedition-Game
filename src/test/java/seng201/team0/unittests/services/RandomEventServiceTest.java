package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Adventurer;
import seng201.team0.services.AdventurerCreationService;
import seng201.team0.services.RandomEventService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RandomEventServiceTest {
    private GameEnvironment gameEnvironment;
    private RandomEventService service;
    private Adventurer testAdventurer;

    @BeforeEach
    public void setup() {
        gameEnvironment = new GameEnvironment(null);

        testAdventurer = AdventurerCreationService.createRandomAdventurer();

        ArrayList<Adventurer> party = new ArrayList<>();
        party.add(testAdventurer);

        gameEnvironment.onSetupComplete(
                party,
                "Normal",
                "Guild",
                5
        );

        service = new RandomEventService(gameEnvironment);
    }

    @Test
    public void testGenerateRandomEventReturnsString() {
        String result = service.generateRandomEvent();

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    // need to add some tests for different event outcomes

}
