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

        ArrayList<Adventurer> mainParty = new ArrayList<>();

        adventurer1 = AdventurerCreationService.createRandomAdventurer();
        adventurer2 = AdventurerCreationService.createRandomAdventurer();

        mainParty.add(adventurer1);
        mainParty.add(adventurer2);

        gameEnvironment.onSetupComplete(
                mainParty,
                "Normal",
                "Guild",
                5
        );

        expeditionService = new ExpeditionService(gameEnvironment, null, 0);
    }

        @Test
    public void testExpeditionRemovesDeadAdventurer() {

        adventurer1.setHealth(0);

        expeditionService.button1Clicked();

        assertEquals(1, gameEnvironment.getMainParty().size());
        assertFalse(gameEnvironment.getMainParty().contains(adventurer1));
        assertTrue(gameEnvironment.getMainParty().contains(adventurer2));
    }


}
