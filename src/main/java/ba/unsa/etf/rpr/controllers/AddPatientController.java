package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.bussines.PatientManager;
import ba.unsa.etf.rpr.domain.Doctor;
import ba.unsa.etf.rpr.domain.Patient;
import ba.unsa.etf.rpr.exceptions.HospitalException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddPatientController {

    private Doctor doctor;

    public Button exit;
    private PatientManager patientManager= new PatientManager();
    public TextField patientsName;
    public TextField patientsUIN;
    public TextField patientsPassword;
    
    
    public Button add;

    public AddPatientController(Doctor doctor){
        this.doctor = doctor;
    }

    @FXML
    private void initialize(){

    }

    public void exitBtn(javafx.event.ActionEvent actionEvent) {
        new OpenNewWindow().openDialog("doctorHome", "/fxml/doctorHome.fxml", new DoctorHomeController(doctor), (Stage) add.getScene().getWindow());
    }

    public void addBtn(javafx.event.ActionEvent actionEvent) {
        try {
            patientManager.add(new Patient(1, patientsName.getText(), patientsPassword.getText(), Long.parseLong(patientsUIN.getText()), doctor));
        } catch (HospitalException e){
            OpenNewWindow.alert(Alert.AlertType.ERROR, "ERROR!", "Something went wrong",
                    e.getMessage());
        }
    }
}
