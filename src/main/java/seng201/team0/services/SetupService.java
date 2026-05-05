package seng201.team0.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import seng201.team0.models.Adventurer;
import seng201.team0.models.Item;

/**
 * Service class for the games setup screen
 */
public class SetupService {
    /**
     * Create a List of given containing randomly generated items
     * @param size the size of the list
     */
    public List<Item> getTestItemList(int size)
    {
        List<Item> itemList = new ArrayList<Item>();
        for (int i = 0; i < size; i++) {
            itemList.add(Item.getRandomItem());
        }
        return itemList;
    }
    /**
     * Checks all inputs to make sure they are valid before starting the game
     * @param expeditionInputTextField the amount of expeditions the user wants to go on
     * @param guildInputTextField the chosen guild name
     * @param difficultyMenuButton the string of the selected difficulty
     * @param chosenAdventurersListView a list of the chosen adventurers
     */
    public boolean checkInputs(String expeditionInputTextField, String guildInputTextField, String difficultyMenuButton, List<Adventurer> chosenAdventurersListView) {
        int numberOfExpeditions;
        try {
            numberOfExpeditions = Integer.parseInt(expeditionInputTextField);
        } catch (NumberFormatException e) {
            System.out.println("Number of expeditions is not an integer");
            return false;
        }

        if (numberOfExpeditions > 15 | numberOfExpeditions < 3) {
            System.out.println("Number of expeditions must be between 3 and 15 inclusive");
            return false;
        }

        // need to add guild parameters
        if (guildInputTextField.isEmpty()) {
            System.out.println("Please enter a guild name");
            return false;
        }
        //check for special characters
        for (int i = 0; i < guildInputTextField.length(); i++)
        {
            char c = guildInputTextField.charAt(i);
            if (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c))
            {
                return false;
            }
        }

        if (difficultyMenuButton.equals("Difficulty")) {
            System.out.println("Please select a difficulty");
            return false;
        }

        if (chosenAdventurersListView.size() != 3) {
            System.out.println("Please choose exactly 3 starter adventurers");
            return false;
        }

        String adventurerListString = "";
        for (Adventurer adv : chosenAdventurersListView) {
            adventurerListString += adv.getName() + " ";
        }
        System.out.println(String.format("%s guild is going on %d %s expeditions with %s",
                guildInputTextField, numberOfExpeditions, difficultyMenuButton, adventurerListString));
        return true;
    }

    /**
     * Fills a ListView of type Adventurer with x amount of randomly generated adventurers
     * @param listView the ListView to be filled
     * @param numberOfAdventurers the amount of adventurers to be added
     */
    public void fillStarterAdventurerList(ListView<Adventurer> listView, int numberOfAdventurers)
    {
        listView.getItems().clear();
        for (int i = 0; i < numberOfAdventurers; i++)
        {
            listView.getItems().add(AdventurerCreationService.createRandomAdventurer());
        }
    }
}
