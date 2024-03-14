package seedu.findvisor.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.findvisor.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.findvisor.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.findvisor.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.findvisor.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.findvisor.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.findvisor.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.findvisor.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.findvisor.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.findvisor.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.findvisor.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.findvisor.testutil.TypicalPersons.ALICE;
import static seedu.findvisor.testutil.TypicalPersons.BENSON;
import static seedu.findvisor.testutil.TypicalPersons.CARL;
import static seedu.findvisor.testutil.TypicalPersons.DANIEL;
import static seedu.findvisor.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.findvisor.model.Model;
import seedu.findvisor.model.ModelManager;
import seedu.findvisor.model.UserPrefs;
import seedu.findvisor.model.person.Person;
import seedu.findvisor.model.person.EmailEqualsKeywordPredicate;
import seedu.findvisor.model.person.NameContainsKeywordPredicate;
import seedu.findvisor.model.person.PhoneEqualsKeywordPredicate;
import seedu.findvisor.model.tag.TagsContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordPredicate firstNamePredicate =
                new NameContainsKeywordPredicate(VALID_NAME_AMY);
        NameContainsKeywordPredicate secondNamePredicate =
                new NameContainsKeywordPredicate(VALID_NAME_BOB);

        EmailEqualsKeywordPredicate firstEmailPredicate =
                new EmailEqualsKeywordPredicate(VALID_EMAIL_AMY);
        EmailEqualsKeywordPredicate secondEmailPredicate =
                new EmailEqualsKeywordPredicate(VALID_EMAIL_BOB);

        PhoneEqualsKeywordPredicate firstPhonePredicate =
                new PhoneEqualsKeywordPredicate(VALID_PHONE_AMY);

        PhoneEqualsKeywordPredicate secondPhonePredicate =
                new PhoneEqualsKeywordPredicate(VALID_PHONE_BOB);

        TagsContainsKeywordsPredicate firstTagsPredicate =
                new TagsContainsKeywordsPredicate(Arrays.asList(VALID_TAG_HUSBAND));
        TagsContainsKeywordsPredicate secondTagsPredicate =
                new TagsContainsKeywordsPredicate(Arrays.asList(VALID_TAG_FRIEND));

        testCommandEquality(firstNamePredicate, secondNamePredicate);
        testCommandEquality(firstEmailPredicate, secondEmailPredicate);
        testCommandEquality(firstPhonePredicate, secondPhonePredicate);
        testCommandEquality(firstTagsPredicate, secondTagsPredicate);
    }

    // Helper method to test equality of FindCommand objects
    private void testCommandEquality(Predicate<Person> firstPredicate, Predicate<Person> secondPredicate) {
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
    }

    @Test
    public void execute_nonExistentName_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordPredicate namePredicate = new NameContainsKeywordPredicate(VALID_NAME_AMY);
        FindCommand command = new FindCommand(namePredicate);
        expectedModel.updateFilteredPersonList(namePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_nonExistentPhone_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PhoneEqualsKeywordPredicate phonePredicate = new PhoneEqualsKeywordPredicate(VALID_PHONE_AMY);
        Command command = new FindCommand(phonePredicate);
        expectedModel.updateFilteredPersonList(phonePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_nonExistentEmail_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        EmailEqualsKeywordPredicate predicate = new EmailEqualsKeywordPredicate(VALID_EMAIL_AMY);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_nonExistentTag_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        TagsContainsKeywordsPredicate tagsPredicate = new TagsContainsKeywordsPredicate(
            Arrays.asList(new String[]{VALID_TAG_HUSBAND}));
        Command command = new FindCommand(tagsPredicate);
        expectedModel.updateFilteredPersonList(tagsPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }


    @Test
    public void execute_ExistingName_PersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        NameContainsKeywordPredicate predicate = new NameContainsKeywordPredicate(ALICE.getName().fullName);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPersonList());
    }

    @Test
    public void execute_ExistingPhone_PersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        PhoneEqualsKeywordPredicate predicate = new PhoneEqualsKeywordPredicate(BENSON.getPhone().value);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_ExistingEmail_PersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        EmailEqualsKeywordPredicate predicate = new EmailEqualsKeywordPredicate(CARL.getEmail().value);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL), model.getFilteredPersonList());
    }

    @Test
    public void execute_ExistingTags_PersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        TagsContainsKeywordsPredicate predicate = new TagsContainsKeywordsPredicate(
            Arrays.asList(new String[]{"friends"}));
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        NameContainsKeywordPredicate predicate = new NameContainsKeywordPredicate("keyword");
        FindCommand findCommand = new FindCommand(predicate);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }
}
