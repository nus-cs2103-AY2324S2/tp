package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.Messages.MESSAGE_PATIENT_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalPatients.BENSON;
import static seedu.address.testutil.TypicalPatients.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.NameContainsKeywordsPredicate;
import seedu.address.model.patient.TagContainsKeywordsPredicate;

public class FindTagsCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_zeroKeyword_noPatientFound() {
        String expectedMessage = String.format(MESSAGE_PATIENT_LISTED_OVERVIEW, 0);
        String userInput = " ";
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(
                Arrays.asList(userInput.split("\\s+")));

        FindTagsCommand command = new FindTagsCommand(predicate);
        expectedModel.updateFilteredPatientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPatientList());
    }

    @Test
    public void execute_oneKeyword_onePatientFound() {
        String expectedMessage = String.format(MESSAGE_PATIENT_LISTED_OVERVIEW, 1);
        String userInput = "depression";
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(
                Arrays.asList(userInput.split("\\s+")));

        FindTagsCommand command = new FindTagsCommand(predicate);
        expectedModel.updateFilteredPatientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(ALICE), model.getFilteredPatientList());
    }

    @Test
    public void execute_twoKeywords_twoPatientFound() {
        String expectedMessage = String.format(MESSAGE_PATIENT_LISTED_OVERVIEW, 2);
        String userInput = "depression diabetes";
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(
                Arrays.asList(userInput.split("\\s+")));

        FindTagsCommand command = new FindTagsCommand(predicate);
        expectedModel.updateFilteredPatientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(ALICE, BENSON), model.getFilteredPatientList());
    }

    @Test
    public void equalsTest() {
        TagContainsKeywordsPredicate firstPredicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("first"));
        TagContainsKeywordsPredicate secondPredicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("second"));

        FindTagsCommand findTagsCommandFirst = new FindTagsCommand(firstPredicate);
        FindTagsCommand findTagsCommandSecond = new FindTagsCommand(secondPredicate);

        // same object -> equals
        assertEquals(findTagsCommandFirst, findTagsCommandFirst);

        // same values -> equals
        FindTagsCommand findTagsCommandFirstDuplicate = new FindTagsCommand(firstPredicate);
        assertEquals(findTagsCommandFirst, findTagsCommandFirstDuplicate);

        // null -> not equals
        assertNotEquals(null, findTagsCommandFirst);

        // different tag -> not equals
        assertNotEquals(findTagsCommandFirst, findTagsCommandSecond);

        //different instance -> not equals
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        FindCommand findCommand = new FindCommand(predicate);
        assertNotEquals(findTagsCommandFirst, findCommand);
    }

    @Test
    public void toStringMethod() {
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindTagsCommand findTagsCommand = new FindTagsCommand(predicate);
        String expected = FindTagsCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findTagsCommand.toString());
    }

}
