package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.EmailMatchesPredicate;
import seedu.address.model.person.GroupMatchesPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PhoneMatchesPredicate;
import seedu.address.model.person.Tag;
import seedu.address.model.person.TagMatchesPredicate;
import seedu.address.testutil.FindCommandBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void test_equals_withNameVariations() {
        // Check that the Builder is first functioning as normal
        assertEquals(new FindCommandBuilder().build(), new FindCommandBuilder().build());
        /* -----------------------------------NAME PRED VARIES------------------------------------------*/
        NameContainsKeywordsPredicate firstNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommandBuilder().withNamePred(firstNamePredicate).build();
        FindCommand findSecondCommand = new FindCommandBuilder().withNamePred(secondNamePredicate).build();

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommandBuilder().withNamePred(firstNamePredicate).build();
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false. Tested ONCE.
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false TEST ONCE
        assertFalse(findFirstCommand.equals(null));

        // different names -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void test_equals_withPhoneVariations() {
        /* vv --------------------------- PHONE PRED VARIES ------------------------------------ */
        PhoneMatchesPredicate firstPhonePredicate =
                new PhoneMatchesPredicate("123");
        PhoneMatchesPredicate secondPhonePredicate =
                new PhoneMatchesPredicate("789");

        FindCommand findPhoneFirstCommand = new FindCommandBuilder().withPhone(firstPhonePredicate).build();
        FindCommand findPhoneFirstCopy = new FindCommandBuilder().withPhone(firstPhonePredicate).build();
        FindCommand findPhoneSecondCommand = new FindCommandBuilder().withPhone(secondPhonePredicate).build();

        // Same object returns true
        assertEquals(findPhoneFirstCommand, findPhoneFirstCommand);

        // Same values returns true
        assertEquals(findPhoneFirstCommand, findPhoneFirstCopy);

        // Different phones returns false
        assertNotEquals(findPhoneFirstCommand, findPhoneSecondCommand);
    }

    @Test
    public void test_equals_withEmailVariations() {
        /* vv --------------------------- EMAIL PRED VARIES ------------------------------ */
        EmailMatchesPredicate firstEmailPredicate =
                new EmailMatchesPredicate("arona@arhive.com");
        EmailMatchesPredicate secondEmailPredicate =
                new EmailMatchesPredicate("shiroko@blue.com");

        FindCommand findEmailFirstCommand = new FindCommandBuilder().withEmail(firstEmailPredicate).build();
        FindCommand firstEmailCopy = new FindCommandBuilder().withEmail(firstEmailPredicate).build();
        FindCommand findEmailSecondCommand = new FindCommandBuilder().withEmail(secondEmailPredicate).build();

        // Same values returns true
        assertEquals(findEmailFirstCommand, firstEmailCopy);

        // Different values returns false
        assertNotEquals(findEmailFirstCommand, findEmailSecondCommand);
    }

    @Test
    public void test_equals_withTagVariations() {
        /* vv --------------------------- TAG PRED VARIES ----------------------------------- */
        TagMatchesPredicate tagFirstPredicate = new TagMatchesPredicate(Tag.TagType.Student.name());
        TagMatchesPredicate tagSecondPredicate = new TagMatchesPredicate(Tag.TagType.TA.name());

        // Same values returns true
        FindCommand firstFindTagCommand = new FindCommandBuilder().withTag(tagFirstPredicate).build();
        FindCommand tagCommandCopy = new FindCommandBuilder().withTag(tagFirstPredicate).build();
        FindCommand secondFindTagCommand = new FindCommandBuilder().withTag(tagSecondPredicate).build();

        assertEquals(firstFindTagCommand, tagCommandCopy);

        assertNotEquals(firstFindTagCommand, secondFindTagCommand);
    }

    @Test
    public void test_equals_withGroupVariations() {
        GroupMatchesPredicate singleGroupPredicate1 = new GroupMatchesPredicate(List.of("CS2103T"));
        GroupMatchesPredicate singleGroupPredicate2 = new GroupMatchesPredicate(List.of("CS2101"));
        GroupMatchesPredicate multiGroupPredicate1 = new GroupMatchesPredicate(List.of("CS2101", "CS2103T"));
        GroupMatchesPredicate multiGroupPredicate2 = new GroupMatchesPredicate(List.of("CS2101", "CS2109S"));

        FindCommand singleGroupCommand1 = new FindCommandBuilder().withGroups(singleGroupPredicate1).build();
        FindCommand singleGroupCommand2 = new FindCommandBuilder().withGroups(singleGroupPredicate2).build();
        assertNotEquals(singleGroupCommand1, singleGroupCommand2);

        FindCommand multiGroupCommand1 = new FindCommandBuilder().withGroups(multiGroupPredicate1).build();
        FindCommand multiGroupCommand2 = new FindCommandBuilder().withGroups(multiGroupPredicate2).build();
        assertNotEquals(multiGroupCommand1, multiGroupCommand2);
        assertNotEquals(singleGroupCommand1, multiGroupCommand1);

        FindCommand multiGroupCopy = new FindCommandBuilder().withGroups(multiGroupPredicate1).build();
        assertEquals(multiGroupCommand1, multiGroupCopy);
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommandBuilder().withNamePred(predicate).build();
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommandBuilder().withNamePred(predicate).build();
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void test_toStringMethod_withNameChange() {
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindCommand findCommand = new FindCommandBuilder().withNamePred(predicate).build();
        String expected = FindCommand.class.getCanonicalName()
                + "{"
                + "namePredicate=" + predicate + ", "
                + "phonePredicate=" + FindCommandBuilder.DEFAULT_PHONE_PRED + ", "
                + "emailPredicate=" + FindCommandBuilder.DEFAULT_EMAIL_PRED + ", "
                + "tagPredicate=" + FindCommandBuilder.DEFAULT_TAG_PRED + ", "
                + "groupPredicate=" + FindCommandBuilder.DEFAULT_GROUPS_PRED
                + "}";
        assertEquals(expected, findCommand.toString());
    }

    @Test
    public void test_toStringMethod_withPhoneChange() {
        PhoneMatchesPredicate phoneMatchesPredicate = new PhoneMatchesPredicate("123");
        FindCommand findPhoneCommand = new FindCommandBuilder().withPhone(phoneMatchesPredicate).build();
        String expectedPhoneChange = FindCommand.class.getCanonicalName()
                + "{"
                + "namePredicate=" + FindCommandBuilder.DEFAULT_NAME_PRED + ", "
                + "phonePredicate=" + phoneMatchesPredicate + ", "
                + "emailPredicate=" + FindCommandBuilder.DEFAULT_EMAIL_PRED + ", "
                + "tagPredicate=" + FindCommandBuilder.DEFAULT_TAG_PRED + ", "
                + "groupPredicate=" + FindCommandBuilder.DEFAULT_GROUPS_PRED
                + "}";
        assertEquals(findPhoneCommand.toString(), expectedPhoneChange);
    }

    @Test
    public void test_toStringMethod_withEmailChange() {
        EmailMatchesPredicate emailMatchesPredicate = new EmailMatchesPredicate("bobby");
        FindCommand findPhoneCommand = new FindCommandBuilder().withEmail(emailMatchesPredicate).build();
        String expectedPhoneChange = FindCommand.class.getCanonicalName()
                + "{"
                + "namePredicate=" + FindCommandBuilder.DEFAULT_NAME_PRED + ", "
                + "phonePredicate=" + FindCommandBuilder.DEFAULT_PHONE_PRED + ", "
                + "emailPredicate=" + emailMatchesPredicate + ", "
                + "tagPredicate=" + FindCommandBuilder.DEFAULT_TAG_PRED + ", "
                + "groupPredicate=" + FindCommandBuilder.DEFAULT_GROUPS_PRED
                + "}";
        assertEquals(findPhoneCommand.toString(), expectedPhoneChange);
    }

    @Test
    public void test_toStringMethod_withTagChange() {
        TagMatchesPredicate tagMatchesPredicate = new TagMatchesPredicate("TA");
        FindCommand findPhoneCommand = new FindCommandBuilder().withTag(tagMatchesPredicate).build();
        String expectedPhoneChange = FindCommand.class.getCanonicalName()
                + "{"
                + "namePredicate=" + FindCommandBuilder.DEFAULT_NAME_PRED + ", "
                + "phonePredicate=" + FindCommandBuilder.DEFAULT_PHONE_PRED + ", "
                + "emailPredicate=" + FindCommandBuilder.DEFAULT_EMAIL_PRED + ", "
                + "tagPredicate=" + tagMatchesPredicate + ", "
                + "groupPredicate=" + FindCommandBuilder.DEFAULT_GROUPS_PRED
                + "}";
        assertEquals(findPhoneCommand.toString(), expectedPhoneChange);
    }

    @Test
    public void test_toStringMethod_withGroupChange() {
        GroupMatchesPredicate groupMatchesPredicate = new GroupMatchesPredicate(List.of("CS2103T"));
        FindCommand findPhoneCommand = new FindCommandBuilder().withGroups(groupMatchesPredicate).build();
        String expectedPhoneChange = FindCommand.class.getCanonicalName()
                + "{"
                + "namePredicate=" + FindCommandBuilder.DEFAULT_NAME_PRED + ", "
                + "phonePredicate=" + FindCommandBuilder.DEFAULT_PHONE_PRED + ", "
                + "emailPredicate=" + FindCommandBuilder.DEFAULT_EMAIL_PRED + ", "
                + "tagPredicate=" + FindCommandBuilder.DEFAULT_TAG_PRED + ", "
                + "groupPredicate=" + groupMatchesPredicate
                + "}";
        assertEquals(findPhoneCommand.toString(), expectedPhoneChange);
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
