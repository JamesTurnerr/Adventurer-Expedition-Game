package seng201.team0.gui;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import seng201.team0.GameEnvironment;
import seng201.team0.models.Adventurer;
import seng201.team0.models.Item;
import seng201.team0.models.RegularItem;
import seng201.team0.services.DisplayStatsService;
import seng201.team0.services.GuiService;
import seng201.team0.services.GuildOverviewService;

import java.util.List;

/**
 * Controller class for guild overview screen gui components
 */
public class GuildOverviewScreenController extends ScreenController {
    @FXML
    private ListView<Adventurer> reservePartyListView;
    @FXML
    private ListView<Item> itemsListView;
    @FXML
    private Label nameLabel, healthLabel, staminaLabel, perceptionLabel, costLabel, payLabel;
    @FXML Label goldAmountLabel, currentExpeditionLabel, expeditionsRemainingLabel;
    @FXML Button slot1Button, slot2Button, slot3Button,  slot4Button, slot5Button;
    @FXML Label errorLabel;

    private List<Button> adventurerSlots;
    private int selectedAdventurerSlot = -1;
    private Item selecteditem = null;

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

    /**
     * Initialize GUI elements, setting listviews, buttons, labels, and adding listeners
     */
    public void initialize()
    {
        errorLabel.setText("");
        guiService.populateListView(reservePartyListView, gameEnvironment.getReserveParty());
        guiService.populateListView(itemsListView, gameEnvironment.getPlayerInventory().getAllItems());
        itemsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selecteditem = newValue;
            updateAdventurerStatLabels(getCurrentSelectedAdventurer());
        });
        guiService.updateTopLabels(goldAmountLabel, currentExpeditionLabel, expeditionsRemainingLabel);
        adventurerSlots = List.of(slot1Button, slot2Button, slot3Button, slot4Button, slot5Button);
        updateGUI();
        adventurerSelectionListView(reservePartyListView);
        adventurerSelectionButtons();
    }

    /**
     * returns player to main screen
     */
    @FXML
    private void backButtonClicked() {
        gameEnvironment.goToMainScreen();
    }

    /**
     * moves selected adventurer from the reserve party to the main party
     */
    @FXML
    private void moveToMainButtonClicked()
    {
        reservePartyListView.getSelectionModel().selectedItemProperty().removeListener(selectionListener);
        if(guildOverviewService.moveAdventurerToMain(reservePartyListView.getSelectionModel().getSelectedItem()))
        {
            guiService.populateListView(reservePartyListView, gameEnvironment.getReserveParty());
            updateGUI();
        }
        else
        {
            errorLabel.setText("No adventurer selected");
            System.out.println("Warning: No adventurer selected!");
        }
    }

    /**
     * moves selected adventurer from the main party to the reserve party
     */
    @FXML
    private void moveFromMainButtonClicked()
    {
        if (selectedAdventurerSlot != -1 && selectedAdventurerSlot < gameEnvironment.getMainParty().size()) {
            if(!guildOverviewService.moveAdventurerToReserve(gameEnvironment.getMainParty().get(selectedAdventurerSlot)))
            {
                errorLabel.setText("Could not move adventurer");
            }
            else
            {
                selectedAdventurerSlot -= 1;
                guiService.populateListView(reservePartyListView, gameEnvironment.getReserveParty());
                updateGUI();
            }
        }
        else
        {
            errorLabel.setText("No adventurer selected");
            System.out.println("Warning: No adventurer selected!");
        }

    }

    /**
     * permanently removes an item from the players inventory
     */
    @FXML
    private void deleteItemButtonClicked(){
        Item item = itemsListView.getSelectionModel().getSelectedItem();
        if (item != null){
            gameEnvironment.getPlayerInventory().removeItem(item);
            guiService.populateListView(itemsListView, gameEnvironment.getPlayerInventory().getAllItems());
            updateGUI();
        }
        else{
            System.out.println("Warning: No item selected!");
            errorLabel.setText("No item selected");
        }
    }

    /**
     * Use an item on an adventurer removing it from the inventory
     */
    @FXML
    private void useItemButtonClicked()
    {
        Adventurer adventurer = getCurrentSelectedAdventurer();
        Item item = itemsListView.getSelectionModel().getSelectedItem();

        if (guildOverviewService.useItem(adventurer, item))
        {
            guiService.populateListView(itemsListView, gameEnvironment.getPlayerInventory().getAllItems());
            updateAdventurerStatLabels(adventurer);
            updateGUI();
        }
        else
        {
            errorLabel.setText("No item selected");
        }
    }

    /**
     * Update the GUI elements
     */
    void updateGUI()
    {
        errorLabel.setText("");
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
        for (int i = 0; i < adventurerSlots.size(); i++) {
            int index = i;

            adventurerSlots.get(i).setOnAction(e -> {
                // Check if slot contains an adventurer
                if (index < gameEnvironment.getMainParty().size()) {
                    guiService.populateListView(reservePartyListView, gameEnvironment.getReserveParty());
                    selectedAdventurerSlot = index;

                    Adventurer adv = gameEnvironment.getMainParty().get(index);

                    updateGUI();
                    updateAdventurerStatLabels(adv);
                } else {
                    // If selected slot is invalid
                    selectedAdventurerSlot = -1;

                    updateGUI();
                    updateAdventurerStatLabels(null);
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
            if (selectedAdventurerSlot < gameEnvironment.getMainParty().size())
            {
                adventurerSlots.get(selectedAdventurerSlot).setStyle("-fx-border-width: 3px; -fx-background-color:#1F2228;");
                updateAdventurerStatLabels(gameEnvironment.getMainParty().get(selectedAdventurerSlot));
            }
            else
            {
                adventurerSlots.get(selectedAdventurerSlot).setStyle("-fx-border-width: 3px;");
                updateAdventurerStatLabels(null);
            }

        }
        else
        {
            for (int i = 0; i < adventurerSlots.size(); i++)
            {
                adventurerSlots.get(i).setStyle("-fx-border-width: 1px;");
                if (i < gameEnvironment.getMainParty().size())
                {
                    adventurerSlots.get(i).setStyle("-fx-background-color:#1F2228;");
                }
            }
        }
    }

    /**
     * Updates the stat labels to the stats of the given adventurer
     * @param adventurer the adventurer whose stats are being updated
     */
    void updateAdventurerStatLabels(Adventurer adventurer)
    {
        displayStatsService.updateStats(adventurer, nameLabel, healthLabel, staminaLabel, perceptionLabel, costLabel, payLabel);

        if (selecteditem instanceof RegularItem regularItem)
        {
            guildOverviewService.applyItemPreview(adventurer, regularItem, healthLabel, staminaLabel);
        }
    }

    /**
     * Gets the current selected adventurer
     * @return The selected adventurer if selected, null otherwise
     */
    private Adventurer getCurrentSelectedAdventurer()
    {
        return guildOverviewService.getCurrentSelectedAdventurer(
                selectedAdventurerSlot,
                reservePartyListView.getSelectionModel().getSelectedItem()
        );
    }

}
