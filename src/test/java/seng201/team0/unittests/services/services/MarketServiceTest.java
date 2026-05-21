package seng201.team0.unittests.services.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Adventurer;
import seng201.team0.models.Item;
import seng201.team0.services.AdventurerCreationService;
import seng201.team0.services.MarketService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MarketServiceTest {
    private GameEnvironment gameEnvironment;
    private MarketService marketService;
    private Item testItem;

    @BeforeEach
    public void setup() {
        gameEnvironment = new GameEnvironment(null);

        ArrayList<Adventurer> party = new ArrayList<>();
        party.add(AdventurerCreationService.createRandomAdventurer());

        gameEnvironment.onSetupComplete(
                party,
                "Normal",
                "Guild",
                5
        );

        marketService = new MarketService(gameEnvironment);
        testItem = Item.getRandomItem();
        gameEnvironment.getMarketInventory().addItem(testItem);

    }

    @Test
    public void testBuyItemSuccess() {
        gameEnvironment.setGold(100);

        int cost = testItem.getCost();

        boolean result = marketService.buyItem(testItem, .8f);

        assertTrue(result);
        assertEquals(100 - cost, gameEnvironment.getGold());
        assertTrue(gameEnvironment.getPlayerInventory().getAllItems().contains(testItem));
        }

    @Test
    public void testBuyItemNotEnoughGold() {
        gameEnvironment.setGold(0);

        boolean result = marketService.buyItem(testItem, 0.8f);

        assertFalse(result);
        assertFalse(gameEnvironment.getPlayerInventory().getAllItems().contains(testItem));
        assertTrue(gameEnvironment.getMarketInventory().getAllItems().contains(testItem));
        assertEquals(0, gameEnvironment.getGold());
    }

    @Test
    public void testSellItemSuccess() {
        gameEnvironment.setGold(50);
        gameEnvironment.getPlayerInventory().addItem(testItem);

        int sellPrice = testItem.getCost();

        boolean result = marketService.sellItem(testItem, 0.8f);

        assertTrue(result);
        assertFalse(gameEnvironment.getPlayerInventory().getAllItems().contains(testItem));
        assertTrue(gameEnvironment.getMarketInventory().getAllItems().contains(testItem));
        assertEquals(50 + sellPrice, gameEnvironment.getGold());
    }
}
