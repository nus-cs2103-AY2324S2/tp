package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskList;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.testutil.PersonBuilder;

class AssignCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskList(), new UserPrefs());

    @Test
    public void execute_assignTaskUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        Set<Task> editedTasks = new HashSet<>(firstPerson.getTasks());
        Task taskToAssign = model.getTaskList().getSerializeTaskList().get(INDEX_FIRST.getZeroBased());
        editedTasks.add(taskToAssign);
        Person editedPerson = new PersonBuilder(firstPerson).withTasks(editedTasks).build();

        AssignCommand assignCommand = new AssignCommand(INDEX_FIRST, INDEX_FIRST);

        String expectedMessage = String.format(AssignCommand.MESSAGE_SUCCESS, Messages.formatTask(taskToAssign),
                Messages.format(editedPerson));

        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new TaskList(model.getTaskList()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(assignCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        Set<Task> editedTasks = new HashSet<>(firstPerson.getTasks());
        Task taskToAssign = model.getTaskList().getSerializeTaskList().get(INDEX_FIRST.getZeroBased());
        editedTasks.add(taskToAssign);
        Person editedPerson = new PersonBuilder(firstPerson).withTasks(editedTasks).build();

        AssignCommand assignCommand = new AssignCommand(INDEX_FIRST, INDEX_FIRST);

        String expectedMessage = String.format(AssignCommand.MESSAGE_SUCCESS, Messages.formatTask(taskToAssign),
                Messages.format(editedPerson));

        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new TaskList(model.getTaskList()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(assignCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidTaskIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getTaskList().getSerializeTaskList().size() + 1);
        AssignCommand assignCommand = new AssignCommand(outOfBoundIndex, INDEX_FIRST);

        assertCommandFailure(assignCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AssignCommand assignCommand = new AssignCommand(INDEX_FIRST, outOfBoundIndex);

        assertCommandFailure(assignCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        AssignCommand assignCommand = new AssignCommand(INDEX_FIRST, outOfBoundIndex);

        assertCommandFailure(assignCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        AssignCommand assignOneToOneCommand = new AssignCommand(INDEX_FIRST, INDEX_FIRST);
        AssignCommand assignOneToTwoCommand = new AssignCommand(INDEX_FIRST, INDEX_SECOND);

        // same object -> returns true
        assertEquals(assignOneToOneCommand, assignOneToOneCommand);

        // same values -> returns true
        AssignCommand assignCommandCopy = new AssignCommand(INDEX_FIRST, INDEX_FIRST);
        assertEquals(assignOneToOneCommand, assignCommandCopy);

        // null -> returns false
        assertNotEquals(assignOneToOneCommand, null);

        // different indices -> returns false
        assertNotEquals(assignOneToOneCommand, assignOneToTwoCommand);
    }
}
