package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import seng201.team0.GameEnvironment;
import seng201.team0.models.Adventurer;
import seng201.team0.models.Item;
import seng201.team0.services.DisplayStatsService;
import seng201.team0.services.GuiService;
import seng201.team0.services.GuildOverviewService;

import java.util.List;

public class GuildOverviewScreenController extends ScreenController {
    @FXML
    private Button backButton, moveToMainButton, moveFromMainButton, useItemButton;
    @FXML
    private ListView<Adventurer> reservePartyListView;
    @FXML
    private ListView<Item> itemsListView;
    @FXML
    private Label nameLabel, healthLabel, staminaLabel, perceptionLabel, costLabel, payLabel, damageLabel;
    @FXML Label goldAmountLabel, currentExpeditionLabel, expeditionsRemainingLabel;
    @FXML Button slot1Button, slot2Button, slot3Button,  slot4Button, slot5Button;

    private List<Button> adventurerSlots;
    private int selectedAdventurerSlot = -1;

    private final GameEnvironment gameEnvironment = getGameEnvironment();
    private final GuiService guiService = new GuiService(gameEnvironment);
    private final GuildOverviewService guildOverviewService = new GuildOverviewService(gameEnvironment);
    private final DisplayStatsService displayStatsService = new DisplayStatsService();

    GuildOverviewScreenController(GameEnvironment gameEnvironment) {super(gameEnvironment);}

    @Override
    protected String getFxmlFile() {
        return "/fxml/guild_overview.fxml";
    }

    @Override
    protected String getTitle() {
        return "Guild Overview";
    }

    public void initialize()
    {
        guiService.updateTopLabels(goldAmountLabel, currentExpeditionLabel, expeditionsRemainingLabel);
        adventurerSlots = List.of(slot1Button, slot2Button, slot3Button, slot4Button, slot5Button);
        updateGUI();
        adventurerSelectionListView(reservePartyListView);
        adventurerSelectionButtons();
    }
    @FXML
    private void backButtonClicked() {
        gameEnvironment.goToMainScreen();
    }
    @FXML
    private void moveToMainButtonClicked()
    {
        guildOverviewService.moveAdventurerToMain(reservePartyListView.getSelectionModel().getSelectedItem());//Move adventurer to main
        updateGUI();
    }
    @FXML
    private void moveFromMainButtonClicked()
    {
        if (selectedAdventurerSlot != -1) {
            guildOverviewService.moveAdventurerToReserve(gameEnvironment.getMainParty().get(selectedAdventurerSlot));//Move adventurer to reserve
            selectedAdventurerSlot -= 1;
            updateGUI();
        }

    }

    @FXML
    private void retireButtonClicked(){
        Adventurer selectedAdventurer = reservePartyListView.getSelectionModel().getSelectedItem();
        gameEnvironment.getReserveParty().remove(selectedAdventurer);
        updateGUI();
    }

    @FXML
    private void deleteItemButtonClicked(){
        Item item = itemsListView.getSelectionModel().getSelectedItem();
        gameEnvironment.getPlayerInventory().removeItem(item);
        updateGUI();
    }

    /**
     * Use an item on an adventurer removing it from the inventory
     */
    @FXML
    private void useItemButtonClicked()
    {
        //Adventurer adventurer = mainPartyListView.getSelectionModel().getSelectedItem();
        //Item item = itemsListView.getSelectionModel().getSelectedItem();
        //guildOverviewService.useItem(adventurer, item);
        //updateListViews();
    }

    /**
     * Update all the GUI elements
     */
    void updateGUI()
    {
        guiService.populateListView(reservePartyListView, gameEnvironment.getReserveParty());
        guiService.populateListView(itemsListView, gameEnvironment.getPlayerInventory().getAllItems());
        guiService.populateAdventurerSlots(adventurerSlots);
        if (selectedAdventurerSlot != -1)
        {
            adventurerSlots.get(selectedAdventurerSlot).setStyle("-fx-border-width: 3px; -fx-background-color:#1F2228;");
            displayStatsService.updateStats(
                    gameEnvironment.getMainParty().get(selectedAdventurerSlot), nameLabel, healthLabel, staminaLabel,
                    perceptionLabel, costLabel, payLabel, damageLabel);
        }
    }

    /**
     * Updates stats labels based on the adventurer selected
     * @param listView the ListView containing adventurers that will have their stats visible on the labels when selected
     */
    private void adventurerSelectionListView(ListView<Adventurer> listView) {
        listView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> {
                    if (newVal != null) {
                        selectedAdventurerSlot = -1;
                        updateGUI();
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
     * Updates stats labels based on the adventurer selected
     */
    private void adventurerSelectionButtons() {
        List<Adventurer> mainParty = gameEnvironment.getMainParty();
        for (int i = 0; i < adventurerSlots.size(); i++) {
            int index = i;

            int finalI = i;
            adventurerSlots.get(i).setOnAction(e -> {
                System.out.println("Selected slot: " + index);
                selectedAdventurerSlot = finalI;
                Adventurer adv = mainParty.get(index);
                if (adv != null) {
                    updateGUI();
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
}
