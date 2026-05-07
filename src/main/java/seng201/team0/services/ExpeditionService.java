package seng201.team0.services;

import javafx.scene.control.TextArea;
import seng201.team0.GameEnvironment;
import seng201.team0.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The class that holds most of the logic for expeditions
 */
public class ExpeditionService {
    GameEnvironment gameEnvironment;
    Random rand = new Random();
    Expedition expedition;
    String location;
    String[] areas;
    TextArea expeditionTextArea;
    private final int numberOfAreas;
    private int currentArea = 0;
    private String button1Text = "";
    private String button2Text = "";
    private String currentEvent = "";
    //Variable storages for events
    private Item currentLootItem;
    private Adventurer currentLostAdventurer;
    private List<Enemy> currentEnemyList;
    /**
     * Creates a new expedition with a given amount of areas
     * @param expeditionTextArea reference to the TextArea that will have information about the expedition written to
     * @param numberOfAreas the number of areas that will be played through in this expedition
     */
    public ExpeditionService(GameEnvironment gameEnvironment, TextArea expeditionTextArea, int numberOfAreas)
    {
        this.gameEnvironment = gameEnvironment;
        this.numberOfAreas = numberOfAreas;
        expedition = new Expedition(numberOfAreas);
        int randInt = rand.nextInt(expedition.ExpeditionLocation.length);
        this.expeditionTextArea = expeditionTextArea;
        location = expedition.ExpeditionLocation[randInt];
        areas = expedition.getAreas(location);
        writeLine(String.format("You start your expedition in the %s at a %s", location, areas[randInt]));
        nextArea();
    }
    /**
     * Go to the next area in the expedition and pick a random event to happen in that area
     * 66% chance of combat event
     * 33% chance of loot event
     * 1% chance of new adventurer event
     */
    public void nextArea()
    {
        currentArea++;
        int randInt = rand.nextInt(100);
        if (randInt <= 66)
        {
            combatEvent();
        }
        else if(randInt <= 98)
        {
            lootEvent();
        }
        else
        {
            newAdventurerEvent();
        }
    }
    /**
     * Start a combat event where the player will have to fight enemies
     */
    private void combatEvent()
    {
        currentEvent = "Combat";
        button1Text = "Attack";
        button2Text = "Defense";
        int randInt = rand.nextInt(4);
        ArrayList<Enemy> entities = expedition.generateEnemies(randInt);
        writeLine(String.format("You see %d %s ", randInt, entities.getFirst().getName()));
    }
    /**
     * Start a loot event where the player will loot the area
     */
    private void lootEvent()
    {
        currentEvent = "Loot";
        button1Text = "Pick Up";
        button2Text = "Leave";
        currentLootItem = Item.RUSTY_SWORD;
        writeLine(String.format("You see a %s", currentLootItem.getName()));
    }
    /**
     * Start a new adventurer event where the player can recruit an adventurer that they have found
     */
    private void newAdventurerEvent()
    {
        currentEvent = "Adventurer";
        button1Text = "Hire";
        button2Text = "Leave";
        //AdventurerCreationService.createAdventurer();
        writeLine("You see an an adventurer trapped under a pile of rubble");
    }
    /**
     * Write text to the TextArea for the user to see
     */
    public void writeLine(String string)
    {
        if (expeditionTextArea.getText().isEmpty())
        {
            expeditionTextArea.setText(string);
        }
        else
        {
            expeditionTextArea.setText(expeditionTextArea.getText() + "\n" + string);
        }
    }

    public String getButton1Text()
    {
        return button1Text;
    }
    public String getButton2Text()
    {
        return button2Text;
    }
    public void button1Clicked()
    {
        switch (currentEvent)
        {
            case "Combat":
                //attack;
                break;
            case "Loot":
                gameEnvironment.addItem(currentLootItem);
                writeLine("You pick up the " + currentLootItem.getName());
                nextArea();
                break;
            case "Adventurer":
                if (gameEnvironment.addAdventurer(currentLostAdventurer))
                {
                    writeLine(currentLostAdventurer.getName() + "joins your party");
                }
                else
                {
                    writeLine("You do not have enough space in your party for this adventurer");
                }
                nextArea();
                break;
        }
    }
    public void button2Clicked()
    {
        switch (currentEvent)
        {
            case "Combat":
                //defend;
                break;
            case "Loot":
                writeLine("You decide not to pick up the item");
                nextArea();
                break;
            case "Adventurer":
                writeLine("You decide not to leave the adventurer");
                nextArea();
                break;
        }
    }
}
