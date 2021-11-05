package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.FormatterClosedException;

public class ExportFileController {

    private ItemList itemList;

    @FXML
    private TextField fileLocationTextField;

    @FXML
    private TextField fileNameTextField;

    @FXML
    private Button saveButton;

    public ExportFileController(ItemList itemList) {
        this.itemList = itemList;
    }

    @FXML
    void saveFile(ActionEvent event) {
        //create text file from the todolist information
        //use the list passed over to know what objects to save
        for (Item item:itemList.getToDoList()) {
            try (Formatter output = new Formatter(String.format("%s\\%s.txt", fileLocationTextField.getText(), fileNameTextField.getText()))) {
                saveLineToFile(item, output);
            } catch (SecurityException | FileNotFoundException | FormatterClosedException e) {
                System.out.println("Could not write file.");
            }
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
