package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import seng201.team0.Adventurer;
import seng201.team0.Item;
import seng201.team0.Party;
import seng201.team0.services.AdventurerListViewService;
import seng201.team0.services.SetupService;
import seng201.team0.services.GuiService;
import seng201.team0.models.UserData;

public class SetupController implements Initializable {
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
    private Party party;
    @FXML
    private ListView<Adventurer> availableAdventurersListView;
    @FXML
    private Button chooseAdventurerButton;
    @FXML
    private Button unchooseAdventurerButton;
    @FXML
    private Button fillListButton;

    private SetupService setupService = new SetupService();
    private GuiService guiService = new GuiService();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        party = Party.getInstance();
        AdventurerListViewService.fill(availableAdventurersListView);
    }

    //currently functionless
    @FXML
    private void onFillListButtonClicked()          //need to randomly generate some adventurers here
    {
        /*List<Adventurer> adventurerList = setupService.getAllAdventurerList();
        for (Adventurer adventurer : adventurerList)
        {
            availableAdventurersListView.getItems().add(adventurer);
        }*/
    }

    @FXML
    private void onButtonClicked() throws IOException {
        if (setupService.checkInputs(expeditionInputTextField.getText(), guildInputTextField.getText(), difficultyMenuButton.getText(), chosenAdventurersListView.getItems()))
        {
            ArrayList<Adventurer> chosenAdventurersList = new ArrayList<Adventurer>(chosenAdventurersListView.getItems());
            UserData userData = new UserData(chosenAdventurersList, difficultyMenuButton.getText(), guildInputTextField.getText(), Integer.parseInt(expeditionInputTextField.getText()));

            Stage stage = (Stage) startButton.getScene().getWindow();
            stage.setUserData(userData);
            guiService.switchWindow(stage, "/fxml/main_screen.fxml");
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
        Adventurer selectedAdventurer = availableAdventurersListView.getSelectionModel().getSelectedItem();
        if (selectedAdventurer != null && party.addToParty(selectedAdventurer)){
            chosenAdventurersListView.getItems().add(selectedAdventurer);
            availableAdventurersListView.getItems().remove(selectedAdventurer);
        }
    }
    @FXML
    private void unchooseAdventurerClicked() {
        Adventurer selectedAdventurer = chosenAdventurersListView.getSelectionModel().getSelectedItem();
        if (selectedAdventurer != null && party.removeFromParty(selectedAdventurer)) {
            availableAdventurersListView.getItems().add(chosenAdventurersListView.getSelectionModel().getSelectedItem());
            chosenAdventurersListView.getItems().remove(chosenAdventurersListView.getSelectionModel().getSelectedItem());
        }
    }
}

