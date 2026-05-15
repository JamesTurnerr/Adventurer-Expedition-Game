package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.List;

import seng201.team0.GameEnvironment;
import seng201.team0.services.DisplayStatsService;
import seng201.team0.services.GuiService;
import seng201.team0.services.GuildHallService;
import seng201.team0.models.Adventurer;

public class GuildHallScreenController extends ScreenController {
    @FXML private Button backButton, hireAdventurerButton;
    @FXML private ListView<Adventurer> hireableAdventurersListView;
    @FXML private ListView<Adventurer> reservePartyListView;
    @FXML private Button slot1Button, slot2Button, slot3Button, slot4Button, slot5Button;
    private List<Button> adventurerSlots;
    @FXML private Label goldAmountLabel, currentExpeditionLabel, expeditionsRemainingLabel;
    //Character stat labels
    @FXML private Label nameLabel, healthLabel, staminaLabel, perceptionLabel, costLabel, payLabel, damageLabel;


    private GameEnvironment game = getGameEnvironment();
    private final GuiService guiService = new GuiService(game);
    private final GuildHallService guildHallService = new GuildHallService(game);
    private final DisplayStatsService displayStatsService = new DisplayStatsService();
    private List<Adventurer> mainParty = game.getMainParty();


    public GuildHallScreenController(GameEnvironment gameEnvironment)
    {
        super(gameEnvironment);
    }

    @Override
    protected String getFxmlFile() {
        return "/fxml/guild_hall.fxml";
    }

    @Override
    protected String getTitle() {
        return "Guild Hall";
    }

    public void initialize()
    {
        guiService.populateListView(hireableAdventurersListView, guildHallService.getAdventurerList());

        guiService.updateTopLabels(
                goldAmountLabel,
                currentExpeditionLabel,
                expeditionsRemainingLabel
        );
        adventurerSlots = List.of(slot1Button, slot2Button, slot3Button, slot4Button, slot5Button);
        guiService.populateAdventurerSlots(adventurerSlots);

        // updates stats for list
        reservePartyListView.getItems().addAll(game.getReserveParty());//initialize reservePartyListView with current reserve party
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
        game.goToMainScreen();
    }

    // this is similar to the other button in setupcontroller.
    // should merge with the 2 listviews as the parameters
    @FXML
    private void hireAdventurerButtonClicked()
    {
        Adventurer selectedAdventurer = hireableAdventurersListView.getSelectionModel().getSelectedItem();
        if (game.hireAdventurer(selectedAdventurer))
        {
            hireableAdventurersListView.getItems().remove(selectedAdventurer);
            reservePartyListView.getItems().add(selectedAdventurer);
            game.getHireableAdventurers().remove(selectedAdventurer);

            goldAmountLabel.setText(String.valueOf(game.getGold()));
        }
    }

    @FXML
    private void retireAdventurerButtonClicked(){
        Adventurer selectedAdventurer = reservePartyListView.getSelectionModel().getSelectedItem();
        reservePartyListView.getItems().remove(selectedAdventurer);
        game.getReserveParty().remove(selectedAdventurer);
    }

    //updates stats for items in list on selection
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
