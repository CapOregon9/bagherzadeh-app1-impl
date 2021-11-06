package baseline;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ItemListControllerTest {
    ItemListController itemListController;
    ItemList itemList;

    @BeforeEach
    void createObjects() {
        //items by default are set to false for being completed
        itemListController = new ItemListController();
        itemList = new ItemList();
        itemList.addItem("TestItem1", "TestDescription", "TestDate");
        itemList.addItem("TestItem2", "TestDescription", "TestDate");
        itemList.setItemCompletedState("TestItem2", true);
        itemList.addItem("TestItem3", "TestDescription", "TestDate");
        itemListController.itemListDataPass(itemList);
    }


    @Test
    void showIncompleteItems() {
        itemListController.showIncompleteItems();
        assertEquals(2, itemListController.getList().size());
    }

    @Test
    void showCompletedItems() {
        itemListController.showCompletedItems();
        assertEquals(1, itemListController.getList().size());
    }

    @Test
    void showAllItems() {
        itemListController.showAllItems();
        assertEquals(3, itemListController.getList().size());
    }

    @Test
    void saveFile() {
        //the file location and name are being handled by the file chooser javafx object
        itemListController.saveFile("D:\\Documents\\TestSave.txt");
        boolean value = new File("D:\\Documents\\TestSave.txt").exists();
        assertTrue(value);
    }

    @Test
    void openFile() {
        //the file location and name are being handled by the file chooser javafx object
        //the list size would be more than three if the file was not overwriting the current data
        itemListController.openFile(new File("D:\\Documents\\TestSave.txt"));
        assertEquals(3, itemListController.getList().size());
    }
}