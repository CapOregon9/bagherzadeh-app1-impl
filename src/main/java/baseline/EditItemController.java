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

public class EditItemController {

    private ItemList itemList;
    private String itemName;


    @FXML
    private DatePicker newDatePickerField;

    @FXML
    private TextArea newDescriptionTextField;

    @FXML
    private TextField newNameTextField;

    @FXML
    private Button saveChangesButton;

    public EditItemController() {
        itemList = new ItemList();
    }

    @FXML
    void editItemValues(ActionEvent event) {
        //cycle through list to get the specific list
        //call function to edit item
        if (!isNameFieldEmpty(newNameTextField.getText()) && isNameFieldUnique(newNameTextField.getText()) && !isDescriptionFieldEmpty(newDescriptionTextField.getText()) && descriptionFieldMaxValidation(newDescriptionTextField.getText())) {
            Stage stage;
            itemList.editItem(itemName, newNameTextField.getText(), newDescriptionTextField.getText(), newDatePickerField.getValue().format(DateTimeFormatter.ISO_DATE));
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
            JMetro jMetro = new JMetro(Style.LIGHT);
            jMetro.setScene(scene);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
        //return list to main scene and load main scene with scene manager
    }

    public boolean descriptionFieldMaxValidation(String description) {
        return description.length() <= 256;
    }

    public boolean isDescriptionFieldEmpty(String description) {
        return description.equals("");
    }

    public boolean isNameFieldUnique(String itemName) {
        if (itemName.equals(this.itemName)) {
            return true;
        }
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

    public void itemListDataPass(ItemList itemList, String itemName) {
        this.itemList = itemList;
        this.itemName = itemName;
        setStageTexts();
    }

    public void setStageTexts() {
        Item item = itemList.getItem(itemName);
        newNameTextField.setText(item.getItemName());
        newDescriptionTextField.setText(item.getItemDescription());
        if (!item.getDueDate().equals("")) {
            newDatePickerField.setValue(LocalDate.parse(item.getDueDate(), DateTimeFormatter.ISO_DATE));
        }
    }
}
