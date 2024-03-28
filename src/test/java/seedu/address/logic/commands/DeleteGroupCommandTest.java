package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroups.LAB10;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;

public class DeleteGroupCommandTest {

    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteGroupCommand(null));
    }

    @Test
    public void execute_groupIsDeletedFromModel_deleteSuccessful() throws Exception {
        ModelStubAcceptingGroupAdded modelStub = new ModelStubAcceptingGroupAdded();
        Group validGroup = new Group("TUT10");
        modelStub.addGroup(validGroup);

        CommandResult commandResult = new DeleteGroupCommand(validGroup).execute(modelStub);

        assertEquals(String.format(DeleteGroupCommand.MESSAGE_SUCCESS, validGroup),
                commandResult.getFeedbackToUser());
        assertEquals(Collections.emptyList(), modelStub.groupsAdded);
    }

    @Test
    public void execute_groupNotInAddressBook_throwsCommandException() {
        ModelStubAcceptingGroupAdded modelStub = new ModelStubAcceptingGroupAdded();
        Group validGroup = new Group("TUT10");
        modelStub.addGroup(validGroup);

        DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand(new Group("LAB01"));
        assertThrows(CommandException.class,
                DeleteGroupCommand.MESSAGE_NOT_FOUND, () -> deleteGroupCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Group tut01 = new Group("TUT01");
        Group lab01 = new Group("LAB01");
        DeleteGroupCommand deleteTut01Command = new DeleteGroupCommand(tut01);
        DeleteGroupCommand deleteLab01Command = new DeleteGroupCommand(lab01);

        // same object -> returns true
        assertTrue(deleteTut01Command.equals(deleteTut01Command));

        // same values -> returns true
        DeleteGroupCommand deleteTut01CommandCopy = new DeleteGroupCommand(tut01);
        assertTrue(deleteTut01Command.equals(deleteTut01CommandCopy));

        // different types -> returns false
        assertFalse(deleteTut01Command.equals(1));

        // null -> returns false
        assertFalse(deleteTut01Command.equals(null));

        // different person -> returns false
        assertFalse(deleteTut01Command.equals(deleteLab01Command));
    }

    @Test
    public void toStringMethod() {
        AddGroupCommand addGroupCommand = new AddGroupCommand(LAB10);
        String expected = AddGroupCommand.class.getCanonicalName() + "{toAdd=" + LAB10 + "}";
        assertEquals(expected, addGroupCommand.toString());
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
        public boolean hasGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteGroup(Group target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGroup(Group target, Group editedGroup) {
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
    private class ModelStubWithGroup extends ModelStub {
        private final Group group;

        ModelStubWithGroup(Group group) {
            requireNonNull(group);
            this.group = group;
        }

        @Override
        public boolean hasGroup(Group group) {
            requireNonNull(group);
            return this.group.isSameGroup(group);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingGroupAdded extends ModelStub {
        final ArrayList<Group> groupsAdded = new ArrayList<>();

        @Override
        public boolean hasGroup(Group group) {
            requireNonNull(group);
            return groupsAdded.stream().anyMatch(group::isSameGroup);
        }

        @Override
        public void addGroup(Group group) {
            requireNonNull(group);
            groupsAdded.add(group);
        }

        @Override
        public void deleteGroup(Group group) {
            requireNonNull(group);
            groupsAdded.remove(group);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
