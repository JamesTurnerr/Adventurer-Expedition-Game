package seng201.team0.services;

import javafx.scene.control.Label;
import seng201.team0.GameEnvironment;
import seng201.team0.models.Adventurer;
import seng201.team0.models.RegularItem;

/**
 * Service class for guild overview logic
 */
public class GuildOverviewService {
    private final GameEnvironment gameEnvironment;

    /**
     * Constructor allowing game data to be passed in
     * @param gameEnvironment The game data to be passed in
     */
    public GuildOverviewService(GameEnvironment gameEnvironment) {this.gameEnvironment = gameEnvironment;}

    /**
     * Use an regularItem on an adventurer, removing it from the players inventory
     * @param adventurer The target adventurer
     * @param regularItem The regularItem to be used on the target adventurer
     */
    public void useItem(Adventurer adventurer, RegularItem regularItem)
    {
        if (adventurer == null || regularItem == null) {
            System.out.println("Warning: adventurer or item is null");
            return;
        }

        switch (regularItem) {
            case RUSTY_SWORD -> System.out.println("You gave "+ adventurer + " the "+ regularItem+"!");
            case SMALL_STAMINA_POTION, STAMINA_POTION, LARGE_STAMINA_POTION -> adventurer.increaseStamina(regularItem.getModifier());
            case SMALL_HEALTH_POTION, HEALTH_POTION, LARGE_HEALTH_POTION -> adventurer.increaseHealth(regularItem.getModifier());
        }
        gameEnvironment.getPlayerInventory().removeItem(regularItem);
    }

    public int getActualModifier(RegularItem selectedRegularItem, Adventurer adventurer)
    {
        return switch (selectedRegularItem) {
            case SMALL_STAMINA_POTION, STAMINA_POTION, LARGE_STAMINA_POTION -> selectedRegularItem.getAdjustedModifier(adventurer.getMaxStamina(), adventurer.getStamina());
            case SMALL_HEALTH_POTION, HEALTH_POTION, LARGE_HEALTH_POTION -> selectedRegularItem.getAdjustedModifier(adventurer.getMaxHealth(), adventurer.getHealth());
            default -> 0;
        };

    }

    /**
     * Move an adventurer from the reserve party to the main party
     * @param adventurer The adventurer to be moved
     * @return If the moving was successful or not
     */
    public boolean moveAdventurerToMain(Adventurer adventurer)
    {
        if (adventurer == null)
        {
            System.out.println("Warning: No adventurer selected!");
            return false;
        }
        if (gameEnvironment.getMainParty().contains(adventurer))
        {
            System.out.println("Warning: Main party already contains this adventurer!");
            return false;
        }
        if (!gameEnvironment.getReserveParty().contains(adventurer))
        {
            System.out.println("Warning: Adventurer not found in reserve party!");
            return false;
        }
        if (addToMainParty(adventurer))
        {
            gameEnvironment.getReserveParty().remove(adventurer);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Add an adventurer to the main party
     * @param adventurer The adventurer to be added
     * @return If adding the adventurer was successful or not
     */
    private boolean addToMainParty(Adventurer adventurer) {
        if (gameEnvironment.getMainParty().size() >= gameEnvironment.MAX_PARTY_SIZE)
        {
            System.out.println("Warning: Main party at maximum capacity!");
            return false;
        }
        else
        {
            gameEnvironment.getMainParty().add(adventurer);
            return true;
        }
    }

    /**
     * Add an adventurer to the reserve party
     * @param adventurer The adventurer to be added
     * @return If adding the adventurer was successful or not
     */
    private boolean addToReserveParty(Adventurer adventurer)
    {
        if (gameEnvironment.getReserveParty().size() >= 5)
        {
            System.out.println("Warning: Reserve party at maximum capacity!");
            return false;
        }
        gameEnvironment.getReserveParty().add(adventurer);
        return true;
    }

    /**
     * Remove an adventurer from the main party
     * @param adventurer The adventurer to be removed
     * @return If removal was successful or not
     */
    private boolean removeFromMainParty(Adventurer adventurer)
    {
        if (gameEnvironment.getMainParty().size() == 1)
        {
            System.out.println("Warning: Removing this adventurer will cause main party to be empty!");
            return false;
        }
        else {
            gameEnvironment.getMainParty().remove(adventurer);
            return true;
        }
    }

    /**
     * Move an adventurer from the main party to the reserve party
     * @param adventurer The adventurer to be moved
     * @return If the moving was successful or not
     */
    public boolean moveAdventurerToReserve(Adventurer adventurer)
    {
        if (adventurer == null)
        {
            System.out.println("Warning: No adventurer selected!");
            return false;
        }
        if (gameEnvironment.getReserveParty().contains(adventurer))
        {
            System.out.println("Warning: Reserve party already contains this adventurer!");
            return false;
        }
        if (!gameEnvironment.getMainParty().contains(adventurer))
        {
            System.out.println("Warning: Adventurer not found in main party!");
            return false;
        }
        if (gameEnvironment.getMainParty().size() == 1)
        {
            System.out.println("Warning: Removing this adventurer will cause main party to be empty!");
            return false;
        }
        if (gameEnvironment.getReserveParty().size() >= gameEnvironment.MAX_PARTY_SIZE)
        {
            System.out.println("Warning: Reserve party at maximum capacity!");
            return false;
        }
        if (removeFromMainParty(adventurer))
        {
            return addToReserveParty(adventurer);
        }
        else {
            return false;
        }
    }

    /**
     * Match a given regularItem to its respective label
     * @param regularItem The regularItem to be matched
     * @param healthLabel The healthLabel to be returned if matched to regularItem
     * @param staminaLabel The staminaLabel to be returned if matched to regularItem
     * @return The label that matches the regularItem
     */
    public Label itemToLabel(RegularItem regularItem, Label healthLabel, Label staminaLabel)
    {
        return switch (regularItem) {
            case SMALL_HEALTH_POTION, HEALTH_POTION, LARGE_HEALTH_POTION -> healthLabel;
            case SMALL_STAMINA_POTION, STAMINA_POTION, LARGE_STAMINA_POTION -> staminaLabel;
            default -> null;
        };
    }
}
