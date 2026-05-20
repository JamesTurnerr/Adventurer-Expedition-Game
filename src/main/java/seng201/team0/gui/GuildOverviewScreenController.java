package seng201.team0.gui;

import javafx.beans.value.ChangeListener;
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

    //Used to update the stat labels when an adventurer is selected from the ReservePartyListView
    ChangeListener<Adventurer> selectionListener = (obs, oldVal, newVal) -> {
        if (newVal != null) {
            selectedAdventurerSlot = -1;
            updateAdventurerStatLabels(newVal);
        }
        updateSelectedAdventurerBorder();
    };

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
        reservePartyListView.getSelectionModel().selectedItemProperty().removeListener(selectionListener);
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
        Item item = itemsListView.getSelectionModel().getSelectedItem();
        Adventurer adventurer;
        if (selectedAdventurerSlot == -1)//Then selected adventurer is in reserve party
        {
            adventurer = reservePartyListView.getSelectionModel().getSelectedItem();
        }
        else
        {
            adventurer = gameEnvironment.getMainParty().get(selectedAdventurerSlot);

        }
        guildOverviewService.useItem(adventurer, item);
        updateAdventurerStatLabels(adventurer);
        updateGUI();
        //Adventurer adventurer = mainPartyListView.getSelectionModel().getSelectedItem();
        //Item item = itemsListView.getSelectionModel().getSelectedItem();
        //guildOverviewService.useItem(adventurer, item);
        //updateListViews();
    }

    /**
     * Update the GUI elements
     */
    void updateGUI()
    {
        guiService.populateListView(reservePartyListView, gameEnvironment.getReserveParty());
        guiService.populateListView(itemsListView, gameEnvironment.getPlayerInventory().getAllItems());
        guiService.populateAdventurerSlots(adventurerSlots);
        adventurerSelectionListView(reservePartyListView);
        updateSelectedAdventurerBorder();
    }

    /**
     * Updates stats labels based on the adventurer selected
     * @param listView the ListView containing adventurers that will have their stats visible on the labels when selected
     */
    private void adventurerSelectionListView(ListView<Adventurer> listView) {
        listView.getSelectionModel().selectedItemProperty().addListener(selectionListener);
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
                    updateAdventurerStatLabels(adv);
                }
            });
        }
    }

    /**
     * Updates the border around the selected adventurer button
     */
    void updateSelectedAdventurerBorder()
    {
        if (selectedAdventurerSlot != -1)
        {
            adventurerSlots.get(selectedAdventurerSlot).setStyle("-fx-border-width: 3px; -fx-background-color:#1F2228;");
            updateAdventurerStatLabels(gameEnvironment.getMainParty().get(selectedAdventurerSlot));
        }
        else
        {
            for (int i = 0; i < gameEnvironment.getMainParty().size(); i++)
            {
                adventurerSlots.get(i).setStyle("-fx-border-width: 1px;");
                if (gameEnvironment.getMainParty().get(i) != null)
                {
                    adventurerSlots.get(i).setStyle("-fx-background-color:#1F2228;");
                }
            }
        }
    }

    /**
     * Updates the stat labels to the stats of the given adventurer
     */
    void updateAdventurerStatLabels(Adventurer adventurer)
    {
        displayStatsService.updateStats(
                adventurer, nameLabel, healthLabel, staminaLabel,
                perceptionLabel, costLabel, payLabel, damageLabel);
    }

}
