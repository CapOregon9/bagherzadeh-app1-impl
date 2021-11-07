package baseline;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemListTest {
    ItemList itemList;

    @BeforeEach
    void createObjects() {
        itemList = new ItemList();
    }

    @Test
    void add100Items() {
        //Tests the ability to store 100 items in the item list
        for (int i = 0; i < 100; i++) {
            itemList.addItem("TestItem", "TestDescription", "TestDate");
        }
        assertEquals(100, itemList.getToDoList().size());
    }

    @Test
    void addItem() {
        //Tests adding a new Item to the list
        itemList.addItem("Item1", "Description1", "Date");
        assertTrue(itemList.itemExists("Item1"));
    }

    @Test
    void removeItem() {
        //Tests removing a specific item from the list
        itemList.addItem("Item1", "Description1", "Date");
        itemList.removeItem("Item1");
        assertFalse(itemList.itemExists("Item1"));
    }

    @Test
    void editItem() {
        //Tests changing values of a specific item in the list
        itemList.addItem("Item1", "Description1", "Date");
        itemList.editItem("Item1","NewItem", "Description1", "Date");
        assertTrue(itemList.itemExists("NewItem"));
    }

    @Test
    void clearItems() {
        //Tests clearing of all items in the itemList
        itemList.addItem("Item1", "Description1", "Date");
        itemList.addItem("Item2", "Description1", "Date");
        itemList.addItem("Item3", "Description1", "Date");
        itemList.clearAllItems();
        assertEquals(0, itemList.getToDoList().size());
    }

    @Test
    void editDescription() {
        //Tests editing the description of an item in the list
        itemList.addItem("Item1", "Description1", "Date");
        itemList.editItem("Item1","Item1", "NewDescription", "Date");
        assertEquals("NewDescription", itemList.getItem("Item1").getItemDescription());
    }

    @Test
    void editDate() {
        //Tests editing the due date of an item in the list
        //No test for date validation because that is handled by the date picker object before it even gets to the item list object in both edit and add item controllers
        itemList.addItem("Item1", "Description1", "Date");
        itemList.editItem("Item1","Item1", "NewDescription", "2011-07-13");
        assertEquals("2011-07-13", itemList.getItem("Item1").getDueDate());
    }

    @Test
    void setCompletionStateToFalse() {
        //Tests changing the completion state of the item to false
        itemList.addItem("Item1", "Description1", "Date");
        itemList.setItemCompletedState("Item1", false);
        assertFalse(itemList.getItem("Item1").getCompleted());
    }

    @Test
    void setCompletionStateToTrue() {
        //Tests changing the completion state of the item to true
        itemList.addItem("Item1", "Description1", "Date");
        itemList.setItemCompletedState("Item1", true);
        assertTrue(itemList.getItem("Item1").getCompleted());
    }
}