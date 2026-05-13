package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import seng201.team0.GameEnvironment;
import seng201.team0.models.Adventurer;
import seng201.team0.services.GuiService;
import seng201.team0.services.SetupService;
import seng201.team0.services.ExpeditionService;

import java.util.List;

public class ExpeditionScreenController extends ScreenController {
    @FXML
    private Button expeditionOption1Button, expeditionOption2Button;
    @FXML
    private TextArea expeditionTextArea;
    @FXML
    private  Label adventurerNameLabel1, adventurerStaminaLabel1, adventurerHealthLabel1,
                    adventurerNameLabel2, adventurerStaminaLabel2, adventurerHealthLabel2,
                    adventurerNameLabel3, adventurerStaminaLabel3, adventurerHealthLabel3,
                    adventurerNameLabel4, adventurerStaminaLabel4, adventurerHealthLabel4,
                    adventurerNameLabel5, adventurerStaminaLabel5, adventurerHealthLabel5;

    private final GuiService guiService = new GuiService(getGameEnvironment());
    private final SetupService setupService = new SetupService();
    private ExpeditionService expeditionService;
    public ExpeditionScreenController(GameEnvironment gameEnvironment) {super(gameEnvironment);}

    @Override
    protected String getFxmlFile() {
        return "/fxml/expedition.fxml";
    }

    @Override
    protected String getTitle() {
        return "Expedition";
    }

    public void initialize()
    {
        expeditionService = new ExpeditionService(getGameEnvironment(), expeditionTextArea, 5);
        updateLabels();
    }

    @FXML
    private void expeditionOption1ButtonClicked() {
        expeditionService.button1Clicked();
        updateLabels();
    }
    @FXML
    private void expeditionOption2ButtonClicked() {
        expeditionService.button2Clicked();
        updateLabels();
    }

    private void updateLabels()
    {
        List<Label> adventurerNameLabels = List.of(adventurerNameLabel1, adventurerNameLabel2, adventurerNameLabel3, adventurerNameLabel4, adventurerNameLabel5);
        List<Label> adventurerStaminaLabels = List.of(adventurerStaminaLabel1, adventurerStaminaLabel2, adventurerStaminaLabel3, adventurerStaminaLabel4, adventurerStaminaLabel5);
        List<Label> adventurerHealthLabels = List.of(adventurerHealthLabel1, adventurerHealthLabel2, adventurerHealthLabel3, adventurerHealthLabel4, adventurerHealthLabel5);
        List<Adventurer> adventurerList = getGameEnvironment().getMainParty();
        for (int i = 0; i < adventurerList.size(); i++)//Update labels
        {
            adventurerNameLabels.get(i).setText(adventurerList.get(i).getName());
            adventurerStaminaLabels.get(i).setText("Stamina: " + adventurerList.get(i).getStamina());
            adventurerHealthLabels.get(i).setText("Health: " + adventurerList.get(i).getHealth());
        }
        for (int i = adventurerList.size(); i < getGameEnvironment().MAX_PARTY_SIZE; i++)//Set rest of labels to blank
        {
            adventurerNameLabels.get(i).setText("");
            adventurerStaminaLabels.get(i).setText("");
            adventurerHealthLabels.get(i).setText("");
        }
    }
}
