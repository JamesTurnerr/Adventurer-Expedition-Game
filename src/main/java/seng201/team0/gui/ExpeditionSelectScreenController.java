package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Expedition;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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

    int locCount = 3;//currently only 3 expeditions implemented so far. Increase as more implemented until max reached of tempExpedition.areaNames.length
    int firstExpeditionIndex;
    int secondExpeditionIndex;
    int thirdExpeditionIndex;


    public void initialize(){
        // give 3 different locations
        randomLocations();

        List<Integer> locations =
                getGameEnvironment().getExpeditionLocations();

        location1Button.setText(tempExpedition.getAreaNames(locations.get(0)));
        location2Button.setText(tempExpedition.getAreaNames(locations.get(1)));
        location3Button.setText(tempExpedition.getAreaNames(locations.get(2)));
    }

    // generates the random locations and prevents duplicates
    private void randomLocations() {

        if (getGameEnvironment().getDoUpdateLocations()) {

            getGameEnvironment().setDoUpdateLocations(false);

            Integer[] indices = new Integer[locCount];

            for (int i = 0; i < locCount; i++) {
                indices[i] = i;
            }

            Collections.shuffle(Arrays.asList(indices));

            getGameEnvironment().getExpeditionLocations().clear();

            for (int i = 0; i < 3; i++) {
                getGameEnvironment().getExpeditionLocations().add(indices[i]);
            }
        }
    }

    @FXML
    private void location1Pressed() {
        selectLocation(0);
    }

    @FXML
    private void location2Pressed() {
        selectLocation(1);
    }

    @FXML
    private void location3Pressed() {
        selectLocation(2);
    }

    @FXML
    private void returnPressed() {
        getGameEnvironment().goToMainScreen();
    }

    private void selectLocation(int index) {
        int expeditionIndex = getGameEnvironment().getExpeditionLocations().get(index);
        String location = tempExpedition.getAreaNames(expeditionIndex);
        System.out.println("Selected location: " + location);
        getGameEnvironment().setSelectedExpeditionLocation(location, expeditionIndex);
        getGameEnvironment().goToExpeditionScreen();

    }
}

