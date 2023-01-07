package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.AppFX;
import ba.unsa.etf.rpr.bussines.DiagnosisManager;
import ba.unsa.etf.rpr.bussines.PatientManager;
import ba.unsa.etf.rpr.controllers.components.OneButtonCellFactory;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Doctor;
import ba.unsa.etf.rpr.domain.History;
import ba.unsa.etf.rpr.domain.Patient;
import ba.unsa.etf.rpr.exceptions.HospitalException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.print.Doc;

public class DoctorHomeController {

    private Doctor doctor;

    public Label doctorName;
    public TextField department;
    public TextField password;

    //First table
    public TableView myPatients;
    public TableColumn<Patient, String> patientName;
    public TableColumn<Patient, Long> patientUIN;
    public TableColumn<Patient, Integer> addNew;
    public Button addPatient;


    //Second Table
    public TableView myDiagnosis;
    public TableColumn<History, String> patientNameTab2;
    public TableColumn<History, String> diagnosisTab2;
    public TableColumn<History, Integer> viewTab2;
    private PatientManager patientManager = new PatientManager();
    private DiagnosisManager diagnosisManager = new DiagnosisManager();

    public DoctorHomeController(Doctor doctor){
        this.doctor = doctor;
    }


    @FXML
    private void initialize() throws HospitalException {

        doctorName.setText(doctor.getName());
        department.setText(doctor.getDepartment().getName());
        password.setText(doctor.getPassword());
        
        patientName.setCellValueFactory(new PropertyValueFactory<>("name"));
        patientUIN.setCellValueFactory(new PropertyValueFactory<>("UIN"));
        patientUIN.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        patientUIN.setCellFactory(new OneButtonCellFactory(addEvent -> {
            int patientId = Integer.parseInt(((Button)addEvent.getSource()).getUserData().toString());
            addNewDiagnosis(patientId);
        }));

        refreshPatients();
        
        patientNameTab2.setCellValueFactory(new PropertyValueFactory<>("patient"));
        diagnosisTab2.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        viewTab2.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        viewTab2.setCellFactory(new OneButtonCellFactory(viewEvent -> {
            int historyId = Integer.parseInt(((Button)viewEvent.getSource()).getUserData().toString());
            showHistory(historyId);
        }));
        
        refreshDiagnosis();
    }



    private void showHistory(int historyId) {
        try {
            ViewHistoryController viewHistoryController = new ViewHistoryController(diagnosisManager.getById(historyId));
            new OpenNewWindow<>().openDialog(AppFX.getPageTitle("viewHistory"), "/fxml/viewHistory.fxml", viewHistoryController, (Stage) myDiagnosis.getScene().getWindow());
        } catch (HospitalException e) {
            e.printStackTrace();
        }
    }

    private void addNewDiagnosis(int patientId) {
        try {
            AddDiagnosisController addDiagnosisController = new AddDiagnosisController(patientManager.getById(patientId), doctor);
            new OpenNewWindow<>().openDialog(AppFX.getPageTitle("addDiagnosis"), "/fxml/addDiagnosis", addDiagnosisController, (Stage) myDiagnosis.getScene().getWindow());
        } catch (HospitalException e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshPatients() throws HospitalException {
        myPatients.setItems(FXCollections.observableList(patientManager.getByDoctor(doctor)));
        myPatients.refresh();
    }

    private void refreshDiagnosis(){
        myDiagnosis.setItems(FXCollections.observableList(diagnosisManager.getByDoctor(doctor)));
        myDiagnosis.refresh();
    }

    public void addPatient(ActionEvent actionEvent){
        AddPatientController addPatientController = new AddPatientController(doctor);
        new OpenNewWindow<>().openDialog(AppFX.getPageTitle("addPatient"), "/fxml/addPatient.fxml", addPatientController, (Stage) myDiagnosis.getScene().getWindow());
    }

    public void edit(ActionEvent actionEvent) {
        EditPasswordController<Doctor> editPasswordController = new EditPasswordController<>(doctor);
        new OpenNewWindow<>().openDialog(AppFX.getPageTitle("editPass"), "/fxml/editPass.fxml", editPasswordController, (Stage) myDiagnosis.getScene().getWindow());
    }
}
