package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import seng201.team0.models.UserData;
import seng201.team0.services.DisplayStatsService;
import seng201.team0.services.GuiService;
import seng201.team0.services.GuildHallService;
import seng201.team0.models.Adventurer;

public class GuildHallController implements Initializable {
    @FXML private Button backButton, hireAdventurerButton;
    @FXML private ListView<Adventurer> hireableAdventurersListView;
    @FXML private ListView<Adventurer> reservePartyListView;
    @FXML private Button slot1Button;
    @FXML private Button slot2Button;
    @FXML private Button slot3Button;
    @FXML private Button slot4Button;
    @FXML private Button slot5Button;
    private List<Button> adventurerSlots;
    @FXML private Label goldAmountLabel;
    @FXML private Label currentExpeditionLabel;
    @FXML private Label expeditionsRemainingLabel;

    private final DisplayStatsService displayStatsService = new DisplayStatsService();

    @FXML private Label nameLabel;
    @FXML private Label healthLabel;
    @FXML private Label staminaLabel;
    @FXML private Label perceptionLabel;
    @FXML private Label costLabel;
    @FXML private Label payLabel;
    @FXML private Label damageLabel;

    private final GuiService guiService = new GuiService();
    private final GuildHallService guildHallService = new GuildHallService();
    private List<Adventurer> mainParty = UserData.getMainParty();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        guildHallService.fillAdventurerList(hireableAdventurersListView, 5);

        guiService.updateTopLabels(
                goldAmountLabel,
                currentExpeditionLabel,
                expeditionsRemainingLabel
        );
        adventurerSlots = List.of(slot1Button, slot2Button, slot3Button, slot4Button, slot5Button);
        guiService.populateAdventurerSlots(adventurerSlots);

        // updates stats for list
        adventurerSelection(reservePartyListView);
        adventurerSelection(hireableAdventurersListView);

        // same as in setup
        for (int i = 0; i < adventurerSlots.size(); i++) {
            int index = i;

            adventurerSlots.get(i).setOnAction(e -> {
                System.out.println("Selected slot: " + index);

                Adventurer adv = mainParty.get(index);
                if (adv != null) {
                    //updateStats(adv);
                    displayStatsService.updateStats(
                            adv,
                            nameLabel,
                            healthLabel,
                            staminaLabel,
                            perceptionLabel,
                            costLabel,
                            payLabel,
                            damageLabel
                    );
                }
            });
        }

    }

    @FXML
    private void backButtonClicked() throws IOException {
        guiService.switchWindow((Stage) backButton.getScene().getWindow(), "/fxml/main_screen.fxml");
    }

    // this is similar to the other button in setupcontroller.
    // should merge with the 2 listviews as the parameters
    @FXML
    private void hireAdventurerButtonClicked()
    {
        Adventurer selectedAdventurer = hireableAdventurersListView.getSelectionModel().getSelectedItem();
        if (UserData.hireAdventurer(selectedAdventurer))
        {
            hireableAdventurersListView.getItems().remove(selectedAdventurer);
            reservePartyListView.getItems().add(selectedAdventurer);

            //update balance
            //int newGold = UserData.getGold() - selectedAdventurer.getHiringCost();
            //System.out.println("previous gold:"+UserData.getGold()+ " new gold:"+newGold);
            //UserData.setGold(newGold);
            goldAmountLabel.setText(String.valueOf(UserData.getGold()));
        }
    }

    private void adventurerSelection(ListView<Adventurer> listView) {
        listView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> {
                    if (newVal != null) {
                        displayStatsService.updateStats(
                                newVal,
                                nameLabel,
                                healthLabel,
                                staminaLabel,
                                perceptionLabel,
                                costLabel,
                                payLabel,
                                damageLabel
                        );
                    }
                }
        );
    }
}
