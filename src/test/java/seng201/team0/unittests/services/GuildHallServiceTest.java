package seng201.team0.unittests.services;

import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.GameEnvironment;
import seng201.team0.gui.ScreenNavigator;
import seng201.team0.models.Adventurer;
import seng201.team0.services.AdventurerCreationService;
import seng201.team0.services.GuildHallService;

import static org.junit.jupiter.api.Assertions.*;

public class GuildHallServiceTest {
    private GameEnvironment gameEnvironment;
    private GuildHallService guildHallService;

    @BeforeEach
    public void setup() {
        // idk how to get the game environment to work
        gameEnvironment = new GameEnvironment(new ScreenNavigator(new Stage()));

        guildHallService = new GuildHallService(gameEnvironment);

        gameEnvironment.setGold(1000);
    }

    @Test
    public void testHireAdventurer(){
        Adventurer adventurer = AdventurerCreationService.createRandomAdventurer();
        int cost = adventurer.getHiringCost();

        boolean result = guildHallService.hireAdventurer(adventurer);
        assertTrue(result);
        assertTrue(gameEnvironment.getReserveParty().contains(adventurer));
        assertEquals(1000-cost, gameEnvironment.getGold());
    }

    @Test
    public void testHireAdventurerNotEnoughGold() {
        Adventurer adventurer = new Adventurer("a", 1, 1, 1,
                100000, 1, 1);

        boolean result = guildHallService.hireAdventurer(adventurer);
        assertFalse(result);
        assertEquals(1000, gameEnvironment.getGold());
    }

    @Test
    public void testHireReserveFullMainAvailable() {
        for (int i = 0; i < 5; i++) {
            gameEnvironment.getReserveParty().add(AdventurerCreationService.createRandomAdventurer());
        }
        Adventurer adventurer = AdventurerCreationService.createRandomAdventurer();
        boolean result = guildHallService.hireAdventurer(adventurer);
        assertTrue(result);

        int cost = adventurer.getHiringCost();
        assertTrue(gameEnvironment.getMainParty().contains(adventurer));
        assertEquals(1000-cost, gameEnvironment.getGold());
    }

    @Test
    public void testHireAllPartiesFull(){
        // Fill reserve party
        for (int i = 0; i < 5; i++) {
            gameEnvironment.getReserveParty().add(AdventurerCreationService.createRandomAdventurer());
        }
        // Fill main party
        for (int i = 0; i < gameEnvironment.MAX_PARTY_SIZE; i++) {
            gameEnvironment.getMainParty().add(AdventurerCreationService.createRandomAdventurer());
        }

        Adventurer adventurer = AdventurerCreationService.createRandomAdventurer();
        boolean result = guildHallService.hireAdventurer(adventurer);
        assertFalse(result);

        assertFalse(gameEnvironment.getMainParty().contains(adventurer));
        assertFalse(gameEnvironment.getReserveParty().contains(adventurer));
        assertEquals(1000, gameEnvironment.getGold());
    }
}

