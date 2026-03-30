package seng201.team0.models;
import seng201.team0.Adventurer;
import seng201.team0.Item;
import java.util.List;
import java.util.ArrayList;

public class UserData {
    private static final int MAX_PARTY_SIZE = 5;
    private List<Adventurer> mainParty;
    private List<Adventurer> reserveParty = new ArrayList<Adventurer>();
    private List<Item> items = new ArrayList<Item>();
    private int gold;
    private String difficulty;
    private String guildName;
    private int currentExpeditionNumber;
    private int expeditionsRemaining;

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
    public List<Adventurer> getMainParty()
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

    //Add data
    public void addItem(Item item)
    {
        items.add(item);
    }
    public boolean addToMainParty(Adventurer adventurer)
    {
        if (mainParty.size() >= MAX_PARTY_SIZE)
        {
            System.out.println("Warning: Party already full");
            return false;
        }
        else
        {
            mainParty.add(adventurer);
            return true;
        }
    }
    public void addToReserveParty(Adventurer adventurer)
    {
        reserveParty.add(adventurer);
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
            addToReserveParty(adventurer);
            return true;
        }
        else {
            return false;
        }
    }
    public boolean buyItem(Item item)
    {
        if (item.getCost() < gold)
        {
            addItem(item);
            return true;
        }
        else {
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
            addToReserveParty(adventurer);
            return true;
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


}
