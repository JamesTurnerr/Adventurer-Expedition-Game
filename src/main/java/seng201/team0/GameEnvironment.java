package seng201.team0;

import seng201.team0.gui.ScreenNavigator;
import seng201.team0.models.Adventurer;
import seng201.team0.models.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameEnvironment {
    private final ScreenNavigator navigator;
    public final int MAX_PARTY_SIZE = 5;
    private List<Adventurer> mainParty;
    private List<Adventurer> reserveParty = new ArrayList<Adventurer>();
    private List<Item> items = new ArrayList<Item>();
    private int gold;
    private String difficulty;
    private String guildName;
    private int currentExpeditionNumber;
    private int expeditionsRemaining;
    private final Random random = new Random();

    // store the hireable list and a bool to keep track of when to update
    // initialize as true so it makes initial list
    private List<Adventurer> hireableAdventurers = new ArrayList<>();
    private List<Item> marketItems = new ArrayList<>();
    public boolean doUpdateHall = true;
    public boolean doUpdateMarket = true;

    //store start location for player to set themselves
    private String selectedExpeditionLocation;

    public GameEnvironment(ScreenNavigator navigator)
    {
        this.navigator = navigator;
        navigator.launchSetupScreen(this);
    }

    public void init(ArrayList<Adventurer> mainParty, String difficulty, String guildName, int numberOfExpeditions)
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
    public int getGold(){return gold;}
    public List<Adventurer> getMainParty() {return mainParty;}
    public List<Adventurer> getReserveParty() {return reserveParty;}
    public List<Item> getItems() {return items;}
    public int getCurrentExpeditionNumber() {return currentExpeditionNumber;}
    public int getExpeditionsRemaining() {return expeditionsRemaining;}

    public List<Adventurer> getHireableAdventurers() {return hireableAdventurers;}
    public List<Item> getMarketItems(){return marketItems;}
    public boolean getDoUpdateHall() {return doUpdateHall;}
    public boolean getDoUpdateMarket() {return doUpdateMarket;}

    public String getSelectedExpeditionLocation(){return selectedExpeditionLocation;}

    //setters
    public void setDoUpdateHall(boolean doUpdateHall) {this.doUpdateHall = doUpdateHall;}
    public void setDoUpdateMarket(boolean doUpdateMarket) {this.doUpdateMarket = doUpdateMarket;}
    public void setSelectedExpeditionLocation(String location){this.selectedExpeditionLocation = location;}

    //Add data
    public void addItem(Item item)
    {
        if (item == null)
        {
            System.out.println("Warning: Item is null");
            return;
        }
        items.add(item);
    }
    public boolean addAdventurer(Adventurer adventurer)
    {
        return addToMainParty(adventurer);
    }
    private boolean addToMainParty(Adventurer adventurer) {
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
    private boolean addToReserveParty(Adventurer adventurer)
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
    private void removeItem(Item item)
    {
        items.remove(item);
    }
    private boolean removeFromMainParty(Adventurer adventurer)
    {
        if (mainParty.size() == 1)
        {
            System.out.println("Warning: Removing this adventurer will cause main party to be empty");
            return false;
        }
        else {
            mainParty.remove(adventurer);
            return true;
        }
    }
    private void removeFromReserveParty(Adventurer adventurer)
    {
        reserveParty.remove(adventurer);
    }

    //Buying
    public boolean hireAdventurer(Adventurer adventurer)
    {
        if (adventurer != null)
        {
            if (adventurer.getHiringCost() <= gold)
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
        else {
            System.out.println("Warning: Adventurer is null");
            return false;
        }
    }
    public boolean buyItem(Item item)
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
        getItems().remove(item);
        //System.out.println("useItem not yet implemented");
    }

    public void onSetupComplete(ArrayList<Adventurer> mainParty, String difficulty, String guildName, int numberOfExpeditions) {
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
        goToMainScreen();
    }
    public void goToExpeditionSelectScreen(){
        navigator.launchExpeditionLocationScreen(this);
    }
    /**
     * Switch to the expedition screen
     */
    public void goToExpeditionScreen()
    {
        navigator.launchExpeditionScreen(this);
    }
    /**
     * Switch to the guild hall screen
     */
    public void goToGuildHallScreen()
    {
        navigator.launchGuildHallScreen(this);
    }
    /**
     * Switch to the guild overview screen
     */
    public void goToGuildOverviewScreen()
    {
        navigator.launchGuildOverviewScreen(this);
    }
    /**
     * Switch to the market screen
     */
    public void goToMarketScreen() {navigator.launchMarketScreen(this);}
    /**
     * Switch to the main screen
     */
    public void goToMainScreen() {navigator.launchMainScreen(this);}
    /**
     * Exit the game
     */
    public void onQuitRequested() {
        System.exit(0);
    }

    public void takeDamage(int damage){
        // each party member loses health based on dmg and defense
        // (since there no defense ill do perception)
        for (Adventurer member : mainParty){

            // damage range
            double modifier = 0.8 + random.nextDouble() * 0.4;
            int randomizedDamage = (int) (damage * modifier);

            int damageTaken = (int) (damage * (100.0 / (100 + member.getPerception())));

            int health = member.getHealth() - damageTaken;
            member.setHealth(Math.max(0, health));
        }
    }

}


