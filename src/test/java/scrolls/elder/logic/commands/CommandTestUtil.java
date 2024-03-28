package scrolls.elder.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import scrolls.elder.commons.core.index.Index;
import scrolls.elder.logic.commands.exceptions.CommandException;
import scrolls.elder.logic.parser.CliSyntax;
import scrolls.elder.model.Datastore;
import scrolls.elder.model.Model;
import scrolls.elder.model.PersonStore;
import scrolls.elder.model.person.NameContainsKeywordsPredicate;
import scrolls.elder.model.person.Person;
import scrolls.elder.testutil.Assert;
import scrolls.elder.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final int VALID_ID_AMY = 0;
    public static final int VALID_ID_BOB = 1;
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";


    public static final String VALID_ROLE_VOLUNTEER = "volunteer";
    public static final String VALID_ROLE_BEFRIENDEE = "befriendee";

    public static final String NAME_DESC_AMY = " " + CliSyntax.PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + CliSyntax.PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + CliSyntax.PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + CliSyntax.PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + CliSyntax.PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + CliSyntax.PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + CliSyntax.PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + CliSyntax.PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + CliSyntax.PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + CliSyntax.PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String ROLE_DESC_VOLUNTEER = " " + CliSyntax.PREFIX_ROLE + VALID_ROLE_VOLUNTEER;
    public static final String ROLE_DESC_BEFRIENDEE = " " + CliSyntax.PREFIX_ROLE + VALID_ROLE_BEFRIENDEE;


    public static final String INVALID_NAME_DESC = " " + CliSyntax.PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + CliSyntax.PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + CliSyntax.PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " "
            + CliSyntax.PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + CliSyntax.PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String INVALID_ROLE_DESC = " " + CliSyntax.PREFIX_ROLE + "person";
    // only values 'volunteer' and 'befriendee' are allowed in role

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY_VOLUNTEER;
    public static final EditCommand.EditPersonDescriptor DESC_BOB_BEFRIENDEE;

    static {
        DESC_AMY_VOLUNTEER = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withRole(VALID_ROLE_VOLUNTEER)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB_BEFRIENDEE = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withRole(VALID_ROLE_BEFRIENDEE)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        // TODO: can consider re-adding check for lists, but might get cumbersome
        Datastore expectedDatastore = new Datastore(actualModel.getDatastore());
        //        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        Assert.assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedDatastore, actualModel.getDatastore());
        //        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(PersonStore personStore, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < personStore.getFilteredPersonList().size());

        Person person = personStore.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        personStore.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, personStore.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the volunteer at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showVolunteerAtIndex(PersonStore personStore, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < personStore.getFilteredVolunteerList().size());

        Person person = personStore.getFilteredVolunteerList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        personStore.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, personStore.getFilteredVolunteerList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the befriendee at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showBefriendeeAtIndex(PersonStore personStore, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < personStore.getFilteredBefriendeeList().size());

        Person person = personStore.getFilteredBefriendeeList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        personStore.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, personStore.getFilteredBefriendeeList().size());
    }

}
