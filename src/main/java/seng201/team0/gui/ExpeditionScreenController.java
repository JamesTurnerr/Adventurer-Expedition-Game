package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import seng201.team0.GameEnvironment;
import seng201.team0.services.GuiService;
import seng201.team0.services.SetupService;
import seng201.team0.services.ExpeditionService;

public class ExpeditionScreenController extends ScreenController {
    @FXML
    private Button expeditionOption1Button, expeditionOption2Button;
    @FXML
    private TextArea expeditionTextArea;
    @FXML
    private Label   adventurerNameLabel1, adventurerStaminaLabel1,
                    adventurerNameLabel2, adventurerStaminaLabel2,
                    adventurerNameLabel3, adventurerStaminaLabel3,
                    adventurerNameLabel4, adventurerStaminaLabel4,
                    adventurerNameLabel5, adventurerStaminaLabel5;

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
        expeditionService = new ExpeditionService(expeditionTextArea, 5);
        expeditionService.nextEvent();
    }

    @FXML
    private void expeditionOption1ButtonClicked() {
        System.out.println("option1ButtonClicked");
    }
    @FXML
    private void expeditionOption2ButtonClicked() {
        System.out.println("option2ButtonClicked");
    }
}
