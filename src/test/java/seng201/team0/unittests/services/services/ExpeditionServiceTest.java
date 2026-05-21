package seng201.team0.unittests.services.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Adventurer;
import seng201.team0.services.AdventurerCreationService;
import seng201.team0.services.ExpeditionService;

import javafx.scene.control.TextArea;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ExpeditionServiceTest {

    private GameEnvironment gameEnvironment;
    private ExpeditionService expeditionService;
    private TextArea textArea;

    private Adventurer adventurer1;

    private Adventurer adventurer2;

    @BeforeEach
    public void setup() {

        gameEnvironment = new GameEnvironment(null);

        adventurer1 = new Adventurer("Name", 10, 100, 100,
                1,10,1);
        adventurer2 = new Adventurer("Name2", 10, 100, 0,
                1,10,1);

        ArrayList<Adventurer> mainParty = new ArrayList<>();
        mainParty.add(adventurer1);
        mainParty.add(adventurer2);

        gameEnvironment.onSetupComplete(
                mainParty,
                "Normal",
                "Guild",
                5
        );

        expeditionService = new ExpeditionService(gameEnvironment, null, 0);
        adventurer1.setHealth(100);
        adventurer1.setStamina(100);
        adventurer2.setHealth(100);
        adventurer2.setStamina(100);
    }

    @Test
    public void testExpeditionRemovesStaminaAdventurer() {
        adventurer1.setStamina(0);
        expeditionService.button1Clicked();

        assertEquals(1, gameEnvironment.getMainParty().size());
        assertFalse(gameEnvironment.getMainParty().contains(adventurer1));
        assertTrue(gameEnvironment.getMainParty().contains(adventurer2));
    }

    @Test
    public void testExpeditionRemovesHealthAdventurer() {
        adventurer1.setHealth(0);
        expeditionService.button1Clicked();

        assertEquals(1, gameEnvironment.getMainParty().size());
        assertFalse(gameEnvironment.getMainParty().contains(adventurer1));
        assertTrue(gameEnvironment.getMainParty().contains(adventurer2));
    }

    @Test
    public void testAllAdventurersRemovedEndsExpedition() {
        adventurer1.setHealth(0);
        adventurer2.setHealth(0);

        // call vitals check
        expeditionService.button1Clicked();
        assertTrue(gameEnvironment.getMainParty().isEmpty());
    }

}
