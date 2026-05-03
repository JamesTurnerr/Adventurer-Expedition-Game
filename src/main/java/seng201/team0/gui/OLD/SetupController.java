/*
package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import seng201.team0.models.Adventurer;
import seng201.team0.models.Item;
import seng201.team0.services.SetupService;
import seng201.team0.services.GuiService;
import seng201.team0.models.UserData;

public class SetupScreenController implements Initializable {
    @FXML
    private TextField guildInputTextField, expeditionInputTextField;
    @FXML
    private Button startButton;
    @FXML
    private MenuButton difficultyMenuButton;
    @FXML
    private MenuItem easyMenuItem, normalMenuItem, hardMenuItem;
    @FXML
    private ListView<Adventurer> chosenAdventurersListView;
    @FXML
    private ListView<Adventurer> availableAdventurersListView;
    @FXML
    private Button chooseAdventurerButton, unchooseAdventurerButton;

    private final SetupService setupService = new SetupService();
    private final GuiService guiService = new GuiService();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        setupService.fillStarterAdventurerList(availableAdventurersListView, 5);
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
    private void startButtonClicked() throws IOException {
        if (setupService.checkInputs(expeditionInputTextField.getText(), guildInputTextField.getText(), difficultyMenuButton.getText(), chosenAdventurersListView.getItems()))
        {
            ArrayList<Adventurer> chosenAdventurersList = new ArrayList<Adventurer>(chosenAdventurersListView.getItems());
            UserData userData = new UserData(chosenAdventurersList, difficultyMenuButton.getText(), guildInputTextField.getText(), Integer.parseInt(expeditionInputTextField.getText()));
            userData.addItem(Item.HEALTH_POTION);

            Stage stage = (Stage) startButton.getScene().getWindow();
            guiService.switchWindow(stage, "/fxml/main_screen.fxml");
        }

    }
    @FXML
    private void chooseAdventurerClicked() {
        Adventurer selectedAdventurer = availableAdventurersListView.getSelectionModel().getSelectedItem();
        if (selectedAdventurer != null) {
            chosenAdventurersListView.getItems().add(selectedAdventurer);
            availableAdventurersListView.getItems().remove(selectedAdventurer);
        }
    }
    @FXML
    private void unchooseAdventurerClicked() {
        Adventurer selectedAdventurer = chosenAdventurersListView.getSelectionModel().getSelectedItem();
        if (selectedAdventurer != null) {
            availableAdventurersListView.getItems().add(chosenAdventurersListView.getSelectionModel().getSelectedItem());
            chosenAdventurersListView.getItems().remove(chosenAdventurersListView.getSelectionModel().getSelectedItem());
        }
    }
}

*/
