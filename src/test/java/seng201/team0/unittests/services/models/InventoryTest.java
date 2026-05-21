package seng201.team0.unittests.services.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.Inventory;
import seng201.team0.models.Item;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {
    private Inventory inventory;
    private Item item1;
    private Item item2;

    @BeforeEach
    public void setUp() {
        inventory = new Inventory();

        item1 = Item.getRandomItem();
        item2 = Item.getRandomItem();
    }
    @Test
    public void testInventoryStartsEmpty() {
        assertTrue(inventory.getAllItems().isEmpty());
    }

    @Test
    public void testAddItem() {
        inventory.addItem(item1);

        assertEquals(1, inventory.getAllItems().size());
        assertTrue(inventory.getAllItems().contains(item1));
    }

    @Test
    public void testAddMultipleItems() {
        inventory.addItem(item1);
        inventory.addItem(item2);

        assertEquals(2, inventory.getAllItems().size());
        assertTrue(inventory.getAllItems().contains(item1));
        assertTrue(inventory.getAllItems().contains(item2));
    }

    @Test
    public void testRemoveItem() {
        inventory.addItem(item1);

        inventory.removeItem(item1);

        assertFalse(inventory.getAllItems().contains(item1));
        assertEquals(0, inventory.getAllItems().size());
    }

    @Test
    public void testRemoveNonExistentItem() {
        inventory.removeItem(item1);

        assertTrue(inventory.getAllItems().isEmpty());
    }

    @Test
    public void testClearInventory() {
        inventory.addItem(item1);
        inventory.addItem(item2);

        inventory.clear();

        assertTrue(inventory.getAllItems().isEmpty());
    }

    @Test
    public void testGetAllItemsReturnsCorrectItems() {
        inventory.addItem(item1);
        inventory.addItem(item2);

        assertEquals(item1, inventory.getAllItems().get(0));
        assertEquals(item2, inventory.getAllItems().get(1));
    }






}
