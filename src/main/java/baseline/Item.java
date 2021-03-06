/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Alexander Bagherzadeh
 */

package baseline;

public class Item {
    //stores the information of an item in a to-do list
    private String itemName;
    private String dueDate;
    private String itemDescription;
    private boolean completed;

    public Item(String itemName, String itemDescription, String dueDate, boolean completed) {
        //initializes info when new item is added
        this.itemName = itemName;
        this.dueDate = dueDate;
        this.itemDescription = itemDescription;
        this.completed = completed;
    }

    //get and set methods to be able to access this information in the controller logic
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        //used for listView in the gui
        //sets what the listView will display on each line
        return String.format("%-15s%6s      %s%6s      %s", itemName, "|", dueDate, "|", (completed) ? "Completed":"Not Completed");
    }
}
