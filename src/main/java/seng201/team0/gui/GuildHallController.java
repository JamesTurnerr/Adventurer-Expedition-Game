package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import seng201.team0.models.UserData;
import seng201.team0.services.GuiService;
import seng201.team0.services.GuildHallService;
import seng201.team0.models.Adventurer;

public class GuildHallController implements Initializable {
    @FXML
    private Button backButton, hireAdventurerButton;
    @FXML
    private ListView<Adventurer> hireableAdventurersListView;

    private final GuiService guiService = new GuiService();
    private final GuildHallService guildHallService = new GuildHallService();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        guildHallService.fillAdventurerList(hireableAdventurersListView, 5);
    }

    @FXML
    private void backButtonClicked() throws IOException {
        guiService.switchWindow((Stage) backButton.getScene().getWindow(), "/fxml/main_screen.fxml");
    }

    // this is similar to the other button in setupcontroller.
    // should merge with the 2 listviews as the parameters
    @FXML
    private void hireAdventurerButtonClicked()
    {
        Adventurer selectedAdventurer = hireableAdventurersListView.getSelectionModel().getSelectedItem();
        if (UserData.hireAdventurer(selectedAdventurer))
        {
            hireableAdventurersListView.getItems().remove(selectedAdventurer);
        }
    }
}
