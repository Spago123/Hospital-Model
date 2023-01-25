package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.bussines.DiagnosisManager;
import ba.unsa.etf.rpr.domain.Doctor;
import ba.unsa.etf.rpr.domain.History;
import ba.unsa.etf.rpr.domain.Patient;
import ba.unsa.etf.rpr.exceptions.HospitalException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * AddDiagnosis controller is a class that handles the input in add diagnosis view
 */
public class AddDiagnosisController {

    private Doctor doctor;
    private Patient patient;

    private DiagnosisManager diagnosisManager = new DiagnosisManager();

    public TextField patientName;
    public TextArea diagnosis;
    public Button save;
    public Button exit;

    public AddDiagnosisController(Patient patient, Doctor doctor){
        this.patient = patient;
        this.doctor = doctor;
    }


    @FXML
    private void initialize(){
        patientName.setText(patient.getName());
    }

    /**
     * Method that saves the exits the current window and saves the new diagnosis to the datasase
     * @param actionEvent save button presses
     * @throws HospitalException
     */
    @FXML
    private void save(ActionEvent actionEvent) throws HospitalException {
        History newHistory = new History(1, patient, doctor, diagnosis.getText());
        diagnosisManager.add(newHistory);
        DoctorHomeController doctorHomeController = new DoctorHomeController(doctor);
        new OpenNewWindow().openDialog("doctorHome", "/fxml/doctorHome.fxml", doctorHomeController, (Stage) save.getScene().getWindow());
    }

    /**
     * Method that exits the current window without saving any changes to the database
     * @param actionEvent exit button pressed
     */
    @FXML
    private void exit(ActionEvent actionEvent) {
        DoctorHomeController doctorHomeController = new DoctorHomeController(doctor);
        new OpenNewWindow().openDialog( "doctorHome", "/fxml/doctorHome.fxml", doctorHomeController, (Stage) save.getScene().getWindow());
    }
}
