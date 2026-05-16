package seng201.team0.models;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> inventory;
    public Inventory()
    {
        inventory = new ArrayList<Item>();
    }

    public void addItem(Item item)
    {
        inventory.add(item);
    }

    public void removeItem(Item item)
    {
        inventory.remove(item);
    }

    public List<Item> getAllItems()
    {
        return inventory;
    }
}
