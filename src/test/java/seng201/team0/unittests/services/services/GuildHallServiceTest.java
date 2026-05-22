package seng201.team0.unittests.services.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Adventurer;
import seng201.team0.services.AdventurerCreationService;
import seng201.team0.services.GuildHallService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GuildHallServiceTest {
    private GameEnvironment gameEnvironment;
    private GuildHallService guildHallService;
    private Adventurer testAdventurer;

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
        guildHallService = new GuildHallService(gameEnvironment);
        testAdventurer = AdventurerCreationService.createRandomAdventurer();
    }

    @Test
    public void testHireAdventurerSuccess() {
        gameEnvironment.setGold(1000);
        int cost = testAdventurer.getHiringCost();
        String result = guildHallService.hireAdventurer(testAdventurer);
        assertNull(result);
        assertTrue(gameEnvironment.getReserveParty().contains(testAdventurer));
        assertEquals(1000 - cost, gameEnvironment.getGold());
    }

    @Test
    public void testHireAdventurerNotEnoughGold() {
        gameEnvironment.setGold(0);
        String result = guildHallService.hireAdventurer(testAdventurer);
        assertEquals("Not enough money", result);
        assertFalse(gameEnvironment.getReserveParty().contains(testAdventurer));
    }
    @Test
    public void testHireAdventurerToMainPartyWhenReserveFull() {
        gameEnvironment.setGold(1000);

        // fill reserve party
        for (int i = 0; i < 5; i++) {
            gameEnvironment.getReserveParty().add(AdventurerCreationService.createRandomAdventurer());
        }
        int mainPartyBefore = gameEnvironment.getMainParty().size();
        String result = guildHallService.hireAdventurer(testAdventurer);
        assertNull(result);
        assertEquals(mainPartyBefore + 1, gameEnvironment.getMainParty().size());
        assertTrue(gameEnvironment.getMainParty().contains(testAdventurer));
    }

    @Test
    public void testHireAdventurerFailsWhenAllPartiesFull() {
        gameEnvironment.setGold(1000);

        // fill reserve party
        for (int i = 0; i < 5; i++) {
            gameEnvironment.getReserveParty().add(AdventurerCreationService.createRandomAdventurer());
        }

        // fill main party
        while (gameEnvironment.getMainParty().size() < gameEnvironment.MAX_PARTY_SIZE) {
            gameEnvironment.getMainParty().add(AdventurerCreationService.createRandomAdventurer());
        }

        String result = guildHallService.hireAdventurer(testAdventurer);
        assertEquals("Main and reserve party full", result);
        assertFalse(gameEnvironment.getMainParty().contains(testAdventurer));
        assertFalse(gameEnvironment.getReserveParty().contains(testAdventurer));
    }

}
