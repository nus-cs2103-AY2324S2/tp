package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.IdUtil.deleteId;
import static seedu.address.commons.util.IdUtil.generateNewId;
import static seedu.address.commons.util.IdUtil.hasId;
import static seedu.address.commons.util.IdUtil.initalMapUpdate;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import jdk.jshell.spi.ExecutionControl;

class IdUtilTest {

    private static final String APPT_REGEX = "a\\d{8}";

    @Test
    void generateNewId_validEntity_generatesValidId() {
        String id = generateNewId(IdUtil.Entities.APPOINTMENT);
        assertTrue(Pattern.compile(APPT_REGEX).matcher(id).find());
    }

    @Test
    void initialMapUpdate_validInput_throwsNotImplementedException() {
        assertThrows(ExecutionControl.NotImplementedException.class, () -> initalMapUpdate());
    }

    @Test
    void deleteId_validIdAppointment_deletesId() {
        String id = generateNewId(IdUtil.Entities.APPOINTMENT);
        assertTrue(hasId(id));
        deleteId(id);
        assertFalse(hasId(id));
    }

    @Test
    void deleteId_validIdPatient_deletesId() {
        String id = generateNewId(IdUtil.Entities.PATIENT);
        assertTrue(hasId(id));
        deleteId(id);
        assertFalse(hasId(id));
    }

    @Test
    void deleteId_validIdDoctor_deletesId() {
        String id = generateNewId(IdUtil.Entities.DOCTOR);
        assertTrue(hasId(id));
        deleteId(id);
        assertFalse(hasId(id));
    }
}
