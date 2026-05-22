package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import seng201.team0.GameEnvironment;
import seng201.team0.models.Item;
import seng201.team0.services.DisplayStatsService;
import seng201.team0.services.GuiService;
import seng201.team0.services.MarketService;

public class MarketScreenController extends ScreenController {
    @FXML private Button buyItemButton;
    @FXML private ListView<Item> marketInventoryListView;
    @FXML private ListView<Item> playerInventoryListView;

    @FXML private Label goldAmountLabel;
    @FXML private Label currentExpeditionLabel;
    @FXML private Label expeditionsRemainingLabel;

    @FXML private Label nameLabel;
    @FXML private Label effectLabel;
    @FXML private Label costLabel;

    private final GuiService guiService = new GuiService(getGameEnvironment());
    private final MarketService marketService = new MarketService(getGameEnvironment());
    private final DisplayStatsService displayStatsService = new DisplayStatsService();

    private boolean buyMode = true;//true is player is buying an item, false if player is selling

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

        // initialize the list views
        updateListViews();

        // checks for if item in lists is selected
        itemSelection(marketInventoryListView);
        itemSelection(playerInventoryListView);
    }

    @FXML
    private void backButtonClicked() {getGameEnvironment().goToMainScreen();}

    /**
     * Called when the user clicks on the player inventory
     * Updates the buy/sell button to display 'sell' allowing the user to sell items from their inventory
     * Also updates the labels displaying the items price, effect and cost
     */
    @FXML
    private void playerInventorySelected()
    {
        if (playerInventoryListView.getSelectionModel().getSelectedIndex() != -1)//Make sure an item is selected
        {
            buyMode = false;
            buyItemButton.setText("Sell");
            itemSelection(playerInventoryListView);
        }

        System.out.println("Mouse clicked");
    }

    /**
     * Called when the user clicks on the market inventory
     * Updates the buy/sell button to display 'buy' allowing the user to buy items from the market
     * Also updates the labels displaying the items price, effect and cost
     */
    @FXML
    private void marketInventorySelected()
    {
        if (marketInventoryListView.getSelectionModel().getSelectedIndex() != -1)//Make sure an item is selected
        {
            buyMode = true;
            buyItemButton.setText("Buy");
            itemSelection(marketInventoryListView);
        }
        System.out.println("Mouse clicked");
    }
    /**
     * If an item was successfully purchased update the market inventory ListView and update players gold
     */
    @FXML
    private void buyItemButtonClicked()
    {
        if (buyMode) { marketService.buyItem(marketInventoryListView.getSelectionModel().getSelectedItem(), 0.75f); }
        else { marketService.sellItem(playerInventoryListView.getSelectionModel().getSelectedItem(), 0.75f); }
        updateListViews();
        guiService.updateTopLabels(goldAmountLabel, currentExpeditionLabel, expeditionsRemainingLabel);
    }

    /**
     * Repopulate the market and player inventory ListViews
     */
    void updateListViews()
    {
        guiService.populateListView(marketInventoryListView, getGameEnvironment().getMarketInventory().getAllItems());
        guiService.populateListView(playerInventoryListView, getGameEnvironment().getPlayerInventory().getAllItems());
    }

    /**
     * Updates stats for items in list on selection
     * @param listView The ListView to be modified
     */
    private void itemSelection(ListView<Item> listView) {
        Item item = listView.getSelectionModel().getSelectedItem();
        if (item != null)
        {
            displayStatsService.updateItemStats(listView.getSelectionModel().getSelectedItem(), nameLabel, effectLabel, costLabel);
        }

    }
}
