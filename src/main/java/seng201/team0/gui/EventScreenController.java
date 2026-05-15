package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Adventurer;
import seng201.team0.models.Item;

import java.util.List;
import java.util.Random;

public class EventScreenController extends ScreenController{
    public EventScreenController(GameEnvironment gameEnvironment) {
        super(gameEnvironment);
    }

    @FXML TextArea eventTextArea;
    private final Random rand = new Random();
    private double modifier = getGameEnvironment().getDifficultyModifier();

    @Override
    protected String getFxmlFile() {return "/fxml/event_screen.fxml";}

    @Override
    protected String getTitle() {return "Special Event";}

    @FXML
    private void onContinueButtonClicked() {
        getGameEnvironment().goToMainScreen();
    }

    @FXML
    public void initialize() {
        generateRandomEvent();
    }

    private void generateRandomEvent() {
        List<Adventurer> party = getGameEnvironment().getMainParty();
        Adventurer adventurer = party.get(rand.nextInt(party.size()));

        int eventRoll = rand.nextInt(3);

        switch (eventRoll) {

            case 0 -> statChangeEvent(adventurer);

            case 1 -> rewardEvent(adventurer);

            case 2 -> retirementEvent(adventurer);
        }
    }

    // state change event. changes based on difficulty
    private void statChangeEvent(Adventurer adventurer){
        int amount = rand.nextInt(20) + 1;
        double baseChance = 50.0;
        boolean positiveChange = rand.nextInt(100) < baseChance/modifier;

        if (positiveChange) {
            adventurer.setStamina(adventurer.getStamina()+amount);
            adventurer.setHealth(adventurer.getHealth()+amount);
            eventTextArea.setText(adventurer.getName()+" has been strengthened by that expedition..\n\n" +
                    "Stamina and Health both increased by " + amount + "!");
        }
        else{
            //adventurer.setHealth(Math.max(1, adventurer.getHealth() - amount));
            adventurer.setStamina(Math.max(1, adventurer.getStamina() - amount));
            eventTextArea.setText(adventurer.getName() +
                            " felt especially fatigued during that expedition.\n\n" +
                            "Stamina decreased by " + amount + "."
            );
        }
    }
    private void rewardEvent(Adventurer adventurer) {
        int roll = rand.nextInt(100);
        String adv = adventurer.getName();

        if (roll < 70/modifier) {
            Item item = Item.getRandomItem();
            getGameEnvironment().addItem(item);

            eventTextArea.setText(
                    adv +
                            " found a "
                            +item.getName().toLowerCase()+
                            " as you were leaving! What luck!");
        }
        else {
            eventTextArea.setText(
                    adv +
                            " thought they found a rare item in the bushes, but it was a snake!\n\n" +
                            "In a panic, "+adv+" stubbed their toe on a rock.\n\n"+
                            "Also, the snake bit them.");
            adventurer.setHealth(Math.max(1, adventurer.getHealth() - 20));
        }
    }

    // retirement events increase based on lack of health
    private void retirementEvent(Adventurer adventurer) {
        int health = adventurer.getHealth();
        if (health < 15* modifier){
            getGameEnvironment().getMainParty().remove(adventurer);
            eventTextArea.setText(
                    adventurer.getName() +
                            " has retired from adventuring.\n\n" +
                            "After suffering too many injuries and exhaustion, they leave the party."
            );
        }
        else{
            int amount = 10;
            adventurer.setStamina(adventurer.getStamina()-amount);
            eventTextArea.setText(
                    adventurer.getName() +
                            " considers retirement...\n" +
                            "But the pay is too good of an incentive.\n\n"+
                    "Stamina decreases by "+amount
            );
        }
    }
}
