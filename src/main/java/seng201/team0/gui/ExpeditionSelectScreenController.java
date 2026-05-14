package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Expedition;

import java.util.Random;


public class ExpeditionSelectScreenController extends ScreenController {
    public ExpeditionSelectScreenController(GameEnvironment gameEnvironment) {super(gameEnvironment);}

    @Override
    protected String getFxmlFile() {return "/fxml/expedition_selection.fxml";}
    @Override
    protected String getTitle() {return "Expedition Selection";}

    @FXML private Button location1Button;
    @FXML private Button location2Button;
    @FXML private Button location3Button;

    private final Random rand = new Random();
    //made up thing to get some start locations
    private final Expedition tempExpedition = new Expedition(5);


    public void initialize(){
        // give 3 different locations
        int locCount = tempExpedition.ExpeditionLocation.length;

        location1Button.setText(tempExpedition.ExpeditionLocation[rand.nextInt(locCount)]);
        location2Button.setText(tempExpedition.ExpeditionLocation[rand.nextInt(locCount)]);
        location3Button.setText(tempExpedition.ExpeditionLocation[rand.nextInt(locCount)]);
    }

    @FXML
    private void location1Pressed() {
        selectLocation(location1Button.getText());
    }

    @FXML
    private void location2Pressed() {
        selectLocation(location2Button.getText());
    }

    @FXML
    private void location3Pressed() {
        selectLocation(location3Button.getText());
    }

    @FXML
    private void returnPressed() {
        getGameEnvironment().goToMainScreen();
    }

    private void selectLocation(String location) {
        System.out.println("Selected location: " + location);
        getGameEnvironment().setSelectedExpeditionLocation(location);
        getGameEnvironment().goToExpeditionScreen();
    }
}

