package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EditItemController {

    private ItemList itemList;
    private ToDoLists toDoLists;
    private String listName;
    private String itemName;

    @FXML
    private TextField newDateTextField;

    @FXML
    private TextArea newDescriptionTextField;

    @FXML
    private TextField newNameTextField;

    @FXML
    private Button saveChangesButton;

    public EditItemController(ItemList itemList, String itemName) {
        this.itemList = itemList;
        this.itemName = itemName;
    }

    @FXML
    void editItemValues(ActionEvent event) {
        //cycle through list to get the specific list
        //call function to edit item
        itemList.editItem(itemName ,newNameTextField.getText(), newDescriptionTextField.getText(), newDateTextField.getText());
        //return list to main scene and load main scene with scene manager
    }

    public void initialize() {
        //store passed in object of all lists
        //store listname and item name as well
    }

}
