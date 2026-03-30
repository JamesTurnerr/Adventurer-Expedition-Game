package seng201.team0.models;
import seng201.team0.Adventurer;
import java.util.List;
import java.util.ArrayList;

public class UserData {
    private static final int MAX_PARTY_SIZE = 5;
    private static List<Adventurer> mainParty;
    private List<Adventurer> reserveParty = new ArrayList<Adventurer>();
    private static List<Item> items = new ArrayList<Item>();
    private static int gold;
    private String difficulty;
    private String guildName;
    private static int currentExpeditionNumber;
    private static int expeditionsRemaining;

    private static UserData userData;

    private UserData() {}
    public UserData(ArrayList<Adventurer> mainParty, String difficulty, String guildName, int numberOfExpeditions)
    {
        this.mainParty = mainParty;
        switch (difficulty)
        {
            case "Easy":
                this.gold = 20;
                this.difficulty = "Easy";
                break;

            case "Normal":
                this.gold = 10;
                this.difficulty = "Normal";
                break;

            case "Hard":
                this.gold = 0;
                this.difficulty = "Hard";
                break;
        }
        this.guildName = guildName;
        this.currentExpeditionNumber = 1;
        this.expeditionsRemaining = numberOfExpeditions;
    }
    //Getters
    public static int getGold(){return gold;}
    public static List<Adventurer> getMainParty()
    {
        return mainParty;
    }
    public List<Adventurer> getReserveParty()
    {
        return reserveParty;
    }
    public List<Item> getItems()
    {
        return items;
    }
    public static int getCurrentExpeditionNumber()
    {
        return currentExpeditionNumber;
    }
    public static int getExpeditionsRemaining()
    {
        return expeditionsRemaining;
    }

    //Add data
    public static void addItem(Item item)
    {
        if (item == null)
        {
            System.out.println("Warning: Item is null");
            return;
        }
        items.add(item);
    }
    public boolean addToMainParty(Adventurer adventurer)
    {
        if (mainParty.size() >= MAX_PARTY_SIZE)
        {
            System.out.println("Warning: Main party at maximum capacity");
            return false;
        }
        else
        {
            mainParty.add(adventurer);
            return true;
        }
    }
    public boolean addToReserveParty(Adventurer adventurer)
    {
        if (reserveParty.size() >= 5)
        {
            System.out.println("Warning: Reserve party at maximum capacity");
            return false;
        }
        reserveParty.add(adventurer);
        return true;
    }

    //Remove data
    public void removeItem(Item item)
    {
        items.remove(item);
    }
    public boolean removeFromMainParty(Adventurer adventurer)
    {
        if (mainParty.size() == MAX_PARTY_SIZE)
        {
            System.out.println("Warning: Removing this adventurer will cause main party to be empty");
            return false;
        }
        else {
            mainParty.remove(adventurer);
            return true;
        }
    }
    public void removeFromReserveParty(Adventurer adventurer)
    {
        reserveParty.remove(adventurer);
    }

    //Buying
    public boolean hireAdventurer(Adventurer adventurer)
    {
        if (adventurer.getHiringCost() < gold)
        {
            if (addToReserveParty(adventurer))//Try to add new adventurer to reserves
            {
                gold -= adventurer.getHiringCost();
                return true;
            }
            else//Try to add new adventurer to main party
            {
                System.out.println("Warning: Reserve party full, attempting to add to main party");
                if (addToMainParty(adventurer))
                {
                    gold -= adventurer.getHiringCost();
                    return true;
                }
                else
                {
                    System.out.println("Warning: Main party full, could not hire new adventurer");
                    return false;
                }

            }
        }
        else {
            System.out.println("Warning: Not enough gold, could not hire new adventurer");
            return false;
        }
    }
    public static boolean buyItem(Item item)
    {
        if (item != null)
        {
            if (item.getCost() <= gold)
            {
                addItem(item);
                gold -= item.getCost();
                System.out.println(String.format("Item bought, %d gold remaining", getGold()));
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

    //Moving adventurers
    public boolean moveAdventurerToReserve(Adventurer adventurer)
    {
        if (adventurer == null)
        {
            System.out.println("Warning: No adventurer selected");
            return false;
        }
        if (reserveParty.contains(adventurer))
        {
            System.out.println("Warning: Reserve party already contains this adventurer");
            return false;
        }
        if (!mainParty.contains(adventurer))
        {
            System.out.println("Warning: Adventurer not found in main party");
            return false;
        }
        if (mainParty.size() == 1)
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
    public boolean moveAdventurerToMain(Adventurer adventurer)
    {
        if (adventurer == null)
        {
            System.out.println("Warning: No adventurer selected");
            return false;
        }
        if (mainParty.contains(adventurer))
        {
            System.out.println("Warning: Main party already contains this adventurer");
            return false;
        }
        if (!reserveParty.contains(adventurer))
        {
            System.out.println("Warning: Adventurer not found in reserve party");
            return false;
        }
        if (addToMainParty(adventurer))
        {
            reserveParty.remove(adventurer);
            return true;
        }
        else {
            return false;
        }
    }

    //Other
    public void useItem(Adventurer adventurer, Item item)
    {
        System.out.println("useItem not yet implemented");
    }

    public static UserData getInstance()
    {
        if (userData == null){
            userData = new UserData();
        }
        return userData;
    }
}
