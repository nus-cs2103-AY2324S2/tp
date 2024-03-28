package seedu.edulink.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.edulink.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.edulink.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.edulink.testutil.TypicalPersons.ALICE;
import static seedu.edulink.testutil.TypicalPersons.BENSON;
import static seedu.edulink.testutil.TypicalPersons.DANIEL;
import static seedu.edulink.testutil.TypicalPersons.OLIVER_1;
import static seedu.edulink.testutil.TypicalPersons.OLIVER_2;
import static seedu.edulink.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.edulink.model.Model;
import seedu.edulink.model.ModelManager;
import seedu.edulink.model.UserPrefs;
import seedu.edulink.model.student.IdAndNameContainsQueryIdAndNamePredicate;
import seedu.edulink.model.student.IdContainsQueryIdPredicate;
import seedu.edulink.model.student.NameContainsQueryNamePredicate;
import seedu.edulink.model.student.Student;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {

        // TEST NAME ---------------------------------------
        Predicate<Student> firstPredicate =
            new NameContainsQueryNamePredicate("first");
        Predicate<Student> secondPredicate =
            new NameContainsQueryNamePredicate("second");

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different name -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));

        // TEST ID  ---------------------------------------
        firstPredicate =
            new IdContainsQueryIdPredicate("A1234567X");
        secondPredicate =
            new IdContainsQueryIdPredicate("B9876543Z");

        findFirstCommand = new FindCommand(firstPredicate);
        findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different id -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));

        // TEST NAME & ID -------------------------------------
        firstPredicate =
            new IdAndNameContainsQueryIdAndNamePredicate("A1234567X", "John");
        secondPredicate =
            new IdAndNameContainsQueryIdAndNamePredicate("B9876543Z", "John");

        findFirstCommand = new FindCommand(firstPredicate);
        findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different id and name-> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_nonExistentName_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);

        NameContainsQueryNamePredicate predicate = new NameContainsQueryNamePredicate("Emily Hong");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_existentFirstName_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        String testString = OLIVER_1.getName().fullName.split("\\s+")[0];

        NameContainsQueryNamePredicate predicate = new NameContainsQueryNamePredicate(testString);
        FindCommand command = new FindCommand(predicate);

        expectedModel.updateFilteredPersonList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(OLIVER_1, OLIVER_2), model.getFilteredPersonList());
    }

    @Test
    public void execute_existentLastName_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        String testString = BENSON.getName().fullName.split("\\s+")[1];

        NameContainsQueryNamePredicate predicate = new NameContainsQueryNamePredicate(testString);
        FindCommand command = new FindCommand(predicate);

        expectedModel.updateFilteredPersonList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_existentFullName_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);

        NameContainsQueryNamePredicate predicate = new NameContainsQueryNamePredicate(OLIVER_1.getName().fullName);
        FindCommand command = new FindCommand(predicate);

        expectedModel.updateFilteredPersonList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(OLIVER_1), model.getFilteredPersonList());
    }

    @Test
    public void execute_nonExistentID_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);

        IdContainsQueryIdPredicate predicate = new IdContainsQueryIdPredicate("Y1234567Z");
        FindCommand command = new FindCommand(predicate);

        expectedModel.updateFilteredPersonList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_existentID_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);

        IdContainsQueryIdPredicate predicate = new IdContainsQueryIdPredicate(ALICE.getId().id);
        FindCommand command = new FindCommand(predicate);

        expectedModel.updateFilteredPersonList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPersonList());
    }

    @Test
    public void execute_existentIDnonExistentName_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);

        IdAndNameContainsQueryIdAndNamePredicate predicate =
            new IdAndNameContainsQueryIdAndNamePredicate(ALICE.getId().id, "Marry Jane");
        FindCommand command = new FindCommand(predicate);

        expectedModel.updateFilteredPersonList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_nonExistentIdExistentName_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);

        IdAndNameContainsQueryIdAndNamePredicate predicate =
            new IdAndNameContainsQueryIdAndNamePredicate("Y1234567Z", OLIVER_1.getName().fullName);
        FindCommand command = new FindCommand(predicate);

        expectedModel.updateFilteredPersonList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_existentIdExistentName_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);

        IdAndNameContainsQueryIdAndNamePredicate predicate =
            new IdAndNameContainsQueryIdAndNamePredicate(ALICE.getId().id, ALICE.getName().fullName);
        FindCommand command = new FindCommand(predicate);

        expectedModel.updateFilteredPersonList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        Predicate<Student> predicate = new NameContainsQueryNamePredicate("first");
        FindCommand findCommand = new FindCommand(predicate);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());

        predicate = new IdContainsQueryIdPredicate("A1234567X");
        findCommand = new FindCommand(predicate);
        expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());

        predicate = new IdAndNameContainsQueryIdAndNamePredicate("A1234567X", "John Doe");
        findCommand = new FindCommand(predicate);
        expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }
}
