package seng201.team0.unittests.services.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Adventurer;
import seng201.team0.services.AdventurerCreationService;
import seng201.team0.services.ExpeditionOverService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ExpeditionOverServiceTest {
    private GameEnvironment gameEnvironment;
    private ExpeditionOverService service;
    private Adventurer adventurer;

    @BeforeEach
    public void setup() {
        gameEnvironment = new GameEnvironment(null);

        adventurer = AdventurerCreationService.createRandomAdventurer();

        ArrayList<Adventurer> mainParty = new ArrayList<>();
        mainParty.add(adventurer);

        ArrayList<Adventurer> reserveParty = new ArrayList<>();

        gameEnvironment.onSetupComplete(
                mainParty,
                "Normal",
                "Guild",
                5
        );

        gameEnvironment.getReserveParty().addAll(reserveParty);

        service = new ExpeditionOverService(gameEnvironment);
    }

    @Test
    public void testCompleteExpeditionRunsWithoutError() {
        assertDoesNotThrow(() -> service.completeExpedition());
    }

    @Test
    public void testExpeditionCountersChange() {
        int beforeCompleted = gameEnvironment.getExpeditionsCompleted();
        int beforeRemaining = gameEnvironment.getExpeditionsRemaining();

        service.completeExpedition();

        assertEquals(beforeCompleted + 1, gameEnvironment.getExpeditionsCompleted());
        assertEquals(beforeRemaining - 1, gameEnvironment.getExpeditionsRemaining());
    }

    @Test
    public void testTotalGoldIncreasesOrStays() {
        int before = gameEnvironment.getTotalGold();
        service.completeExpedition();
        assertTrue(gameEnvironment.getTotalGold() >= before);
    }

    @Test
    public void testMainPartyExpeditionsInARowIncrease() {
        int before = adventurer.getExpeditionsInARow();
        service.completeExpedition();

        assertEquals(adventurer.getExpeditionsInARow(), before + 1);
    }

    @Test
    public void testReservePartyStaminaIncreasesIfExists() {
        for (Adventurer adventurer : gameEnvironment.getReserveParty()){
            int before = adventurer.getStamina();
            service.completeExpedition();
            assertTrue(adventurer.getStamina() >= before);
        }
    }

    @Test
    public void testExpeditionsNeverNegative() {
        service.completeExpedition();
        assertTrue(gameEnvironment.getExpeditionsRemaining() >= 0);
    }

}
