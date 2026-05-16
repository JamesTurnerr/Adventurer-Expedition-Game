package seng201.team0.services;

import seng201.team0.GameEnvironment;
import seng201.team0.models.Item;

import java.util.ArrayList;
import java.util.List;

public class MarketService {
    private final GameEnvironment gameEnvironment;
    public MarketService(GameEnvironment gameEnvironment) {this.gameEnvironment = gameEnvironment;}

    public List<Item> getTestItemList(int size)
    {
        if (gameEnvironment.getDoUpdateMarket()){
            gameEnvironment.setDoUpdateMarket(false);
            //System.out.println("Made new market");
            //System.out.println(gameEnvironment.getDoUpdateMarket());
            return getNewList(size);

        }
        else{
            return gameEnvironment.getMarketItems();
        }
    }

    public List<Item> getNewList(int size){
        gameEnvironment.getMarketItems().clear();
        for (int i = 0; i < size; i++) {
            Item item = Item.getRandomItem();
            gameEnvironment.getMarketItems().add(item);
        }
        return gameEnvironment.getMarketItems();
    }

    public boolean buyItem(Item item)
    {
        if (item != null)
        {
            if (item.getCost() <= gameEnvironment.getGold())
            {
                gameEnvironment.getPlayerInventory().addItem(item);
                gameEnvironment.setGold(gameEnvironment.getGold()-item.getCost());
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
