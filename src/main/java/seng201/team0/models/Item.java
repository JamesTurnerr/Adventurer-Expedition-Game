package seng201.team0.models;

import java.util.Random;

public enum Item {
    SMALL_STAMINA_POTION("Small Stamina Potion", "Slightly restores stamina", 5, 5),
    STAMINA_POTION("Stamina Potion", "Moderately restores stamina", 10, 10),
    LARGE_STAMINA_POTION("Large Stamina Potion", "Fully restores stamina", 1000, 50),
    SMALL_HEALTH_POTION("Small Health Potion", "Slightly restores Health", 5, 5),
    HEALTH_POTION("Health Potion", "Moderately increases health", 10, 10),
    LARGE_HEALTH_POTION("Large Health Potion", "Fully restores Health", 1000, 50),
    RUSTY_SWORD("Rusty Sword", "Pretty useless", 0, 3);

    private final String name;
    private int cost;
    private final String description;
    private final int modifier;

    /**
     * Create a new item given the name, modifier and cost of the item
     * @param name The displayed name of the item
     * @param modifier The amount the item modifies a stat
     * @param description The description of the item
     * @param cost How much the item costs
     */
    Item(String name, String description, int modifier, int cost)
    {
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.modifier = modifier;
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
     * Get the amount the item modifies a stat
     * @return The stat modifier amount
     */
    public int getModifier()
    {
        return modifier;
    }

    /**
     * Get the amount the item modifies a stat accounting for their current value and max value of that stat
     * @param maxStat The maximum value that stat could be
     * @param currentStat The current value of that stat
     * @return The stat modifier amount
     */
    public int getAdjustedModifier(int maxStat, int currentStat)
    {
        return Math.min(modifier, maxStat - currentStat);
    }

    /**
     * Gets how much the item costs in gold
     * @return Item cost
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
    public static Item getRandomItem()
    {
        Item[] items = values();
        Random random = new Random();
        return items[random.nextInt(items.length)];
    }
}
