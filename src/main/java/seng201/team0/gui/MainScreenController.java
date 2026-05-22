package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

import seng201.team0.GameEnvironment;
import seng201.team0.services.GuiService;


/**
 * Controller for the main_screen.fxml window
 * From here the user can go to the Guild Hall, Market, Guild Overview, or Expeditions
 */
public class MainScreenController extends ScreenController {
    @FXML private Label goldAmountLabel, currentExpeditionLabel, expeditionsRemainingLabel, guildNameLabel;
    @FXML private Button mainSlot1Button, mainSlot2Button, mainSlot3Button, mainSlot4Button, mainSlot5Button;
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
        guildNameLabel.setText(getGameEnvironment().getGuildName() + " Guild");
        guiService.updateTopLabels(
                goldAmountLabel,
                currentExpeditionLabel,
                expeditionsRemainingLabel
        );
        adventurerSlots = List.of(mainSlot1Button, mainSlot2Button, mainSlot3Button, mainSlot4Button, mainSlot5Button);
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

