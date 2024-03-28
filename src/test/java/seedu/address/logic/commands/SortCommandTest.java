package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BENSON;
import static seedu.address.testutil.TypicalStudents.CARL;
import static seedu.address.testutil.TypicalStudents.DANIEL;
import static seedu.address.testutil.TypicalStudents.ELLE;
import static seedu.address.testutil.TypicalStudents.FIONA;
import static seedu.address.testutil.TypicalStudents.GEORGE;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


/**
 * Contains integration tests (interaction with the Model) for {@code SortAscendingCommand}.
 */
public class SortCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_starAscending_success() {
        String expectedMessage = "Sorted all persons by star in ascending order.";
        SortCommand command = new SortCommand("star", true);
        expectedModel.updateSortedStudentListByField("star", true);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        List expectedList = Arrays.asList(BENSON, CARL, DANIEL, FIONA, ELLE, ALICE, GEORGE);
        assertEquals(expectedList, model.getCorrectStudentList());
    }
    @Test
    public void execute_nameDescending_success() {
        String expectedMessage = "Sorted all persons by name in descending order.";
        SortCommand command = new SortCommand("name", false);
        expectedModel.updateSortedStudentListByField("name", false);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        List expectedList = Arrays.asList(GEORGE, FIONA, ELLE, DANIEL, CARL, BENSON, ALICE);
        assertEquals(expectedList, model.getCorrectStudentList());
    }
    @Test
    public void execute_majorAscendingFieldAllCaps_success() {
        String expectedMessage = "Sorted all persons by major in ascending order.";
        SortCommand command = new SortCommand("MAJOR", true);
        expectedModel.updateSortedStudentListByField("MAJOR", true);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        List expectedList = Arrays.asList(ALICE, CARL, ELLE, BENSON, DANIEL, GEORGE, FIONA);
        assertEquals(expectedList, model.getCorrectStudentList());
    }
    @Test
    public void equals() {
        String firstField = "major";
        boolean firstIsAscending = false;
        String secondField = "name";
        boolean secondIsAscending = true;
        SortCommand firstSortCommand = new SortCommand(firstField, firstIsAscending);
        SortCommand secondSortCommand = new SortCommand(secondField, secondIsAscending);

        // same object -> returns true
        assertTrue(firstSortCommand.equals(firstSortCommand));

        // same values -> returns true
        SortCommand firstSortCommandCopy = new SortCommand(firstField, firstIsAscending);
        assertTrue(firstSortCommand.equals(firstSortCommandCopy));

        // different types -> returns false
        assertFalse(firstSortCommand.equals(1));

        // null -> returns false
        assertFalse(firstSortCommand.equals(null));

        // different sorting orders -> returns false
        assertFalse(firstSortCommand.equals(secondSortCommand));
    }

    @Test
    public void toStringMethod() {
        SortCommand sortCommand = new SortCommand("email", true);
        String expected = SortCommand.class.getCanonicalName() + "{field=email, isAscending=true}";
        assertEquals(expected, sortCommand.toString());
    }

}
