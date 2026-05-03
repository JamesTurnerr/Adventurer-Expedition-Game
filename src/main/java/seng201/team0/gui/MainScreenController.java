package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import seng201.team0.models.Adventurer;
import seng201.team0.services.GuiService;
import seng201.team0.models.UserData;


/**
 * Controller for the main_screen.fxml window
 * From here the user can go to the Guild Hall, Market, Guild Overview, or Expeditions
 */
public class MainScreenController implements Initializable {
    @FXML private Label goldAmountLabel, currentExpeditionLabel, expeditionsRemainingLabel;
    @FXML private Button
            goToGuildHallButton,
            goToGuildOverviewButton,
            goToMarketButton,
            goOnExpeditionButton;
    @FXML private Button slot1Button;
    @FXML private Button slot2Button;
    @FXML private Button slot3Button;
    @FXML private Button slot4Button;
    @FXML private Button slot5Button;
    private List<Button> adventurerSlots;

    private final GuiService guiService = new GuiService();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        guiService.updateTopLabels(
                goldAmountLabel,
                currentExpeditionLabel,
                expeditionsRemainingLabel
        );
        adventurerSlots = List.of(slot1Button, slot2Button, slot3Button, slot4Button, slot5Button);
        guiService.populateAdventurerSlots(adventurerSlots);
    }
    @FXML
    private void goToGuildHallButtonClicked() throws IOException {
        guiService.switchWindow((Stage) goToGuildHallButton.getScene().getWindow(), "/fxml/guild_hall.fxml");
    }
    @FXML
    private void goToGuildOverviewButtonClicked() throws IOException {
        guiService.switchWindow((Stage) goToGuildOverviewButton.getScene().getWindow(), "/fxml/guild_overview.fxml");
    }
    @FXML
    private void goToMarketButtonClicked() throws IOException {
        guiService.switchWindow((Stage) goToMarketButton.getScene().getWindow(), "/fxml/market.fxml");
    }
    @FXML
    private void goOnExpeditionButtonClicked() throws IOException {
        guiService.switchWindow((Stage) goOnExpeditionButton.getScene().getWindow(), "/fxml/expedition.fxml");
    }
}

