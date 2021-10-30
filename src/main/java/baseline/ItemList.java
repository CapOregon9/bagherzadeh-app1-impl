package baseline;

import java.util.ArrayList;
import java.util.List;

public class ItemList {
    //Stores the info of one to-do list
    private String listName;
    List<Item> toDoList = new ArrayList<>();

    public ItemList(String listName) {
        //initialized name when new list is added
        this.listName = listName;
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
        for (Item item : toDoList) {
            if (item.getItemName().equals(name)) {
                item.setCompleted(state);
            }
        }
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

    public void toggleCompleted(String name) {
        //another form of setMethod
        //uses name to find item
        //toggle value of completed with get and set methods of it
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
