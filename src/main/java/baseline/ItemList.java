package baseline;

import java.util.ArrayList;
import java.util.List;

public class ItemList {
    //Stores the info of one to-do list
    private String listName;
    List<Item> toDoList = new ArrayList<>();

    public ItemList() {
        //initialized  when new list is made
    }

    public void addItem(String newItemName, String newDescription, String newDate) {
        //use name to add item from arraylist
        toDoList.add(new Item(newItemName, newDescription, newDate, false));
    }

    public Item getItem(String name) {
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
        for (Item item : toDoList) {
            if (item.getItemName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    public void removeItem(String name) {
        //use name to remove item from arraylist
    }

    public void editItem(String name, String newItemName, String newDescription, String newDate) {
        //use name to find specific item and use setter methods of class Item to change info of the item
        for (Item item : toDoList) {
            if (item.getItemName().equals(name)) {
                item.setItemName(newItemName);
                item.setItemDescription(newDescription);
                item.setDueDate(newDate);
            }
        }
    }

    public void clearAllItems() {
        toDoList.clear();
    }

    //used to access info for controllers
    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public List<Item> getToDoList() {
        return toDoList;
    }

    public void setToDoList(List<Item> toDoList) {
        this.toDoList = toDoList;
    }

    @Override
    public String toString() {
        //used for displaying in listview
        return listName;
    }
}
