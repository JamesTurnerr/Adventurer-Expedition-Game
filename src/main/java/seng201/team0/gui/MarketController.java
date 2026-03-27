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
import java.util.List;
import java.util.ResourceBundle;

import seng201.team0.services.WindowSwitchService;
import seng201.team0.services.SetupService;
import seng201.team0.Item;

public class MarketController implements Initializable {
    @FXML
    private Button backButton;
    @FXML
    private Button buyItemButton;
    @FXML
    private ListView<Item> itemListView;

    private WindowSwitchService windowSwitchService = new WindowSwitchService();
    private SetupService setupService = new SetupService();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        List<Item> itemList = setupService.getTestItemList(3);
        for (Item item : itemList)
        {
            itemListView.getItems().add(item);
        }
    }

    @FXML
    private void backButtonClicked() throws IOException {
        windowSwitchService.switchWindow((Stage) backButton.getScene().getWindow(), "/fxml/main_screen.fxml");
    }
    @FXML
    private void buyItemButtonClicked()
    {
        System.out.println("buyItemButtonClicked() not yet implemented");
    }
}
