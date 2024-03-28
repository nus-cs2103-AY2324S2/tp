package educonnect.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import educonnect.logic.Messages;
import educonnect.model.Model;
import educonnect.model.ModelManager;
import educonnect.model.UserPrefs;
import educonnect.model.student.Student;
import educonnect.model.student.predicates.NameContainsKeywordsPredicate;
import educonnect.testutil.TypicalStudents;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(TypicalStudents.getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalStudents.getTypicalAddressBook(), new UserPrefs());

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate("first");
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate("second");

        FindCommand findFirstCommand = new FindCommand(List.of(firstPredicate));
        FindCommand findSecondCommand = new FindCommand(List.of(secondPredicate));

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(List.of(firstPredicate));
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noStudentFound() {
        String expectedMessage = String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate("Zero-Keyword-String-No-Match");
        FindCommand command = new FindCommand(List.of(predicate));
        expectedModel.updateFilteredStudentList(List.of(predicate));
        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    @Test
    public void execute_keywords_multipleStudentsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Me");
        FindCommand command = new FindCommand(List.of(predicate));
        expectedModel.updateFilteredStudentList(List.of(predicate));
        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalStudents.BENSON,
                TypicalStudents.DANIEL, TypicalStudents.ELLE), model.getFilteredStudentList());
    }

    @Test
    public void toStringMethod() {
        List<Predicate<Student>> predicates = List.of(new NameContainsKeywordsPredicate("keyword"));
        FindCommand findCommand = new FindCommand(predicates);
        String expected = FindCommand.class.getCanonicalName() + "{predicates=" + predicates + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(userInput);
    }
}
