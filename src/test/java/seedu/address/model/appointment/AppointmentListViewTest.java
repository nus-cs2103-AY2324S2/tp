package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointmentViews.ALICE_APPT_VIEW;
import static seedu.address.testutil.TypicalAppointmentViews.ALICE_APPT_VIEW_1;
import static seedu.address.testutil.TypicalAppointmentViews.BOB_APPT_VIEW;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.model.appointment.exceptions.DuplicateAppointmentException;
import seedu.address.model.person.Name;

public class AppointmentListViewTest {

    private final AppointmentListView appointmentListView = new AppointmentListView();

    @Test
    public void contains_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentListView.contains(null));
    }

    @Test
    public void contains_appointmentNotInList_returnsFalse() {
        assertFalse(appointmentListView.contains(ALICE_APPT_VIEW));
    }

    @Test
    public void contains_appointmentInList_returnsTrue() {
        appointmentListView.add(ALICE_APPT_VIEW);
        assertTrue(appointmentListView.contains(ALICE_APPT_VIEW));
    }

    @Test
    public void add_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentListView.add(null));
    }

    @Test
    public void add_duplicateAppointment_throwsDuplicateAppointmentException() {
        appointmentListView.add(ALICE_APPT_VIEW);
        assertThrows(DuplicateAppointmentException.class, () -> appointmentListView.add(ALICE_APPT_VIEW));
    }

    @Test
    public void setAppointment_nullTargetAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentListView.setAppointment(null, ALICE_APPT_VIEW));
    }

    @Test
    public void setAppointment_nullEditedAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentListView
                .setAppointment(ALICE_APPT_VIEW, null));
    }

    @Test
    public void setAppointment_targetAppointmentNotInList_throwsAppointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, () -> appointmentListView
                .setAppointment(ALICE_APPT_VIEW, ALICE_APPT_VIEW));
    }

    @Test
    public void setAppointment_editedAppointmentIsSameAppointment_success() {
        appointmentListView.add(ALICE_APPT_VIEW);
        appointmentListView.setAppointment(ALICE_APPT_VIEW, ALICE_APPT_VIEW);
        AppointmentListView expectedAppointmentList = new AppointmentListView();
        expectedAppointmentList.add(ALICE_APPT_VIEW);
        assertEquals(expectedAppointmentList, appointmentListView);
    }

    @Test
    public void setAppointment_editedAppointmentHasDifferentIdentity_success() {
        appointmentListView.add(ALICE_APPT_VIEW);
        appointmentListView.setAppointment(ALICE_APPT_VIEW, BOB_APPT_VIEW);
        AppointmentListView expectedAppointmentList = new AppointmentListView();
        expectedAppointmentList.add(BOB_APPT_VIEW);
        assertEquals(expectedAppointmentList, appointmentListView);
    }

    @Test
    public void setAppointment_editedAppointmentHasNonUniqueIdentity_throwsDuplicateAppointmentException() {
        appointmentListView.add(ALICE_APPT_VIEW);
        appointmentListView.add(BOB_APPT_VIEW);
        assertThrows(DuplicateAppointmentException.class, () -> appointmentListView
                .setAppointment(ALICE_APPT_VIEW, BOB_APPT_VIEW));
    }

    @Test
    public void remove_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentListView.remove(null));
    }

    @Test
    public void remove_appointmentDoesNotExist_throwsAppointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, () -> appointmentListView.remove(ALICE_APPT_VIEW));
    }

    @Test
    public void remove_existingAppointment_removesAppointment() {
        appointmentListView.add(ALICE_APPT_VIEW);
        appointmentListView.remove(ALICE_APPT_VIEW);
        AppointmentListView expectedAppointmentList = new AppointmentListView();
        assertEquals(expectedAppointmentList, appointmentListView);
    }

    @Test
    public void setAppointments_nullAppointmentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentListView.setAppointments((AppointmentListView) null));
    }

    @Test
    public void setAppointments_appointmentList_replacesOwnListWithProvidedAppointmentList() {
        appointmentListView.add(ALICE_APPT_VIEW);
        AppointmentListView expectedAppointmentList = new AppointmentListView();
        expectedAppointmentList.add(BOB_APPT_VIEW);
        appointmentListView.setAppointments(expectedAppointmentList);
        assertEquals(expectedAppointmentList, appointmentListView);
    }

    @Test
    public void setAppointments_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
            appointmentListView.setAppointments((List<AppointmentView>) null));
    }

    @Test
    public void setAppointments_list_replacesOwnListWithProvidedList() {
        appointmentListView.add(ALICE_APPT_VIEW);
        List<AppointmentView> listOfAppointments = Collections.singletonList(BOB_APPT_VIEW);
        appointmentListView.setAppointments(listOfAppointments);
        AppointmentListView expectedAppointmentList = new AppointmentListView();
        expectedAppointmentList.add(BOB_APPT_VIEW);
        assertEquals(expectedAppointmentList, appointmentListView);
    }

    @Test
    public void setAppointments_listWithDuplicateAppointments_throwsDuplicateAppointmentException() {
        List<AppointmentView> listWithDuplicateAppointments = Arrays.asList(ALICE_APPT_VIEW, ALICE_APPT_VIEW);
        assertThrows(DuplicateAppointmentException.class, () -> appointmentListView
                .setAppointments(listWithDuplicateAppointments));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> appointmentListView
                .asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void getMatchingAppointment_validInput_appointmentFound() {
        appointmentListView.add(ALICE_APPT_VIEW);
        Name nameToMatch = ALICE_APPT_VIEW.getName();
        Appointment apptToMatch = ALICE_APPT_VIEW.getAppointment();

        assertEquals(ALICE_APPT_VIEW, appointmentListView.getMatchingAppointment(nameToMatch, apptToMatch));
    }

    @Test
    public void getMatchingAppointment_invalidInput_appointmentFound() {
        appointmentListView.add(ALICE_APPT_VIEW);
        Name nameToMatch = ALICE_APPT_VIEW_1.getName();
        Appointment apptToMatch = ALICE_APPT_VIEW_1.getAppointment();

        assertThrows(AppointmentNotFoundException.class, () ->
                appointmentListView.getMatchingAppointment(nameToMatch, apptToMatch));
    }

    @Test
    public void getMatchingAppointment_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentListView.getMatchingAppointment(null, null));
    }

    @Test
    public void toStringMethod() {
        assertEquals(appointmentListView.asUnmodifiableObservableList().toString(), appointmentListView.toString());
    }
}

