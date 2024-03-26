package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.ALICE_APPT;
import static seedu.address.testutil.TypicalAppointments.getTypicalAddressBookWithAppointments;
import static seedu.address.testutil.TypicalPatients.ALICE;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentView;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.exceptions.DuplicatePatientException;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.PatientBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPatientList());
        assertEquals(Collections.emptyList(), addressBook.getAppointmentList());
        assertEquals(Collections.emptyList(), addressBook.getAppointmentViewList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBookWithAppointments();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePatients_throwsDuplicatePatientException() {
        // Two patients with the same identity fields
        Patient editedAlice = new PatientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Patient> newPatients = Arrays.asList(ALICE, editedAlice);
        List<Appointment> newAppointments = List.of();
        List<AppointmentView> newAppointmentViews = List.of();
        AddressBookStub newData = new AddressBookStub(newPatients, newAppointments, newAppointmentViews);

        assertThrows(DuplicatePatientException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPatient_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPatient(null));
    }

    @Test
    public void hasPatient_patientNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPatient(ALICE));
    }

    @Test
    public void hasPatient_patientInAddressBook_returnsTrue() {
        addressBook.addPatient(ALICE);
        assertTrue(addressBook.hasPatient(ALICE));
    }

    @Test
    public void hasPatient_patientWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPatient(ALICE);
        Patient editedAlice = new PatientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasPatient(editedAlice));
    }

    @Test
    public void hasPatient_patientWithNricInAddressBook_returnsTrue() {
        addressBook.addPatient(ALICE);
        Nric aliceNric = ALICE.getNric();
        assertTrue(addressBook.hasPatientWithNric(aliceNric));
    }

    @Test
    public void getPatientWithNric_patientWithNricInAddressBook_returnsPatient() {
        addressBook.addPatient(ALICE);
        Nric aliceNric = ALICE.getNric();
        assertEquals(ALICE, addressBook.getPatientWithNric(aliceNric));
    }

    @Test
    public void getPatientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPatientList().remove(0));
    }

    @Test
    public void hasAppointment_withAppointmentInAddressBook_returnsTrue() {
        addressBook.addPatient(ALICE);
        addressBook.addAppointment(ALICE_APPT);
        assertTrue(addressBook.hasAppointment(ALICE_APPT));
    }

    @Test
    public void hasAppointment_withAppointmentWithSameDetailsInAddressBook_returnsTrue() {
        addressBook.addPatient(ALICE);
        addressBook.addAppointment(ALICE_APPT);
        Appointment editedAliceAppt = new AppointmentBuilder(ALICE_APPT)
                .withAppointmentType("Blood test").withNote("routine checks")
                .build();
        assertTrue(addressBook.hasAppointment(editedAliceAppt));
    }

    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName() + "{patients=" + addressBook.getPatientList() + "}";
        assertEquals(expected, addressBook.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose patients list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Patient> patients = FXCollections.observableArrayList();
        private final ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        private final ObservableList<AppointmentView> appointmentView = FXCollections.observableArrayList();

        AddressBookStub(Collection<Patient> patients, Collection<Appointment> appointments,
                        Collection<AppointmentView> appointmentsView) {
            this.patients.setAll(patients);
            this.appointments.setAll(appointments);
            this.appointmentView.setAll(appointmentView);
        }

        @Override
        public ObservableList<Patient> getPatientList() {
            return patients;
        }

        @Override
        public ObservableList<Appointment> getAppointmentList() {
            return appointments;
        }

        @Override
        public ObservableList<AppointmentView> getAppointmentViewList() {
            return appointmentView;
        }

    }
}
