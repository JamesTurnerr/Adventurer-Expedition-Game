package seng201.team0.unittests.services.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Adventurer;
import seng201.team0.models.Item;
import seng201.team0.services.AdventurerCreationService;
import seng201.team0.services.GuildOverviewService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GuildOverviewServiceTest {
    private GameEnvironment gameEnvironment;
    private GuildOverviewService guildOverviewService;

    private Adventurer mainAdventurer;
    private Adventurer reserveAdventurer;

    @BeforeEach
    public void setup() {
        gameEnvironment = new GameEnvironment(null);
        ArrayList<Adventurer> party = new ArrayList<>();
        mainAdventurer = AdventurerCreationService.createRandomAdventurer();
        reserveAdventurer = AdventurerCreationService.createRandomAdventurer();
        party.add(mainAdventurer);
        gameEnvironment.onSetupComplete(party, "Normal", "Guild", 5);
        gameEnvironment.getReserveParty().add(reserveAdventurer);
        guildOverviewService = new GuildOverviewService(gameEnvironment);
    }

    @Test
    public void testUseHealthPotion() {

        mainAdventurer.takeHealthDamage(20);
        int before = mainAdventurer.getHealth();
        gameEnvironment.getPlayerInventory().addItem(Item.HEALTH_POTION);

        guildOverviewService.useItem(mainAdventurer, Item.HEALTH_POTION);

        assertTrue(mainAdventurer.getHealth() > before);
        assertFalse(gameEnvironment.getPlayerInventory().getAllItems().contains(Item.HEALTH_POTION));
    }

    @Test
    public void testUseStaminaPotion() {
        mainAdventurer.takeStaminaDamage(20);
        int before = mainAdventurer.getStamina();
        gameEnvironment.getPlayerInventory().addItem(Item.STAMINA_POTION);

        guildOverviewService.useItem(mainAdventurer, Item.STAMINA_POTION);

        assertTrue(mainAdventurer.getStamina() > before);
        assertFalse(gameEnvironment.getPlayerInventory().getAllItems().contains(Item.STAMINA_POTION));
    }

    @Test
    public void testUseItemNullInputs() {
        guildOverviewService.useItem(null, null);
        // if no exceptions, will return true
        assertTrue(true);
    }

    @Test
    public void testMoveAdventurerToMainSuccess() {
        boolean result = guildOverviewService.moveAdventurerToMain(reserveAdventurer);
        assertTrue(result);
        assertTrue(gameEnvironment.getMainParty().contains(reserveAdventurer));
        assertFalse(gameEnvironment.getReserveParty().contains(reserveAdventurer));
    }

    @Test
    public void testMoveAdventurerToMainFailsWhenNull() {
        boolean result = guildOverviewService.moveAdventurerToMain(null);
        assertFalse(result);
    }

    @Test
    public void testMoveAdventurerToReserveSuccess() {
        Adventurer secondMain = AdventurerCreationService.createRandomAdventurer();
        gameEnvironment.getMainParty().add(secondMain);
        boolean result = guildOverviewService.moveAdventurerToReserve(secondMain);
        assertTrue(result);
        assertFalse(gameEnvironment.getMainParty().contains(secondMain));
        assertTrue(gameEnvironment.getReserveParty().contains(secondMain));
    }

    @Test
    public void testMoveAdventurerToReserveFailsWhenOnlyOneMember() {
        boolean result = guildOverviewService.moveAdventurerToReserve(mainAdventurer);
        assertFalse(result);
        assertTrue(gameEnvironment.getMainParty().contains(mainAdventurer));
    }
    @Test
    public void testMoveAdventurerToMainWhenFull() {
        while (gameEnvironment.getMainParty().size() < gameEnvironment.MAX_PARTY_SIZE) {
            gameEnvironment.getMainParty().add(AdventurerCreationService.createRandomAdventurer());
        }

        boolean result = guildOverviewService.moveAdventurerToMain(reserveAdventurer);
        assertFalse(result);
        assertTrue(gameEnvironment.getReserveParty().contains(reserveAdventurer));
    }

    @Test
    public void testMoveAdventurerToReserveWhenReserveFull() {
        // Fill reserve party
        while (gameEnvironment.getReserveParty().size() < 5) {
            gameEnvironment.getReserveParty().add(AdventurerCreationService.createRandomAdventurer());
        }
        gameEnvironment.getMainParty().clear();

        Adventurer secondMain = AdventurerCreationService.createRandomAdventurer();
        gameEnvironment.getMainParty().add(secondMain);

        boolean result = guildOverviewService.moveAdventurerToReserve(secondMain);
        assertFalse(result);
        assertTrue(gameEnvironment.getMainParty().contains(secondMain));
    }

    @Test
    public void testUseItemWithNullAdventurer() {
        gameEnvironment.getPlayerInventory().addItem(Item.HEALTH_POTION);
        guildOverviewService.useItem(null, Item.HEALTH_POTION);
        assertTrue(gameEnvironment.getPlayerInventory().getAllItems().contains(Item.HEALTH_POTION));
    }
}
