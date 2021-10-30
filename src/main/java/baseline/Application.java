package baseline;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Application extends javafx.application.Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Application.fxml")));

        Scene scene = new Scene(root);
        stage.setTitle("To-Do List"); // displayed in window's title bar
        stage.setScene(scene);
        stage.show();
    }
}
