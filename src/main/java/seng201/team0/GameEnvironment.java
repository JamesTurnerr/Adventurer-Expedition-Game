package seng201.team0;

import seng201.team0.gui.ScreenNavigator;
import seng201.team0.models.Adventurer;
import seng201.team0.models.Inventory;
import seng201.team0.models.Item;
import seng201.team0.services.AdventurerCreationService;

import java.util.*;

public class GameEnvironment {
    private final ScreenNavigator navigator;
    public final int MAX_PARTY_SIZE = 5;
    private final int LOCATION_COUNT = 5;//currently only 3 expeditions implemented so far. Increase as more implemented until max reached of tempExpedition.areaNames.length
    private List<Adventurer> mainParty;
    private List<Adventurer> reserveParty = new ArrayList<Adventurer>();
    private Inventory playerInventory = new Inventory();
    private Inventory marketInventory = new Inventory();
    private int gold;
    private int totalGold;
    private String difficulty;
    private String guildName;
    private int expeditionsCompleted;
    private int expeditionsRemaining;
    private final Random random = new Random();

    // store the hireable list and a bool to keep track of when to update
    // initialize as true so it makes initial list
    private final List<Adventurer> hireableAdventurers = new ArrayList<>();
    private final List<Integer> expeditionLocations = new ArrayList<>();
    private int eventChance;

    //difficulty (higher the number the worse)
    double difficultyModifier;

    //store start location for player to set themselves
    private String selectedExpeditionLocation;
    private int selectedExpeditionIndex;


    public GameEnvironment(ScreenNavigator navigator)
    {
        this.navigator = navigator;
        navigator.launchSetupScreen(this);
    }

    //Getters
    public int getGold(){return gold;}
    public List<Adventurer> getMainParty() {return mainParty;}
    public List<Adventurer> getReserveParty() {return reserveParty;}
    public Adventurer getRandomAdventurerFromParty(){return mainParty.get(random.nextInt(mainParty.size()));}
    public int getExpeditionsCompleted() {return expeditionsCompleted;}
    public int getExpeditionsRemaining() {return expeditionsRemaining;}
    public int getExpeditionIndex(){return selectedExpeditionIndex;}
    public Inventory getPlayerInventory() {return playerInventory;}

    public List<Adventurer> getHireableAdventurers() {return hireableAdventurers;}
    public Inventory getMarketInventory() {return marketInventory;}
    public List<Integer> getExpeditionLocations(){return expeditionLocations;}
    public int getEventChance() {System.out.println("the chance is"+ eventChance); return eventChance;}
    public String getSelectedExpeditionLocation(){return selectedExpeditionLocation;}
    public double getDifficultyModifier(){return difficultyModifier;}
    public String getGuildName(){return guildName;}
    public int getTotalGold() {return totalGold;}

    //setters
    public void setGold(int gold){this.gold = gold;}
    public void setSelectedExpeditionLocation(String location, int index){this.selectedExpeditionLocation = location; this.selectedExpeditionIndex = index;}
    public void setRemainingExpeditionNumber(int remaining){this.expeditionsRemaining = remaining;}
    public void setExpeditionsCompleted(int current){this.expeditionsCompleted = current;}
    public void addTotalGold(int i) {totalGold += i;}

    /**
     * Initializes/updates the market inventory after setup or expedition completion
     */
    public void updateMarketInventory()
    {
        marketInventory.clear();
        //generate 3 - 5 items
        int numberOfItems = random.nextInt(3)+3;
        for (int i = 0; i < numberOfItems; i++)
        {
            marketInventory.addItem(Item.getRandomItem());
        }
    }

    /**
     * Initializes/updates the list of buyable adventurers after setup or expedition completion
     */
    public void updateBuyableAdventurers()
    {
        hireableAdventurers.clear();
        for (int i = 0; i < 5; i++) {//generate 5 adventurers
            Adventurer adventurer = AdventurerCreationService.createRandomAdventurer();
            hireableAdventurers.add(adventurer);
        }
    }

    /**
     * Initializes/updates the list of available expeditions the player can go on after setup or expedition completion
     */
    public void updateAvailableExpeditionLocations() {
        Integer[] indices = new Integer[LOCATION_COUNT];
        for (int i = 0; i < LOCATION_COUNT; i++) {
            indices[i] = i;
        }
        Collections.shuffle(Arrays.asList(indices));
        getExpeditionLocations().clear();
        for (int i = 0; i < 3; i++) {
            getExpeditionLocations().add(indices[i]);
        }
    }

    /**
     * Called after player has gone through game setup, it will set initial values
     * @param mainParty an ArrayList of the adventurers the player has chosen
     * @param difficulty difficultly the player chose
     * @param guildName the suitable guild name the player has chosen
     * @param numberOfExpeditions the number of expeditions the player wants their game to last
     */
    public void onSetupComplete(ArrayList<Adventurer> mainParty, String difficulty, String guildName, int numberOfExpeditions) {
        this.mainParty = mainParty;
        switch (difficulty)//set difficulty modifiers
        {
            case "Easy":
                this.gold = 20;
                this.difficulty = "Easy";
                eventChance = 70;
                difficultyModifier = .8;
                break;

            case "Normal":
                this.gold = 10;
                this.difficulty = "Normal";
                eventChance = 50;
                difficultyModifier = 1;
                break;

            case "Hard":
                this.gold = 0;
                this.difficulty = "Hard";
                eventChance = 30;
                difficultyModifier = 1.2;
                break;
        }
        this.guildName = guildName;
        this.totalGold = gold;
        this.expeditionsCompleted = 0;
        this.expeditionsRemaining = numberOfExpeditions;
        updateMarketInventory();
        updateBuyableAdventurers();
        updateAvailableExpeditionLocations();
        goToMainScreen();
    }
    /**
     * Switch to the post expedition event screen
     */
    public void goToRandomEventScreen(){navigator.launchRandomEventScreen(this);}
    /**
     * Switch to the expedition selection screen
     */
    public void goToExpeditionSelectScreen(){navigator.launchExpeditionLocationScreen(this);}
    /**
     * Switch to the expedition screen
     */
    public void goToExpeditionScreen() {navigator.launchExpeditionScreen(this);}
    /**
     * Switch to the guild hall screen
     */
    public void goToGuildHallScreen() {navigator.launchGuildHallScreen(this);}
    /**
     * Switch to the guild overview screen
     */
    public void goToGuildOverviewScreen() {navigator.launchGuildOverviewScreen(this);}
    /**
     * Switch to the market screen
     */
    public void goToMarketScreen() {navigator.launchMarketScreen(this);}
    /**
     * Switch to the main screen
     */
    public void goToMainScreen() {navigator.launchMainScreen(this);}
    /**
     * Switch to the game over screen
     */
    public void goToGameOverScreen() {navigator.launchGameOverScreen(this);}
    /**
     * Exit the game
     */
    public void onQuitRequested() {System.exit(0);}

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

    public void recoverReserveParty(){
        for (Adventurer adventurer : reserveParty){
            int currentStam = adventurer.getStamina();
            int newStam = (int) (currentStam + 20/difficultyModifier);
            adventurer.setStamina(newStam);
        }
    }

    public void payMainParty(){
        for (Adventurer adventurer : mainParty){
            gold -= adventurer.getPay();
            if (gold < 0) {
                System.out.println("you owe "+adventurer.getName()+" money!");
                //gold = 0;
            }
        }
    }
}


