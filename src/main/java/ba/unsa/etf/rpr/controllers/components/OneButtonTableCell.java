package ba.unsa.etf.rpr.controllers.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;

/**
 * Class that specifies the design of the button inside the table
 * @param <T>
 */
public class OneButtonTableCell<T> extends TableCell<T, T> {

    private Button view;
    public OneButtonTableCell(EventHandler<ActionEvent> buttonOne, String name) {
        view = new Button(name);
        view.setOnAction(buttonOne);
    }

    @Override
    protected void updateItem(T o, boolean b) {
        super.updateItem(o, b);
        if (o != null) {
            HBox box = new HBox();
            box.setAlignment(Pos.CENTER);
            view.setUserData(o);
            box.getChildren().add(view);
            setGraphic(box);
        }
    }
}
