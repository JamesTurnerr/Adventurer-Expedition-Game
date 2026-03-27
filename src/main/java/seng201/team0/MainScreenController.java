package seng201.team0;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    @FXML
    private void goToGuildHallButtonClicked() throws IOException {
        FXMLLoader baseLoader = new FXMLLoader(getClass().getResource("/fxml/guild_hall.fxml"));
        Parent root = baseLoader.load();
        Stage window = (Stage) goToGuildHallButton.getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        window.setScene(scene);
    }
    @FXML
    private void goToGuildOverviewButtonClicked() throws IOException {
        FXMLLoader baseLoader = new FXMLLoader(getClass().getResource("/fxml/guild_overview.fxml"));
        Parent root = baseLoader.load();
        Stage window = (Stage) goToGuildOverviewButton.getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        window.setScene(scene);
    }
    @FXML
    private void goToMarketButtonClicked() throws IOException {
        FXMLLoader baseLoader = new FXMLLoader(getClass().getResource("/fxml/market.fxml"));
        Parent root = baseLoader.load();
        Stage window = (Stage) goToMarketButton.getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        window.setScene(scene);
    }
    @FXML
    private void goOnExpeditionButtonClicked()
    {
        System.out.println("goOnExpeditionButtonClicked() not yet implemented");
    }
}

