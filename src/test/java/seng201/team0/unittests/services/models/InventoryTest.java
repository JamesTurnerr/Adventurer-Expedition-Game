package seng201.team0.unittests.services.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.Inventory;
import seng201.team0.models.RegularItem;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {
    private Inventory inventory;
    private RegularItem regularItem1;
    private RegularItem regularItem2;

    @BeforeEach
    public void setUp() {
        inventory = new Inventory();

        regularItem1 = RegularItem.getRandomItem();
        regularItem2 = RegularItem.getRandomItem();
    }
    @Test
    public void testInventoryStartsEmpty() {
        assertTrue(inventory.getAllItems().isEmpty());
    }

    @Test
    public void testAddItem() {
        inventory.addItem(regularItem1);

        assertEquals(1, inventory.getAllItems().size());
        assertTrue(inventory.getAllItems().contains(regularItem1));
    }

    @Test
    public void testAddMultipleItems() {
        inventory.addItem(regularItem1);
        inventory.addItem(regularItem2);

        assertEquals(2, inventory.getAllItems().size());
        assertTrue(inventory.getAllItems().contains(regularItem1));
        assertTrue(inventory.getAllItems().contains(regularItem2));
    }

    @Test
    public void testRemoveItem() {
        inventory.addItem(regularItem1);

        inventory.removeItem(regularItem1);

        assertFalse(inventory.getAllItems().contains(regularItem1));
        assertEquals(0, inventory.getAllItems().size());
    }

    @Test
    public void testRemoveNonExistentItem() {
        inventory.removeItem(regularItem1);

        assertTrue(inventory.getAllItems().isEmpty());
    }

    @Test
    public void testClearInventory() {
        inventory.addItem(regularItem1);
        inventory.addItem(regularItem2);

        inventory.clear();

        assertTrue(inventory.getAllItems().isEmpty());
    }

    @Test
    public void testGetAllItemsReturnsCorrectItems() {
        inventory.addItem(regularItem1);
        inventory.addItem(regularItem2);

        assertEquals(regularItem1, inventory.getAllItems().get(0));
        assertEquals(regularItem2, inventory.getAllItems().get(1));
    }






}
