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
    @FXML private ListView<Adventurer> hireableAdventurersListView;
    @FXML private ListView<Adventurer> reservePartyListView;
    @FXML private Button slot1Button, slot2Button, slot3Button, slot4Button, slot5Button;
    private List<Button> adventurerSlots;
    @FXML private Label goldAmountLabel, currentExpeditionLabel, expeditionsRemainingLabel;
    //Character stat labels
    @FXML private Label nameLabel, healthLabel, staminaLabel, perceptionLabel, costLabel, payLabel;
    @FXML private Label errorLabel;


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


        guiService.updateTopLabels(
                goldAmountLabel,
                currentExpeditionLabel,
                expeditionsRemainingLabel
        );
        adventurerSlots = List.of(slot1Button, slot2Button, slot3Button, slot4Button, slot5Button);
        updateSlots();
        updateGUI();
        adventurerSelection(reservePartyListView);
        adventurerSelection(hireableAdventurersListView);
    }



    @FXML
    private void backButtonClicked() {
        gameEnvironment.goToMainScreen();
    }

    @FXML
    private void hireAdventurerButtonClicked()
    {
        Adventurer selectedAdventurer =
                hireableAdventurersListView.getSelectionModel().getSelectedItem();

        String error = guildHallService.hireAdventurer(selectedAdventurer);

        if (error == null)
        {
            gameEnvironment.getHireableAdventurers().remove(selectedAdventurer);
            updateGUI();
        }
        else
        {
            errorLabel.setText(error);
        }
    }

    @FXML
    private void retireAdventurerButtonClicked()
    {
        Adventurer selectedAdventurer = reservePartyListView.getSelectionModel().getSelectedItem();

        if (guildHallService.retireAdventurer(selectedAdventurer))
        {
            updateGUI();
        }
        else
        {
            errorLabel.setText("No adventurer selected");
        }
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
                                payLabel
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
        errorLabel.setText("");
        guiService.populateListView(hireableAdventurersListView,  gameEnvironment.getHireableAdventurers());
        guiService.populateListView(reservePartyListView,  gameEnvironment.getReserveParty());
        goldAmountLabel.setText(String.valueOf(gameEnvironment.getGold()));
        guiService.populateAdventurerSlots(adventurerSlots);
    }


    private void updateSlots()
    {
        for (int i = 0; i < adventurerSlots.size(); i++)
        {
            int index = i;

            adventurerSlots.get(i).setOnAction(e -> {

                Adventurer adv =
                        guildHallService.getMainPartyAdventurer(index);

                displayStatsService.updateStats(
                        adv,
                        nameLabel,
                        healthLabel,
                        staminaLabel,
                        perceptionLabel,
                        costLabel,
                        payLabel
                );
            });
        }
    }
}
