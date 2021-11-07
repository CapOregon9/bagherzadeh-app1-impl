/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Alexander Bagherzadeh
 */

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
        //Tests showing only incomplete items and also verifies that the observable list in the controller successfully mirrors the item list appropriately
        itemListController.showIncompleteItems();
        assertEquals(2, itemListController.getList().size());
    }

    @Test
    void showCompletedItems() {
        //Tests showing only complete items and also verifies that the observable list in the controller successfully mirrors the item list appropriately
        itemListController.showCompletedItems();
        assertEquals(1, itemListController.getList().size());
    }

    @Test
    void showAllItems() {
        //Tests showing all items and also verifies that the observable list in the controller successfully mirrors the item list appropriately
        itemListController.showAllItems();
        assertEquals(3, itemListController.getList().size());
    }

    @Test
    void saveFile() {
        //tests if the file is created when running the save file method
        //the file location and name are being handled by the file chooser javafx object
        itemListController.saveFile("D:\\Documents\\TestSave.txt");
        boolean value = new File("D:\\Documents\\TestSave.txt").exists();
        assertTrue(value);
    }

    @Test
    void openFile() {
        //tests if the size of the list was changed after opening the file.
        //file location and name are being handled by the file chooser javafx object
        //the list size would be more than three if the file was not overwriting the current data
        itemListController.openFile(new File("D:\\Documents\\TestSave.txt"));
        assertEquals(3, itemListController.getList().size());
    }
}