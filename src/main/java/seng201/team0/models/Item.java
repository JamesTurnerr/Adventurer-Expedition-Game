package seng201.team0.models;

import java.util.Random;

public enum Item {
    STAMINA_POTION("Stamina Potion", 5, 10),
    HEALTH_POTION("Health Potion", 5, 10),
    RUSTY_SWORD("Rusty Sword", 5, 0);

    private final String name;
    private final int cost;
    private final int modifier;
    Item(String name, int modifier, int cost)
    {
        this.name = name;
        this.cost = cost;
        this.modifier = modifier;
    }
    @Override
    public String toString()
    {
        return String.format("%s +%d %sGold", name, modifier, cost);
    }
    public String getName()
    {
        return name;
    }
    public int getModifier()
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
