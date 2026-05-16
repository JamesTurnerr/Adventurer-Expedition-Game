package seng201.team0.services;

import seng201.team0.GameEnvironment;

public class GameOverService {
    private final GameEnvironment gameEnvironment;

    public GameOverService(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    public String getOutcomeString() {

        if (gameEnvironment.getGold() > 0) {
            return "You completed all the expeditions!";
        }

        return "Your guild failed to make money.";
    }
}
