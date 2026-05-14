package seng201.team0.gui;

import javafx.fxml.FXML;
import seng201.team0.GameEnvironment;

public class EventScreenController extends ScreenController{
    public EventScreenController(GameEnvironment gameEnvironment) {
        super(gameEnvironment);
    }

    @Override
    protected String getFxmlFile() {return "/fxml/event_screen.fxml";}

    @Override
    protected String getTitle() {return "Special Event";}

    @FXML
    private void onContinueButtonClicked() {
        getGameEnvironment().goToMainScreen();
    }
}
