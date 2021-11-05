package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class EditItemController {

    private ItemList itemList;
    private ToDoLists toDoLists;
    private String listName;
    private String itemName;
    private Stage stage;

    @FXML
    private TextField newDateTextField;

    @FXML
    private TextArea newDescriptionTextField;

    @FXML
    private TextField newNameTextField;

    @FXML
    private Button saveChangesButton;
    public EditItemController() {
        itemList = new ItemList();
    }


    public EditItemController(ItemList itemList, String itemName) {
        this.itemList = itemList;
        this.itemName = itemName;
    }

    @FXML
    void editItemValues(ActionEvent event) {
        //cycle through list to get the specific list
        //call function to edit item
        itemList.editItem(itemName ,newNameTextField.getText(), newDescriptionTextField.getText(), newDateTextField.getText());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ItemsList.fxml"));

        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Could not load add item fxml.");
        }
        ItemListController controller = fxmlLoader.getController();
        controller.itemListDataPass(itemList);
        Scene scene = new Scene(root);
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        //return list to main scene and load main scene with scene manager
    }

    public void initialize() {
        //store passed in object of all lists
        //store listname and item name as well
    }

    public void itemListDataPass(ItemList itemList, String itemName) {
        this.itemList = itemList;
        this.itemName = itemName;
    }
}
