package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PersonComparator;
import seedu.address.model.person.SortCriteria;
import seedu.address.model.person.SortOrder;

public class SortCommandTest {

    private final ModelManager model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final String invalidSortPriorityAsc = SortCommand.getMessageSuccess(SortCriteria.PRIORITY, SortOrder.ASC);

    @Test
    public void constructor_nullArguments_throwsNullPointerException() {
        // null sort criteria
        assertThrows(NullPointerException.class, () -> new SortCommand(null, SortOrder.ASC));
        // null sort order
        assertThrows(NullPointerException.class, () -> new SortCommand(SortCriteria.PRIORITY, null));
        // both null
        assertThrows(NullPointerException.class, () -> new SortCommand(null, null));
    }

    @Test
    public void execute_sortNoPersonsModel_sortSuccesstul() {
        ModelManager expectedEmptyModel = new ModelManager(new AddressBook(new AddressBook()), new UserPrefs());
        expectedEmptyModel.sortFilteredPersonList(PersonComparator.getComparator(SortCriteria.PRIORITY, SortOrder.ASC));
        CommandTestUtil.assertCommandSuccess(new SortCommand(SortCriteria.PRIORITY, SortOrder.ASC),
                new ModelManager(new AddressBook(new AddressBook()), new UserPrefs()),
                invalidSortPriorityAsc, expectedEmptyModel);
    }

    @Test
    public void execute_sortPersonsModel_sortSuccessful() {
        ModelManager expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.sortFilteredPersonList(PersonComparator.getComparator(SortCriteria.PRIORITY, SortOrder.ASC));
        CommandTestUtil.assertCommandSuccess(new SortCommand(SortCriteria.PRIORITY, SortOrder.ASC), model,
                invalidSortPriorityAsc, expectedModel);
    }

    @Test
    public void execute_invalidSortCriteria_sortSuccessful() {
        ModelManager expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        SortCommand sortCommand = new SortCommand(SortCriteria.INVALID, SortOrder.ASC);
        expectedModel.sortFilteredPersonList(PersonComparator.getComparator(SortCriteria.INVALID, SortOrder.ASC));
        String expectedMessage = SortCommand.getMessageSuccess(SortCriteria.INVALID, SortOrder.ASC);
        CommandTestUtil.assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidSortOrder_sortSuccessful() {
        ModelManager expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        SortCommand sortCommand = new SortCommand(SortCriteria.PRIORITY, SortOrder.INVALID);
        expectedModel.sortFilteredPersonList(PersonComparator.getComparator(SortCriteria.PRIORITY, SortOrder.INVALID));
        String expectedMessage = SortCommand.getMessageSuccess(SortCriteria.PRIORITY, SortOrder.INVALID);
        CommandTestUtil.assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        SortCommand sortPriorityAscCommand = new SortCommand(SortCriteria.PRIORITY, SortOrder.ASC);
        SortCommand sortPriorityDescCommand = new SortCommand(SortCriteria.PRIORITY, SortOrder.DESC);
        SortCommand sortNameAscCommand = new SortCommand(SortCriteria.NAME, SortOrder.ASC);

        // same object -> returns true
        assertTrue(sortPriorityAscCommand.equals(sortPriorityAscCommand));

        // same values -> returns true
        SortCommand sortPriorityAscCommandCopy = new SortCommand(SortCriteria.PRIORITY, SortOrder.ASC);
        assertTrue(sortPriorityAscCommand.equals(sortPriorityAscCommandCopy));

        // different types -> returns false
        assertFalse(sortPriorityAscCommand.equals(1));

        // null -> returns false
        assertFalse(sortPriorityAscCommand.equals(null));

        // different sort criteria -> returns false
        assertFalse(sortPriorityAscCommand.equals(sortNameAscCommand));

        // different sort order -> returns false
        assertFalse(sortPriorityAscCommand.equals(sortPriorityDescCommand));
    }

    @Test
    public void toStringMethod() {
        SortCommand sortPriorityAscCommand = new SortCommand(SortCriteria.PRIORITY, SortOrder.ASC);
        String expected = SortCommand.class.getCanonicalName()
                + "{sortCriteria=" + SortCriteria.PRIORITY
                + ", sortOrder=" + SortOrder.ASC + "}";
        assertEquals(expected, sortPriorityAscCommand.toString());
    }
}
