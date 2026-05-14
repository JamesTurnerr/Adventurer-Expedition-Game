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
    TextArea expeditionTextArea;
    private final int expeditionIndex;
    private int currentArea = 0;
    private String button1Text = "";
    private String button2Text = "";
    private String currentEvent = "";

    private boolean expeditionFinished;
    /**
     * Creates a new expedition with a given amount of areas
     * @param expeditionTextArea reference to the TextArea that will have information about the expedition written to
     * @param expeditionIndex the different expedition that the player has chosen
     */
    public ExpeditionService(GameEnvironment gameEnvironment, TextArea expeditionTextArea, int expeditionIndex)
    {
        this.gameEnvironment = gameEnvironment;
        this.expeditionIndex = expeditionIndex;
        expedition = new Expedition(expeditionIndex);
        this.expeditionTextArea = expeditionTextArea;
        writeLine(String.format("You start your expedition in the %s", expedition.getAreaName(gameEnvironment.getExpeditionIndex())));
        writeLine(expedition.getAreaDescription(gameEnvironment.getExpeditionIndex()));
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
        Event currEvent = expedition.areaEvents[expeditionIndex][currentArea];
        writeLine("-------------");
        writeLine(currEvent.getEventDescription());
        writeLine("What do you do?");
        for(Choice choice : currEvent.getChoices())
        {
            writeLine(choice.getChoice());
        }
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
            // this will append text, keeping position
            expeditionTextArea.appendText("\n" + string);
        }
    }
    public void button1Clicked()
    {
        if(currentArea < 7)
        {
            nextArea();
        }
        else {expeditionOver();}

    }
    public void button2Clicked()
    {
        if(currentArea < 7)
        {
            nextArea();
        }
        else {expeditionOver();}
    }

    // not yet implemented
    public void button3Clicked()
    {
        if(currentArea < 7){
        nextArea();
        }
        else {expeditionOver();}
    }

    public void itemCollected(Item item){
        int cost=item.getCost();
        Random rand = new Random();

        int min = (int) Math.ceil(cost * 0.25);  // 25% of cost
        int max = (int) Math.ceil(cost * 1.25);  // 125% of cost

        // more likely to receive bad items
        int sellValue = rand.nextInt(max - min + 1) + min;

        System.out.println("item collected: " + item.getName() + ", sell value: " + sellValue);
    }

    //expedition finished
    public void expeditionOver(){
        // add bool to prevent multiple checks
        if (expeditionFinished) {return;}
        expeditionFinished = true;

        // get gold based on amount of stuff picked up

        // update remaining
        gameEnvironment.setExpeditionsCompleted(gameEnvironment.getExpeditionsCompleted()+1);
        gameEnvironment.setRemainingExpeditionNumber(gameEnvironment.getExpeditionsRemaining()-1);
        // refresh guild hall and market
        gameEnvironment.setDoUpdateHall(true);
        gameEnvironment.setDoUpdateMarket(true);
        //return to main place, or special event first (based on difficulty)
        if (rand.nextInt(100) < gameEnvironment.getEventChance()) {gameEnvironment.goToEventScreen();}
        else{gameEnvironment.goToMainScreen();}
    }
}
