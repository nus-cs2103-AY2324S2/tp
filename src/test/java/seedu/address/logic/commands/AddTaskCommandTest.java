package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.project.Task;
import seedu.address.testutil.PersonBuilder;

import java.nio.file.Path;
import java.util.*;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;

class AddTaskCommandTest {

    Task tempTask = new Task("Presentation");

    @Test
    public void constructor_nullProject_throwsNullPointerException() {
        Person validPerson = new PersonBuilder().build();
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null, validPerson));
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(tempTask, null));
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null, null));
    }


    @Test
    public void equals() {
        Person presentation = new PersonBuilder().withName("Presentation").build();
        Person duke = new PersonBuilder().withName("Duke chatbot").build();

        Task rehearse = new Task("rehearse");
        Task rehearseCopy = new Task("rehearse");
        Task script = new Task("write scipt");
        AddTaskCommand addTaskCommand = new AddTaskCommand(rehearse, presentation);
        AddTaskCommand addTaskCommandCopy = new AddTaskCommand(rehearseCopy, presentation);
        AddTaskCommand addTaskCommandScript = new AddTaskCommand(script, presentation);
        AddTaskCommand addTaskCommandScript2 = new AddTaskCommand(rehearse, duke);

        // different project, same task -> returns false
        assertFalse(addTaskCommand.equals(addTaskCommandScript2));

        // same object -> returns true
        assertTrue(addTaskCommand.equals(addTaskCommand));

        // same values -> returns true
        assertTrue(addTaskCommand.equals(addTaskCommandCopy));

        // different types -> returns false
        assertFalse(addTaskCommand.equals(1));

        // null -> returns false
        assertFalse(addTaskCommand.equals(null));

        // different project -> returns false
        assertFalse(addTaskCommand.equals(addTaskCommandScript));

    }

    @Test
    void testToString() {
        AddTaskCommand addTaskCommand = new AddTaskCommand(tempTask, ALICE);
        String expected = AddTaskCommand.class.getCanonicalName() + "{toAdd=" + tempTask + "}";
        assertEquals(expected, addTaskCommand.toString());
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
        public Person findPerson(Name name) {
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
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithTask extends AddTaskCommandTest.ModelStub {
        private final Map<String, List<Task>> tasksByProject = new HashMap<>();

    }

}
