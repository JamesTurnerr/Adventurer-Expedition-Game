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
     * Attempt to buy an item from the market
     * @param item The item to be bought
     * @param taxModifier multiply the value of the item by a value less than 1
     * @return If the buying was successful or not
     */
    public boolean buyItem(Item item, float taxModifier)
    {
        if (item != null)
        {
            if (item.getCost() <= gameEnvironment.getGold())
            {
                gameEnvironment.getPlayerInventory().addItem(item);
                gameEnvironment.setGold(gameEnvironment.getGold()-item.getCost());
                item.decreaseCost(item.getCost() - (int)(item.getCost() * taxModifier));
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
            System.out.println("Warning: Null item could not be bought");
            return false;
        }
    }

    /**
     * Attempt to sell an item to the market
     * @param item The item to be sold
     * @param taxModifier multiply the value of the item by a value less than 1
     * @return If the buying was successful or not
     */
    public boolean sellItem(Item item, float taxModifier)
    {
        if (item != null)
        {
            gameEnvironment.getPlayerInventory().removeItem(item);
            gameEnvironment.setGold(gameEnvironment.getGold() + item.getCost());
            gameEnvironment.addTotalGold(item.getCost());
            item.increaseCost(item.getCost() - (int)(item.getCost() * taxModifier));
            gameEnvironment.getMarketInventory().addItem(item);
            System.out.println(String.format("Item sold, new gold value: %d", gameEnvironment.getGold()));
            return true;
        }
        else
        {
            System.out.println("Warning: Null item could not be sold");
            return false;
        }
    }
}
