package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalClassBook;

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
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyClassBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.AttendanceStatus;
import seedu.address.model.person.Classes;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Attendance;

public class AddAttendanceRecordCommandTest {



    @Test
    public void constructor_nullAttendance_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAttendanceRecordCommand((null)));
    }

    @Test
    public void execute_addAttendance_success() throws Exception {
        // Setup your model with a few persons
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalClassBook());
        Attendance validAttendance = new Attendance(new AttendanceStatus("19-03-2024", "1"));

        CommandResult commandResult = new AddAttendanceRecordCommand(validAttendance).execute(model);

        assertEquals(String.format(AddAttendanceRecordCommand.MESSAGE_SUCCESS, validAttendance),
                commandResult.getFeedbackToUser());
        // Further assertions to check if the attendance was correctly added to all persons
    }

    @Test
    public void execute_emptyPersonList_throwsCommandException() {
        Model model = new ModelManager(); // No persons in the model
        Attendance validAttendance = new Attendance(new AttendanceStatus("19-03-2024", "1"));

        AddAttendanceRecordCommand addAttendanceRecordCommand = new AddAttendanceRecordCommand(validAttendance);

        assertThrows(CommandException.class,
                Messages.MESSAGE_NO_PERSON_IN_THE_CLASS, () -> addAttendanceRecordCommand.execute(model));
    }

    @Test
    public void equals() {
        Attendance attendance1 = new Attendance(new AttendanceStatus("19-03-2024", "1"));
        Attendance attendance2 = new Attendance(new AttendanceStatus("20-03-2024", "1"));
        AddAttendanceRecordCommand addAttendance1Command = new AddAttendanceRecordCommand(attendance1);
        AddAttendanceRecordCommand addAttendance2Command = new AddAttendanceRecordCommand(attendance2);

        // same object -> returns true
        assertTrue(addAttendance1Command.equals(addAttendance1Command));

        // same values -> returns true
        AddAttendanceRecordCommand addAttendance1CommandCopy = new AddAttendanceRecordCommand(attendance1);
        assertTrue(addAttendance1Command.equals(addAttendance1CommandCopy));

        // different types -> returns false
        assertFalse(addAttendance1Command.equals(1));

        // null -> returns false
        assertFalse(addAttendance1Command.equals(null));

        // different attendance -> returns false
        assertFalse(addAttendance1Command.equals(addAttendance2Command));
    }





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
        public Path getClassBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClassBookFilePath(Path classBookFilePath) {
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
        public void setClassBook(ReadOnlyClassBook classBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyClassBook getClassBook() {
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
        public ObservableList<Classes> getFilteredClassList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getFormattedClassList() {
            return null;
        }

        @Override
        public void createClass(Classes classes) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeClass(Classes classes) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasClass(Classes classes) {
            return false;
        }

        @Override
        public void selectClass(Classes classes) {

        }

        @Override
        public String getSelectedClassName() {
            return null;
        }
    }


    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends AddAttendanceRecordCommandTest.ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
