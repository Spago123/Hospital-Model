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

/**
 * AddPatientController is a class that models the input on the addPatient window
 */
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

    /**
     * Method that exits the current window
     * @param actionEvent exit button pressed
     */
    public void exitBtn(javafx.event.ActionEvent actionEvent) {
        new OpenNewWindow().openDialog("doctorHome", "/fxml/doctorHome.fxml", new DoctorHomeController(doctor), (Stage) add.getScene().getWindow());
    }

    /**
     * Method that exits te current window and adds a patient to the database
     * @param actionEvent add button pressed
     */
    public void addBtn(javafx.event.ActionEvent actionEvent) {
        try {
            Long UIN;
            try {
                UIN = Long.parseLong(patientsUIN.getText());
            } catch(Exception e){
                throw new HospitalException("UIN is not valid");
            }
            patientManager.add(new Patient(1, patientsName.getText(), patientsPassword.getText(), UIN, doctor));
        } catch (HospitalException e){
            OpenNewWindow.alert(Alert.AlertType.ERROR, "ERROR!", "Something went wrong",
                    e.getMessage());
        } finally {
            new OpenNewWindow().openDialog("doctorHome", "/fxml/doctorHome.fxml", new DoctorHomeController(doctor), (Stage) add.getScene().getWindow());
        }
    }
}
