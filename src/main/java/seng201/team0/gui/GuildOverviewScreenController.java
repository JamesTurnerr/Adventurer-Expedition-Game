package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

import seng201.team0.GameEnvironment;
import seng201.team0.models.Adventurer;
import seng201.team0.models.Item;
import seng201.team0.services.GuiService;
import seng201.team0.services.GuildOverviewService;

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
    private final GuildOverviewService guildOverviewService = new GuildOverviewService(getGameEnvironment());

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
        updateListViews();
    }
    @FXML
    private void backButtonClicked() throws IOException {
        getGameEnvironment().goToMainScreen();
    }
    @FXML
    private void moveToMainButtonClicked()
    {
        guildOverviewService.moveAdventurerToMain(reservePartyListView.getSelectionModel().getSelectedItem());//Move adventurer to main
        updateListViews();
    }
    @FXML
    private void moveFromMainButtonClicked()
    {
        guildOverviewService.moveAdventurerToReserve(mainPartyListView.getSelectionModel().getSelectedItem());//Move adventurer to reserve
        updateListViews();
    }
    @FXML
    private void useItemButtonClicked()
    {
        Adventurer adventurer = mainPartyListView.getSelectionModel().getSelectedItem();
        Item item = itemsListView.getSelectionModel().getSelectedItem();
        guildOverviewService.useItem(adventurer, item);
        updateListViews();
    }

    private void updateListViews()
    {
        guiService.populateListView(mainPartyListView, getGameEnvironment().getMainParty());
        guiService.populateListView(reservePartyListView, getGameEnvironment().getReserveParty());
        guiService.populateListView(itemsListView, getGameEnvironment().getPlayerInventory().getAllItems());
    }
}
