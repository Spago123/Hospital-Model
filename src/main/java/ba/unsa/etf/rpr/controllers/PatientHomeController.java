package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.AppFX;
import ba.unsa.etf.rpr.bussines.DiagnosisManager;
import ba.unsa.etf.rpr.controllers.components.OneButtonCellFactory;
import ba.unsa.etf.rpr.controllers.components.OneButtonTableCell;
import ba.unsa.etf.rpr.domain.History;
import ba.unsa.etf.rpr.domain.Patient;
import ba.unsa.etf.rpr.exceptions.HospitalException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * Class that handles the input on the patientHome view
 */
public class PatientHomeController {

    private Patient patient;

    private DiagnosisManager diagnosisManager = new DiagnosisManager();

    public Tab personalInfo;
    public Button editPersonalInfo;
    public TextField patientUIN;
    public TextField patientDoctor;
    public Label welcomeName;


    public TableView patientDiagnosisView;
    public TableColumn<History, Integer> id;
    public TableColumn<History, String> doctor;
    public TableColumn<History, String> diagnosis;
    public TableColumn<History, Integer> view;

    public PatientHomeController(Patient patient){
        this.patient = patient;
    }

    /**
     * Here we fill the javaFx components with required data
     */
    public void initialize(){
        welcomeName.setText(patient.getName());
        patientUIN.setText(String.valueOf(patient.getUIN()));
        patientDoctor.setText(patient.getDoctor().getName());


        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        doctor.setCellValueFactory(new PropertyValueFactory<>("doctor"));
        diagnosis.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        view.setCellValueFactory(new PropertyValueFactory<>("id"));

        view.setCellFactory(new OneButtonCellFactory(viewEvent -> {
            int historyId = Integer.parseInt(((Button)viewEvent.getSource()).getUserData().toString());
            showHistoryScene(historyId);
        }, "View"));

        updateHistories();
    }

    /**
     * Method that updates the diagnosis table
     */
    private void updateHistories() {
        patientDiagnosisView.setItems(FXCollections.observableList(diagnosisManager.getByPatient(patient)));
        patientDiagnosisView.refresh();
    }

    /**
     * Method that is being called when we press the view button in the diagnosis table
     * @param historyId
     */
    private void showHistoryScene(int historyId) {
        try {
            ViewHistoryController viewHistoryController = new ViewHistoryController<Patient>(diagnosisManager.getById(historyId), patient);
            new OpenNewWindow<>().openDialog("viewHistory", "/fxml/viewHistory.fxml", viewHistoryController, (Stage) patientUIN.getScene().getWindow());
        } catch (HospitalException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Method that is being called when we want to edit our password
     * @param actionEvent on edit button pressed
     */
    public void editPersonalInfo(ActionEvent actionEvent) {
        EditPasswordController editPasswordController = new EditPasswordController<Patient>(patient);
        new OpenNewWindow<>().openDialog("editPass", "/fxml/editPass.fxml", editPasswordController, (Stage) patientUIN.getScene().getWindow());
    }
}
