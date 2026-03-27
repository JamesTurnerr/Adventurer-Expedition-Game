package seng201.team0;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SetupController {
    @FXML
    private TextField guildInputTextField;
    @FXML
    private TextField expeditionInputTextField;
    @FXML
    private Button startButton;
    @FXML
    private MenuButton difficultyMenuButton;
    @FXML
    private MenuItem easyMenuItem;
    @FXML
    private MenuItem normalMenuItem;
    @FXML
    private MenuItem hardMenuItem;
    @FXML
    private ListView<Adventurer> chosenAdventurersListView;
    @FXML
    private ListView<Adventurer> availableAdventurersListView;
    @FXML
    private Button chooseAdventurerButton;
    @FXML
    private Button unchooseAdventurerButton;
    @FXML
    private Button fillListButton;

    @FXML
    private void onFillListButtonClicked()          //need to randomly generate some adventurers here
    {
        Adventurer a = new Adventurer("bob", 100, 100, 100, 100, 100, 100);
        Adventurer b = new Adventurer("jim", 100, 100, 100, 100, 100, 100);
        Adventurer c = new Adventurer("steve", 100, 100, 100, 100, 100, 100);
        availableAdventurersListView.getItems().add(a);
        availableAdventurersListView.getItems().add(b);
        availableAdventurersListView.getItems().add(c);
    }
    @FXML
    private void onButtonClicked() throws IOException {
        System.out.println("Start button clicked");
        int numberOfExpeditions = 3;
        try {
            numberOfExpeditions = Integer.parseInt(expeditionInputTextField.getText());
        } catch (NumberFormatException e) {
            System.out.println("Number of expeditions is not an integer");
        }
        if (numberOfExpeditions > 15 | numberOfExpeditions < 3)
        {
            System.out.println("Number of expeditions must be between 3 and 15 inclusive");
        }
        else
        {
            if (guildInputTextField.getText() == "")
            {
                System.out.println("Please enter a guild name");
            }
            else
            {
                if (difficultyMenuButton.getText() == "Difficulty")//this is not working (not matching "Difficulty" with "Difficulty" for some reason)
                {
                    System.out.println("Please select a difficulty");
                }
                else
                {
                    if (chosenAdventurersListView.getItems().size() < 3)
                    {
                        System.out.println("Please choose at least 3 adventurers");
                    }
                    else
                    {
                        String adventurerListString = "";
                        for (Adventurer adv : chosenAdventurersListView.getItems())
                        {
                            adventurerListString += adv.toString() + " ";
                        }
                        System.out.println(String.format("%s guild is going on %d %s expeditions with %s",
                                guildInputTextField.getText(), numberOfExpeditions, difficultyMenuButton.getText(), adventurerListString));
                        //SWITCH WINDOW
                        FXMLLoader baseLoader = new FXMLLoader(getClass().getResource("/fxml/main_screen.fxml"));
                        Parent root = baseLoader.load();
                        Stage window = (Stage) startButton.getScene().getWindow();
                        Scene scene = new Scene(root, 600, 400);
                        window.setScene(scene);
                    }

                }
            }
        }
    }
    @FXML
    private void easyModeSelected() {
        difficultyMenuButton.setText("Easy");
        System.out.println("Easy mode selected");
    }
    @FXML
    private void normalModeSelected() {
        difficultyMenuButton.setText("Normal");
        System.out.println("Normal mode selected");
    }
    @FXML
    private void hardModeSelected() {
        difficultyMenuButton.setText("Hard");
        System.out.println("Hard mode selected");
    }
    @FXML
    private void chooseAdventurerClicked() {
        if (availableAdventurersListView.getSelectionModel().getSelectedItem() != null)
        {
            chosenAdventurersListView.getItems().add(availableAdventurersListView.getSelectionModel().getSelectedItem());
            availableAdventurersListView.getItems().remove(availableAdventurersListView.getSelectionModel().getSelectedItem());
        }
    }
    @FXML
    private void unchooseAdventurerClicked() {
        if (chosenAdventurersListView.getSelectionModel().getSelectedItem() != null)
        {
            availableAdventurersListView.getItems().add(chosenAdventurersListView.getSelectionModel().getSelectedItem());
            chosenAdventurersListView.getItems().remove(chosenAdventurersListView.getSelectionModel().getSelectedItem());
        }
    }
}

