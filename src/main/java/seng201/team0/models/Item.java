package seng201.team0.models;

import java.util.Random;

public enum Item {
    STAMINA_POTION("Stamina Potion", "Moderately increases stamina", 10),
    HEALTH_POTION("Health Potion", "Moderately increases health", 10),
    RUSTY_SWORD("Rusty Sword", "Pretty useless", 3);

    private final String name;
    private final int cost;
    private final String modifier;
    Item(String name, String modifier, int cost)
    {
        this.name = name;
        this.cost = cost;
        this.modifier = modifier;
    }
    @Override
    public String toString()
    {
        return String.format(name);
    }
    public String getName()
    {
        return name;
    }
    public String getModifier()
    {
        return modifier;
    }
    public int getCost()
    {
        return cost;
    }
    public static Item getRandomItem()
    {
        Item[] items = values();
        Random random = new Random();
        return items[random.nextInt(items.length)];
    }
}
