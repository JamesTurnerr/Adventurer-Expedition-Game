package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import seng201.team0.Adventurer;
import seng201.team0.services.SetupService;

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

    private SetupService setupService = new SetupService();

    @FXML
    private void onFillListButtonClicked()          //need to randomly generate some adventurers here
    {
        List<Adventurer> adventurerList = setupService.getTestAdventurerList(3);
        for (Adventurer adventurer : adventurerList)
        {
            availableAdventurersListView.getItems().add(adventurer);
        }
    }
    @FXML
    private void onButtonClicked() throws IOException {
        if (setupService.checkInputs(expeditionInputTextField.getText(), guildInputTextField.getText(), difficultyMenuButton.getText(), chosenAdventurersListView.getItems()))
        {
            setupService.switchToMainWindow((Stage) startButton.getScene().getWindow());
        }

    }
    @FXML
    private void easyModeSelected() {
        difficultyMenuButton.setText("Easy");
    }
    @FXML
    private void normalModeSelected() {
        difficultyMenuButton.setText("Normal");
    }
    @FXML
    private void hardModeSelected() {
        difficultyMenuButton.setText("Hard");
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

