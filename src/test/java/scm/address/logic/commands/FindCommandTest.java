package scm.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static scm.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static scm.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static scm.address.testutil.TypicalPersons.CARL;
import static scm.address.testutil.TypicalPersons.ELLE;
import static scm.address.testutil.TypicalPersons.FIONA;
import static scm.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import scm.address.model.Model;
import scm.address.model.ModelManager;
import scm.address.model.UserPrefs;
import scm.address.model.person.AddressContainsKeywordsPredicate;
import scm.address.model.person.NameContainsKeywordsPredicate;
import scm.address.model.person.TagsContainKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));
        AddressContainsKeywordsPredicate firstAddressPredicate =
                new AddressContainsKeywordsPredicate(Collections.singletonList("first"));
        AddressContainsKeywordsPredicate secondAddressPredicate =
                new AddressContainsKeywordsPredicate(Collections.singletonList("second"));
        TagsContainKeywordsPredicate firstTagsPredicate =
                new TagsContainKeywordsPredicate(Collections.singletonList("first"));
        TagsContainKeywordsPredicate secondTagsPredicate =
                new TagsContainKeywordsPredicate(Collections.singletonList("second"));


        FindCommand findFirstCommand = new FindCommand(firstNamePredicate, firstAddressPredicate, firstTagsPredicate);
        FindCommand findSecondCommand = new FindCommand(secondNamePredicate, secondAddressPredicate, secondTagsPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstNamePredicate, firstAddressPredicate, firstTagsPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate(" ");
        AddressContainsKeywordsPredicate addressPredicate = prepareAddressPredicate(" ");
        TagsContainKeywordsPredicate tagsPredicate = prepareTagsPredicate(" ");

        FindCommand command = new FindCommand(namePredicate, addressPredicate, tagsPredicate);
        expectedModel.updateFilteredPersonList(namePredicate.and(addressPredicate.and(tagsPredicate)));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate("Kurz Elle Kunz");
        AddressContainsKeywordsPredicate addressPredicate = prepareAddressPredicate(" ");
        TagsContainKeywordsPredicate tagsPredicate = prepareTagsPredicate(" ");

        FindCommand command = new FindCommand(namePredicate, addressPredicate, tagsPredicate);
        expectedModel.updateFilteredPersonList(namePredicate.and(addressPredicate.and(tagsPredicate)));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        NameContainsKeywordsPredicate namePredicate = new NameContainsKeywordsPredicate(Arrays.asList("keyword"));
        AddressContainsKeywordsPredicate addressPredicate = new AddressContainsKeywordsPredicate(Arrays.asList("keyword"));
        TagsContainKeywordsPredicate tagPredicate = new TagsContainKeywordsPredicate(Arrays.asList("keyword"));

        FindCommand findCommand = new FindCommand(namePredicate, addressPredicate, tagPredicate);
        String expected = FindCommand.class.getCanonicalName() + "{namePredicate=" + namePredicate + ", "
            + addressPredicate + "addressPredicate=" + addressPredicate + ", "
            + tagPredicate + "tagPredicate=" + tagPredicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into an {@code AddressContainsKeywordsPredicate}.
     */
    private AddressContainsKeywordsPredicate prepareAddressPredicate(String userInput) {
        return new AddressContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private TagsContainKeywordsPredicate prepareTagsPredicate(String userInput) {
        return new TagsContainKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
