package seng201.team0.gui;

import seng201.team0.GameEnvironment;

public class GameOverScreenController extends ScreenController {

    public GameOverScreenController(GameEnvironment gameEnvironment)
    {
        super(gameEnvironment);
    }

    @Override
    protected String getFxmlFile() { return "/fxml/game_over.fxml"; }

    @Override
    protected String getTitle() { return "Game Over"; }
}
