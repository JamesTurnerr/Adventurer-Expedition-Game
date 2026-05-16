package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.List;

import seng201.team0.GameEnvironment;
import seng201.team0.services.GuiService;


/**
 * Controller for the main_screen.fxml window
 * From here the user can go to the Guild Hall, Market, Guild Overview, or Expeditions
 */
public class MainScreenController extends ScreenController {
    @FXML private Label goldAmountLabel, currentExpeditionLabel, expeditionsRemainingLabel;
    @FXML private Button
            goToGuildHallButton,
            goToGuildOverviewButton,
            goToMarketButton,
            goOnExpeditionButton;
    @FXML private Button slot1Button, slot2Button, slot3Button, slot4Button, slot5Button;
    private List<Button> adventurerSlots;

    private final GuiService guiService = new GuiService(getGameEnvironment());

    MainScreenController(GameEnvironment gameEnvironment) {super(gameEnvironment);}

    @Override
    protected String getFxmlFile() {
        return "/fxml/main_screen.fxml";
    }

    @Override
    protected String getTitle() {
        return "Main Screen";
    }

    /**
     * Update labels and populate adventurer slots
     */
    public void initialize()
    {
        guiService.updateTopLabels(
                goldAmountLabel,
                currentExpeditionLabel,
                expeditionsRemainingLabel
        );
        adventurerSlots = List.of(slot1Button, slot2Button, slot3Button, slot4Button, slot5Button);
        guiService.populateAdventurerSlots(adventurerSlots);
    }

    /**
     * Switch screens to guild hall screen
     */
    @FXML
    private void goToGuildHallButtonClicked() {
        getGameEnvironment().goToGuildHallScreen();
    }

    /**
     * Switch screens to guild overview screen
     */
    @FXML
    private void goToGuildOverviewButtonClicked() {
        getGameEnvironment().goToGuildOverviewScreen();
    }

    /**
     * Switch screens to market screen
     */
    @FXML
    private void goToMarketButtonClicked() {
        getGameEnvironment().goToMarketScreen();
    }

    /**
     * Switch screens to expedition selection screen
     */
    @FXML
    private void goOnExpeditionButtonClicked() {
        if (getGameEnvironment().getExpeditionsRemaining() > 0){
            getGameEnvironment().goToExpeditionSelectScreen();
        }
    }
}

