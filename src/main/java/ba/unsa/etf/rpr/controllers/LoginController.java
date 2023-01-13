package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.AppFX;
import ba.unsa.etf.rpr.bussines.DoctorManager;
import ba.unsa.etf.rpr.bussines.PatientManager;
import ba.unsa.etf.rpr.domain.Doctor;
import ba.unsa.etf.rpr.domain.Patient;
import ba.unsa.etf.rpr.exceptions.HospitalException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import net.bytebuddy.asm.Advice;

import javax.print.Doc;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

public class LoginController {

    private PatientManager patientManager = new PatientManager();
    private DoctorManager doctorManager = new DoctorManager();
    public Button login;
    public TextField username;
    public PasswordField password;

    public Button ipad_d;


    public LoginController(){

    }

    @FXML
    private void initialize() throws HospitalException, IOException {
        password.setOnKeyPressed((EnterKeyBoard)(event) -> login(event));
    }

    @FXML
    private void login(ActionEvent actionEvent) {
        try {

            List<Patient> patient = patientManager.getByNameAndPass(username.getText(), password.getText());
            if (patient.size() != 0) {
                PatientHomeController patientHomeController = new PatientHomeController(patient.get(0));
                new OpenNewWindow<>().openDialog("patientHome", "/fxml/patientHome.fxml",
                        patientHomeController, (Stage) username.getScene().getWindow());
                return;
            }


            List<Doctor> doctors = doctorManager.getByNameAndPass(username.getText(), password.getText());
            if (doctors.size() != 0) {
                DoctorHomeController doctorHomeController = new DoctorHomeController(doctors.get(0));
                new OpenNewWindow<>().openDialog("doctorHome", "/fxml/doctorHome.fxml",
                        doctorHomeController, (Stage) username.getScene().getWindow());
                return;
            }

            username.setText("Username and password don't match");
            password.setText("");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Incorrect login form");
            alert.setContentText("Username or password do not match!");

            alert.showAndWait();


        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Something went terribly wrong!");
            alert.setContentText("Something went wrong try again later!");

            alert.showAndWait();
        }
    }
}