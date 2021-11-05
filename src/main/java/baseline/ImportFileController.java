package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ImportFileController {

    private ItemList itemList;

    @FXML
    private TextField fileLocationTextField;

    @FXML
    private TextField fileNameTextField;

    @FXML
    private Button loadFileButton;

    public ImportFileController(ItemList itemList) {
        this.itemList = itemList;
    }

    @FXML
    void openFile(ActionEvent event) {
        //use text file to make objects of list information
        int tempComplete;
        String tempName;
        itemList = new ItemList();
        try (Scanner inputLine = new Scanner(Paths.get(String.format("%s\\%s.txt", fileLocationTextField.getText(), fileNameTextField.getText())))) {
            while (inputLine.hasNextLine()) {
                String line = inputLine.nextLine();
                try (Scanner inputString = new Scanner(line)) {
                    tempName = inputString.next();
                    itemList.addItem(tempName, inputString.next(), inputString.next());
                    tempComplete = Integer.parseInt(inputString.next());
                    itemList.setItemCompletedState(tempName, tempComplete == 1);
                }
            }
        } catch (IOException | NoSuchElementException | IllegalStateException e) {
            System.out.println("Could not read file.");
        }
        //return list to main scene and load main scene from scene manager
    }

}
