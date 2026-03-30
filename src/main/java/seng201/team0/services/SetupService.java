package seng201.team0.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import seng201.team0.Adventurer;
import seng201.team0.models.Item;

public class SetupService {


    /*public List<Adventurer> getAllAdventurerList() {
        return List.of(Adventurer.values());
    }

    public void addAllAdventurersToListView(ListView<Adventurer> listView){
        listView.getItems().setAll(getAllAdventurerList());
    }*/

    public List<Item> getTestItemList(int size)
    {
        List<Item> itemList = new ArrayList<Item>();
        for (int i = 0; i < size; i++) {
            itemList.add(Item.HEALTH_POTION);
        }
        return itemList;
    }

    public void switchToMainWindow(Stage window) throws IOException {
        FXMLLoader baseLoader = new FXMLLoader(getClass().getResource("/fxml/main_screen.fxml"));
        Parent root = baseLoader.load();
        Scene scene = new Scene(root, 600, 400);
        window.setScene(scene);
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
        if (guildInputTextField == "") {
            System.out.println("Please enter a guild name");
            return false;
        }
        if (difficultyMenuButton.equals("Difficulty")) {
            System.out.println("Please select a difficulty");
            return false;
        }

        if (chosenAdventurersListView.size() < 3) {
            System.out.println("Please choose at least 3 adventurers");
            return false;
        }

        String adventurerListString = "";
        for (Adventurer adv : chosenAdventurersListView) {
            adventurerListString += adv.toString() + " ";
        }
        System.out.println(String.format("%s guild is going on %d %s expeditions with %s",
                guildInputTextField, numberOfExpeditions, difficultyMenuButton, adventurerListString));
        return true;
    }
}
