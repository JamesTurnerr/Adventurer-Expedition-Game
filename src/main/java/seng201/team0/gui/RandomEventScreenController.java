package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Adventurer;
import seng201.team0.models.Item;
import seng201.team0.services.RandomEventService;

import java.util.List;
import java.util.Random;

public class RandomEventScreenController extends ScreenController{
    public RandomEventScreenController(GameEnvironment gameEnvironment) {
        super(gameEnvironment);
    }
    RandomEventService randomEventService = new RandomEventService(getGameEnvironment());

    @FXML TextArea eventTextArea;


    @Override
    protected String getFxmlFile() {return "/fxml/random_event_screen.fxml";}

    @Override
    protected String getTitle() {return "Special Event";}

    @FXML
    private void onContinueButtonClicked() {
        getGameEnvironment().goToMainScreen();
    }

    @FXML
    public void initialize()
    {
        eventTextArea.setText(randomEventService.generateRandomEvent());
    }


}
