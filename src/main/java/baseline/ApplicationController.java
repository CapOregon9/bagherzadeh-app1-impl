package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ApplicationController {
    private ToDoLists toDoLists;

    @FXML
    private Button addListButton;

    @FXML
    private Button checkAllItemsButton;

    @FXML
    private Button exportListsButton;

    @FXML
    private TextField listTitleTextField;

    @FXML
    private Button removeListButton;

    @FXML
    private ListView<?> toDoListView;

    @FXML
    void ExportListsToFile(ActionEvent event) {
        //open scene to check save name and location
        //create a list of selected objects
        //store this list in a new To-do list object and send it to the export file scene controller
    }

    @FXML
    void addListToListView(ActionEvent event) {
        //checks if to-do list was initialized (check for null)
        //validate input of the title
        //call method to add a new to-do list
        //send title as parameter
        //update listView
    }

    @FXML
    void checkItemsFromList(ActionEvent event) {
        //send name of list selected and the whole object storing lists to the new scene of itemList
    }

    @FXML
    void loadDataFromFile(ActionEvent event) {
        //create objects that store list information using JSON FIle by using load data scene
    }

    @FXML
    void removeListFromListView(ActionEvent event) {
        //check name and call function to remove list from arrayList of all lists
    }

    public void initialize() {
        //use this section to add listener a listener to the listView so that you can detect what is selected
        //initialize what is in the listView as well
        //use list of all lists to initialize an observation list for listView
    }
}
