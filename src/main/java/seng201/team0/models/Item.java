package seng201.team0.models;

import java.util.Random;

/**
 * Interface for items, forces items to implement necessary methods
 */
public interface Item {
    /**
     * Get the name of the item
     * @return The items name
     */
    @Override
    String toString();

    /**
     * Get the amount the item modifies a stat
     * @return The stat modifier amount
     */
    String getDescription();

    /**
     * Gets how much the item costs in gold
     * @return RegularItem cost
     */
    int getCost();

    /**
     * Increase the cost of an item
     * @param amount The amount of gold to increase the items cost by
     */
    void increaseCost(int amount);

    /**
     * Decrease the cost of an item
     * @param amount The amount of gold to decrease the items cost by
     */
    void decreaseCost(int amount);
}
