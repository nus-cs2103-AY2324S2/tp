package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.ALICE_APPT;
import static seedu.address.testutil.TypicalAppointments.BOB_APPT;
import static seedu.address.testutil.TypicalPatients.ALICE;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.date.Date;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentView;
import seedu.address.model.appointment.TimePeriod;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.Patient;
import seedu.address.testutil.AppointmentBuilder;

public class AddApptCommandTest {

    @Test
    public void constructor_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddApptCommand(null));
    }

    @Test
    public void execute_appointmentAcceptedByModel_addSuccessful() throws Exception {
        Appointment validAppointment = new AppointmentBuilder(ALICE_APPT).build();
        ModelStub modelStub = new ModelStubWithPatient(ALICE);
        CommandResult commandResult = new AddApptCommand(validAppointment).execute(modelStub);

        assertEquals(String.format(AddApptCommand.MESSAGE_ADD_APPOINTMENT_SUCCESS, Messages.format(validAppointment)),
                commandResult.getFeedbackToUser());
        assertTrue(modelStub.hasAppointment(validAppointment));
    }

    @Test
    public void execute_duplicateAppointment_throwsCommandException() {
        Appointment validAppointment = new AppointmentBuilder().build();
        AddApptCommand addApptCommand = new AddApptCommand(validAppointment);
        ModelStub modelStub = new ModelStubWithAppointment(validAppointment);

        assertThrows(CommandException.class,
                AddApptCommand.MESSAGE_ADD_DUPLICATE_APPOINTMENT_FAILURE, () -> addApptCommand
                        .execute(modelStub));
    }

    @Test
    public void execute_patientNotFound_throwsCommandException() {
        Appointment validAppointment = new AppointmentBuilder(BOB_APPT).build();
        AddApptCommand addApptCommand = new AddApptCommand(validAppointment);
        ModelStub modelStub = new ModelStubWithPatient(ALICE);

        assertThrows(CommandException.class, Messages.MESSAGE_PATIENT_NRIC_NOT_FOUND, () -> addApptCommand
                .execute(modelStub));
    }

    @Test
    public void equals() {
        Appointment aliceAppointment = new AppointmentBuilder().withNric("T0000001A").build();
        Appointment bobAppointment = new AppointmentBuilder().withNric("T0000002A").build();
        Appointment aliceSecondAppointment = new AppointmentBuilder().withDate("2023-01-01").build();

        AddApptCommand addAliceAppCommand = new AddApptCommand(aliceAppointment);
        AddApptCommand addBobAppCommand = new AddApptCommand(bobAppointment);
        AddApptCommand addAliceSecondAppCommand = new AddApptCommand(aliceSecondAppointment);

        // same object -> returns true
        assertTrue(addAliceAppCommand.equals(addAliceAppCommand));

        // same values -> returns true
        AddApptCommand addAliceAppCommandCopy = new AddApptCommand(aliceAppointment);
        assertTrue(addAliceAppCommand.equals(addAliceAppCommandCopy));

        // different types -> returns false
        assertFalse(addAliceAppCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceAppCommand.equals(null));

        // appointment with different nric -> returns false
        assertFalse(addAliceAppCommand.equals(addBobAppCommand));

        // appointment with different date -> returns false
        assertFalse(addAliceAppCommand.equals(addAliceSecondAppCommand));
    }

    @Test
    public void toStringMethod() {
        AddApptCommand addApptCommand = new AddApptCommand(ALICE_APPT);
        String expected = AddApptCommand.class.getCanonicalName()
                + "{appointment=" + ALICE_APPT + "}";
        assertEquals(expected, addApptCommand.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPatient(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPatientWithNric(Nric nric) {
            return false;
        }

        @Override
        public Patient getPatientWithNric(Nric nric) {
            return null;
        }

        @Override
        public void deletePatientWithNric(Nric nric) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPatient(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePatient(Patient target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPatient(Patient target, Patient editedPatient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Patient> getFilteredPatientList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPatientList(Predicate<Patient> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            return false;
        }

        @Override
        public void cancelAppointment(Appointment appointment, AppointmentView apptView) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAppointment(Appointment appointment) {

        }

        @Override
        public void setAppointment(Appointment target, Appointment editedAppointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Appointment> getFilteredAppointmentList() {
            return null;
        }

        @Override
        public void updateFilteredAppointmentList(Predicate<AppointmentView> predicate) {
        }

        @Override
        public ObservableList<AppointmentView> getFilteredAppointmentDayViewList() {
            return null;
        }

        @Override
        public void updateFilteredAppointmentDayViewList() {
        }

        @Override
        public Appointment getMatchingAppointment(Nric nric, Date date, TimePeriod timePeriod) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public AppointmentView getMatchingAppointmentView(Name name, Appointment appt) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAppointmentsWithNric(Nric targetNric) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<AppointmentView> getFilteredAppointmentViewList() {
            return null;
        }
    }

    /**
     * A Model stub that contains a single appointment.
     */
    private class ModelStubWithAppointment extends ModelStub {
        private final Appointment appointment;

        ModelStubWithAppointment(Appointment appointment) {
            requireNonNull(appointment);
            this.appointment = appointment;
        }

        @Override
        public boolean hasPatientWithNric(Nric nric) {
            return true;
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return this.appointment.equals(appointment);
        }
    }

    /**
     * A Model stub that contains a single patient.
     */
    private class ModelStubWithPatient extends ModelStub {
        final ArrayList<Appointment> appointmentsAdded;
        private final Patient patient;

        ModelStubWithPatient(Patient patient) {
            requireNonNull(patient);
            this.patient = patient;
            this.appointmentsAdded = new ArrayList<>();
        }

        @Override
        public boolean hasPatientWithNric(Nric nric) {
            requireNonNull(nric);
            return patient.getNric().equals(nric);
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return appointmentsAdded.stream().anyMatch(appointment::equals);
        }

        @Override
        public Patient getPatientWithNric(Nric nric) {
            requireNonNull(nric);
            if (patient.getNric().equals(nric)) {
                return patient;
            }
            return null;
        }

        @Override
        public void addAppointment(Appointment appointment) {
            requireNonNull(appointment);
            appointmentsAdded.add(appointment);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}

