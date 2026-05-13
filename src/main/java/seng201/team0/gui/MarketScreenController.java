package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

import seng201.team0.GameEnvironment;
import seng201.team0.services.GuiService;
import seng201.team0.services.MarketService;
import seng201.team0.services.SetupService;
import seng201.team0.models.Item;

public class MarketScreenController extends ScreenController {
    @FXML
    private Button backButton, buyItemButton;
    @FXML
    private ListView<Item> itemListView;

    private final GuiService guiService = new GuiService(getGameEnvironment());
    private final MarketService marketService = new MarketService(getGameEnvironment());

    MarketScreenController(GameEnvironment gameEnvironment) {super(gameEnvironment);}

    @Override
    protected String getFxmlFile() {
        return "/fxml/market.fxml";
    }

    @Override
    protected String getTitle() {
        return "Market";
    }

    public void initialize()
    {

        guiService.populateListView(itemListView, marketService.getTestItemList(3));
    }

    @FXML
    private void backButtonClicked() throws IOException {getGameEnvironment().goToMainScreen();}
    @FXML
    private void buyItemButtonClicked()
    {
        if(getGameEnvironment().buyItem(itemListView.getSelectionModel().getSelectedItem()))
        {
            Item item = itemListView.getSelectionModel().getSelectedItem();
            itemListView.getItems().remove(item);
            getGameEnvironment().getMarketItems().remove(item);
        }
    }
}
