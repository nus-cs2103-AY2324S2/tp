package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedAppointment.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.ALICE_APPT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.date.Date;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.AppointmentType;
import seedu.address.model.appointment.Note;
import seedu.address.model.appointment.Time;
import seedu.address.model.appointment.TimePeriod;
import seedu.address.model.patient.Nric;

public class JsonAdaptedAppointmentTest {
    private static final String INVALID_NRIC = "t01234567A";
    private static final String INVALID_DATE = "2024-13-01";
    private static final String INVALID_TIME = "25:00";
    private static final String INVALID_TIME_PERIOD_START = "12:00";
    private static final String INVALID_TIME_PERIOD_END = "11:00";


    private static final String INVALID_TYPE = "";

    private static final String VALID_NRIC = ALICE_APPT.getNric().toString();
    private static final String VALID_DATE = ALICE_APPT.getDate().toString();
    private static final String VALID_START_TIME = ALICE_APPT.getStartTime().toString();
    private static final String VALID_END_TIME = ALICE_APPT.getEndTime().toString();
    private static final String VALID_TYPE = ALICE_APPT.getAppointmentType().typeName;
    private static final String VALID_NOTE = ALICE_APPT.getNote().note;
    private static final boolean VALID_MARK = ALICE_APPT.getMark().isMarked;
    @Test
    public void toModelType_validAppointmentDetails_returnsAppointment() throws Exception {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(ALICE_APPT);
        assertEquals(ALICE_APPT, appointment.toModelType());
    }

    @Test
    public void toModelType_invalidNric_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(INVALID_NRIC, VALID_DATE,
                VALID_START_TIME, VALID_END_TIME, VALID_TYPE, VALID_NOTE, VALID_MARK);
        String expectedMessage = Nric.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullNric_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(null, VALID_DATE,
                VALID_START_TIME, VALID_END_TIME, VALID_TYPE, VALID_NOTE, VALID_MARK);
        String expectedMessage = String.format(JsonAdaptedAppointment.MISSING_FIELD_MESSAGE_FORMAT,
                Nric.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_NRIC, INVALID_DATE,
                VALID_START_TIME, VALID_END_TIME, VALID_TYPE, VALID_NOTE, VALID_MARK);
        String expectedMessage = seedu.address.commons.core.date.Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_NRIC, null,
                VALID_START_TIME, VALID_END_TIME, VALID_TYPE, VALID_NOTE, VALID_MARK);
        String expectedMessage = String.format(JsonAdaptedAppointment.MISSING_FIELD_MESSAGE_FORMAT,
                Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_invalidStartTime_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_NRIC, VALID_DATE,
                INVALID_TIME, VALID_END_TIME, VALID_TYPE, VALID_NOTE, VALID_MARK);
        String expectedMessage = Time.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullStartTime_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_NRIC, VALID_DATE,
                null, VALID_END_TIME, VALID_TYPE, VALID_NOTE, VALID_MARK);
        String expectedMessage = String.format(JsonAdaptedAppointment.MISSING_FIELD_MESSAGE_FORMAT,
                Time.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_invalidEndTime_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_NRIC, VALID_DATE,
                VALID_START_TIME, INVALID_TIME, VALID_TYPE, VALID_NOTE, VALID_MARK);
        String expectedMessage = Time.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_invalidTimePeriod_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_NRIC, VALID_DATE,
                INVALID_TIME_PERIOD_START, INVALID_TIME_PERIOD_END, VALID_TYPE, VALID_NOTE, VALID_MARK);
        String expectedMessage = TimePeriod.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullEndTime_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_NRIC, VALID_DATE,
                VALID_END_TIME, null, VALID_TYPE, VALID_NOTE, VALID_MARK);
        String expectedMessage = String.format(JsonAdaptedAppointment.MISSING_FIELD_MESSAGE_FORMAT,
                Time.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_invalidType_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_NRIC, VALID_DATE,
                VALID_START_TIME, VALID_END_TIME, INVALID_TYPE, VALID_NOTE, VALID_MARK);
        String expectedMessage = AppointmentType.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullType_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_NRIC, VALID_DATE,
                VALID_START_TIME, VALID_END_TIME, null, VALID_NOTE, VALID_MARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                AppointmentType.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullNote_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_NRIC, VALID_DATE,
                VALID_START_TIME, VALID_END_TIME, VALID_TYPE, null, VALID_MARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Note.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_convertTrueMark_succeeds() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_NRIC, VALID_DATE,
                VALID_START_TIME, VALID_END_TIME, VALID_TYPE, VALID_NOTE, true);
        assertDoesNotThrow(appointment::toModelType); //does not throw any exceptions
    }

    @Test
    public void toModelType_convertFalseMark_succeeds() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_NRIC, VALID_DATE,
                VALID_START_TIME, VALID_END_TIME, VALID_TYPE, VALID_NOTE, false);
        assertDoesNotThrow(appointment::toModelType); //does not throw any exceptions
    }
}
