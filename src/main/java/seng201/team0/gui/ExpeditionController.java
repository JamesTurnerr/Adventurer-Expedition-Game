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
import seng201.team0.Adventurer;

public class ExpeditionController implements Initializable {
    @FXML
    private Button expeditionOption1Button;
    @FXML
    private Button expeditionOption2Button;
    @FXML
    private TextArea expeditionTextArea;
    @FXML
    private Label adventurerNameLabel1;
    @FXML
    private Label adventurerStaminaLabel1;
    @FXML
    private Label adventurerNameLabel2;
    @FXML
    private Label adventurerStaminaLabel2;
    @FXML
    private Label adventurerNameLabel3;
    @FXML
    private Label adventurerStaminaLabel3;
    @FXML
    private Label adventurerNameLabel4;
    @FXML
    private Label adventurerStaminaLabel4;
    @FXML
    private Label adventurerNameLabel5;
    @FXML
    private Label adventurerStaminaLabel5;

    private WindowSwitchService windowSwitchService = new WindowSwitchService();
    private SetupService setupService = new SetupService();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        expeditionTextArea.setText("aa");
    }

    @FXML
    private void expeditionOption1ButtonClicked() {
        System.out.println("option1ButtonClicked");
    }
    @FXML
    private void expeditionOption2ButtonClicked() {
        System.out.println("option2ButtonClicked");
    }
}
