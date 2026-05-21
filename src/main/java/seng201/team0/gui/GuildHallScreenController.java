package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

import seng201.team0.GameEnvironment;
import seng201.team0.services.DisplayStatsService;
import seng201.team0.services.GuiService;
import seng201.team0.services.GuildHallService;
import seng201.team0.models.Adventurer;

/**
 * GUI controller class for the guild hall screen
 */
public class GuildHallScreenController extends ScreenController {
    @FXML private Button backButton, hireAdventurerButton;
    @FXML private ListView<Adventurer> hireableAdventurersListView;
    @FXML private ListView<Adventurer> reservePartyListView;
    @FXML private Button slot1Button, slot2Button, slot3Button, slot4Button, slot5Button;
    private List<Button> adventurerSlots;
    @FXML private Label goldAmountLabel, currentExpeditionLabel, expeditionsRemainingLabel;
    //Character stat labels
    @FXML private Label nameLabel, healthLabel, staminaLabel, perceptionLabel, costLabel, payLabel, damageLabel;


    private GameEnvironment gameEnvironment = getGameEnvironment();
    private final GuiService guiService = new GuiService(gameEnvironment);
    private final GuildHallService guildHallService = new GuildHallService(gameEnvironment);
    private final DisplayStatsService displayStatsService = new DisplayStatsService();
    private List<Adventurer> mainParty = gameEnvironment.getMainParty();

    /**
     * Constructor to pass in game data
     * @param gameEnvironment reference to game data
     */
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

    /**
     * Initialize the guild halls labels, listViews, and buttons
     */
    public void initialize()
    {
        updateGUI();

        guiService.updateTopLabels(
                goldAmountLabel,
                currentExpeditionLabel,
                expeditionsRemainingLabel
        );
        adventurerSlots = List.of(slot1Button, slot2Button, slot3Button, slot4Button, slot5Button);
        guiService.populateAdventurerSlots(adventurerSlots);

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
    private void backButtonClicked() {
        gameEnvironment.goToMainScreen();
    }

    @FXML
    private void hireAdventurerButtonClicked()
    {
        Adventurer selectedAdventurer = hireableAdventurersListView.getSelectionModel().getSelectedItem();
        if (guildHallService.hireAdventurer(selectedAdventurer))
        {
            gameEnvironment.getHireableAdventurers().remove(selectedAdventurer);
            updateGUI();
        }
    }

    @FXML
    private void retireAdventurerButtonClicked(){
        Adventurer selectedAdventurer = reservePartyListView.getSelectionModel().getSelectedItem();
        gameEnvironment.getReserveParty().remove(selectedAdventurer);
        updateGUI();
    }

    /**
     * Updates stats labels based on the adventurer selected
     * @param listView the ListView containing adventurers that will have their stats visible on the labels when selected
     */
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

    /**
     * Update the ListViews when adventurers are being moved between them/removed
     */
    private void updateGUI()
    {
        guiService.populateListView(hireableAdventurersListView,  gameEnvironment.getHireableAdventurers());
        guiService.populateListView(reservePartyListView,  gameEnvironment.getReserveParty());
        goldAmountLabel.setText(String.valueOf(gameEnvironment.getGold()));
    }
}
