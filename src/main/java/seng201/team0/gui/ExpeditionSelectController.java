package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import seng201.team0.GameEnvironment;

import java.util.ArrayList;

// need to add Screen to the name my bad
public class ExpeditionSelectController extends ScreenController {
    public ExpeditionSelectController(GameEnvironment gameEnvironment) {super(gameEnvironment);}

    @Override
    protected String getFxmlFile() {return "/fxml/expedition_selection.fxml";}
    @Override
    protected String getTitle() {return "Expedition Selection";}

    @FXML private Button location1Button;
    @FXML private Button location2Button;
    @FXML private Button location3Button;


    public void initialize(){
        // give 3 different locations
        location1Button.setText("his island");
    }

    @FXML
    private void location1Pressed() {
        System.out.println("Location 1 selected");
        // this needs to take the expeditions initial location
        getGameEnvironment().goToExpeditionScreen();
    }

    @FXML
    private void location2Pressed() {
        System.out.println("Location 2 selected");
        getGameEnvironment().goToExpeditionScreen();
    }

    @FXML
    private void location3Pressed() {
        System.out.println("Location 3 selected");
        getGameEnvironment().goToExpeditionScreen();
    }

    @FXML
    private void returnPressed() {
        getGameEnvironment().goToMainScreen();
    }
}

