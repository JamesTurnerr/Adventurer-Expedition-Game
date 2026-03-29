package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import seng201.team0.Party;
import seng201.team0.services.AdventurerListViewService;
import seng201.team0.services.WindowSwitchService;
import seng201.team0.services.SetupService;
import seng201.team0.Adventurer;

public class GuildHallController implements Initializable {
    @FXML
    private Button backButton;
    @FXML
    private Button hireAdventurerButton;
    @FXML
    private ListView<Adventurer> HireableAdventurersListView;

    private Party party;
    private WindowSwitchService windowSwitchService = new WindowSwitchService();
    private SetupService setupService = new SetupService();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        /*List<Adventurer> adventurerList = setupService.getAllAdventurerList(3);
        for (Adventurer adventurer : adventurerList)
        {
            adventurerListView.getItems().add(adventurer);
        }*/
        party = Party.getInstance();
        AdventurerListViewService.fill(HireableAdventurersListView);

    }

    @FXML
    private void backButtonClicked() throws IOException {
        windowSwitchService.switchWindow((Stage) backButton.getScene().getWindow(), "/fxml/main_screen.fxml");
    }
    @FXML
    private void hireAdventurerButtonClicked()
    {
        //System.out.println("hireAdventurerButtonClicked() not yet implemented");

    }
}
