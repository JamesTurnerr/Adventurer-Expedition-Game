package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seng201.team0.GameEnvironment;
import seng201.team0.services.GameOverService;
import seng201.team0.services.RandomEventService;

public class GameOverScreenController extends ScreenController {
    @FXML private Label expeditionsSelectedLabel;
    @FXML private Label expeditionsCompletedLabel;
    @FXML private Label totalGoldLabel;
    @FXML private Label goldLabel;
    @FXML private Label guildLabel;
    @FXML private Label outcomeLabel;

    public GameOverScreenController(GameEnvironment gameEnvironment)
    {
        super(gameEnvironment);
    }
    GameOverService gameOverService = new GameOverService(getGameEnvironment());
    GameEnvironment game = getGameEnvironment();

    @Override
    protected String getFxmlFile() { return "/fxml/game_over.fxml"; }

    @Override
    protected String getTitle() { return "Game Over"; }

    @FXML
    public void initialize() {
        outcomeLabel.setText(gameOverService.getOutcomeString());
        guildLabel.setText("The "+game.getGuildName()+" Guild.");
        expeditionsSelectedLabel.setText(String.valueOf(game.getExpeditionsCompleted()+game.getExpeditionsRemaining()));
        expeditionsCompletedLabel.setText(String.valueOf(game.getExpeditionsCompleted()));
        totalGoldLabel.setText(String.valueOf(game.getTotalGold()));
        goldLabel.setText(String.valueOf(game.getGold()));
    }

    @FXML
    private void onQuitPressed(){
        game.onQuitRequested();
    }
}
