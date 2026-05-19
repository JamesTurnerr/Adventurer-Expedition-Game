package seng201.team0.services;

import seng201.team0.GameEnvironment;
import seng201.team0.models.Adventurer;

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

    /**
     * Checks if the game should end
     */
    public boolean isGameOver() {

        return getTotalPartyMembers() == 0
                || !canAffordAnyAdventurer();
    }

    /**
     * Counts all owned adventurers
     */
    private int getTotalPartyMembers() {
        return gameEnvironment.getMainParty().size()
                + gameEnvironment.getReserveParty().size();
    }

    /**
     * Checks if player can afford at least one adventurer in guild hall
     */
    private boolean canAffordAnyAdventurer() {

        for (Adventurer adventurer : gameEnvironment.getHireableAdventurers()) {

            System.out.println("can i afford"+adventurer);
            if (gameEnvironment.getGold() >= adventurer.getHiringCost()) {
                return true;
            }
        }
        return false;
    }


}
