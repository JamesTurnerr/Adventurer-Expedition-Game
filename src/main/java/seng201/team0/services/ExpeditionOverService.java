package seng201.team0.services;

import seng201.team0.GameEnvironment;

import java.util.Random;

/**
 * Service class to handle the post expedition flow
 */
public class ExpeditionOverService {
    private final GameEnvironment gameEnvironment;
    private final Random rand = new Random();

    public ExpeditionOverService(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Handles everything that happens after an expedition finishes
     */
    public void completeExpedition() {

        giveCompletionReward();

        payMainParty();

        gameEnvironment.setExpeditionsCompleted(
                gameEnvironment.getExpeditionsCompleted() + 1
        );

        gameEnvironment.setRemainingExpeditionNumber(
                gameEnvironment.getExpeditionsRemaining() - 1
        );

        gameEnvironment.updateBuyableAdventurers();
        gameEnvironment.updateMarketInventory();
        gameEnvironment.updateAvailableExpeditionLocations();

        recoverReserveParty();

        handleScreenTransition();
    }

    /**
     * Give gold reward based on difficulty
     */
    private void giveCompletionReward() {

        int goldReward = (int) (
                50 * (1 / gameEnvironment.getDifficultyModifier())
        );

        gameEnvironment.setGold(
                gameEnvironment.getGold() + goldReward
        );

        gameEnvironment.addTotalGold(goldReward);
    }

    /**
     * Pay all adventurers in the main party
     * Increments the expeditions in a row
     */
    private void payMainParty() {

        for (var adventurer : gameEnvironment.getMainParty()) {

            adventurer.incrementExpeditionsInARow();

            gameEnvironment.setGold(
                    gameEnvironment.getGold() - adventurer.getPay()
            );

            if (gameEnvironment.getGold() < 0) {

                System.out.println(
                        "You owe " + adventurer.getName() + " money!"
                );
            }
        }
    }

    /**
     * Recover reserve party stamina
     * Resets the expeditions in a row
     */
    private void recoverReserveParty() {

        for (var adventurer : gameEnvironment.getReserveParty()) {

            int currentStam = adventurer.getStamina();

            int newStam = (int) (
                    currentStam + 20 / gameEnvironment.getDifficultyModifier()
            );
            adventurer.resetExpeditionsInARow();

            adventurer.setStamina(newStam);
        }
    }

    /**
     * Decide if random event will take place
     */
    private void handleScreenTransition() {

        if (gameEnvironment.getExpeditionsRemaining() == 0) {

            gameEnvironment.goToGameOverScreen();

        } else {

            if (rand.nextInt(100) < gameEnvironment.getEventChance()) {

                gameEnvironment.goToRandomEventScreen();

            } else {

                gameEnvironment.goToMainScreen();
            }
        }
    }

}
