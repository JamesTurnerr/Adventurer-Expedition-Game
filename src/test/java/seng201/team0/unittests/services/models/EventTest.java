package seng201.team0.unittests.services.models;

import org.junit.jupiter.api.Test;
import seng201.team0.models.Choice;
import seng201.team0.models.Event;
import seng201.team0.models.EventOutcome;
import seng201.team0.models.Expedition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EventTest {
    private final Expedition expedition = new Expedition();

    // uses existing events.
    @Test
    public void testGetEventDescription() {
        Event event = expedition.areaEvents[0][0];

        assertEquals("A section of the tunnel collapses ahead.", event.getEventDescription());
    }

    @Test
    public void testGetChoices() {
        Event event = expedition.areaEvents[0][0];

        Choice[] choices = event.getChoices();

        assertNotNull(choices);
        assertEquals(3, choices.length);
    }

    @Test
    public void testChoicesLength() {
        Event event = expedition.areaEvents[0][1];

        assertEquals(3, event.getChoices().length);
    }
}
