package baseline;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Map;
import java.util.Objects;

public class TodoListApplication extends javafx.application.Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Map<String, Scene> scenes;
        //FXMLLoader itemListLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("ItemsList.fxml")));
        //itemListLoader.setController(new ItemListController());
        //Parent itemListUI = itemListLoader.load();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ItemsList.fxml")));
        Scene scene = new Scene(root);
        stage.setTitle("To-Do List"); // displayed in window's title bar
        stage.setScene(scene);
        stage.show();
    }
}
