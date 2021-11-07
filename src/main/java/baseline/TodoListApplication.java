package baseline;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.util.Map;
import java.util.Objects;

public class TodoListApplication extends javafx.application.Application {
    //launches the javafx application and calls the overridden start method
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //opens the initial scene and used the JMetro javafx skin to modernize the look of the application.
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ItemsList.fxml")));
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setTitle("To-Do List"); // displayed in window's title bar
        stage.setScene(scene);
        stage.show();
    }
}
