package seng201.team0.services;

import seng201.team0.GameEnvironment;
import seng201.team0.models.Adventurer;
import seng201.team0.models.Item;

public class GuildOverviewService {
    private final GameEnvironment gameEnvironment;
    public GuildOverviewService(GameEnvironment gameEnvironment) {this.gameEnvironment = gameEnvironment;}

    public void useItem(Adventurer adventurer, Item item)
    {
        if (adventurer == null || item == null) {
            System.out.println("Warning: adventurer or item is null");
            return;
        }

        switch (item) {
            case RUSTY_SWORD:
                System.out.println("you gave "+ adventurer + " the "+ item);
                break;
            case STAMINA_POTION:
                adventurer.setStamina(adventurer.getStamina()+20);
                System.out.println("you used the "+item+" on "+adventurer);
                break;
            case HEALTH_POTION:
                adventurer.setHealth(adventurer.getHealth()+30);
                System.out.println("you used the "+item+" on "+adventurer);
                break;

        }
        gameEnvironment.getPlayerInventory().removeItem(item);
    }
}
