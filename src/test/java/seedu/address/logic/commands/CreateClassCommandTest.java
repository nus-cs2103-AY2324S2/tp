package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.TYPICAL_CLASS_1;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ClassBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyClassBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Classes;
import seedu.address.model.person.Person;
import seedu.address.testutil.ClassBuilder;
public class CreateClassCommandTest {

    @Test
    public void constructor_nullClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateClassCommand(null));
    }

    @Test
    public void execute_classAcceptedByModel_createSuccessful() throws Exception {
        ModelStubAcceptingClassCreated modelStub = new ModelStubAcceptingClassCreated();
        Classes validClass = new ClassBuilder().build();

        CommandResult commandResult = new CreateClassCommand(validClass).execute(modelStub);

        assertEquals(String.format(CreateClassCommand.MESSAGE_SUCCESS, Messages.classFormat(validClass)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validClass), modelStub.classesCreated);
    }

    @Test
    public void execute_duplicateClass_throwsCommandException() {
        Classes validClass = new ClassBuilder().build();
        CreateClassCommand createClassCommand = new CreateClassCommand(validClass);
        ModelStub modelStub = new ModelStubWithClass(validClass);

        assertThrows(CommandException.class, CreateClassCommand.MESSAGE_DUPLICATE_CLASS, () ->
                createClassCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Classes cs1 = new ClassBuilder().withCC("cc123").build();
        Classes cs2 = new ClassBuilder().withCC("cc789").build();
        CreateClassCommand createCs1Command = new CreateClassCommand(cs1);
        CreateClassCommand createCs2Command = new CreateClassCommand(cs2);

        assertTrue(createCs1Command.equals(createCs1Command));

        CreateClassCommand cs1Copy = new CreateClassCommand(cs1);
        assertTrue(createCs1Command.equals(cs1Copy));

        assertFalse(createCs1Command.equals(1));

        assertFalse(createCs1Command.equals(null));

        assertFalse(createCs1Command.equals(createCs2Command));
    }

    @Test
    public void toStringMethod() {
        CreateClassCommand createClassCommand = new CreateClassCommand(TYPICAL_CLASS_1);
        String expected = CreateClassCommand.class.getCanonicalName() + "{toCreate=" + TYPICAL_CLASS_1 + "}";
        assertEquals(expected, createClassCommand.toString());
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
     * A Model stub that contains a single person.
     */
    private class ModelStubWithClass extends CreateClassCommandTest.ModelStub {
        private final Classes classes;

        ModelStubWithClass(Classes classes) {
            requireNonNull(classes);
            this.classes = classes;
        }

        @Override
        public boolean hasClass(Classes classes) {
            requireNonNull(classes);
            return this.classes.isSameClass(classes);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingClassCreated extends CreateClassCommandTest.ModelStub {
        final ArrayList<Classes> classesCreated = new ArrayList<>();

        @Override
        public boolean hasClass(Classes classes) {
            requireNonNull(classes);
            return classesCreated.stream().anyMatch(classes::isSameClass);
        }

        @Override
        public void createClass(Classes classes) {
            requireNonNull(classes);
            classesCreated.add(classes);
        }

        @Override
        public ReadOnlyClassBook getClassBook() {
            return new ClassBook();
        }
    }
}
