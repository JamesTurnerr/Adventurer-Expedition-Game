package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

import seng201.team0.services.GuiService;


/**
 * Controller for the main_screen.fxml window
 * From here the user can go to the Guild Hall, Market, Guild Overview, or Expeditions
 */
public class MainScreenController {
    @FXML
    private Label goldAmountLabel;
    @FXML
    private Label currentExpeditionLabel;
    @FXML
    private Label expeditionsRemainingLabel;
    @FXML
    private Button goToGuildHallButton;
    @FXML
    private Button goToGuildOverviewButton;
    @FXML
    private Button goToMarketButton;
    @FXML
    private Button goOnExpeditionButton;

    GuiService guiService = new GuiService();

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

