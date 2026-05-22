package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Adventurer;
import seng201.team0.services.AdventurerCreationService;
import seng201.team0.services.DisplayStatsService;
import seng201.team0.services.SetupService;

import java.util.ArrayList;
import java.util.List;

public class SetupScreenController extends ScreenController {
    @FXML private TextField guildInputTextField;
    @FXML private MenuButton difficultyMenuButton;
    @FXML private ListView<Adventurer> availableAdventurersListView;
    @FXML private Slider expeditionCountSlider;
    @FXML Label numberOfExpeditionsLabel, errorLabel;

    private final SetupService setupService = new SetupService(getGameEnvironment());
    private final DisplayStatsService displayStatsService = new DisplayStatsService();

    //Character stat labels
    @FXML private Label nameLabel, healthLabel, staminaLabel, perceptionLabel, costLabel, payLabel, damageLabel;
    @FXML private Button slot1Button, slot2Button, slot3Button;

    private List<Button> selectedAdventurerButtons;
    private List<Adventurer> mainParty;

    private int selectedSlotIndex = -1;

    SetupScreenController(GameEnvironment gameEnvironment) {super(gameEnvironment);}

    @Override
    protected String getFxmlFile() {
        return "/fxml/setup.fxml";
    }

    @Override
    protected String getTitle() {
        return "Setup";
    }

    /**
     * Initialize the setup screen fxml elements
     */
    public void initialize() {
        setupService.fillStarterAdventurerList(availableAdventurersListView, 5);
        errorLabel.setText("");
        //Add listener to slider
        expeditionCountSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            numberOfExpeditionsLabel.setText("Number of Expeditions: (" + newVal.intValue() + ")");
        });

        selectedAdventurerButtons = List.of(slot1Button, slot2Button, slot3Button);

        // sets the slot index
        for (int i = 0; i < selectedAdventurerButtons.size(); i++) {
            int index = i;

            selectedAdventurerButtons.get(i).setOnAction(e -> {
                selectedSlotIndex = index;

                Adventurer adv = mainParty.get(selectedSlotIndex);
                if (adv != null) {
                    displayStatsService.updateStats(
                            adv,
                            nameLabel,
                            healthLabel,
                            staminaLabel,
                            perceptionLabel,
                            costLabel,
                            payLabel
                    );
                }
            });
        }

        // updates stats for list
        availableAdventurersListView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> {
                    if (newVal != null) {
                        //updateStats(newVal);
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

        mainParty = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            mainParty.add(null);
        }
    }

    // difficulty affects the base state range for adventurers (excluding already generated)
    @FXML private void easyModeSelected() {difficultyMenuButton.setText("Easy");
        AdventurerCreationService.setAverageStatValue(125);}
    @FXML private void normalModeSelected() {difficultyMenuButton.setText("Normal");
        AdventurerCreationService.setAverageStatValue(100);}
    @FXML private void hardModeSelected() {difficultyMenuButton.setText("Hard");
        AdventurerCreationService.setAverageStatValue(75);}

    /**
     * Checks all fields are appropriately set when start button is pressed
     */
    @FXML
    private void startButtonClicked() {
        String errorMessage = setupService.checkInputs(
                guildInputTextField.getText(),
                difficultyMenuButton.getText(),
                mainParty,
                (int)expeditionCountSlider.getValue());
        if(errorMessage.isEmpty())
        {
            getGameEnvironment().onSetupComplete(mainParty,
                    difficultyMenuButton.getText(),
                    guildInputTextField.getText(),
                    (int)expeditionCountSlider.getValue());
        }
        else
        {
            errorLabel.setText(errorMessage);
        }
    }

    /**
     * Attempt to update fxml elements when an adventurer is chosen
     */
    @FXML
    private void chooseAdventurerClicked() {
        Adventurer selectedAdventurer = availableAdventurersListView.getSelectionModel().getSelectedItem();
        System.out.println(selectedAdventurer);


        // if player doesn't select slot, auto select
        if (selectedSlotIndex == -1) {
            for (int i = 0; i < mainParty.size(); i++) {
                if (mainParty.get(i) == null) {
                    selectedSlotIndex = i;
                    break;
                }
            }
            if (selectedSlotIndex == -1) {
                System.out.println("Party is full!");
                return;
            }
        }

        if (mainParty.size() >= selectedSlotIndex){
            if ( mainParty.get(selectedSlotIndex) != null) {
                System.out.println("This slot is already selected!");
                return;
            }
        }
        availableAdventurersListView.getItems().remove(selectedAdventurer);
        mainParty.set(selectedSlotIndex, selectedAdventurer);
        updateSlotButton(selectedSlotIndex, selectedAdventurer);
        selectedSlotIndex = -1;
    }

    /**
     * Attempt to update fxml elements when an adventurer is removed from chosen
     */
    @FXML
    private void unchooseAdventurerClicked() {
        if (selectedSlotIndex == -1) {
            System.out.println("No slot selected!");
            return;
        }
        Adventurer removed = mainParty.get(selectedSlotIndex);
        if (removed == null) {
            System.out.println("Slot already empty!");
            return;
        }
        availableAdventurersListView.getItems().add(removed);
        mainParty.set(selectedSlotIndex, null);
        updateSlotButton(selectedSlotIndex, null);
        selectedSlotIndex = -1;
    }

    /**
     * Update the chosen adventurer slots
     */
    private void updateSlotButton(int index, Adventurer adventurer) {
        Button button = selectedAdventurerButtons.get(index);
        if (adventurer == null) {
            button.setText("Empty");
            button.setStyle("-fx-background-color: #0D1117;");
        } else {
            button.setText(adventurer.getName());
            button.setStyle("-fx-background-color: #1F2228;");
        }
    }

}
