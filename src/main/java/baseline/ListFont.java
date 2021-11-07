package baseline;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class ListFont extends javafx.scene.control.ListCell<Item> {
    //tried to use this to fix the spacing problem in list view. Was unsuccessful.
    private Label label = new Label();


    public ListFont() {
        //changes the font of the label in the listview
        label.setFont(new Font("Monospaced", 12));

    }

    @Override
    protected void updateItem(Item item, boolean empty) {
        //sets what is displayed in the listview
        super.updateItem(item, empty);
        if (empty || item == null) {
            setGraphic(null);//don't display anything
        } else {
            // set list view's text
            label.setText(item.toString());
            setGraphic(label);
        }
    }
}
