/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Alexander Bagherzadeh
 */

package baseline;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;
import java.util.Objects;

public class TodoListApplication extends javafx.application.Application {
    //launches the javafx application and calls the overridden start method
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //opens the initial scene and used the JMetro javafx skin to modernize the look of the application.
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("ItemsList.fxml")));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            System.out.println("Could not load item list fxml.");
        }
        ItemListController itemListController = loader.getController();
        itemListController.setHostServices(getHostServices());
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setTitle("To-Do List"); // displayed in window's title bar
        stage.setScene(scene);
        stage.show();
    }
}
