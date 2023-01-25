package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.AppFX;
import ba.unsa.etf.rpr.domain.Doctor;
import ba.unsa.etf.rpr.domain.History;
import ba.unsa.etf.rpr.domain.Passwordabel;
import ba.unsa.etf.rpr.domain.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class that handles the input on the view history view
 * @param <Type> that implements the passwordabel interface
 */
public class ViewHistoryController<Type extends Passwordabel> {

    private Type user;

    private History history;
    public TextField patientName;
    public TextField doctorName;
    public Button exit;
    public TextArea diagnosis;


    public ViewHistoryController(History history, Type user){
        this.user = user;
        this.history = history;
    }

    @FXML
    private void initialize(){
        patientName.setText(history.getPatient().getName());
        doctorName.setText(history.getDoctor().getName());
        diagnosis.setText(history.getDiagnosis());
    }

    /**
     * Method that exits the window
     * @param actionEvent on exit button pressed
     * @throws IOException
     */
    public void exitButton(ActionEvent actionEvent) throws IOException {
        System.out.println("dao");
        if(user.getClass().getName().equals("ba.unsa.etf.rpr.domain.Patient")) {
            PatientHomeController patientHomeController = new PatientHomeController((Patient) user);
            new OpenNewWindow().openDialog("patientHome", "/fxml/patientHome.fxml", patientHomeController, (Stage) patientName.getScene().getWindow());
        } else if(user.getClass().getName().equals("ba.unsa.etf.rpr.domain.Doctor")){
            System.out.println("so");
            DoctorHomeController doctorHomeController = new DoctorHomeController((Doctor) user);
            new OpenNewWindow().openDialog("doctorHome", "/fxml/doctorHome.fxml", doctorHomeController, (Stage) patientName.getScene().getWindow());
        }
    }
}
