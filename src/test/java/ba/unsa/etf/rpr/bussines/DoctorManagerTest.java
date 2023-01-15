package ba.unsa.etf.rpr.bussines;

import ba.unsa.etf.rpr.dao.DepartmentDaoSQLImpl;
import ba.unsa.etf.rpr.dao.DoctorDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Doctor;
import ba.unsa.etf.rpr.exceptions.HospitalException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DoctorManagerTest {

    private DoctorManager doctorManager;
    private Doctor doctor;
    private DoctorDaoSQLImpl doctorDaoSQLMock;
    private List<Doctor> doctors;

    private DepartmentManagerTest departmentManagerTest;

    @BeforeEach
    public void initializeObjects() throws HospitalException {
        departmentManagerTest = new DepartmentManagerTest();
        doctorManager = Mockito.mock(DoctorManager.class);
        doctorDaoSQLMock = Mockito.mock(DoctorDaoSQLImpl.class);

        Doctor doctor1 = new Doctor(1, "Ahmed Ahmedic", "Litvanija", departmentManagerTest.getDepartmentManager().getById(40));
        Doctor doctor2 = new Doctor(2, "Halili Halilic", "Estonija", departmentManagerTest.getDepartmentManager().getById(41));

        doctors = new ArrayList<>();
        doctors.addAll(Arrays.asList(doctor1, doctor2));
    }

    public DoctorManager getDoctorManager() {
        return doctorManager;
    }

    public void setDoctorManager(DoctorManager doctorManager) {
        this.doctorManager = doctorManager;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public DoctorDaoSQLImpl getDoctorDaoSQLMock() {
        return doctorDaoSQLMock;
    }

    public void setDoctorDaoSQLMock(DoctorDaoSQLImpl doctorDaoSQLMock) {
        this.doctorDaoSQLMock = doctorDaoSQLMock;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    @Test
    public void addDoctor() throws HospitalException {
        doctor = new Doctor(3, "Zajim Zajimovic", "Korčula", departmentManagerTest.getDepartmentManager().getById(42));
        doctorManager.add(doctor);

        Assertions.assertTrue(true);
        Mockito.verify(doctorManager).add(doctor);
    }

    @Test
    public void deleteDoctor() throws HospitalException {
        doctor = new Doctor(3, "Zajim Zajimovic", "Korčula", departmentManagerTest.getDepartmentManager().getById(42));
        doctorManager.delete(doctor.getId());

        Assertions.assertTrue(true);
        Mockito.verify(doctorManager).delete(doctor.getId());
    }

    @Test
    public void addAlreadyExisting() throws HospitalException {
        doctor = new Doctor(3, "Zajim Zajimovic", "Korčula", departmentManagerTest.getDepartmentManager().getById(42));
        Mockito.doCallRealMethod().when(doctorManager).add(doctor);

        HospitalException hospitalException = Assertions.assertThrows(HospitalException.class, () -> doctorManager.add(doctor),
                "Doctor already exists");
        Assert.assertEquals("Doctor already exists", hospitalException.getMessage());
    }

}
