package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BROWN;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.model.appointment.exceptions.DuplicateAppointmentException;
import seedu.address.testutil.AppointmentBuilder;

public class UniqueAppointmentListTest {

    private static final Appointment VALID_APPT = new AppointmentBuilder().build();

    private final UniqueAppointmentList uniqueAppointmentList = new UniqueAppointmentList();

    @Test
    public void contains_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.contains(null));
    }

    @Test
    public void contains_appointmentNotInList_returnsFalse() {
        assertFalse(uniqueAppointmentList.contains(VALID_APPT));
    }

    @Test
    public void contains_appointmentInList_returnsTrue() {
        uniqueAppointmentList.add(VALID_APPT);
        assertTrue(uniqueAppointmentList.contains(VALID_APPT));
    }

    @Test
    public void contains_appointmentWithSameIdentityFieldsInList_returnsTrue() {
        uniqueAppointmentList.add(VALID_APPT);
        Appointment editedAppt = new Appointment(
                VALID_APPT.getDoctoNric(),
                VALID_APPT.getPatientNric(),
                VALID_APPT.getAppointmentDate()
        );
        assertTrue(uniqueAppointmentList.contains(editedAppt));
    }

    @Test
    public void add_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.add(null));
    }

    @Test
    public void add_duplicateAppointment_throwsDuplicateAppointmentException() {
        uniqueAppointmentList.add(VALID_APPT);
        assertThrows(DuplicateAppointmentException.class, () -> uniqueAppointmentList.add(VALID_APPT));
    }

    @Test
    public void setAppointment_nullTargetAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.setAppointment(null, VALID_APPT));
    }

    @Test
    public void setAppointment_nullEditedAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.setAppointment(
                VALID_APPT, null));
    }

    @Test
    public void setAppointment_targetAppointmentNotInList_throwsAppointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, () ->
                uniqueAppointmentList.setAppointment(VALID_APPT, VALID_APPT));
    }

    @Test
    public void setAppointment_editedAppointmentIsSameAppointment_success() {
        uniqueAppointmentList.add(VALID_APPT);
        uniqueAppointmentList.setAppointment(VALID_APPT, VALID_APPT);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(VALID_APPT);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointment_editedAppointmentHasSameIdentity_success() {
        uniqueAppointmentList.add(VALID_APPT);
        Appointment editedAppt = new Appointment(
                VALID_APPT.getDoctoNric(),
                VALID_APPT.getPatientNric(),
                VALID_APPT.getAppointmentDate()
        );
        uniqueAppointmentList.setAppointment(VALID_APPT, editedAppt);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(editedAppt);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointment_editedAppointmentHasDifferentIdentity_success() {
        uniqueAppointmentList.add(VALID_APPT);
        Appointment editedAppt = new Appointment(
                BROWN.getNric(),
                VALID_APPT.getPatientNric(),
                VALID_APPT.getAppointmentDate()
        );
        uniqueAppointmentList.setAppointment(VALID_APPT, editedAppt);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(editedAppt);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointment_editedAppointmentHasNonUniqueIdentity_throwsDuplicateAppointmentException() {
        uniqueAppointmentList.add(VALID_APPT);
        Appointment editedAppt = new Appointment(
                BROWN.getNric(),
                VALID_APPT.getPatientNric(),
                VALID_APPT.getAppointmentDate()
        );
        uniqueAppointmentList.add(editedAppt);
        assertThrows(DuplicateAppointmentException.class, () ->
                uniqueAppointmentList.setAppointment(VALID_APPT, editedAppt));
    }

    @Test
    public void remove_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.remove(null));
    }

    @Test
    public void remove_appointmentDoesNotExist_throwsAppointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, () -> uniqueAppointmentList.remove(VALID_APPT));
    }

    @Test
    public void remove_existingAppointment_removesAppointment() {
        uniqueAppointmentList.add(VALID_APPT);
        uniqueAppointmentList.remove(VALID_APPT);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointments_nullUniqueAppointmentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.setAppointments(null));
    }

    @Test
    public void setAppointments_uniqueAppointmentList_replacesOwnListWithProvidedUniqueAppointmentList() {
        uniqueAppointmentList.add(VALID_APPT);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        Appointment editedAppt = new Appointment(
                BROWN.getNric(),
                VALID_APPT.getPatientNric(),
                VALID_APPT.getAppointmentDate()
        );
        expectedUniqueAppointmentList.add(editedAppt);
        uniqueAppointmentList.setAppointments(expectedUniqueAppointmentList.asUnmodifiableObservableList());
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointments_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.setAppointments((List<Appointment>) null));
    }

    // TODO: check this testcase
    @Test
    public void setAppointments_list_replacesOwnListWithProvidedList() {
        uniqueAppointmentList.add(VALID_APPT);
        Appointment editedAppt = new Appointment(
                VALID_APPT.getDoctoNric(),
                VALID_APPT.getPatientNric(),
                VALID_APPT.getAppointmentDate()
        );
        List<Appointment> appointmentList = Collections.singletonList(editedAppt);
        uniqueAppointmentList.setAppointments(appointmentList);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(editedAppt);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointments_listWithDuplicateAppointments_throwsDuplicateAppointmentException() {
        List<Appointment> listWithDuplicateAppointments = Arrays.asList(VALID_APPT, VALID_APPT);
        assertThrows(DuplicateAppointmentException.class, () ->
                uniqueAppointmentList.setAppointments(listWithDuplicateAppointments));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueAppointmentList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueAppointmentList.asUnmodifiableObservableList().toString(), uniqueAppointmentList.toString());
    }

}
