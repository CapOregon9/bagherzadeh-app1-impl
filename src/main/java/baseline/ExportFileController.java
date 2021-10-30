package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ExportFileController {

    @FXML
    private TextField fileLocationTextField;

    @FXML
    private TextField fileNameTextField;

    @FXML
    private Button saveButton;

    @FXML
    void saveFile(ActionEvent event) {
        //create JSON file from objects storing list information
        //use the list passed over to know what objects to save
    }

}
