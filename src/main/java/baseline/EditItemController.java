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

public class EditItemController {
    //create item list and item name to store the information passed in which is used in multiple methods
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

    //unused controller which is needed when changing scenes (caused a null pointer exception without a specified controller)
    public EditItemController() {
        itemList = new ItemList();
    }

    @FXML
    void editItemValues(ActionEvent event) {
        //use the item list and item name passed through to edit a specific item
        //four boolean methods used to validate input from the user: 1. Name field not empty 2. Name field Unique 3. Description field not empty 4. Description field <= 256 in length
        //if these are all true, then the item list edit item method is called to edit the current item
        if (!isNameFieldEmpty(newNameTextField.getText()) && isNameFieldUnique(newNameTextField.getText()) && !isDescriptionFieldEmpty(newDescriptionTextField.getText()) && descriptionFieldMaxValidation(newDescriptionTextField.getText())) {
            itemList.editItem(itemName, newNameTextField.getText(), newDescriptionTextField.getText(), newDatePickerField.getValue().format(DateTimeFormatter.ISO_DATE));
            //Transition back to main scene as well as sending the item list data back to it.
            transitionToItemList(event);
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

    private void transitionToItemList(ActionEvent event) {
        Stage stage;
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
        //checks if the item is the same as before
        //if so, the item is marked as unique still because this is the only item with that name
        if (itemName.equals(this.itemName)) {
            return true;
        }
        //otherwise, returns the inverted value form the item list item exists function
        return !itemList.itemExists(itemName);
    }

    public boolean isNameFieldEmpty(String itemName) {
        //checks if the name field is empty
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

    public void itemListDataPass(ItemList itemList, String itemName) {
        //receives the item list and the item name from another controller and calls the method to set the field values
        this.itemList = itemList;
        this.itemName = itemName;
        setStageTexts();
    }

    public void setStageTexts() {
        //sets the values for item name, item description, and item date to what they were before,
        //so the user can choose what to edit and keep the same what they want to keep the same
        Item item = itemList.getItem(itemName);
        newNameTextField.setText(item.getItemName());
        newDescriptionTextField.setText(item.getItemDescription());
        if (!item.getDueDate().equals("")) {
            newDatePickerField.setValue(LocalDate.parse(item.getDueDate(), DateTimeFormatter.ISO_DATE));
        }
    }
}
