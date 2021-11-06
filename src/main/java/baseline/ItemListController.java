package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ItemListController {
    private ItemList itemList = new ItemList();
    Stage stage;
    private final ObservableList<Item> list = FXCollections.observableArrayList();
    private FileChooser fileChooser = new FileChooser();

    @FXML
    private TextField itemDateTextField;

    @FXML
    private TextArea itemDescriptionTextField;

    @FXML
    private TextField itemNameTextField;

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

    public void itemListDataPass(ItemList itemList) {
        this.itemList = itemList;
        fillListView();
    }

    @FXML
    void ExportToFile(ActionEvent event) {
        //waiting for scene manager info
        fileChooser.setTitle("Save File");
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);
        saveFile(file.toString());
    }

    @FXML
    void ImportFromFile(ActionEvent event) {
        //waiting for scene manager info
        fileChooser.setTitle("Open File");
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            openFile(file);
            list.clear();
            fillListView();
        }
    }

    @FXML
    void addNewItem(ActionEvent event) {
        //move to add item scene passing over all lists as well
        //pass in value for the selected item as well as the selected list
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddItem.fxml"));

        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Could not load add item fxml.");
        }
        AddItemController controller = fxmlLoader.getController();
        controller.itemListDataPass(itemList);
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

      //  itemList.addItem(itemNameTextField.getText(), itemDescriptionTextField.getText(), itemDateTextField.getText());
      //  list.add(itemList.getItem(itemNameTextField.getText()));
      //  itemsListView.setItems(list);
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
        //return to main scene of all lists
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void editSelectedItem(ActionEvent event) {
        //move to edit item scene passing over all lists as well
        //pass in value for the selected item as well as the selected list
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
            controller.itemListDataPass(itemList, itemName);
            Scene scene = new Scene(root);
            JMetro jMetro = new JMetro(Style.LIGHT);
            jMetro.setScene(scene);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        //itemList.editItem( ,itemNameTextField.getText(), itemDescriptionTextField.getText(), itemDateTextField.getText());
        //list.clear();
        //list.addAll(itemList.getToDoList());
        //itemsListView.setItems(list);
    }

    @FXML
    void removeSelectedItem(ActionEvent event) {
        //check listener to get name of what item is selected
        //call method of itemList to remove item from list
        //update observationList and listView
        if (!itemsListView.getSelectionModel().isEmpty()) {
            itemList.removeItem(itemsListView.getSelectionModel().getSelectedItem().getItemName());
            list.remove(itemsListView.getSelectionModel().getSelectedItem());

        }
    }

    @FXML
    void selectItemsToShow(ActionEvent event) {
        //cycle between different filters
        //radio buttons will give a value 0, 1, or 2
        //compare that value with the completed value of each item
        //update listView
        if (completedToggleGroup.getSelectedToggle().getUserData().equals("All")){
            list.clear();
            list.addAll(itemList.getToDoList());

        }
        else if (completedToggleGroup.getSelectedToggle().getUserData().equals("Completed")) {
            list.clear();
            for (Item item : itemList.getToDoList()) {
                if (item.getCompleted()) {
                    list.add(item);
                }
            }

        } else {
            list.clear();
            for (Item item : itemList.getToDoList()) {
                if (!item.getCompleted()) {
                    list.add(item);
                }
            }

        }

    }

    @FXML
    void clearAllItems(ActionEvent event) {
        itemList.clearAllItems();
        list.clear();
        itemsListView.setItems(list);
    }

    public void initialize() {
        //use passed in title to know what list to access
        //initialize listView of items and create a listener selection detection on an observationList
        showAllItemsRadioButton.setUserData("All");
        showCompleteItemsRadioButton.setUserData("Completed");
        showIncompleteItemsRadioButton.setUserData("Not Completed");

        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        fillListView();
        itemsListView.setItems(list);

        itemsListView.getSelectionModel().selectedItemProperty().addListener(
                (ov, oldValue, newValue) -> {
                    itemNameTextField.setText(newValue.getItemName());
                    itemDescriptionTextField.setText(newValue.getItemDescription());
                    itemDateTextField.setText(newValue.getDueDate());
                    completedCheckBox.setSelected(newValue.getCompleted());
                });
    }

    public void fillListView() {
        list.addAll(itemList.getToDoList());
    }


    public void openFile(File file) {
        int tempComplete;
        String tempName;
        itemList = new ItemList();
        try (Scanner inputLine = new Scanner(file)) {
            while (inputLine.hasNextLine()) {
                String line = inputLine.nextLine();
                try (Scanner inputString = new Scanner(line)) {
                    tempName = inputString.next();
                    itemList.addItem(tempName, inputString.next(), inputString.next());
                    tempComplete = Integer.parseInt(inputString.next());
                    itemList.setItemCompletedState(tempName, tempComplete == 1);
                }
            }
        } catch (NoSuchElementException | IllegalStateException | FileNotFoundException e) {
            System.out.println("Could not read file.");
        }
    }

    public void saveFile(String fileURI) {
            try (Formatter output = new Formatter(fileURI)) {
                for (Item item:itemList.getToDoList()) {
                    saveLineToFile(item, output);
                    output.format("%n");
                }
            } catch (SecurityException | FileNotFoundException | FormatterClosedException e) {
                System.out.println("Could not write file.");
            }
    }

    public void saveLineToFile(Item item, Formatter output) {
        int tempComplete = 0;
        if (item.getCompleted()){
            tempComplete = 1;
        }
        output.format("%s %s %s %d", item.getItemName(), item.getItemDescription(), item.getDueDate(), tempComplete);
    }
}
