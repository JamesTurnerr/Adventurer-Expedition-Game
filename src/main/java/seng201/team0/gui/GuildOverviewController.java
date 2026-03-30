package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import seng201.team0.Adventurer;
import seng201.team0.services.WindowSwitchService;
import seng201.team0.Item;
import seng201.team0.models.UserData;

public class GuildOverviewController implements Initializable {
    @FXML
    private Button backButton;
    @FXML
    private Button moveToMainButton;
    @FXML
    private Button moveFromMainButton;
    @FXML
    private Button useItemButton;
    @FXML
    private Button loadButton;
    @FXML
    private ListView<Adventurer> reservePartyListView;
    @FXML
    private ListView<Adventurer> mainPartyListView;
    @FXML
    private ListView<Item> itemsListView;

    WindowSwitchService windowSwitchService = new WindowSwitchService();
    UserData userData = UserData.getInstance();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        for (Adventurer adventurer : userData.getMainParty())
        {
            mainPartyListView.getItems().add(adventurer);

        }
        for (Adventurer adventurer : userData.getReserveParty())
        {
            reservePartyListView.getItems().add(adventurer);
        }
        for (Item item : userData.getItems())
        {
            itemsListView.getItems().add(item);
        }
    }
    @FXML
    private void loadButtonClicked() throws IOException {//load user data into main party list view
        mainPartyListView.getItems().clear();
        reservePartyListView.getItems().clear();
        itemsListView.getItems().clear();
        for (Adventurer adventurer : userData.getMainParty())
        {
            mainPartyListView.getItems().add(adventurer);
        }
        for (Adventurer adventurer : userData.getReserveParty())
        {
            reservePartyListView.getItems().add(adventurer);
        }
        for (Item item : userData.getItems())
        {
            itemsListView.getItems().add(item);
        }
    }
    @FXML
    private void backButtonClicked() throws IOException {
        //Switch windows
        windowSwitchService.switchWindow((Stage) backButton.getScene().getWindow(), "/fxml/main_screen.fxml");
    }
    @FXML
    private void moveToMainButtonClicked()
    {
        userData.moveAdventurerToMain(reservePartyListView.getSelectionModel().getSelectedItem());//Move adventurer to main
        //Update ListViews
        mainPartyListView.getItems().clear();
        for (Adventurer adventurer : userData.getMainParty())
        {
            mainPartyListView.getItems().add(adventurer);
        }
        reservePartyListView.getItems().clear();
        for (Adventurer adventurer : userData.getReserveParty())
        {
            reservePartyListView.getItems().add(adventurer);
        }
    }
    @FXML
    private void moveFromMainButtonClicked()
    {
        userData.moveAdventurerToReserve(mainPartyListView.getSelectionModel().getSelectedItem());//Move adventurer to reserve
        //Update ListViews
        mainPartyListView.getItems().clear();
        for (Adventurer adventurer : userData.getMainParty())
        {
            mainPartyListView.getItems().add(adventurer);
        }
        reservePartyListView.getItems().clear();
        for (Adventurer adventurer : userData.getReserveParty())
        {
            reservePartyListView.getItems().add(adventurer);
        }
    }
    @FXML
    private void useItemButtonClicked()
    {
        System.out.println("useItemButtonClicked() not yet implemented");
    }
}
