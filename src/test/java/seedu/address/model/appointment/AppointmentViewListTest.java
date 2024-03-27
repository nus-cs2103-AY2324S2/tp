package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.ALICE_APPT;
import static seedu.address.testutil.TypicalAppointments.BOB_APPT;
import static seedu.address.testutil.TypicalPatients.ALICE;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.patient.UniquePatientList;

public class AppointmentViewListTest {

    private final AppointmentViewList appointmentListView = new AppointmentViewList();

    @Test
    public void setAppointmentViews_nullAppointmentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
            appointmentListView.setAppointmentViews(null, (AppointmentList) null));
    }

    @Test
    public void setAppointmentViews_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
            appointmentListView.setAppointmentViews(null, (List<Appointment>) null));
    }

    @Test
    public void setAppointmentViews_nonEmptyLists_success() {
        UniquePatientList patientList = new UniquePatientList();
        AppointmentList appointmentList = new AppointmentList();
        patientList.add(ALICE);
        appointmentList.add(ALICE_APPT);

        appointmentListView.setAppointmentViews(patientList, appointmentList);

        assertEquals(patientList.asUnmodifiableObservableList().size(),
            appointmentListView.asUnmodifiableObservableList().size());
        assertEquals(appointmentList.asUnmodifiableObservableList().size(),
            appointmentListView.asUnmodifiableObservableList().size());
        //view list should not be empty
        assertFalse(appointmentListView.asUnmodifiableObservableList().isEmpty());
    }

    @Test
    public void setAppointmentViews_emptyPatientList_success() {
        UniquePatientList patientList = new UniquePatientList();
        AppointmentList appointmentList = new AppointmentList();
        appointmentList.add(ALICE_APPT);

        appointmentListView.setAppointmentViews(patientList, appointmentList);
        assertTrue(appointmentListView.asUnmodifiableObservableList().isEmpty());
    }

    @Test
    public void setAppointmentViews_emptyAppointmentList_success() {
        UniquePatientList patientList = new UniquePatientList();
        AppointmentList appointmentList = new AppointmentList();
        patientList.add(ALICE);

        appointmentListView.setAppointmentViews(patientList, appointmentList);
        assertTrue(appointmentListView.asUnmodifiableObservableList().isEmpty());
    }

    @Test
    public void setAppointmentViews_noMatches_success() {
        UniquePatientList patientList = new UniquePatientList();
        AppointmentList appointmentList = new AppointmentList();
        patientList.add(ALICE);
        appointmentList.add(BOB_APPT);

        appointmentListView.setAppointmentViews(patientList, appointmentList);
        assertTrue(appointmentListView.asUnmodifiableObservableList().isEmpty());
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> appointmentListView
                .asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(appointmentListView.asUnmodifiableObservableList().toString(), appointmentListView.toString());
    }
}

