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
import seng201.team0.models.Item;
import seng201.team0.models.UserData;
import seng201.team0.services.GuiService;

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
    private ListView<Adventurer> reservePartyListView;
    @FXML
    private ListView<Adventurer> mainPartyListView;
    @FXML
    private ListView<Item> itemsListView;

    GuiService guiService = new GuiService();
    UserData userData = UserData.getInstance();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        guiService.populateListView(mainPartyListView, userData.getMainParty());
        guiService.populateListView(reservePartyListView, userData.getReserveParty());
        guiService.populateListView(itemsListView, userData.getItems());
    }
    @FXML
    private void backButtonClicked() throws IOException {
        //Switch windows
        guiService.switchWindow((Stage) backButton.getScene().getWindow(), "/fxml/main_screen.fxml");
    }
    @FXML
    private void moveToMainButtonClicked()
    {
        userData.moveAdventurerToMain(reservePartyListView.getSelectionModel().getSelectedItem());//Move adventurer to main
        //Update ListViews
        guiService.populateListView(mainPartyListView, userData.getMainParty());
        guiService.populateListView(reservePartyListView, userData.getReserveParty());
    }
    @FXML
    private void moveFromMainButtonClicked()
    {
        userData.moveAdventurerToReserve(mainPartyListView.getSelectionModel().getSelectedItem());//Move adventurer to reserve
        guiService.populateListView(mainPartyListView, userData.getMainParty());
        guiService.populateListView(reservePartyListView, userData.getReserveParty());
    }
    @FXML
    private void useItemButtonClicked()
    {
        System.out.println("useItemButtonClicked() not yet implemented");
    }
}
