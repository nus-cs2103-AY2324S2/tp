package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.ALICE_APPT;
import static seedu.address.testutil.TypicalAppointments.BOB_APPT;
import static seedu.address.testutil.TypicalAppointmentViews.ALICE_APPT_VIEW;
import static seedu.address.testutil.TypicalAppointmentViews.ALICE_APPT_VIEW_1;
import static seedu.address.testutil.TypicalAppointmentViews.BOB_APPT_VIEW;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.model.appointment.exceptions.DuplicateAppointmentException;
import seedu.address.model.person.Name;
import seedu.address.model.person.UniquePersonList;

public class AppointmentViewListTest {

    private final AppointmentViewList appointmentListView = new AppointmentViewList();
    

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
    public void add_nullAppointmentView_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentListView.add(null));
    }

    @Test
    public void add_duplicateAppointmentView_throwsDuplicateAppointmentException() {
        appointmentListView.add(ALICE_APPT_VIEW);
        assertThrows(DuplicateAppointmentException.class, () -> appointmentListView.add(ALICE_APPT_VIEW));
    }

    @Test
    public void setAppointmentView_nullTargetAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentListView.setAppointmentView(null, ALICE_APPT_VIEW));
    }

    @Test
    public void setAppointmentView_nullEditedAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentListView
                .setAppointmentView(ALICE_APPT_VIEW, null));
    }

    @Test
    public void setAppointmentView_targetAppointmentNotInList_throwsAppointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, () -> appointmentListView
                .setAppointmentView(ALICE_APPT_VIEW, ALICE_APPT_VIEW));
    }

    @Test
    public void setAppointmentView_editedAppointmentIsSameAppointment_success() {
        appointmentListView.add(ALICE_APPT_VIEW);
        appointmentListView.setAppointmentView(ALICE_APPT_VIEW, ALICE_APPT_VIEW);
        AppointmentViewList expectedAppointmentList = new AppointmentViewList();
        expectedAppointmentList.add(ALICE_APPT_VIEW);
        assertEquals(expectedAppointmentList, appointmentListView);
    }

    @Test
    public void setAppointmentView_editedAppointmentHasDifferentIdentity_success() {
        appointmentListView.add(ALICE_APPT_VIEW);
        appointmentListView.setAppointmentView(ALICE_APPT_VIEW, BOB_APPT_VIEW);
        AppointmentViewList expectedAppointmentList = new AppointmentViewList();
        expectedAppointmentList.add(BOB_APPT_VIEW);
        assertEquals(expectedAppointmentList, appointmentListView);
    }

    @Test
    public void setAppointmentView_editedAppointmentHasNonUniqueIdentity_throwsDuplicateAppointmentException() {
        appointmentListView.add(ALICE_APPT_VIEW);
        appointmentListView.add(BOB_APPT_VIEW);
        assertThrows(DuplicateAppointmentException.class, () -> appointmentListView
                .setAppointmentView(ALICE_APPT_VIEW, BOB_APPT_VIEW));
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
        AppointmentViewList expectedAppointmentList = new AppointmentViewList();
        assertEquals(expectedAppointmentList, appointmentListView);
    }

    @Test
    public void setAppointmentViews_nullAppointmentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
            appointmentListView.setAppointmentViews(null,(AppointmentList) null));
    }

    @Test
    public void setAppointmentViews_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
            appointmentListView.setAppointmentViews(null, (List<Appointment>) null));
    }

    @Test
    public void setAppointmentViews_nonEmptyLists_success() {
        UniquePersonList personList = new UniquePersonList();
        AppointmentList appointmentList = new AppointmentList();
        personList.add(ALICE);
        appointmentList.add(ALICE_APPT);

        appointmentListView.setAppointmentViews(personList, appointmentList);
        AppointmentView apptView = new AppointmentView(ALICE.getName(), ALICE_APPT);
        AppointmentViewList expectedAppointmentList = new AppointmentViewList();
        expectedAppointmentList.add(apptView);

        assertEquals(expectedAppointmentList, appointmentListView);
    }

    @Test
    public void setAppointmentViews_emptyPersonList_success() {
        UniquePersonList personList = new UniquePersonList();
        AppointmentList appointmentList = new AppointmentList();
        appointmentList.add(ALICE_APPT);

        appointmentListView.setAppointmentViews(personList, appointmentList);
        assertTrue(appointmentListView.asUnmodifiableObservableList().isEmpty());
    }

    @Test
    public void setAppointmentViews_emptyAppointmentList_success() {
        UniquePersonList personList = new UniquePersonList();
        AppointmentList appointmentList = new AppointmentList();
        personList.add(ALICE);

        appointmentListView.setAppointmentViews(personList, appointmentList);
        assertTrue(appointmentListView.asUnmodifiableObservableList().isEmpty());
    }

    @Test
    public void setAppointmentViews_noMatches_success() {
        UniquePersonList personList = new UniquePersonList();
        AppointmentList appointmentList = new AppointmentList();
        personList.add(ALICE);
        appointmentList.add(BOB_APPT);

        appointmentListView.setAppointmentViews(personList, appointmentList);
        assertTrue(appointmentListView.asUnmodifiableObservableList().isEmpty());
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

        assertEquals(ALICE_APPT_VIEW, appointmentListView.getMatchingAppointmentView(nameToMatch, apptToMatch));
    }

    @Test
    public void getMatchingAppointment_invalidInput_appointmentFound() {
        appointmentListView.add(ALICE_APPT_VIEW);
        Name nameToMatch = ALICE_APPT_VIEW_1.getName();
        Appointment apptToMatch = ALICE_APPT_VIEW_1.getAppointment();

        assertThrows(AppointmentNotFoundException.class, () ->
                appointmentListView.getMatchingAppointmentView(nameToMatch, apptToMatch));
    }

    @Test
    public void getMatchingAppointment_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentListView.getMatchingAppointmentView(null, null));
    }

    @Test
    public void toStringMethod() {
        assertEquals(appointmentListView.asUnmodifiableObservableList().toString(), appointmentListView.toString());
    }
}

