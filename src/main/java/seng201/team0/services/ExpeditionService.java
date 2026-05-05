package seng201.team0.services;

import javafx.scene.control.TextArea;
import seng201.team0.models.Enemy;
import seng201.team0.models.Entity;
import seng201.team0.models.Expedition;

import java.util.ArrayList;
import java.util.Random;

/**
 * The class that holds most of the logic for expeditions
 */
public class ExpeditionService {
    Random rand = new Random();
    Expedition expedition;
    String location;
    String[] areas;
    TextArea expeditionTextArea;
    private final int numberOfAreas;
    private int currentArea = 0;
    /**
     * Creates a new expedition with a given amount of areas
     * @param expeditionTextArea reference to the TextArea that will have information about the expedition written to
     * @param numberOfAreas the number of areas that will be played through in this expedition
     */
    public ExpeditionService(TextArea expeditionTextArea, int numberOfAreas)
    {
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
        int randInt = rand.nextInt(4);
        ArrayList<Enemy> entities = expedition.generateEnemies(randInt);
        writeLine(String.format("You see %d %s ", randInt, entities.getFirst().getName()));
    }
    /**
     * Start a loot event where the player will loot the area
     */
    private void lootEvent()
    {
        writeLine(String.format("You see a %s", "Golden Scar"));
    }
    /**
     * Start a new adventurer event where the player can recruit an adventurer that they have found
     */
    private void newAdventurerEvent()
    {
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
}
