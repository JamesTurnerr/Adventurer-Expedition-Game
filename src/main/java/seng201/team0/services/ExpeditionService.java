package seng201.team0.services;

import javafx.scene.control.TextArea;
import seng201.team0.GameEnvironment;
import seng201.team0.models.*;

import java.util.*;

/**
 * The class that holds most of the logic for expeditions
 */
public class ExpeditionService {
    GameEnvironment gameEnvironment;
    Random rand = new Random();
    Expedition expedition;
    TextArea expeditionTextArea;
    private final int expeditionIndex;
    private int currentArea = -1;
    private boolean perceptionCheckPassed = false;

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
     * Go to the next area in the expedition and give the player a selection of choices to make
     */
    public void nextArea()
    {
        currentArea++;
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

    /**
     * Button 1 was clicked which has an index value of 0
     */
    public void button1Clicked()
    {
        applyEffect(0);//applyEffect before outputting the result as applyEffect does the perception check
        writeLine(String.format("You %s causing you to %s", getChoiceText(0), getChoiceResult(0)));
        if(currentArea < 7)
        {
            nextArea();
        }
        else {expeditionOver();}

    }

    /**
     * Button 2 was clicked which has an index value of 1
     */
    public void button2Clicked()
    {
        applyEffect(1);//applyEffect before outputting the result as applyEffect does the perception check
        writeLine(String.format("You %s causing you to %s", getChoiceText(1), getChoiceResult(1)));
        if(currentArea < 7)
        {
            nextArea();
        }
        else {expeditionOver();}
    }

    /**
     * Button 3 was clicked which has an index value of 2
     */
    public void button3Clicked()
    {
        applyEffect(2);//applyEffect before outputting the result as applyEffect does the perception check
        writeLine(String.format("You %s causing you to %s", getChoiceText(2).toLowerCase(Locale.ROOT), getChoiceResult(2)));
        if(currentArea < 7){
        nextArea();
        }
        else {expeditionOver();}
    }

    /**
     * returns the text of the action you chose e.g. "Mine rocks", "Walk around the danger", or "Inspect the loot"
     * @param choiceIndex the index of the chosen action from 0 to 2
     * @return the String of the action you chose
     */
    private String getChoiceText(int choiceIndex)
    {
        return expedition.areaEvents[expeditionIndex][currentArea].getChoices()[choiceIndex].getChoice();
    }

    /**
     * returns the text what your chosen action caused e.g. "Took damage", "Lost stamina", or "Found some gold coins"
     * @param choiceIndex the index of the chosen action from 0 to 2
     * @return the String of what effect your action had
     */
    private String getChoiceResult(int choiceIndex)
    {
        //Convert EventOutcomes to a List
        List<EventOutcome> eventOutcomes = Arrays.asList(expedition.areaEvents[expeditionIndex][currentArea].getChoices()[choiceIndex].getEventOutcomes());
        //Check if EventOutcomes is a perception check
        if (eventOutcomes.contains(EventOutcome.LARGE_PERCEPTION) | eventOutcomes.contains(EventOutcome.MEDIUM_PERCEPTION) | eventOutcomes.contains(EventOutcome.SMALL_PERCEPTION))
        {
            if (perceptionCheckPassed) {
                if (eventOutcomes.contains(EventOutcome.LARGE_PERCEPTION)) {
                    return expedition.areaEvents[expeditionIndex][currentArea].getChoices()[choiceIndex].getChoiceOutcome().replace("LARGE_PERCEPTION_CHECK", EventOutcome.LARGE_GOLD.getOutcome());
                }
                else if (eventOutcomes.contains(EventOutcome.MEDIUM_PERCEPTION)) {
                    return expedition.areaEvents[expeditionIndex][currentArea].getChoices()[choiceIndex].getChoiceOutcome().replace("MEDIUM_PERCEPTION_CHECK", EventOutcome.MEDIUM_GOLD.getOutcome());
                }
                else {
                    return expedition.areaEvents[expeditionIndex][currentArea].getChoices()[choiceIndex].getChoiceOutcome().replace("SMALL_PERCEPTION_CHECK", EventOutcome.SMALL_GOLD.getOutcome());
                }
            }
            else {
                if (eventOutcomes.contains(EventOutcome.LARGE_PERCEPTION)) {
                    return expedition.areaEvents[expeditionIndex][currentArea].getChoices()[choiceIndex].getChoiceOutcome().replace("LARGE_PERCEPTION_CHECK", EventOutcome.LARGE_HEALTH_LOSS.getOutcome());
                }
                else if (eventOutcomes.contains(EventOutcome.MEDIUM_PERCEPTION)) {
                    return expedition.areaEvents[expeditionIndex][currentArea].getChoices()[choiceIndex].getChoiceOutcome().replace("MEDIUM_PERCEPTION_CHECK", EventOutcome.MEDIUM_HEALTH_LOSS.getOutcome());
                }
                else {
                    return expedition.areaEvents[expeditionIndex][currentArea].getChoices()[choiceIndex].getChoiceOutcome().replace("SMALL_PERCEPTION_CHECK", EventOutcome.SMALL_HEALTH_LOSS.getOutcome());
                }
            }
        }
        else //else return the action outcome
        {
            return expedition.areaEvents[expeditionIndex][currentArea].getChoices()[choiceIndex].getChoiceOutcome();
        }
    }

    /**Applies the effect of the choice that the player made
     * @param choiceIndex the index of the button that the player clicked
     */
    private void applyEffect(int choiceIndex)
    {
        EventOutcome[] eventOutcomes = expedition.areaEvents[expeditionIndex][currentArea].getChoices()[choiceIndex].getEventOutcomes();
        for(EventOutcome eventOutcome : eventOutcomes)
        {
            switch (eventOutcome)
            {
                case SMALL_HEALTH_LOSS -> gameEnvironment.getRandomAdventurerFromParty().takeHealthDamage(5);
                case MEDIUM_HEALTH_LOSS -> gameEnvironment.getRandomAdventurerFromParty().takeHealthDamage(10);
                case LARGE_HEALTH_LOSS -> gameEnvironment.getRandomAdventurerFromParty().takeHealthDamage(20);
                case SMALL_STAMINA_LOSS -> gameEnvironment.getRandomAdventurerFromParty().takeStaminaDamage(5);
                case MEDIUM_STAMINA_LOSS -> gameEnvironment.getRandomAdventurerFromParty().takeStaminaDamage(10);
                case LARGE_STAMINA_LOSS -> gameEnvironment.getRandomAdventurerFromParty().takeStaminaDamage(20);
                case SMALL_GOLD -> {
                    gameEnvironment.setGold(gameEnvironment.getGold() + 5);
                    gameEnvironment.addTotalGold(5);}
                case MEDIUM_GOLD -> {
                    gameEnvironment.setGold(gameEnvironment.getGold() + 10);
                    gameEnvironment.addTotalGold(10);}
                case LARGE_GOLD -> {
                    gameEnvironment.setGold(gameEnvironment.getGold() + 20);
                    gameEnvironment.addTotalGold(20);}
                case RANDOM -> System.out.println("RANDOM");
                case LOSE_PROGRESS -> currentArea+=1;//Skip a potential loot room
                case NOTHING -> System.out.println("NOTHING");
                case SMALL_PERCEPTION -> perceptionCheck(EventOutcome.SMALL_PERCEPTION);
                case MEDIUM_PERCEPTION -> perceptionCheck(EventOutcome.MEDIUM_PERCEPTION);
                case LARGE_PERCEPTION -> perceptionCheck(EventOutcome.LARGE_PERCEPTION);
                default -> System.out.println("Warning: Unknown EventOutcome");
            }
        }

    }

    /**
     * Calculates if the perception check is passed based on the difficulty of the perception check and the parties
     * total perception
     * @param eventOutcome effectively the difficulty of the perception check, takes SMALL_PERCEPTION, MEDIUM_PERCEPTION or LARGE_PERCEPTION as an input
     */
    private void perceptionCheck(EventOutcome eventOutcome)
    {
        int totalPerception = 0;
        switch(eventOutcome)
        {
            case SMALL_PERCEPTION:
                for (Adventurer adventurer : gameEnvironment.getMainParty()) { totalPerception += adventurer.getPerception(); }
                if (totalPerception > ((rand.nextInt(21) + 90) * gameEnvironment.getMainParty().size())) {//if average perception of adventurers > 100 (+-10)
                    perceptionCheckPassed = true;
                    gameEnvironment.setGold(gameEnvironment.getGold() + 5);
                }
                else {
                    perceptionCheckPassed = false;
                    gameEnvironment.getRandomAdventurerFromParty().takeHealthDamage(5);
                }
                break;
            case MEDIUM_PERCEPTION:
                for (Adventurer adventurer : gameEnvironment.getMainParty()) { totalPerception += adventurer.getPerception(); }
                if (totalPerception > ((rand.nextInt(41) + 100) * gameEnvironment.getMainParty().size())) {//if average perception of adventurers > 120 (+-20)
                    perceptionCheckPassed = true;
                    gameEnvironment.setGold(gameEnvironment.getGold() + 10);
                }
                else {
                    perceptionCheckPassed = false;
                    gameEnvironment.getRandomAdventurerFromParty().takeHealthDamage(10);
                }
                break;
            case LARGE_PERCEPTION:
                for (Adventurer adventurer : gameEnvironment.getMainParty()) { totalPerception += adventurer.getPerception(); }
                if (totalPerception > ((rand.nextInt(81) + 100) * gameEnvironment.getMainParty().size())) {//if average perception of adventurers > 140 (+-40)
                    perceptionCheckPassed = true;
                    gameEnvironment.setGold(gameEnvironment.getGold() + 20);
                }
                else {
                    perceptionCheckPassed = false;
                    gameEnvironment.getRandomAdventurerFromParty().takeHealthDamage(20);
                }
                break;
        }
    }

    /**
     * Function called after all area in the expedition have been explored, this will either send the player to the
     * game over screen, the post expedition random event screen, or back to the main menu.
     * This method will:
     * Pay main party members, heal reserve party members,
     * Refresh the market, guild hall, and expedition locations,
     * and give the player gold for their expedition completed.
     */
    public void expeditionOver(){
        if (expeditionFinished) {return;}
        expeditionFinished = true;
        expeditionCompletionReward();
        gameEnvironment.payMainParty();
        gameEnvironment.setExpeditionsCompleted(gameEnvironment.getExpeditionsCompleted()+1);
        gameEnvironment.setRemainingExpeditionNumber(gameEnvironment.getExpeditionsRemaining()-1);
        gameEnvironment.updateBuyableAdventurers();
        gameEnvironment.updateMarketInventory();
        gameEnvironment.updateAvailableExpeditionLocations();
        gameEnvironment.recoverReserveParty();
        if (gameEnvironment.getExpeditionsRemaining() == 0)
        {
            gameEnvironment.goToGameOverScreen();
        }
        else {
            if (rand.nextInt(100) < gameEnvironment.getEventChance()) {
                gameEnvironment.goToRandomEventScreen();
            }
            else
            {
                gameEnvironment.goToMainScreen();
            }
        }

    }

    /**
     * The bonus reward gained at the end of an expedition based on difficulty
     */
    private void expeditionCompletionReward()
    {
        int goldReward = (int) (50 * (1 / gameEnvironment.getDifficultyModifier()));
        gameEnvironment.setGold(gameEnvironment.getGold() + goldReward);
        gameEnvironment.addTotalGold(goldReward);
    }
}
