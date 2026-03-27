package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

import seng201.team0.services.WindowSwitchService;

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

    WindowSwitchService windowSwitchService = new WindowSwitchService();

    @FXML
    private void goToGuildHallButtonClicked() throws IOException {
        windowSwitchService.switchWindow((Stage) goToGuildHallButton.getScene().getWindow(), "/fxml/guild_hall.fxml");
    }
    @FXML
    private void goToGuildOverviewButtonClicked() throws IOException {
        windowSwitchService.switchWindow((Stage) goToGuildOverviewButton.getScene().getWindow(), "/fxml/guild_overview.fxml");
    }
    @FXML
    private void goToMarketButtonClicked() throws IOException {
        windowSwitchService.switchWindow((Stage) goToMarketButton.getScene().getWindow(), "/fxml/market.fxml");
    }
    @FXML
    private void goOnExpeditionButtonClicked()
    {
        System.out.println("goOnExpeditionButtonClicked() not yet implemented");
    }
}

