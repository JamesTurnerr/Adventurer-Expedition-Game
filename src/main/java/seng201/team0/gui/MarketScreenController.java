package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.List;

import seng201.team0.GameEnvironment;
import seng201.team0.models.Adventurer;
import seng201.team0.services.DisplayStatsService;
import seng201.team0.services.GuiService;
import seng201.team0.services.MarketService;
import seng201.team0.services.SetupService;
import seng201.team0.models.Item;

public class MarketScreenController extends ScreenController {
    @FXML private Button backButton, buyItemButton;
    @FXML private ListView<Item> itemListView;
    @FXML private ListView<Item> inventoryListView;

    @FXML private Label goldAmountLabel;
    @FXML private Label currentExpeditionLabel;
    @FXML private Label expeditionsRemainingLabel;

    @FXML private Label nameLabel;
    @FXML private Label effectLabel;
    @FXML private Label costLabel;

    private final GuiService guiService = new GuiService(getGameEnvironment());
    private final MarketService marketService = new MarketService(getGameEnvironment());
    private final DisplayStatsService displayStatsService = new DisplayStatsService();

    MarketScreenController(GameEnvironment gameEnvironment) {super(gameEnvironment);}

    @Override
    protected String getFxmlFile() {
        return "/fxml/market.fxml";
    }

    @Override
    protected String getTitle() {
        return "Market";
    }

    /**
     * Initialize top labels and ListViews
     */
    public void initialize()
    {
        guiService.updateTopLabels(goldAmountLabel, currentExpeditionLabel, expeditionsRemainingLabel);

        // inintialize the list views
        updateListViews();

        // checks for if item in lists is selected
        itemSelection(itemListView);
        itemSelection(inventoryListView);
    }

    @FXML
    private void backButtonClicked() {getGameEnvironment().goToMainScreen();}

    /**
     * If an item was successfully purchased update the market inventory ListView and update players gold
     */
    @FXML
    private void buyItemButtonClicked()
    {
        if(marketService.buyItem(itemListView.getSelectionModel().getSelectedItem()))
        {
            updateListViews();
            guiService.updateTopLabels(goldAmountLabel, currentExpeditionLabel, expeditionsRemainingLabel);
        }
    }

    /**
     * Repopulate the market and player inventory ListViews
     */
    void updateListViews()
    {
        guiService.populateListView(itemListView, getGameEnvironment().getMarketInventory().getAllItems());
        guiService.populateListView(inventoryListView, getGameEnvironment().getPlayerInventory().getAllItems());
    }

    /**
     * Updates stats for items in list on selection
     * @param listView The ListView to be modified
     */
    private void itemSelection(ListView<Item> listView) {
        listView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> {
                    if (newVal != null) {
                        displayStatsService.updateItemStats(
                                newVal,
                                nameLabel,
                                effectLabel,
                                costLabel
                        );
                    }
                }
        );
    }
}
