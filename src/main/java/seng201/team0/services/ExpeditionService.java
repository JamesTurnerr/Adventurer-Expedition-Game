package seng201.team0.services;

import javafx.scene.control.TextArea;
import seng201.team0.models.Entity;
import seng201.team0.models.Expedition;

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
    /**
     * Creates a new expedition with a given amount of areas
     * @param expeditionTextArea reference to the TextArea that will have information about the expedition written to
     * @param numberOfAreas the number of areas that will be played through in this expedition
     */
    public ExpeditionService(TextArea expeditionTextArea, int numberOfAreas)
    {
        expedition = new Expedition(numberOfAreas);
        int randInt = rand.nextInt(expedition.ExpeditionLocation.length);
        this.expeditionTextArea = expeditionTextArea;
        location = expedition.ExpeditionLocation[randInt];
        areas = expedition.getAreas(location);
        writeLine(String.format("You start your expedition in the %s at a %s", location, areas[randInt]));
    }
    /**
     * Go to the next area in the expedition and pick a random event to happen in that area
     */
    public void nextEvent()
    {
        int randInt = rand.nextInt(2);
        if (randInt == 0)
        {
            combatEvent();
        }
        else if(randInt == 1)
        {
            lootEvent();
        }
    }
    /**
     * Start a combat event where the player will have to fight enemies
     */
    private void combatEvent()
    {
        Entity[] entities = {new Entity("Skeleton", 10, 1)};
        writeLine(String.format("You see %d %s ", entities.length, entities[0].getName()));
    }
    /**
     * Start a loot event where the player will loot the area
     */
    private void lootEvent()
    {
        writeLine(String.format("You see a %s", "Golden Scar"));
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
