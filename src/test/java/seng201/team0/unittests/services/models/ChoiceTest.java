package seng201.team0.unittests.services.models;

import org.junit.jupiter.api.Test;
import seng201.team0.models.Choice;
import seng201.team0.models.EventOutcome;
import seng201.team0.models.Expedition;

import static org.junit.jupiter.api.Assertions.*;

public class ChoiceTest {
    private final Expedition expedition = new Expedition();

    // this uses the existing events choices
    @Test
    public void testGetChoice() {
        Choice choice = expedition.areaEvents[0][0].getChoices()[0];

        assertNotNull(choice.getChoice());
    }

    @Test
    public void testGetChoiceOutcomeNotEmpty() {
        Choice choice = expedition.areaEvents[0][0].getChoices()[0];

        assertNotNull(choice.getChoiceOutcome());
        assertFalse(choice.getChoiceOutcome().isEmpty());
    }

    @Test
    public void testGetEventOutcomes() {
        Choice choice = expedition.areaEvents[0][0].getChoices()[0];

        assertNotNull(choice.getEventOutcomes());
        assertTrue(choice.getEventOutcomes().length > 0);
    }

    @Test
    public void testChoiceOutcomeConsistency() {
        Choice choice = expedition.areaEvents[0][0].getChoices()[0];

        String outcome = choice.getChoiceOutcome();

        // sanity check: outcome should contain at least one event outcome string
        boolean hasValidText = false;
        for (var eo : choice.getEventOutcomes()) {
            if (outcome.contains(eo.getOutcome())) {
                hasValidText = true;
                break;
            }
        }

        assertTrue(hasValidText);
    }
}