/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Alexander Bagherzadeh
 */

package baseline;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ListFont extends javafx.scene.control.ListCell<Item> {
    //fixes the spacing problem in list view.
    private Label label = new Label();
    private Label label2 = new Label();
    private Label label3 = new Label();
    private HBox hBox = new HBox(16);
    private VBox vBoxName = new VBox(8);
    private VBox vBoxDate = new VBox(8);
    private VBox vBoxComplete = new VBox(8);


    public ListFont() {
        //changes the font of the label in the listview
        vBoxName.getChildren().add(label);
        vBoxDate.getChildren().add(label2);
        vBoxComplete.getChildren().add(label3);
        vBoxName.setAlignment(Pos.CENTER_LEFT);
        vBoxComplete.setAlignment(Pos.CENTER_RIGHT);
        vBoxDate.setAlignment(Pos.CENTER);
        hBox.getChildren().add(vBoxName);
        hBox.getChildren().add(vBoxDate);
        hBox.getChildren().add(vBoxComplete);
        vBoxComplete.setPrefWidth(152);
        vBoxDate.setPrefWidth(112);
        vBoxName.setPrefWidth(104);
        vBoxName.setMaxWidth(104);
    }

    @Override
    protected void updateItem(Item item, boolean empty) {
        //sets what is displayed in the listview
        super.updateItem(item, empty);
        if (empty || item == null) {
            setGraphic(null);//don't display anything
        } else {
            // set list view's text
            label.setText(item.getItemName());
            label2.setText(item.getDueDate());
            label3.setText((item.getCompleted()) ? "Completed" : "Not Completed");
            setGraphic(hBox);
        }
    }
}
