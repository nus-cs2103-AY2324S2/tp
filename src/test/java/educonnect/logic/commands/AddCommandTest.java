package educonnect.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import educonnect.commons.core.GuiSettings;
import educonnect.logic.Messages;
import educonnect.logic.commands.exceptions.CommandException;
import educonnect.model.AddressBook;
import educonnect.model.Model;
import educonnect.model.ReadOnlyAddressBook;
import educonnect.model.ReadOnlyUserPrefs;
import educonnect.model.student.Student;
import educonnect.testutil.Assert;
import educonnect.testutil.StudentBuilder;
import educonnect.testutil.TypicalStudents;
import javafx.collections.ObservableList;

public class AddCommandTest {

    @Test
    public void constructor_nullStudent_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_studentAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingStudentAdded modelStub = new ModelStubAcceptingStudentAdded();
        Student validStudent = new StudentBuilder().build();

        CommandResult commandResult = new AddCommand(validStudent).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validStudent)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validStudent), modelStub.studentsAdded);
    }


    @Test
    public void execute_duplicateStudentId_throwsCommandException() {
        Student validStudent1 = new StudentBuilder().build();
        Student validStudent2 = new StudentBuilder().alternate()
                .withStudentId(validStudent1.getStudentId().value).build();
        AddCommand addCommand = new AddCommand(validStudent1);
        ModelStub modelStub = new ModelStubAcceptingStudentAdded();
        modelStub.addStudent(validStudent2);

        Assert.assertThrows(CommandException.class, AddCommand
                .MESSAGE_DUPLICATE_STUDENT_ID, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_duplicateEmail_throwsCommandException() {
        Student validStudent1 = new StudentBuilder().build();
        Student validStudent2 = new StudentBuilder().alternate()
                .withEmail(validStudent1.getEmail().value).build();
        AddCommand addCommand = new AddCommand(validStudent1);
        ModelStub modelStub = new ModelStubAcceptingStudentAdded();
        modelStub.addStudent(validStudent2);

        Assert.assertThrows(CommandException.class, AddCommand
                .MESSAGE_DUPLICATE_EMAIL, () -> addCommand.execute(modelStub));
    }
    @Test
    public void execute_duplicateTelegramId_throwsCommandException() {
        Student validStudent1 = new StudentBuilder().build();
        Student validStudent2 = new StudentBuilder().alternate()
                .withTelegramHandle(validStudent1.getTelegramHandle().value).build();
        AddCommand addCommand = new AddCommand(validStudent1);
        ModelStub modelStub = new ModelStubAcceptingStudentAdded();
        modelStub.addStudent(validStudent2);

        Assert.assertThrows(CommandException.class, AddCommand
                .MESSAGE_DUPLICATE_TELEGRAM_HANDLE, () -> addCommand.execute(modelStub));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void equals() {
        Student alice = new StudentBuilder().withName("Alice").build();
        Student bob = new StudentBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different student -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddCommand addCommand = new AddCommand(TypicalStudents.ALICE);
        String expected = AddCommand.class.getCanonicalName() + "{toAdd=" + TypicalStudents.ALICE + "}";
        assertEquals(expected, addCommand.toString());
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
        public void addStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSameUniqueIdentifier(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasStudentId(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEmail(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTelegramHandle(Student student) {
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
        public void deleteStudent(Student target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStudent(Student target, Student editedStudent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredStudentList(Predicate<Student> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single student.
     */
    private class ModelStubWithStudent extends ModelStub {
        private final Student student;



        ModelStubWithStudent(Student student) {
            requireNonNull(student);
            this.student = student;
        }
    }

    /**
     * A Model stub that always accept the student being added.
     */
    private class ModelStubAcceptingStudentAdded extends ModelStub {
        final ArrayList<Student> studentsAdded = new ArrayList<>();

        @Override
        public boolean hasStudentId(Student student) {
            requireNonNull(student);
            return studentsAdded.stream().anyMatch(student::isSameStudentId);
        }

        @Override
        public boolean hasEmail(Student student) {
            requireNonNull(student);
            return studentsAdded.stream().anyMatch(student::isSameEmail);
        }

        @Override
        public boolean hasTelegramHandle(Student student) {
            requireNonNull(student);
            return studentsAdded.stream().anyMatch(student::isSameTelegramHandle);
        }

        @Override
        public void addStudent(Student student) {
            requireNonNull(student);
            studentsAdded.add(student);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
