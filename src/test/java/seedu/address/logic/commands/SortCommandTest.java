package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PersonCompanyNameComparator;
import seedu.address.model.person.PersonInterviewTimeComparator;
import seedu.address.model.person.PersonNameComparator;
import seedu.address.model.person.PersonPriorityComparator;
import seedu.address.model.person.PersonSalaryComparator;

public class SortCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validPriorityCliUnsortedList_success() {
        Integer prefixToSort = 0; //sort by priority
        SortCommand sortCommand = new SortCommand(prefixToSort);

        String expectedMessage = SortCommand.MESSAGE_LIST_SORTED_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateSortedPersonList(new PersonPriorityComparator());

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }
    @Test
    public void execute_validCompanyNameCliUnsortedList_success() {
        Integer prefixToSort = 1; //sort by Company Name
        SortCommand sortCommand = new SortCommand(prefixToSort);

        String expectedMessage = SortCommand.MESSAGE_LIST_SORTED_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateSortedPersonList(new PersonCompanyNameComparator());

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validNameCliUnsortedList_success() {
        Integer prefixToSort = 2; //sort by Name
        SortCommand sortCommand = new SortCommand(prefixToSort);

        String expectedMessage = SortCommand.MESSAGE_LIST_SORTED_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateSortedPersonList(new PersonNameComparator());

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validInterviewTimeCliUnsortedList_success() {
        Integer prefixToSort = 3; //sort by InterviewTime
        SortCommand sortCommand = new SortCommand(prefixToSort);

        String expectedMessage = SortCommand.MESSAGE_LIST_SORTED_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateSortedPersonList(new PersonInterviewTimeComparator());

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validSalaryCliUnsortedList_success() {
        Integer prefixToSort = 4; //sort by InterviewTime
        SortCommand sortCommand = new SortCommand(prefixToSort);

        String expectedMessage = SortCommand.MESSAGE_LIST_SORTED_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateSortedPersonList(new PersonSalaryComparator());

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidCliUnsortedList_throwsCommandException() {
        Integer prefixToSort = 5; //outside [0-4]
        SortCommand sortCommand = new SortCommand(prefixToSort);

        assertCommandFailure(sortCommand, model, Messages.MESSAGE_INVALID_SORT_COMMAND_INDEX);
    }

    @Test
    public void equals() {
        SortCommand firstSort = new SortCommand(1);
        SortCommand secondSort = new SortCommand(2);

        // same object -> returns true
        assertTrue(firstSort.equals(firstSort));
        // same values -> returns true
        SortCommand firstSortCopy = new SortCommand(1);
        assertTrue(firstSort.equals(firstSortCopy));
        // different types -> returns false
        assertFalse(firstSort.equals(1));
        // null -> returns false
        assertFalse(firstSort.equals(null));
        // different person -> returns false
        assertFalse(firstSort.equals(secondSort));
    }

}
