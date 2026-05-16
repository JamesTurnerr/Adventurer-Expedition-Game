package seng201.team0.services;

import seng201.team0.GameEnvironment;
import seng201.team0.models.Inventory;
import seng201.team0.models.Item;

import java.util.ArrayList;
import java.util.List;

public class MarketService {
    private final GameEnvironment gameEnvironment;
    public MarketService(GameEnvironment gameEnvironment) {this.gameEnvironment = gameEnvironment;}

    /**
     * Attempt to buy an item form the market
     * @param item The item to be bought
     * @return If the buying was successful or not
     */
    public boolean buyItem(Item item)
    {
        if (item != null)
        {
            if (item.getCost() <= gameEnvironment.getGold())
            {
                gameEnvironment.getPlayerInventory().addItem(item);
                gameEnvironment.setGold(gameEnvironment.getGold()-item.getCost());
                gameEnvironment.getMarketInventory().removeItem(item);
                System.out.println(String.format("Item bought, %d gold remaining", gameEnvironment.getGold()));
                return true;
            }
            else {
                System.out.println("Warning: Not enough gold, could not buy item");
                return false;
            }
        }
        else
        {
            System.out.println("Warning: Item is null");
            return false;
        }
    }
}
