package seng201.team0.models;

import java.util.Random;

public enum Item {
    STAMINA_POTION("Stamina Potion", "Moderately increases stamina", 10),
    HEALTH_POTION("Health Potion", "Moderately increases health", 10),
    RUSTY_SWORD("Rusty Sword", "Pretty useless", 3);

    private final String name;
    private final int cost;
    private final String modifier;

    /**
     * Create a new item given the name, modifier and cost of the item
     * @param name The displayed name of the item
     * @param modifier The amount the item modifies a stat
     * @param cost How much the item costs
     */
    Item(String name, String modifier, int cost)
    {
        this.name = name;
        this.cost = cost;
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
    public String getModifier()
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
