package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.ATTENDED_FIRST_APPOINTMENT;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;



public class JsonAdaptedAppointmentTest {
    private static final int INVALID_APPOINTMENT_ID = -1;
    private static final int INVALID_STUDENT_ID = -1;

    private static final int VALID_APPOINTMENT_ID = ATTENDED_FIRST_APPOINTMENT.getAppointmentId();
    private static final LocalDateTime VALID_APPOINTMENT_DATE_TIME =
            ATTENDED_FIRST_APPOINTMENT.getAppointmentDateTime();
    private static final int VALID_STUDENT_ID = ATTENDED_FIRST_APPOINTMENT.getStudentId();
    private static final String VALID_APPOINTMENT_DESCRIPTION =
            ATTENDED_FIRST_APPOINTMENT.getAppointmentDescription();

    private static final boolean VALID_ATTENDED_STATUS = ATTENDED_FIRST_APPOINTMENT.getAttendedStatus();

    @Test
    public void toModelType_validAppointmentDetails_returnsAppointment() throws Exception {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(ATTENDED_FIRST_APPOINTMENT);
        assertEquals(ATTENDED_FIRST_APPOINTMENT, appointment.toModelType());
    }

    @Test
    public void toModelType_invalidAppointmentId_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(INVALID_APPOINTMENT_ID,
                VALID_APPOINTMENT_DATE_TIME, VALID_STUDENT_ID, VALID_APPOINTMENT_DESCRIPTION, VALID_ATTENDED_STATUS);
        String expectedMessage = "Please only use positive index.";
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_invalidStudentId_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_APPOINTMENT_ID,
                VALID_APPOINTMENT_DATE_TIME, INVALID_STUDENT_ID, VALID_APPOINTMENT_DESCRIPTION, VALID_ATTENDED_STATUS);
        String expectedMessage = "Please only use positive index.";
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    //TODO: Add null check
}
