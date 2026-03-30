package seng201.team0;

public class Item {
    private String name;
    private int cost;
    public Item(String name, int cost)
    {
        this.name = name;
        this.cost = cost;
    }
    @Override
    public String toString()
    {
        return name;
    }
    public String getName()
    {
        return name;
    }
    public int getCost()
    {
        return cost;
    }
}
