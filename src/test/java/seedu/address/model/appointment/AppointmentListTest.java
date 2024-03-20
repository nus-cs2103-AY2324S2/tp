package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.ATTENDED_FIRST_APPOINTMENT;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentList;

import java.util.Collections;

import org.junit.jupiter.api.Test;

public class AppointmentListTest {

    private final AppointmentList appointmentList = new AppointmentList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), appointmentList.getAppointmentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AppointmentList newData = getTypicalAppointmentList();
        appointmentList.resetData(newData);
        assertEquals(newData, appointmentList);
    }

    //TODO: add duplicate appointments check

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentList.hasAppointment(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(appointmentList.hasAppointment(ATTENDED_FIRST_APPOINTMENT));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        appointmentList.addAppointment(ATTENDED_FIRST_APPOINTMENT);
        assertTrue(appointmentList.hasAppointment(ATTENDED_FIRST_APPOINTMENT));
    }

}
