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

public class GuiService {
    public <T> void populateListView(ListView<T> listView, List<T> data)//Must be same type
    {
        listView.getItems().clear();
        for (T t : data)
        {
            listView.getItems().add(t);
        }
    }
    public void switchWindow(Stage stage, String fxmlWindowPath) throws IOException {
        FXMLLoader baseLoader = new FXMLLoader(getClass().getResource(fxmlWindowPath));
        Parent root = baseLoader.load();
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
    }
    public void updateTopLabels(Label gold, Label expedition, Label remaining, GameEnvironment gameEnvironment) {
        gold.setText(String.valueOf(gameEnvironment.getGold()));
        expedition.setText(String.valueOf(gameEnvironment.getCurrentExpeditionNumber()));
        remaining.setText(String.valueOf(gameEnvironment.getExpeditionsRemaining()));
    }

    public void populateAdventurerSlots(List<Button> slots, GameEnvironment gameEnvironment) {
        List<Adventurer> party = gameEnvironment.getMainParty();

        for (int i = 0; i < slots.size(); i++) {
            Button slot = slots.get(i);

            if (party != null && i < party.size() && party.get(i) != null) {
                slot.setText(party.get(i).getName());
            } else {
                slot.setText("Empty");
            }
        }
    }
}
