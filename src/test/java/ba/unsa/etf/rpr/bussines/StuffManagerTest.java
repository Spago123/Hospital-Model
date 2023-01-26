package ba.unsa.etf.rpr.bussines;

import ba.unsa.etf.rpr.exceptions.HospitalException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StuffManagerTest {

    @Test
    public void checkUINOK() throws HospitalException {
        Assertions.assertTrue(StuffManager.checkUIN("1709001151978"));
    }
    @Test
    public void checkUINFalse1() throws HospitalException {
        Assertions.assertFalse(StuffManager.checkUIN("111311111114"));
    }

    @Test
    public void checkUINFalse2() throws HospitalException {
        Assertions.assertFalse(StuffManager.checkUIN("-1113111č1114"));
    }

    /**
     * Test for when password is too short
     */
    @Test
    public void passwordTooShort() {
        Assertions.assertEquals(false, StuffManager.verifyPassword("Zalik"));
    }

    /**
     * Testing when password to long
     */
    @Test
    public void passwordTooLong() {
        Assertions.assertEquals(false, StuffManager.verifyPassword("MelikiAlikim1234"));
    }
}
