package seng201.team0.models;

import java.util.Random;

public enum Item {
    STAMINA_POTION("Stamina Potion", 5, 10),
    HEALTH_POTION("Health Potion", 5, 10);

    private final String name;
    private final int cost;
    private final int amount;
    Item(String name, int amount, int cost)
    {
        this.name = name;
        this.cost = cost;
        this.amount = amount;
    }
    @Override
    public String toString()
    {
        return String.format("%s +%d %sGold", name, amount, cost);
    }
    public String getName()
    {
        return name;
    }
    public int getAmount()
    {
        return amount;
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
