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

    public boolean moveAdventurerToMain(Adventurer adventurer)
    {
        if (adventurer == null)
        {
            System.out.println("Warning: No adventurer selected");
            return false;
        }
        if (gameEnvironment.getMainParty().contains(adventurer))
        {
            System.out.println("Warning: Main party already contains this adventurer");
            return false;
        }
        if (!gameEnvironment.getReserveParty().contains(adventurer))
        {
            System.out.println("Warning: Adventurer not found in reserve party");
            return false;
        }
        if (addToMainParty(adventurer))
        {
            gameEnvironment.getReserveParty().remove(adventurer);
            return true;
        }
        else {
            return false;
        }
    }

    private boolean addToMainParty(Adventurer adventurer) {
        if (gameEnvironment.getMainParty().size() >= gameEnvironment.MAX_PARTY_SIZE)
        {
            System.out.println("Warning: Main party at maximum capacity");
            return false;
        }
        else
        {
            gameEnvironment.getMainParty().add(adventurer);
            return true;
        }
    }

    private boolean addToReserveParty(Adventurer adventurer)
    {
        if (gameEnvironment.getReserveParty().size() >= 5)
        {
            System.out.println("Warning: Reserve party at maximum capacity");
            return false;
        }
        gameEnvironment.getReserveParty().add(adventurer);
        return true;
    }

    private boolean removeFromMainParty(Adventurer adventurer)
    {
        if (gameEnvironment.getMainParty().size() == 1)
        {
            System.out.println("Warning: Removing this adventurer will cause main party to be empty");
            return false;
        }
        else {
            gameEnvironment.getMainParty().remove(adventurer);
            return true;
        }
    }

    public boolean moveAdventurerToReserve(Adventurer adventurer)
    {
        if (adventurer == null)
        {
            System.out.println("Warning: No adventurer selected");
            return false;
        }
        if (gameEnvironment.getReserveParty().contains(adventurer))
        {
            System.out.println("Warning: Reserve party already contains this adventurer");
            return false;
        }
        if (!gameEnvironment.getMainParty().contains(adventurer))
        {
            System.out.println("Warning: Adventurer not found in main party");
            return false;
        }
        if (gameEnvironment.getMainParty().size() == 1)
        {
            System.out.println("Warning: Removing this adventurer will cause main party to be empty");
            return false;
        }
        if (removeFromMainParty(adventurer))
        {
            return addToReserveParty(adventurer);
        }
        else {
            return false;
        }
    }
}
