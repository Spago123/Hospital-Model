package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public interface EnterKeyBoard extends EventHandler<KeyEvent> {

    @Override
    public default void handle(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER){
            try {
                spagoHandle(new ActionEvent(keyEvent.getSource(), keyEvent.getTarget()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void spagoHandle(ActionEvent event);
}
