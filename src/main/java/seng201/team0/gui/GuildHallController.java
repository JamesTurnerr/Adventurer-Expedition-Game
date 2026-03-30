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
import seng201.team0.services.GuiService;
import seng201.team0.services.SetupService;
import seng201.team0.Adventurer;

public class GuildHallController implements Initializable {
    @FXML
    private Button backButton, hireAdventurerButton;
    @FXML
    private ListView<Adventurer> hireableAdventurersListView;

    private Party party;
    private final GuiService guiService = new GuiService();
    private final SetupService setupService = new SetupService();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        party = Party.getInstance();
        AdventurerListViewService.fill(hireableAdventurersListView);

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
        if (selectedAdventurer != null && party.addToParty(selectedAdventurer)){
            //lose moolah
            System.out.println("added "+selectedAdventurer+" to party");
            hireableAdventurersListView.getItems().remove(selectedAdventurer);
            //need to add an undo + way to see party
            // need to add a test case for party full
        }
        else{
            System.out.println("your party might be full gang");
        }
    }
}
