package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import seng201.team0.GameEnvironment;
import seng201.team0.services.RandomEventService;

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

    /**
     * Send the player back to the main screen
     */
    @FXML
    private void onContinueButtonClicked() {
        randomEventService.nextScreen();
    }

    /**
     * Initialize the random event screen with a randomly chosen post expedition event
     */
    @FXML
    public void initialize()
    {
        eventTextArea.setText(randomEventService.generateRandomEvent());
    }


}
