package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.ALICE_APPT;
import static seedu.address.testutil.TypicalAppointments.BOB_APPT;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.testutil.AppointmentBuilder;

public class AddAppCommandTest {

    @Test
    public void constructor_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAppCommand(null));
    }

    @Test
    public void execute_appointmentAcceptedByModel_addSuccessful() throws Exception {
        Appointment validAppointment = new AppointmentBuilder(ALICE_APPT).build();
        ModelStub modelStub = new ModelStubWithPerson(ALICE);
        CommandResult commandResult = new AddAppCommand(validAppointment).execute(modelStub);

        assertEquals(String.format(AddAppCommand.MESSAGE_SUCCESS, Messages.format(validAppointment)),
                commandResult.getFeedbackToUser());
        assertTrue(modelStub.hasAppointment(validAppointment));
    }

    @Test
    public void execute_duplicateAppointment_throwsCommandException() {
        Appointment validAppointment = new AppointmentBuilder().build();
        AddAppCommand addAppCommand = new AddAppCommand(validAppointment);
        ModelStub modelStub = new ModelStubWithAppointment(validAppointment);

        assertThrows(CommandException.class, AddAppCommand.MESSAGE_DUPLICATE_APPOINTMENT, () -> addAppCommand
                .execute(modelStub));
    }

    @Test
    public void execute_patientNotFound_throwsCommandException() {
        Appointment validAppointment = new AppointmentBuilder(BOB_APPT).build();
        AddAppCommand addAppCommand = new AddAppCommand(validAppointment);
        ModelStub modelStub = new ModelStubWithPerson(ALICE);

        assertThrows(CommandException.class, AddAppCommand.MESSAGE_PATIENT_NOT_FOUND, () -> addAppCommand
                .execute(modelStub));
    }

    @Test
    public void equals() {
        Appointment aliceAppointment = new AppointmentBuilder().withNric("T0000001A").build();
        Appointment bobAppointment = new AppointmentBuilder().withNric("T0000002A").build();
        Appointment aliceSecondAppointment = new AppointmentBuilder().withDate("2023-01-01").build();

        AddAppCommand addAliceAppCommand = new AddAppCommand(aliceAppointment);
        AddAppCommand addBobAppCommand = new AddAppCommand(bobAppointment);
        AddAppCommand addAliceSecondAppCommand = new AddAppCommand(aliceSecondAppointment);

        // same object -> returns true
        assertTrue(addAliceAppCommand.equals(addAliceAppCommand));

        // same values -> returns true
        AddAppCommand addAliceAppCommandCopy = new AddAppCommand(aliceAppointment);
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
        AddAppCommand addAppCommand = new AddAppCommand(ALICE_APPT);
        String expected = AddAppCommand.class.getCanonicalName()
                + "{appointment=" + ALICE_APPT + "}";
        assertEquals(expected, addAppCommand.toString());
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
        public void addPerson(Person person) {
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
        public boolean hasPersonWithNric(Nric nric) {
            return false;
        }

        @Override
        public Person getPersonWithNric(Nric nric) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            return false;
        }

        @Override
        public void deleteAppointment(Appointment appointment) {
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
        public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {

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
        public boolean hasPersonWithNric(Nric nric) {
            return true;
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return this.appointment.equals(appointment);
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        final ArrayList<Appointment> appointmentsAdded;
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
            this.appointmentsAdded = new ArrayList<>();
        }

        @Override
        public boolean hasPersonWithNric(Nric nric) {
            requireNonNull(nric);
            return person.getNric().equals(nric);
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return appointmentsAdded.stream().anyMatch(appointment::equals);
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

