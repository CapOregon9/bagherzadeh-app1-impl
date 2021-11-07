/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Alexander Bagherzadeh
 */

package baseline;

import java.util.ArrayList;
import java.util.List;

public class ItemList {
    //Stores the info of the to-do-list
    private List<Item> toDoList = new ArrayList<>();

    public ItemList() {
        //initialized  when new list is made
    }

    public void addItem(String newItemName, String newDescription, String newDate) {
        //used passed in values of name description and date to add a new item to the list
        toDoList.add(new Item(newItemName, newDescription, newDate, false));
    }

    public Item getItem(String name) {
        //returns an item when the item name equals the passed in name
        //this will never return null because of using the selection property from the list view to get the item name
        for (Item item : toDoList) {
            if (item.getItemName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public void setItemCompletedState(String name, boolean state) {
        //another form of setMethod
        //uses name to find item
        //toggle value of completed with get and set methods of it
        for (Item item : toDoList) {
            if (item.getItemName().equals(name)) {
                item.setCompleted(state);
            }
        }
    }

    public boolean itemExists(String itemName) {
        //cycle through the items in the list
        //return true if the item name matches the passed in name
        //otherwise false
        for (Item item : toDoList) {
            if (item.getItemName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    public void removeItem(String name) {
        //use name to remove item from arraylist
        toDoList.removeIf(item -> item.getItemName().equals(name));
    }

    public void editItem(String name, String newItemName, String newDescription, String newDate) {
        //use name to find specific item and use setter methods of class Item to change info of the item
        //using the passed in parameters for the fields
        for (Item item : toDoList) {
            if (item.getItemName().equals(name)) {
                item.setItemName(newItemName);
                item.setItemDescription(newDescription);
                item.setDueDate(newDate);
            }
        }
    }

    public void clearAllItems() {
        //clears the list of items with built-in function
        toDoList.clear();
    }

    public List<Item> getToDoList() {
        //returns the toDoList to be used in other methods, mainly in opening and saving to a file
        return toDoList;
    }
}
