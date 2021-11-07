/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Alexander Bagherzadeh
 */

package baseline;

import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ItemListController {
    //create item list to store all item data
    private ItemList itemList = new ItemList();
    //create a stage object to store the current stage when transferring to other controllers as well as for the file chooser
    private Stage stage;
    //create observable list that mirrors the item list and is linked to the list view
    private final ObservableList<Item> list = FXCollections.observableArrayList();
    //create a file chooser to be used in opening and saving the text files of an item list
    private FileChooser fileChooser = new FileChooser();

    @FXML
    private TextField itemDateTextField;

    @FXML
    private TextArea itemDescriptionTextField;

    @FXML
    private TextField itemNameTextField;

    @FXML
    private Button sortListButton;

    @FXML
    private Button addItemButton;

    @FXML
    private Button closeButton;

    @FXML
    private Button clearItemsButton;

    @FXML
    private CheckBox completedCheckBox;

    @FXML
    private ToggleGroup completedToggleGroup;

    @FXML
    private Button editSelectedItemButton;

    @FXML
    private Button helpButton;

    @FXML
    private ListView<Item> itemsListView;

    @FXML
    private Button removeSelectedItemButton;

    @FXML
    private RadioButton showAllItemsRadioButton;

    @FXML
    private RadioButton showCompleteItemsRadioButton;

    @FXML
    private RadioButton showIncompleteItemsRadioButton;

    @FXML
    private Button exportFileButton;

    @FXML
    private Button importFileButton;

    private HostServices hostServices;

    private Hyperlink hyperlink;

    public void itemListDataPass(ItemList itemList, HostServices hostServices) {
        //Receive the item list from another controller object (either edit item or add item controller)
        this.hostServices = hostServices;
        this.itemList = itemList;
        fillListView();
    }

    @FXML
    void exportToFile(ActionEvent event) {
        //Save the file using a file chooser that uses the same stage
        //call method to save file only if a file is chosen
        fileChooser.setTitle("Save File");
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            saveFile(file.toString());
        }
    }

    @FXML
    void importFromFile(ActionEvent event) {
        //Open a file using a file chooser that uses the same stage
        //call method to open file only if a file is chosen
        fileChooser.setTitle("Open File");
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            openFile(file);
        }
    }

    @FXML
    void addNewItem(ActionEvent event) {
        //move to add item scene passing over all lists as well
        //pass in value for the item list
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddItem.fxml"));

        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Could not load add item fxml.");
        }
        AddItemController controller = fxmlLoader.getController();
        controller.itemListDataPass(itemList, hostServices);
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void changeCompletedState(ActionEvent event) {
         //toggle completed for item using method
         //update observation list and ListView
            if (!itemsListView.getSelectionModel().isEmpty()) {
                itemList.setItemCompletedState(itemsListView.getSelectionModel().getSelectedItem().getItemName(), completedCheckBox.isSelected());
                list.clear();
                list.addAll(itemList.getToDoList());

            }
    }

    @FXML
    void closeList(ActionEvent event) {
        //close application
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void editSelectedItem(ActionEvent event) {
        //move to edit item scene passing over all lists as well
        //pass in value for the selected item as well as the item list
        if (!itemsListView.getSelectionModel().isEmpty()) {
            String itemName = itemsListView.getSelectionModel().getSelectedItem().getItemName();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditItem.fxml"));

            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException e) {
                System.out.println("Could not load add item fxml.");
            }
            EditItemController controller = fxmlLoader.getController();
            controller.itemListDataPass(itemList, itemName, hostServices);
            Scene scene = new Scene(root);
            JMetro jMetro = new JMetro(Style.LIGHT);
            jMetro.setScene(scene);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void sortListByDate(ActionEvent event) {
        //calls the sort list method
        sortList();
    }

    void sortList() {
        //sorts items by due date
        list.sort(Comparator.comparing(Item::getDueDate));
    }

    @FXML
    void removeSelectedItem(ActionEvent event) {
        //check listener to get name of what item is selected
        //call method of itemList to remove item from list
        //update observationList and listView
        if (!itemsListView.getSelectionModel().isEmpty()) {
            itemList.removeItem(itemsListView.getSelectionModel().getSelectedItem().getItemName());
            list.remove(itemsListView.getSelectionModel().getSelectedItem());
            resetItemValues();
        }
    }

    public void resetItemValues() {
        //resets the display information for an item
        itemNameTextField.clear();
        itemDescriptionTextField.clear();
        itemDateTextField.clear();
        completedCheckBox.setSelected(false);
    }

    @FXML
    void selectItemsToShow(ActionEvent event) {
        //cycle between different filters
        //radio buttons will give a value All, Completed, or Not Completed
        //use this to chose which method to run
        //each method call updates the observable list which then changes the list view
            resetItemValues();
            if (completedToggleGroup.getSelectedToggle().getUserData().equals("All")){
                showAllItems();
            }
            else if (completedToggleGroup.getSelectedToggle().getUserData().equals("Completed")) {
                showCompletedItems();
            } else {
                showIncompleteItems();
            }
    }

    public void showIncompleteItems() {
        //adds only incomplete items to observable list after clearing it
        list.clear();
        for (Item item : itemList.getToDoList()) {
            if (!item.getCompleted()) {
                list.add(item);
            }
        }
    }

    public void showCompletedItems() {
        //adds only completed items to observable list after clearing it
        list.clear();
        for (Item item : itemList.getToDoList()) {
            if (item.getCompleted()) {
                list.add(item);
            }
        }
    }

    public void showAllItems() {
        //adds all items to observable list after clearing it
        list.clear();
        list.addAll(itemList.getToDoList());
    }

    public ObservableList<Item> getList() {
        //used for junit testing
        return this.list;
    }

    @FXML
    void clearAllItems(ActionEvent event) {
        //clears all items from the item list and observable list
        itemList.clearAllItems();
        list.clear();
        itemsListView.setItems(list);
        resetItemValues();
    }

    public void initialize() {
        //sets the user data of each radio button to be used to distinguish in toggle group method
        showAllItemsRadioButton.setUserData("All");
        showCompleteItemsRadioButton.setUserData("Completed");
        showIncompleteItemsRadioButton.setUserData("Not Completed");
        //sets the display fields of item information to not be visible by the mouse (can't click them)
        itemNameTextField.setMouseTransparent(true);
        itemDateTextField.setMouseTransparent(true);
        itemDescriptionTextField.setMouseTransparent(true);

        //initializes file chooser available extensions
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        //initialize listView of items and create a listener selection detection on an observationList
        //Fix spacing issue using cell factory
        itemsListView.setCellFactory(listView -> new ListFont());
        fillListView();
        itemsListView.setItems(list);

        //listener only changes data if something is selected in order to remove null pointer exception
        //when swapping what is displayed with the radio buttons
        itemsListView.getSelectionModel().selectedItemProperty().addListener(
                (ov, oldValue, newValue) -> {
                    if (!itemsListView.getSelectionModel().isEmpty()) {
                        itemNameTextField.setText(newValue.getItemName());
                        itemDescriptionTextField.setText(newValue.getItemDescription());
                        itemDateTextField.setText(newValue.getDueDate());
                        completedCheckBox.setSelected(newValue.getCompleted());
                    }
                });
    }

    public void fillListView() {
        //adds all items from the item list to the observable list connected to the listview
        list.addAll(itemList.getToDoList());
    }


    public void openFile(File file) {
        //opens all items from a text file using a scanner object
        //file is passed in from the file made by the file chooser
        itemList = new ItemList();
        try (Scanner inputLine = new Scanner(file)) {
            while (inputLine.hasNextLine()) {
                String name = inputLine.nextLine();
                String description = inputLine.nextLine();
                String date = inputLine.nextLine();
                int complete = Integer.parseInt(inputLine.nextLine());
                itemList.addItem(name, description, date);
                itemList.setItemCompletedState(name, complete == 1);
            }
        } catch (NoSuchElementException | IllegalStateException | FileNotFoundException e) {
            System.out.println("Could not read file.");
        }
        list.clear();
        fillListView();
    }

    public void saveFile(String fileURI) {
        //saves all items to a text file using formatter object
        //file location is passed in via a string from the file made by the file chooser
            try (Formatter output = new Formatter(fileURI)) {
                for (Item item:itemList.getToDoList()) {
                    saveLineToFile(item, output);
                }
            } catch (SecurityException | FileNotFoundException | FormatterClosedException e) {
                System.out.println("Could not write file.");
            }
    }

    public void saveLineToFile(Item item, Formatter output) {
        //Formats a single item to the text file
        int tempComplete = 0;
        if (item.getCompleted()){
            tempComplete = 1;
        }
        output.format("%s%n%s%n%s%n%d%n", item.getItemName(), item.getItemDescription(), item.getDueDate(), tempComplete);
    }

    @FXML
    void openUserGuide(ActionEvent event) {
        hostServices.showDocument("docs\\ItemListUserGuide.pdf");
    }

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }
}
