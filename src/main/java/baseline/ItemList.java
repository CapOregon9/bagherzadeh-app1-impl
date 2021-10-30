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
    }

    public void removeItem(String name) {
        //use name to remove item from arraylist
    }

    public void editItem(String name, String newItemName, String newDescription, String newDate) {
        //use name to find specific item and use setter methods of class Item to change info of the item
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
