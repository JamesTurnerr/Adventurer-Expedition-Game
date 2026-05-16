package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Expedition;
import seng201.team0.services.ExpeditionSelectService;
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
    private final ExpeditionSelectService expeditionSelectService = new ExpeditionSelectService(getGameEnvironment());
    private final Expedition expedition = new Expedition();

    public void initialize(){
        List<Integer> locations = getGameEnvironment().getExpeditionLocations();

        location1Button.setText(expedition.getAreaNames(locations.get(0)));
        location2Button.setText(expedition.getAreaNames(locations.get(1)));
        location3Button.setText(expedition.getAreaNames(locations.get(2)));
    }

    @FXML
    private void location1Pressed() { expeditionSelectService.selectLocation(expedition, 0); }

    @FXML
    private void location2Pressed() {
        expeditionSelectService.selectLocation(expedition, 1);
    }

    @FXML
    private void location3Pressed() {
        expeditionSelectService.selectLocation(expedition, 2);
    }

    @FXML
    private void returnPressed() {
        getGameEnvironment().goToMainScreen();
    }


}

