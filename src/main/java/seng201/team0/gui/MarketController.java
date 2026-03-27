package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

import seng201.team0.services.WindowSwitchService;
import seng201.team0.Item;

public class MarketController {
    @FXML
    private Button backButton;
    @FXML
    private Button buyItemButton;
    @FXML
    private ListView<Item> itemListView;

    WindowSwitchService windowSwitchService = new WindowSwitchService();

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
