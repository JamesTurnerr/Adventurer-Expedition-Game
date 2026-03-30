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
import seng201.team0.Adventurer;
import seng201.team0.services.ExpeditionService;

public class ExpeditionController implements Initializable {
    @FXML
    private Button expeditionOption1Button, expeditionOption2Button;
    @FXML
    private TextArea expeditionTextArea;
    @FXML
    private Label   adventurerNameLabel1, adventurerStaminaLabel1,
                    adventurerNameLabel2, adventurerStaminaLabel2,
                    adventurerNameLabel3, adventurerStaminaLabel3,
                    adventurerNameLabel4, adventurerStaminaLabel4,
                    adventurerNameLabel5, adventurerStaminaLabel5;

    private GuiService guiService = new GuiService();
    private SetupService setupService = new SetupService();
    private ExpeditionService expeditionService;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        expeditionService = new ExpeditionService(expeditionTextArea);//'initialize' is called after 'expeditionTextArea' is created so 'new ExpeditionService' has to go here
        expeditionService.writeLine("Text line 1");
        expeditionService.writeLine("Text line 2");
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
