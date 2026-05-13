package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

import seng201.team0.GameEnvironment;
import seng201.team0.models.Adventurer;
import seng201.team0.models.Item;
import seng201.team0.services.GuiService;

public class GuildOverviewScreenController extends ScreenController {
    @FXML
    private Button backButton, moveToMainButton, moveFromMainButton, useItemButton;
    @FXML
    private ListView<Adventurer> reservePartyListView;
    @FXML
    private ListView<Adventurer> mainPartyListView;
    @FXML
    private ListView<Item> itemsListView;

    private final GuiService guiService = new GuiService(getGameEnvironment());

    GuildOverviewScreenController(GameEnvironment gameEnvironment) {super(gameEnvironment);}

    @Override
    protected String getFxmlFile() {
        return "/fxml/guild_overview.fxml";
    }

    @Override
    protected String getTitle() {
        return "Guild Overview";
    }

    public void initialize()
    {
        guiService.populateListView(mainPartyListView, getGameEnvironment().getMainParty());
        guiService.populateListView(reservePartyListView, getGameEnvironment().getReserveParty());
        guiService.populateListView(itemsListView, getGameEnvironment().getItems());
    }
    @FXML
    private void backButtonClicked() throws IOException {
        getGameEnvironment().goToMainScreen();
    }
    @FXML
    private void moveToMainButtonClicked()
    {
        getGameEnvironment().moveAdventurerToMain(reservePartyListView.getSelectionModel().getSelectedItem());//Move adventurer to main
        //Update ListViews
        guiService.populateListView(mainPartyListView, getGameEnvironment().getMainParty());
        guiService.populateListView(reservePartyListView, getGameEnvironment().getReserveParty());
    }
    @FXML
    private void moveFromMainButtonClicked()
    {
        getGameEnvironment().moveAdventurerToReserve(mainPartyListView.getSelectionModel().getSelectedItem());//Move adventurer to reserve
        guiService.populateListView(mainPartyListView, getGameEnvironment().getMainParty());
        guiService.populateListView(reservePartyListView, getGameEnvironment().getReserveParty());
    }
    @FXML
    private void useItemButtonClicked()
    {
        Adventurer adventurer = mainPartyListView.getSelectionModel().getSelectedItem();
        Item item = itemsListView.getSelectionModel().getSelectedItem();
        getGameEnvironment().useItem(adventurer, item);
        guiService.populateListView(itemsListView, getGameEnvironment().getItems());
        //System.out.println("useItemButtonClicked() not yet implemented");
    }
}
