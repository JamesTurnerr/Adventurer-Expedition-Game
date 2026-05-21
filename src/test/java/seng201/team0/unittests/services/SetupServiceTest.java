package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.services.SetupService;
import seng201.team0.models.Adventurer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SetupServiceTest {
    private SetupService testSetupService;
    List<Adventurer> testAventurerList = new ArrayList<Adventurer>();

    @BeforeEach
    public void setupTest() {
        testSetupService = new SetupService();
        testAventurerList.add(new Adventurer("AAA", 100, 100, 100, 100, 100, 100));
        testAventurerList.add(new Adventurer("BBB", 100, 100, 100, 100, 100, 100));
        testAventurerList.add(new Adventurer("CCC", 100, 100, 100, 100, 100, 100));
    }

    /**
     * Test going through the setup with expected parameters
     */
    @Test
    void testExpectedSetupParameters() {
        assertTrue(testSetupService.checkInputs("AAA", "Easy", testAventurerList));
    }
    /**
     * Test going through the setup with an empty guild name
     */
    @Test
    void testEmptyGuildName() {
        assertFalse(testSetupService.checkInputs("", "Easy", testAventurerList));
    }
    /**
     * Test going through the setup with an invalid guild name
     */
    @Test
    void testInvalidGuildName() {
        assertFalse(testSetupService.checkInputs("!@#$%^&*", "Easy", testAventurerList));
    }
    /**
     * Test going through the setup with an invalid difficulty
     */
    @Test
    void testInvalidDifficulty() {
        assertFalse(testSetupService.checkInputs("AAA", "Difficulty", testAventurerList));
    }
    /**
     * Test going through the setup with an invalid number of adventurers
     */
    @Test
    void testInvalidNumAdventurers() {
        Adventurer newAdventurer = new Adventurer("DDD", 100, 100, 100, 100, 100, 100);
        testAventurerList.add(newAdventurer);
        assertFalse(testSetupService.checkInputs("AAA", "Easy", testAventurerList));
        testAventurerList.remove(newAdventurer);
        Adventurer oldAdventurer = testAventurerList.removeLast();
        assertFalse(testSetupService.checkInputs("AAA", "Easy", testAventurerList));
        testAventurerList.add(oldAdventurer);
    }
}
