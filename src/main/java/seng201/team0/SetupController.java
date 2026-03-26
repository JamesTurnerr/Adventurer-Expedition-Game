package seng201.team0;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SetupController {
    @FXML
    private TextField guildInputTextField;
    @FXML
    private TextField expeditionInputTextField;
    @FXML
    private Button startButton;
    @FXML
    private MenuButton difficultyMenuButton;
    @FXML
    private MenuItem easyMenuItem;
    @FXML
    private MenuItem normalMenuItem;
    @FXML
    private MenuItem hardMenuItem;

    @FXML
    private void onDifficultyClicked() {
        System.out.println("difficultyMenuButton");
    }
    @FXML
    private void onButtonClicked() {
        System.out.println("Start button clicked");
        int numberOfExpeditions = 3;
        try {
            numberOfExpeditions = Integer.parseInt(expeditionInputTextField.getText());
        } catch (NumberFormatException e) {
            System.out.println("Number of expeditions is not an integer");
        }
        if (numberOfExpeditions > 15 | numberOfExpeditions < 3)
        {
            System.out.println("Number of expeditions must be between 3 and 15 inclusive");
        }
        else
        {
            if (guildInputTextField.getText() == "")
            {
                System.out.println("Please enter a guild name");
            }
            else
            {
                System.out.println(String.format("%s guild is going on %d %s expeditions", guildInputTextField.getText(), numberOfExpeditions, difficultyMenuButton.getText()));
            }
        }
    }
    @FXML
    private void easyModeSelected() {
        difficultyMenuButton.setText("Easy");
        System.out.println("Easy mode selected");
    }
    @FXML
    private void normalModeSelected() {
        difficultyMenuButton.setText("Normal");
        System.out.println("Normal mode selected");
    }
    @FXML
    private void hardModeSelected() {
        difficultyMenuButton.setText("Hard");
        System.out.println("Hard mode selected");
    }
}

