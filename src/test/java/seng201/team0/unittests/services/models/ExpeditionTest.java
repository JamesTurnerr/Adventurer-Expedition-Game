package seng201.team0.unittests.services.models;

import org.junit.jupiter.api.Test;
import seng201.team0.models.Choice;
import seng201.team0.models.EventOutcome;
import seng201.team0.models.Expedition;

import static org.junit.jupiter.api.Assertions.*;

public class ExpeditionTest {
    private int locationsImplemented = 5;
    private int eventsPerLocation = 8;

    @Test
    public void testGetAreaNameValidIndex() {
        Expedition expedition = new Expedition();
        assertEquals("Abandoned Mine", expedition.getAreaName(0));
        assertEquals("Burning Wasteland", expedition.getAreaName(6));
        assertEquals("Overgrown Jungle", expedition.getAreaName(7));
    }

    @Test
    public void testGetAreaDescriptionValidIndex() {
        Expedition expedition = new Expedition();
        String desc = expedition.getAreaDescription(0);

        assertNotNull(desc);
        assertTrue(desc.contains("Collapsing underground tunnels"));
        // make sure description isn't null or blank
        assertNotNull(desc);
        assertFalse(desc.isBlank());
    }

    @Test
    public void testAreaEventsExistAndAreCorrectSize() {
        Expedition expedition = new Expedition();

        assertEquals(locationsImplemented, expedition.areaEvents.length);
        assertEquals(eventsPerLocation, expedition.areaEvents[0].length);
    }

    @Test
    public void testEventHasThreeChoices() {
        Expedition expedition = new Expedition();
        Choice[] choices = expedition.areaEvents[0][0].getChoices();
        assertEquals(3, choices.length);
    }

    @Test
    public void testChoiceHasValidOutcomes() {
        Expedition expedition = new Expedition();
        Choice choice = expedition.areaEvents[0][0].getChoices()[0];
        EventOutcome[] outcomes = choice.getEventOutcomes();

        assertNotNull(outcomes);
        assertTrue(outcomes.length > 0);
    }

    @Test
    public void testChoiceDescriptionsContainSomething() {
        Expedition expedition = new Expedition();
        Choice choice = expedition.areaEvents[1][2].getChoices()[1];

        assertNotNull(choice.getChoice());
        assertFalse(choice.getChoice().isBlank());
    }

    @Test
    public void testExpeditionIndexIsStoredCorrectly() {
        Expedition expedition = new Expedition(0);
        assertDoesNotThrow(() -> new Expedition(0));
    }

    @Test
    public void testInvalidAreaIndex() {
        Expedition expedition = new Expedition();
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> {expedition.getAreaName(999);
        });
    }

    @Test
    public void testAllLocationsHaveEightEvents() {
        Expedition expedition = new Expedition();
        for (int i = 0; i < expedition.areaEvents.length; i++) {
            assertEquals(8, expedition.areaEvents[i].length);
        }
    }
}
