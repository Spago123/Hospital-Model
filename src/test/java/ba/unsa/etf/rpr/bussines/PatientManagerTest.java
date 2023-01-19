package ba.unsa.etf.rpr.bussines;


import ba.unsa.etf.rpr.dao.PatientDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Patient;
import ba.unsa.etf.rpr.exceptions.HospitalException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PatientManagerTest {

    private DoctorManagerTest doctorManagerTest;
    private DepartmentManagerTest departmentManagerTest;

    private PatientManager patientManager;
    private Patient patient;
    private PatientDaoSQLImpl patientDaoSQLMock;
    private List<Patient> patients;

    @BeforeEach
    public void initializeObjects() throws HospitalException {
        doctorManagerTest = new DoctorManagerTest();
        departmentManagerTest = new DepartmentManagerTest();
        patientManager = Mockito.mock(PatientManager.class);
        patientDaoSQLMock = Mockito.mock(PatientDaoSQLImpl.class);


        Patient patient1 = new Patient(1, "Halid Halidic","Vahidic", 123456789101L,  doctorManagerTest.getDoctorManager().getById(1));
        Patient patient2 = new Patient(1, "Velid Velidic","Kamiondzic", 123456789121L,  doctorManagerTest.getDoctorManager().getById(1));

        patient = patient1;

        patients = new ArrayList<>();
        patients.addAll(Arrays.asList(patient1, patient2));
    }

    public PatientManager getPatientManager() {
        return patientManager;
    }

    public void setPatientManager(PatientManager patientManager) {
        this.patientManager = patientManager;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public PatientDaoSQLImpl getPatientDaoSQLMock() {
        return patientDaoSQLMock;
    }

    public void setPatientDaoSQLMock(PatientDaoSQLImpl patientDaoSQLMock) {
        this.patientDaoSQLMock = patientDaoSQLMock;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    @Test
    public void passwordTooShort() {
        Assertions.assertEquals(false, StuffManager.verifyPassword("Zalik"));
    }

    @Test
    public void passwordTooLong() {
        Assertions.assertEquals(false, StuffManager.verifyPassword("MelikiAlikim1234"));
    }

    @Test
    public void addPatient() throws HospitalException {
        patient = new Patient(1, "Vezo Vezic", "VezoVezic123", 10101010101010L, doctorManagerTest.getDoctorManager().getById(2));
        patientManager.add(patient);

        Assertions.assertTrue(true);
        Mockito.verify(patientManager).add(patient);
    }

    @Test
    public void addAlreadyExisting() throws HospitalException {
        patient = new Patient(1, "Halid Halidic","Vahidic", 123456789101L,  doctorManagerTest.getDoctorManager().getById(1));
        Mockito.doCallRealMethod().when(patientManager).add(patient);

        HospitalException HospitalException = Assertions.assertThrows(HospitalException.class, () -> patientManager.add(patient),
                "Patient already in database");

        Assertions.assertEquals("Patient already in database", HospitalException.getMessage());
    }

    @Test
    public void addDonExistingButPasswordShort() throws HospitalException {
        patient = new Patient(2, "Hehehhehe", "Valid", 891010101010L, doctorManagerTest.getDoctorManager().getById(3));
        Mockito.doCallRealMethod().when(patientManager).add(patient);

        HospitalException HospitalException = Assertions.assertThrows(HospitalException.class, () -> patientManager.add(patient),
                "Password too short!");

        Assertions.assertEquals("Password too short", HospitalException.getMessage());
    }

    @Test
    public void deletePatient() throws HospitalException {
        patient = patientManager.getById(1);

        patientManager.delete(patient.getId());

        Assertions.assertTrue(true);
        Mockito.verify(patientManager).delete(patient.getId());
    }
}
