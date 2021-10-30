package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class ItemListController {

    @FXML
    private TextField ItemDateTextField;

    @FXML
    private TextArea ItemDescriptionTextField;

    @FXML
    private TextField ItemNameTextField;

    @FXML
    private Button addItemButton;

    @FXML
    private Button closeButton;

    @FXML
    private CheckBox completedCheckBox;

    @FXML
    private ToggleGroup completedToggleGroup;

    @FXML
    private Button editSelectedItemButton;

    @FXML
    private ListView<?> itemsListView;

    @FXML
    private Button removeSelectedItemButton;

    @FXML
    private RadioButton showAllItemsRadioButton;

    @FXML
    private RadioButton showCompleteItemsRadioButton;

    @FXML
    private RadioButton showIncompleteItemsRadioButton;

    @FXML
    void addNewItem(ActionEvent event) {
        //move to add item scene passing over all lists as well
        //pass in value for the selected item as well as the selected list
    }

    @FXML
    void changeCompletedState(ActionEvent event) {
         //toggle completed for item using method
         //update observation list and ListView
    }

    @FXML
    void closeList(ActionEvent event) {
        //return to main scene of all lists
    }

    @FXML
    void editSelectedItem(ActionEvent event) {
        //move to edit item scene passing over all lists as well
        //pass in value for the selected item as well as the selected list
    }

    @FXML
    void removeSelectedItem(ActionEvent event) {
        //check listener to get name of what item is selected
        //call method of itemList to remove item from list
        //update observationList and listView
    }

    @FXML
    void selectItemsToShow(ActionEvent event) {
        //cycle between different filters
        //radio buttons will give a value 0, 1, or 2
        //compare that value with the completed value of each item
        //update listView
    }

    public void initialize() {
        //use passed in title to know what list to access
        //initialize listView of items and create a listener selection detection on an observationList
    }
}
