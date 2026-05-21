package seng201.team0.unittests.services.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Adventurer;
import seng201.team0.services.AdventurerCreationService;
import seng201.team0.services.GameOverService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameOverServiceTest {
    private GameEnvironment gameEnvironment;
    private GameOverService gameOverService;

    @BeforeEach
    public void setup() {

        gameEnvironment = new GameEnvironment(null);

        ArrayList<Adventurer> mainParty = new ArrayList<>();
        mainParty.add(AdventurerCreationService.createRandomAdventurer());
        gameEnvironment.onSetupComplete(
                mainParty,
                "Normal",
                "Guild",
                5
        );
        gameOverService = new GameOverService(gameEnvironment);
    }

    @Test
    public void testGetOutcomeStringSuccess() {
        gameEnvironment.setGold(10);
        String result = gameOverService.getOutcomeString();
        assertEquals("You completed all the expeditions!", result);
    }

    @Test
    public void testGetOutcomeStringFailure() {
        gameEnvironment.setGold(0);
        String result = gameOverService.getOutcomeString();
        assertEquals("Your guild failed to make money.", result);
    }

    @Test
    public void testIsGameOverEnoughMoney() {
        gameEnvironment.setGold(9999);
        boolean result = gameOverService.isGameOver();
        assertFalse(result);
    }

    @Test
    public void testIsGameOverTrueNoPartyMembers() {
        gameEnvironment.getMainParty().clear();
        gameEnvironment.getReserveParty().clear();
        boolean result = gameOverService.isGameOver();
        assertTrue(result);
    }

    @Test
    public void testIsGameOverCannotAffordAnyAdventurer() {
        gameEnvironment.setGold(0);
        // there are no free adventurers
        boolean result = gameOverService.isGameOver();
        assertTrue(result);
    }
}
