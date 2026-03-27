package seng201.team0;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class MainScreenController {
    @FXML
    private Label goldAmountLabel;
    @FXML
    private Label currentExpeditionLabel;
    @FXML
    private Label expeditionsRemainingLabel;
    @FXML
    private Button goToGuildHallButton;
    @FXML
    private Button goToGuildOverviewButton;
    @FXML
    private Button goToMarketButton;
    @FXML
    private Button goOnExpeditionButton;

    @FXML
    private void goToGuildHallButtonClicked()
    {
        System.out.println("goToGuildHall");
    }
    @FXML
    private void goToGuildOverviewButtonClicked()
    {
        System.out.println("goToGuildOverview");
    }
    @FXML
    private void goToMarketButtonClicked()
    {
        System.out.println("goToMarket");
    }
    @FXML
    private void goOnExpeditionButtonClicked()
    {
        System.out.println("goOnExpedition");
    }
}

