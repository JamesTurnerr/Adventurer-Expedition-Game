package seng201.team0.models;

import java.util.Random;

public enum ValuableItem implements Item {
    GOLDEN_SWORD("Golden Sword", "A pure gold sword", 100),
    GOLD_BAR("Gold Bar", "A pure gold bar", 100),
    DIAMOND("Diamond", "A pristine diamond", 150);

    private final String name;
    private int cost;
    private final String description;

    /**
     * Create a new item given the name, modifier and cost of the item
     * @param name The displayed name of the item
     * @param description The description of the item
     * @param cost How much the item costs
     */
    ValuableItem(String name, String description, int cost)
    {
        this.name = name;
        this.cost = cost;
        this.description = description;
    }

    /**
     * Get the name of the item
     * @return The items name
     */
    @Override
    public String toString()
    {
        return String.format(name);
    }

    /**
     * Get the amount the item modifies a stat
     * @return The stat modifier amount
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Gets how much the item costs in gold
     * @return ValuableItem cost
     */
    public int getCost()
    {
        return cost;
    }

    /**
     * Change the cost of an item to a set value
     * @param newValue The new cost to set the item to
     */
    private void setCost(int newValue)
    { cost = newValue; }

    /**
     * Increase the cost of an item
     * @param amount The amount of gold to increase the items cost by
     */
    public void increaseCost(int amount)
    { cost += amount; }

    /**
     * Decrease the cost of an item
     * @param amount The amount of gold to decrease the items cost by
     */
    public void decreaseCost(int amount)
    { cost -= amount; }

    /**
     * Create a random item
     * @return A random item
     */
    public static ValuableItem getRandomItem()
    {
        ValuableItem[] valuableItem = values();
        Random random = new Random();
        return valuableItem[random.nextInt(valuableItem.length)];
    }
}
