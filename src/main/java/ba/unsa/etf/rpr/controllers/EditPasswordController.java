package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.bussines.DoctorManager;
import ba.unsa.etf.rpr.bussines.PatientManager;
import ba.unsa.etf.rpr.domain.Doctor;
import ba.unsa.etf.rpr.domain.Passwordabel;
import ba.unsa.etf.rpr.domain.Patient;
import ba.unsa.etf.rpr.exceptions.HospitalException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Ovo radi samo jos povezivanje
 * @param <Type>
 */

public class EditPasswordController<Type extends Passwordabel> {

    private DoctorManager doctorManager = new DoctorManager();
    private PatientManager patientManager = new PatientManager();

    public TextField newPass;
    public TextField prevPass;
    private Type user;


    public EditPasswordController(Type user){
        this.user = user;
    }

    public void initialize(){
        //prevPass.setText("tu sam");

        prevPass.setText(user.getPassword());

        newPass.setOnKeyPressed((EnterKeyBoard) (event) -> save(event));
    }

    public void save(ActionEvent actionEvent){
        try {
            user.setPassword(newPass.getText());
            if (ifDoctor()) {
                doctorManager.update((Doctor) user);
            } else if (ifPatient()) {
                patientManager.update((Patient) user);
            }
        } catch (HospitalException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText("Something went wrong while updating password!");
            alert.setContentText("An error occurred while updating the new password, please try again later");
        }

        closeWindow();
    }

    public void exit(ActionEvent actionEvent) {
        closeWindow();
    }

    private void closeWindow(){
        //System.out.println("Naso ga");
       if(ifPatient()){
            //System.out.println("Uso");
            PatientHomeController patientHomeController = new PatientHomeController((Patient) user);
            new OpenNewWindow<>().openDialog("patientHome", "/fxml/patientHome.fxml", patientHomeController, (Stage) newPass.getScene().getWindow());
        } else if(ifDoctor()){
           //System.out.println("Uso");
            DoctorHomeController doctorHomeController = new DoctorHomeController((Doctor) user);
            new OpenNewWindow<>().openDialog("doctorHome", "/fxml/doctorHome.fxml", doctorHomeController, (Stage) newPass.getScene().getWindow());
        }
    }

    private boolean ifPatient(){
        return user.getClass().getName().equals("ba.unsa.etf.rpr.domain.Patient");
    }
    private boolean ifDoctor(){
        return user.getClass().getName().equals("ba.unsa.etf.rpr.domain.Doctor");
    }
}

