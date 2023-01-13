package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.AppFX;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.beans.EventHandler;
import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class OpenNewWindow<Type> {

    public void openDialog(String title, String file, Type controller, Stage previous) {
        try {
            if(previous != null) {
                previous.close();
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
            loader.setController(controller);
            Stage stage = new Stage();
            stage.setScene(new Scene((Parent) loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle(AppFX.getPageTitle(title));
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }

    }
}

