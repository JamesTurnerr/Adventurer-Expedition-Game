package seng201.team0.unittests.services.services;

import javafx.scene.control.Label;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.GameEnvironment;
import seng201.team0.services.AdventurerCreationService;
import seng201.team0.services.SetupService;
import seng201.team0.models.Adventurer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SetupServiceTest {
    private SetupService testSetupService;
    List<Adventurer> testAventurerList = new ArrayList<Adventurer>();
    GameEnvironment gameEnvironment;
    private Adventurer testAdventurer;

    @BeforeEach
    public void setupTest() {
        gameEnvironment = new GameEnvironment(null);
        testAdventurer = AdventurerCreationService.createRandomAdventurer();

        ArrayList<Adventurer> party = new ArrayList<>();
        party.add(testAdventurer);

        gameEnvironment.onSetupComplete(
                party,
                "Normal",
                "Guild",
                5);
        testSetupService = new SetupService(null);
        testAventurerList.add(new Adventurer("AAA", 100, 100, 100, 100, 100, 100));
        testAventurerList.add(new Adventurer("BBB", 100, 100, 100, 100, 100, 100));
        testAventurerList.add(new Adventurer("CCC", 100, 100, 100, 100, 100, 100));
    }

    /**
     * Test going through the setup with expected parameters
     */
    @Test
    void testExpectedSetupParameters() {
        assertTrue(testSetupService.checkInputs("AAA", "Easy", testAventurerList, 5).isEmpty());
    }
    /**
     * Test going through the setup with an empty guild name
     */
    @Test
    void testEmptyGuildName() {
        assertFalse(testSetupService.checkInputs("", "Easy", testAventurerList, 5).isEmpty());
    }
    /**
     * Test going through the setup with a guild name length less than 3
     */
    @Test
    void testShortGuildName() {
        assertFalse(testSetupService.checkInputs("AA", "Easy", testAventurerList, 5).isEmpty());
    }
    /**
     * Test going through the setup with a guild name length greater than 15
     */
    @Test
    void testLongGuildName() {
        assertFalse(testSetupService.checkInputs("ReallyLongGuildName", "Easy", testAventurerList, 5).isEmpty());
    }
    /**
     * Test going through the setup with an invalid guild name
     */
    @Test
    void testInvalidGuildName() {
        assertFalse(testSetupService.checkInputs("!@#$%^&*", "Easy", testAventurerList, 5).isEmpty());
    }
    /**
     * Test going through the setup with an invalid difficulty
     */
    @Test
    void testInvalidDifficulty() {
        assertFalse(testSetupService.checkInputs("AAA", "Difficulty", testAventurerList, 5).isEmpty());
    }
    /**
     * Test going through the setup with an invalid number of adventurers
     */
    @Test
    void testInvalidNumAdventurers() {
        Adventurer newAdventurer = new Adventurer("DDD", 100, 100, 100, 100, 100, 100);
        testAventurerList.add(newAdventurer);
        assertTrue(testSetupService.checkInputs("AAA", "Easy", testAventurerList, 5).isEmpty());
        testAventurerList.remove(newAdventurer);
        Adventurer oldAdventurer = testAventurerList.removeLast();
        assertTrue(testSetupService.checkInputs("AAA", "Easy", testAventurerList, 5).isEmpty());
        testAventurerList.add(oldAdventurer);
    }

    @Test
    void testEmptyAdventurerList() {
        testAventurerList.clear();
        assertTrue(testSetupService.checkInputs("Guild", "Easy", testAventurerList, 5).isEmpty());
    }
    @Test
    void testNullAdventurerList() {
        assertThrows(NullPointerException.class, () -> {
            testSetupService.checkInputs("Guild", "Easy", null, 5);
        });
    }
}
