/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Alexander Bagherzadeh
 */

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
        //use the item list passed through to add an item
        //four boolean methods used to validate input from the user: 1. Name field not empty 2. Name field Unique 3. Description field not empty 4. Description field <= 256 in length
        //if these are all true, then the item list edit item method is called to edit the current item
        if (!isNameFieldEmpty(newNameTextField.getText()) && isNameFieldUnique(newNameTextField.getText()) && !isDescriptionFieldEmpty(newDescriptionTextField.getText()) && descriptionFieldMaxValidation(newDescriptionTextField.getText())) {
            Stage stage;
            String date;
            if (newDatePickerField.getValue() == null) {
                date = "";
            } else {
                date = newDatePickerField.getValue().format(DateTimeFormatter.ISO_DATE);
            }
            itemList.addItem(newNameTextField.getText(), newDescriptionTextField.getText(), date);
            //Transition back to main scene as well as sending the item list data back to it.
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
                //clears the name text field and sets a prompt if validation for the name didn't pass
                newNameTextField.clear();
                newNameTextField.setPromptText("Enter valid unique name!");
                if (isDescriptionFieldEmpty(newDescriptionTextField.getText()) || !descriptionFieldMaxValidation(newDescriptionTextField.getText())) {
                    //also clears the description text field and sets a prompt if validation for the description didn't pass
                    newDescriptionTextField.clear();
                    newDescriptionTextField.setPromptText("Enter a valid description that is no longer than 256 characters.");
                }
            } else {
                //clears the description text field and sets a prompt if validation for the description didn't pass when name did pass
                newDescriptionTextField.clear();
                newDescriptionTextField.setPromptText("Enter a valid description that is no longer than 256 characters.");
            }
        }

    }

    public boolean descriptionFieldMaxValidation(String description) {
        //validates the length of the description to be <= 256 characters
        return description.length() <= 256;
    }

    public boolean isDescriptionFieldEmpty(String description) {
        //checks if the description is empty
        return description.equals("");
    }

    public boolean isNameFieldUnique(String itemName) {
        //returns the inverted value form the item list item exists function
        //to verify if the item is unique
        return !itemList.itemExists(itemName);
    }

    public boolean isNameFieldEmpty(String itemName) {
        //checks if the item name is empty
        return itemName.equals("");
    }

    public void initialize() {
        //set format for the date picker field using string converter to match the wanted format
        //using the date formatter ISO_DATE will give this result
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
