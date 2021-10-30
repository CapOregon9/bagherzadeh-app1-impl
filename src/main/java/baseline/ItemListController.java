package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ItemListController {
    private ItemList itemList = new ItemList();
    Stage stage;
    private final ObservableList<Item> list = FXCollections.observableArrayList();

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

    public ItemListController() {

    }

    public ItemListController(ItemList itemList) {
        this.itemList = itemList;
    }

    @FXML
    void ExportToFile(ActionEvent event) {
        //waiting for scene manager info
    }

    @FXML
    void ImportFromFile(ActionEvent event) {
        //waiting for scene manager info
    }

    @FXML
    void addNewItem(ActionEvent event) {
        //move to add item scene passing over all lists as well
        //pass in value for the selected item as well as the selected list
        itemList.addItem(itemNameTextField.getText(), itemDescriptionTextField.getText(), itemDateTextField.getText());
        list.add(itemList.getItem(itemNameTextField.getText()));
        itemsListView.setItems(list);
    }

    @FXML
    void changeCompletedState(ActionEvent event) {
         //toggle completed for item using method
         //update observation list and ListView
        itemList.setItemCompletedState(itemsListView.getSelectionModel().getSelectedItem().getItemName(), completedCheckBox.isSelected());
        list.clear();
        list.addAll(itemList.getToDoList());
        itemsListView.setItems(list);
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
        itemList.editItem(itemsListView.getSelectionModel().getSelectedItem().getItemName() ,itemNameTextField.getText(), itemDescriptionTextField.getText(), itemDateTextField.getText());
        list.clear();
        list.addAll(itemList.getToDoList());
        itemsListView.setItems(list);
    }

    @FXML
    void removeSelectedItem(ActionEvent event) {
        //check listener to get name of what item is selected
        //call method of itemList to remove item from list
        //update observationList and listView
        itemList.removeItem(itemsListView.getSelectionModel().getSelectedItem().getItemName());
        list.remove(itemsListView.getSelectionModel().getSelectedItem());
        itemsListView.setItems(list);
    }

    @FXML
    void selectItemsToShow(ActionEvent event) {
        //cycle between different filters
        //radio buttons will give a value 0, 1, or 2
        //compare that value with the completed value of each item
        //update listView
        if (((Item) completedToggleGroup.getSelectedToggle().getUserData()).getItemName().equals("All")){
            list.clear();
            list.addAll(itemList.getToDoList());
            itemsListView.setItems(list);
        }
        else if (((Item) completedToggleGroup.getSelectedToggle().getUserData()).getCompleted()) {
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

    public void initialize() {
        //use passed in title to know what list to access
        //initialize listView of items and create a listener selection detection on an observationList
        showAllItemsRadioButton.setUserData(new Item("All", "", "", false));
        showCompleteItemsRadioButton.setUserData(new Item("", "", "", true));
        showIncompleteItemsRadioButton.setUserData(new Item("", "", "", false));


        itemList.addItem("Item1", "Description1", "01-DD-YYYY");
        itemList.addItem("Item2", "Description2", "02-DD-YYYY");
        itemList.addItem("Item3", "Description3", "03-DD-YYYY");

        list.addAll(itemList.getToDoList());
        itemsListView.setItems(list);

        itemsListView.getSelectionModel().selectedItemProperty().addListener(
                (ov, oldValue, newValue) -> {
                    itemNameTextField.setText(newValue.getItemName());
                    itemDescriptionTextField.setText(newValue.getItemDescription());
                    itemDateTextField.setText(newValue.getDueDate());
                    completedCheckBox.setSelected(newValue.getCompleted());
                });
    }
}
