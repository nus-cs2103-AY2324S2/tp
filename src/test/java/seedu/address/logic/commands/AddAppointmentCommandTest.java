package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.ReadOnlyAppointmentList;
import seedu.address.model.person.Person;
import seedu.address.testutil.AppointmentBuilder;

public class AddAppointmentCommandTest {

    @Test
    public void constructor_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAppointmentCommand(null));
    }

    @Test
    public void execute_appointmentAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingAppointmentsAdded modelStub = new ModelStubAcceptingAppointmentsAdded();
        Appointment validAppointment = new AppointmentBuilder().build();

        CommandResult commandResult = new AddAppointmentCommand(validAppointment).execute(modelStub);

        assertEquals(String.format(AddAppointmentCommand.MESSAGE_SUCCESS, Messages.formatAppointment(validAppointment)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validAppointment), modelStub.appointmentsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Appointment validAppointment = new AppointmentBuilder().build();
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(validAppointment);
        AddAppointmentCommandTest.ModelStub modelStub =
                new AddAppointmentCommandTest.ModelStubWithAppointment(validAppointment);

        assertThrows(CommandException.class, AddAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT, () ->
                addAppointmentCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Appointment appointmentOne = new AppointmentBuilder().withStudentId(1).build();
        Appointment appointmentTwo = new AppointmentBuilder().withStudentId(2).build();
        AddAppointmentCommand addAppointmentOneCommand = new AddAppointmentCommand(appointmentOne);
        AddAppointmentCommand addAppointmentTwoCommand = new AddAppointmentCommand(appointmentTwo);

        // same object -> returns true
        assertTrue(addAppointmentOneCommand.equals(addAppointmentOneCommand));

        // same values -> returns true
        AddAppointmentCommand addAppointmentOneCommandCopy = new AddAppointmentCommand(appointmentOne);
        assertTrue(addAppointmentOneCommand.equals(addAppointmentOneCommandCopy));

        // different types -> returns false
        assertFalse(addAppointmentOneCommand.equals(1));

        // null -> returns false
        assertFalse(addAppointmentOneCommand.equals(null));

        // different person -> returns false
        assertFalse(addAppointmentOneCommand.equals(addAppointmentTwoCommand));
    }

    @Test
    public void toStringMethod() {
        Appointment appointment = new AppointmentBuilder().withStudentId(1)
                .withAppointmentDateTime(LocalDateTime.parse("2024-03-18 12:15",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .withAppointmentId(1)
                .withHasAttended(true)
                .withAppointmentDescription("This is a dummy appointment").build();

        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(appointment);
        String expected = AddAppointmentCommand.class.getCanonicalName() + "{toAdd=" + appointment + "}";
        assertEquals(expected, addAppointmentCommand.toString());
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
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void deleteAppointment(Appointment target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Appointment> getFilteredAppointmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAppointmentList getAppointmentList() {
            throw new AssertionError("This method should not be called.");
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
        public boolean hasAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return this.appointment.isSameAppointment(appointment);
        }
    }

    /**
     * A Model stub that always accept the appointment being added.
     */
    private class ModelStubAcceptingAppointmentsAdded extends ModelStub {
        final ArrayList<Appointment> appointmentsAdded = new ArrayList<>();

        @Override
        public boolean hasAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return appointmentsAdded.stream().anyMatch(appointment::isSameAppointment);
        }

        @Override
        public void addAppointment(Appointment appointment) {
            requireNonNull(appointment);
            appointmentsAdded.add(appointment);
        }

    }

}
