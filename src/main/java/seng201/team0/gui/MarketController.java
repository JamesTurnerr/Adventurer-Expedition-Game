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

import seng201.team0.services.GuiService;
import seng201.team0.services.SetupService;
import seng201.team0.models.Item;
import seng201.team0.models.UserData;

public class MarketController implements Initializable {
    @FXML
    private Button backButton, buyItemButton;
    @FXML
    private ListView<Item> itemListView;

    private GuiService guiService = new GuiService();
    private SetupService setupService = new SetupService();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        List<Item> itemList = setupService.getTestItemList(3);
        guiService.populateListView(itemListView, itemList);
    }

    @FXML
    private void backButtonClicked() throws IOException {
        guiService.switchWindow((Stage) backButton.getScene().getWindow(), "/fxml/main_screen.fxml");
    }
    @FXML
    private void buyItemButtonClicked()
    {
        UserData.addItem(itemListView.getSelectionModel().getSelectedItem());
        itemListView.getItems().remove(itemListView.getSelectionModel().getSelectedItem());
    }
}
