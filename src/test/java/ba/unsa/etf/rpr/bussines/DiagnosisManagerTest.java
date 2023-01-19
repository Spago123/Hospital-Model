package ba.unsa.etf.rpr.bussines;

import ba.unsa.etf.rpr.dao.HistoryDaoSQLImpl;
import ba.unsa.etf.rpr.dao.PatientDaoSQLImpl;
import ba.unsa.etf.rpr.domain.History;
import ba.unsa.etf.rpr.exceptions.HospitalException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class DiagnosisManagerTest {

    private DoctorManagerTest doctorManagerTest;
    private PatientManagerTest patientManagerTest;

    private DiagnosisManager diagnosisManager;
    private History history;
    private HistoryDaoSQLImpl historyDaoSQLMock;
    private List<History> historyList;

    @BeforeEach
    public void initializeObjects() throws HospitalException {
        doctorManagerTest = new DoctorManagerTest();
        doctorManagerTest.initializeObjects();
        patientManagerTest = new PatientManagerTest();
        patientManagerTest.initializeObjects();

        diagnosisManager = Mockito.mock(DiagnosisManager.class);
        historyDaoSQLMock = Mockito.mock(HistoryDaoSQLImpl.class);

        History history1 = new History(1, patientManagerTest.getPatients().get(0), doctorManagerTest.getDoctors().get(0), "Kao drijen");
        History history2 = new History(2, patientManagerTest.getPatients().get(1), doctorManagerTest.getDoctors().get(1), "Kao kamen");

        historyList = new ArrayList<>();
        historyList.addAll(Arrays.asList(history1, history2));
    }

    @Test
    public void printAllHistories() throws HospitalException {
        when(diagnosisManager.getAll()).thenReturn(historyList);

        Assertions.assertEquals(historyList, diagnosisManager.getAll());
    }
}
