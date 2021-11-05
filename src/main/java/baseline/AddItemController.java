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

public class AddItemController {

    private ItemList itemList;
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
    private Button saveItemButton;

    public void itemListDataPass(ItemList itemList) {
        this.itemList = itemList;
    }

    @FXML
    void saveItem(ActionEvent event) {
        //cycle through list to get the specific list
        //call function to add item
        itemList.addItem(newNameTextField.getText(), newDescriptionTextField.getText(), newDateTextField.getText());
        //return itemlist to main scene and load main scene with scene manager
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ItemsList.fxml"));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Could not load item list fxml.");
        }
        ItemListController controller = fxmlLoader.getController();
        controller.itemListDataPass(itemList);
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        //store passed in object of all lists
        //store listname and item name as well

    }

}
