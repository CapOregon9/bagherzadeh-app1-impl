package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddItemController {

    private ItemList itemList;
    private String listName;
    private String itemName;

    @FXML
    private TextField newDateTextField;

    @FXML
    private TextArea newDescriptionTextField;

    @FXML
    private TextField newNameTextField;

    @FXML
    private Button saveItemButton;

    public AddItemController(ItemList itemList) {
        this.itemList = itemList;
    }

    @FXML
    void saveItem(ActionEvent event) {
        //cycle through list to get the specific list
        //call function to add item
        itemList.addItem(newNameTextField.getText(), newDescriptionTextField.getText(), newDateTextField.getText());
        //return itemlist to main scene and load main scene with scene manager
    }

    public void initialize() {
        //store passed in object of all lists
        //store listname and item name as well

    }

}
