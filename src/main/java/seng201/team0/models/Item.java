package seng201.team0.models;

import java.util.Random;

public enum Item {
    STAMINA_POTION("Stamina Potion", "Moderately increases stamina", 20, 10),
    HEALTH_POTION("Health Potion", "Moderately increases health", 30, 10),
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
     * Bruh sound effect #2
     * @return the same as toString()
     */
    public String getName()
    {
        return name;
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
