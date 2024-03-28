package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.InternshipMessages.MESSAGE_INTERNSHIPS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.InternshipCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalInternships.ALICE_MICROSOFT;
import static seedu.address.testutil.TypicalInternships.CARL_OPENAI;
import static seedu.address.testutil.TypicalInternships.HOON_APPLE;
import static seedu.address.testutil.TypicalInternships.IDA_NETFLIX;
import static seedu.address.testutil.TypicalInternships.getTypicalInternshipData;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.InternshipModel;
import seedu.address.model.InternshipModelManager;
import seedu.address.model.InternshipUserPrefs;
import seedu.address.model.internship.InternshipContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the InternshipModel) for {@code InternshipFindCommand}.
 */
public class InternshipFindCommandTest {
    private InternshipModel model;
    private InternshipModel expectedModel;
    public InternshipFindCommandTest() {
        model = new InternshipModelManager(getTypicalInternshipData(), new InternshipUserPrefs());
        model.addInternship(HOON_APPLE);
        model.addInternship(IDA_NETFLIX);
        expectedModel = new InternshipModelManager(getTypicalInternshipData(), new InternshipUserPrefs());
        expectedModel.addInternship(HOON_APPLE);
        expectedModel.addInternship(IDA_NETFLIX);
    }

    @Test
    public void equals() {
        InternshipContainsKeywordsPredicate firstPredicate =
                new InternshipContainsKeywordsPredicate("first", null, null,
                        null, null, null, false);
        InternshipContainsKeywordsPredicate secondPredicate =
                new InternshipContainsKeywordsPredicate("second", null, null,
                        null, null, null, false);

        InternshipFindCommand findFirstCommand = new InternshipFindCommand(firstPredicate);
        InternshipFindCommand findSecondCommand = new InternshipFindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        InternshipFindCommand findFirstCommandCopy = new InternshipFindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different internship -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_INTERNSHIPS_LISTED_OVERVIEW, 0);
        InternshipContainsKeywordsPredicate predicate =
                new InternshipContainsKeywordsPredicate("Tiktok", null, null,
                null, null, null, false);
        InternshipFindCommand command = new InternshipFindCommand(predicate);
        expectedModel.updateFilteredInternshipList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredInternshipList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_INTERNSHIPS_LISTED_OVERVIEW, 2);
        InternshipContainsKeywordsPredicate predicate =
                new InternshipContainsKeywordsPredicate("Microsoft OpenAI", null, null,
                        null, null, null, false);
        InternshipFindCommand command = new InternshipFindCommand(predicate);
        expectedModel.updateFilteredInternshipList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE_MICROSOFT, CARL_OPENAI), model.getFilteredInternshipList());
    }

    @Test
    public void execute_oneFieldIsMatchAll_multiplePersonsFound() {
        // Predicate only role contains "Engineer"
        String expectedMessage = String.format(MESSAGE_INTERNSHIPS_LISTED_OVERVIEW, 4);
        InternshipContainsKeywordsPredicate predicateRole =
                new InternshipContainsKeywordsPredicate(null, null, null,
                        null, null, "Engineer", true);
        InternshipFindCommand command = new InternshipFindCommand(predicateRole);
        expectedModel.updateFilteredInternshipList(predicateRole);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertFalse(model.getFilteredInternshipList().contains(HOON_APPLE));
    }

    @Test
    public void execute_twoFieldsIsMatchAll_lessPersonsFound() {
        // Predicate with role contains "Engineer" and status contains "ongoing"
        String expectedMessage = String.format(MESSAGE_INTERNSHIPS_LISTED_OVERVIEW, 2);
        InternshipContainsKeywordsPredicate predicateRoleStatus =
                new InternshipContainsKeywordsPredicate(null, null, null,
                        "ongoing", null, "Engineer", true);
        InternshipFindCommand command = new InternshipFindCommand(predicateRoleStatus);
        expectedModel.updateFilteredInternshipList(predicateRoleStatus);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertTrue(model.getFilteredInternshipList().contains(ALICE_MICROSOFT));
        assertTrue(model.getFilteredInternshipList().contains(IDA_NETFLIX));
    }

    @Test
    public void execute_twoFieldsIsMatchAny_morePersonsFound() {
        // Predicate with role contains "Engineer" or status contains "pending"
        String expectedMessage = String.format(MESSAGE_INTERNSHIPS_LISTED_OVERVIEW, 5);
        InternshipContainsKeywordsPredicate predicateRoleStatus =
                new InternshipContainsKeywordsPredicate(null, null, null,
                        "pending", null, "Engineer", false);
        InternshipFindCommand command = new InternshipFindCommand(predicateRoleStatus);
        expectedModel.updateFilteredInternshipList(predicateRoleStatus);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
    @Test
    public void toStringMethod() {
        InternshipContainsKeywordsPredicate predicate =
                new InternshipContainsKeywordsPredicate("Tiktok", null, null,
                        null, null, null, false);
        InternshipFindCommand findCommand = new InternshipFindCommand(predicate);
        String expected = InternshipFindCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }
}
