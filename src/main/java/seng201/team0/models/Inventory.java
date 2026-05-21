package seng201.team0.models;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> inventory;

    /**
     * Initialize a new empty inventory
     */
    public Inventory()
    {
        inventory = new ArrayList<>();
    }

    /**
     * Add a specific Item to this inventory
     * @param item The specific item to be added
     */
    public void addItem(Item item)
    {
        inventory.add(item);
    }

    /**
     * Remove a specific Item from this inventory
     * @param item The specific item to be removed
     */
    public void removeItem(Item item)
    {
        inventory.remove(item);
    }

    /**
     * Get a List of all items in the inventory
     * @return A List of all items in this inventory
     */
    public List<Item> getAllItems()
    {
        return inventory;
    }

    /**
     * Remove all items from the inventory
     */
    public void clear()
    {
        inventory.clear();
    }
}
