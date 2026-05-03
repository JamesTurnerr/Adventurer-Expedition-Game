package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import seng201.team0.models.Adventurer;
import seng201.team0.models.Item;
import seng201.team0.models.UserData;
import seng201.team0.services.DisplayStatsService;
import seng201.team0.services.GuiService;
import seng201.team0.services.SetupService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SetupController implements Initializable {
    @FXML private TextField guildInputTextField, expeditionInputTextField;
    @FXML private Button startButton;
    @FXML private MenuButton difficultyMenuButton;
    @FXML private MenuItem easyMenuItem, normalMenuItem, hardMenuItem;
    @FXML private ListView<Adventurer> availableAdventurersListView;
    @FXML private Button chooseAdventurerButton, unchooseAdventurerButton;

    private final SetupService setupService = new SetupService();
    private final GuiService guiService = new GuiService();

    @FXML private Label nameLabel;
    @FXML private Label healthLabel;
    @FXML private Label staminaLabel;
    @FXML private Label perceptionLabel;
    @FXML private Label costLabel;
    @FXML private Label payLabel;
    @FXML private Label damageLabel;

    @FXML private Button slot1Button;
    @FXML private Button slot2Button;
    @FXML private Button slot3Button;
    private List<Button> selectedAdventurerButtons;
    private final DisplayStatsService displayStatsService = new DisplayStatsService();

    private List<Adventurer> mainParty;

    private int selectedSlotIndex = -1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupService.fillStarterAdventurerList(availableAdventurersListView, 5);

        selectedAdventurerButtons = List.of(slot1Button, slot2Button, slot3Button);

        // sets the slot index
        for (int i = 0; i < selectedAdventurerButtons.size(); i++) {
            int index = i;

            selectedAdventurerButtons.get(i).setOnAction(e -> {
                selectedSlotIndex = index;

                System.out.println("Selected slot: " + selectedSlotIndex);

                Adventurer adv = mainParty.get(selectedSlotIndex);
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
                                payLabel,
                                damageLabel
                        );
                    }
                }
        );

        mainParty = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            mainParty.add(null);
        }
    }
    @FXML private void easyModeSelected() {difficultyMenuButton.setText("Easy");}
    @FXML private void normalModeSelected() {difficultyMenuButton.setText("Normal");}
    @FXML private void hardModeSelected() {difficultyMenuButton.setText("Hard");}

    @FXML
    private void startButtonClicked() throws IOException {
        if (mainParty.contains(null)) {
            System.out.println("please select 3");
            return;
        }

        if (!setupService.checkInputs(
                expeditionInputTextField.getText(),
                guildInputTextField.getText(),
                difficultyMenuButton.getText(),
                mainParty
        )) {
            return;
        }
        ArrayList<Adventurer> chosenAdventurersList =
                new ArrayList<>(mainParty);

        UserData userData = new UserData(
                chosenAdventurersList,
                difficultyMenuButton.getText(),
                guildInputTextField.getText(),
                Integer.parseInt(expeditionInputTextField.getText())
        );

        userData.addItem(Item.HEALTH_POTION);

        Stage stage = (Stage) startButton.getScene().getWindow();
        guiService.switchWindow(stage, "/fxml/main_screen.fxml");
    }

    @FXML
    private void chooseAdventurerClicked() {
        Adventurer selectedAdventurer = availableAdventurersListView.getSelectionModel().getSelectedItem();
        System.out.println(selectedAdventurer);


        // if player doesnt select slot, auto select
        if (selectedSlotIndex == -1) {
            for (int i = 0; i < mainParty.size(); i++) {
                if (mainParty.get(i) == null) {
                    selectedSlotIndex = i;
                    break;
                }
            }
            if (selectedSlotIndex == -1) {
                System.out.println("party is full");
                return;
            }
        }

        if (mainParty.size() >= selectedSlotIndex){
            if ( mainParty.get(selectedSlotIndex) != null) {
                System.out.println("already active");
                return;
            }
        }
        availableAdventurersListView.getItems().remove(selectedAdventurer);
        mainParty.set(selectedSlotIndex, selectedAdventurer);
        updateSlotButton(selectedSlotIndex, selectedAdventurer);
        selectedSlotIndex = -1;
    }

    @FXML
    private void unchooseAdventurerClicked() {
        if (selectedSlotIndex == -1) {
            System.out.println("nothing selected");
            return;
        }
        Adventurer removed = mainParty.get(selectedSlotIndex);
        if (removed == null) {
            System.out.println("slot already empty");
            return;
        }
        availableAdventurersListView.getItems().add(removed);
        mainParty.set(selectedSlotIndex, null);
        updateSlotButton(selectedSlotIndex, null);
        selectedSlotIndex = -1;
    }

    /*private void updateStats(Adventurer adventurer) {
        nameLabel.setText(adventurer.getName());
        healthLabel.setText(String.valueOf(adventurer.getHealth()));
        staminaLabel.setText(String.valueOf(adventurer.getStamina()));
        perceptionLabel.setText(String.valueOf(adventurer.getPerception()));
        costLabel.setText(String.valueOf(adventurer.getHiringCost()));
        payLabel.setText(String.valueOf(adventurer.getPay()));
        damageLabel.setText(String.valueOf(adventurer.getDamage()));
    }*/

    private void updateSlotButton(int index, Adventurer adventurer) {
        Button button = selectedAdventurerButtons.get(index);

        if (adventurer == null) {
            button.setText("Empty");
        } else {
            button.setText(adventurer.getName());
        }
    }

}
