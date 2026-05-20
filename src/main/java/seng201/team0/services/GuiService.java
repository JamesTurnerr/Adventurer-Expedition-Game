package seng201.team0.services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Adventurer;

import java.io.IOException;
import java.util.List;

/**
 * Service class for GUI elements
 */
public class GuiService {
    GameEnvironment gameEnvironment;
    /**
     * Set up the GUIService class
     * @param gameEnvironment pass in a reference to the GameEnvironment to be able to link GUI elements to the GameEnvironment
     */
    public GuiService(GameEnvironment gameEnvironment)
    {
        this.gameEnvironment = gameEnvironment;
    }
    /**
     * Populate a ListView with given data
     * @param listView the ListView to be populated
     * @param data the elements to be added to the ListView
     */
    public <T> void populateListView(ListView<T> listView, List<T> data)//Must be same type
    {
        listView.getItems().clear();
        for (T t : data)
        {
            listView.getItems().add(t);
        }
    }
    /**
     * Switch to a different scene in the game
     * @param stage the stage of the window
     * @param fxmlWindowPath the file path to the fxml window
     */
    public void switchWindow(Stage stage, String fxmlWindowPath) throws IOException {
        FXMLLoader baseLoader = new FXMLLoader(getClass().getResource(fxmlWindowPath));
        Parent root = baseLoader.load();
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
    }
    /**
     * Updates the Gold, Current expedition number, and Expeditions remaining labels
     * @param gold a reference to the gold label
     * @param expedition a reference to the current expedition label
     * @param remaining a reference to the expeditions remaining label
     */
    public void updateTopLabels(Label gold, Label expedition, Label remaining) {
        gold.setText(String.valueOf(gameEnvironment.getGold()));
        expedition.setText(String.valueOf(gameEnvironment.getExpeditionsCompleted()));
        remaining.setText(String.valueOf(gameEnvironment.getExpeditionsRemaining()));
    }
    /**
     * Populates the buttons containing the current adventurer party names
     * Sets the color of filled and unfilled adventurer slots
     * @param slots a reference to a list of the buttons containing the adventurers names
     */
    public void populateAdventurerSlots(List<Button> slots) {
        List<Adventurer> party = gameEnvironment.getMainParty();

        for (int i = 0; i < slots.size(); i++) {
            Button slot = slots.get(i);

            if (party != null && i < party.size() && party.get(i) != null) {
                slot.setText(party.get(i).getName());
                slot.setStyle("-fx-background-color:#1F2228");
            } else {
                slot.setText("Empty");
                slot.setStyle("-fx-background-color:#0D1117");
            }
        }
    }
}
