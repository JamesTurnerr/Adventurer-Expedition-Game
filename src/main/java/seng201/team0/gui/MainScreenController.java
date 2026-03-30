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
import seng201.team0.models.UserData;


/**
 * Controller for the main_screen.fxml window
 * From here the user can go to the Guild Hall, Market, Guild Overview, or Expeditions
 */
public class MainScreenController implements Initializable {
    @FXML
    private Label goldAmountLabel, currentExpeditionLabel, expeditionsRemainingLabel;
    @FXML
    private Button goToGuildHallButton, goToGuildOverviewButton, goToMarketButton, goOnExpeditionButton;

    GuiService guiService = new GuiService();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        goldAmountLabel.setText("Gold: " + String.valueOf(UserData.getGold()));
        currentExpeditionLabel.setText("Current Expedition: " + String.valueOf(UserData.getCurrentExpeditionNumber()));
        expeditionsRemainingLabel.setText("Expeditions Remaining: " + String.valueOf(UserData.getExpeditionsRemaining()));
    }
    @FXML
    private void goToGuildHallButtonClicked() throws IOException {
        guiService.switchWindow((Stage) goToGuildHallButton.getScene().getWindow(), "/fxml/guild_hall.fxml");
    }
    @FXML
    private void goToGuildOverviewButtonClicked() throws IOException {
        guiService.switchWindow((Stage) goToGuildOverviewButton.getScene().getWindow(), "/fxml/guild_overview.fxml");
    }
    @FXML
    private void goToMarketButtonClicked() throws IOException {
        guiService.switchWindow((Stage) goToMarketButton.getScene().getWindow(), "/fxml/market.fxml");
    }
    @FXML
    private void goOnExpeditionButtonClicked() throws IOException {
        guiService.switchWindow((Stage) goOnExpeditionButton.getScene().getWindow(), "/fxml/expedition.fxml");
    }
}

