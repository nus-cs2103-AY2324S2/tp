package seedu.findvisor.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.findvisor.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.findvisor.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
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
import seedu.findvisor.model.person.PersonAddressPredicate;
import seedu.findvisor.model.person.PersonEmailPredicate;
import seedu.findvisor.model.person.PersonNamePredicate;
import seedu.findvisor.model.person.Person;
import seedu.findvisor.model.person.PersonPhonePredicate;
import seedu.findvisor.model.tag.PersonTagsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        PersonNamePredicate firstNamePredicate =
                new PersonNamePredicate(VALID_NAME_AMY);
        PersonNamePredicate secondNamePredicate =
                new PersonNamePredicate(VALID_NAME_BOB);

        PersonEmailPredicate firstEmailPredicate =
                new PersonEmailPredicate(VALID_EMAIL_AMY);
        PersonEmailPredicate secondEmailPredicate =
                new PersonEmailPredicate(VALID_EMAIL_BOB);

        PersonPhonePredicate firstPhonePredicate =
                new PersonPhonePredicate(VALID_PHONE_AMY);

        PersonPhonePredicate secondPhonePredicate =
                new PersonPhonePredicate(VALID_PHONE_BOB);

        PersonTagsPredicate firstTagsPredicate =
                new PersonTagsPredicate(Arrays.asList(VALID_TAG_HUSBAND));
        PersonTagsPredicate secondTagsPredicate =
                new PersonTagsPredicate(Arrays.asList(VALID_TAG_FRIEND));

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
        PersonNamePredicate namePredicate = new PersonNamePredicate(VALID_NAME_AMY);
        FindCommand command = new FindCommand(namePredicate);
        expectedModel.updateFilteredPersonList(namePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_nonExistentPhone_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonPhonePredicate phonePredicate = new PersonPhonePredicate(VALID_PHONE_AMY);
        Command command = new FindCommand(phonePredicate);
        expectedModel.updateFilteredPersonList(phonePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_nonExistentEmail_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonEmailPredicate predicate = new PersonEmailPredicate(VALID_EMAIL_AMY);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_nonExistentAddress_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonAddressPredicate predicate = new PersonAddressPredicate(VALID_ADDRESS_AMY);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_nonExistentTag_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonTagsPredicate tagsPredicate = new PersonTagsPredicate(
            Arrays.asList(new String[]{VALID_TAG_HUSBAND}));
        Command command = new FindCommand(tagsPredicate);
        expectedModel.updateFilteredPersonList(tagsPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }


    @Test
    public void execute_existingName_personFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        PersonNamePredicate predicate = new PersonNamePredicate(ALICE.getName().fullName);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPersonList());
    }

    @Test
    public void execute_existingPhone_personFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        PersonPhonePredicate predicate = new PersonPhonePredicate(BENSON.getPhone().value);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_existingEmail_personFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        PersonEmailPredicate predicate = new PersonEmailPredicate(CARL.getEmail().value);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL), model.getFilteredPersonList());
    }

    @Test
    public void execute_existingAddress_personFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        PersonAddressPredicate predicate = new PersonAddressPredicate(BENSON.getAddress().value);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_existingTags_personFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonTagsPredicate predicate = new PersonTagsPredicate(
            Arrays.asList(new String[]{"friends"}));
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        PersonNamePredicate predicate = new PersonNamePredicate("keyword");
        FindCommand findCommand = new FindCommand(predicate);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }
}
