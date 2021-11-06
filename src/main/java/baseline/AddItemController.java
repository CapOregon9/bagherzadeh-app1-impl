package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddItemController {

    private ItemList itemList;

    @FXML
    private DatePicker newDatePickerField;

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

        if (!isNameFieldEmpty(newNameTextField.getText()) && isNameFieldUnique(newNameTextField.getText()) && !isDescriptionFieldEmpty(newDescriptionTextField.getText()) && descriptionFieldMaxValidation(newDescriptionTextField.getText())) {
            Stage stage;
            String date;
            if (newDatePickerField.getValue() == null) {
                date = "";
            } else {
                date = newDatePickerField.getValue().format(DateTimeFormatter.ISO_DATE);
            }
            itemList.addItem(newNameTextField.getText(), newDescriptionTextField.getText(), date);
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
            JMetro jMetro = new JMetro(Style.LIGHT);
            jMetro.setScene(scene);
            stage.setScene(scene);
            stage.show();
        } else {
            if (isNameFieldEmpty(newNameTextField.getText()) || !isNameFieldUnique(newNameTextField.getText())) {
                newNameTextField.clear();
                newNameTextField.setPromptText("Enter valid unique name!");
                if (isDescriptionFieldEmpty(newDescriptionTextField.getText()) || !descriptionFieldMaxValidation(newDescriptionTextField.getText())) {
                    newDescriptionTextField.clear();
                    newDescriptionTextField.setPromptText("Enter a valid description that is no longer than 256 characters.");
                }
            } else {
                newDescriptionTextField.clear();
                newDescriptionTextField.setPromptText("Enter a valid description that is no longer than 256 characters.");
            }
        }

    }

    public boolean descriptionFieldMaxValidation(String description) {
        return description.length() <= 256;
    }

    public boolean isDescriptionFieldEmpty(String description) {
        return description.equals("");
    }

    public boolean isNameFieldUnique(String itemName) {
        return !itemList.itemExists(itemName);
    }

    public boolean isNameFieldEmpty(String itemName) {
        return itemName.equals("");
    }

    public void initialize() {
        //store passed in object of all lists
        //store listname and item name as well
        newDatePickerField.setPromptText("YYYY-MM-DD");
        newDatePickerField.setConverter(new StringConverter<>() {

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return date.format(DateTimeFormatter.ISO_DATE);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, DateTimeFormatter.ISO_DATE);
                } else {
                    return null;
                }
            }
        });
    }

}
