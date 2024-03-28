package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FREETIMETAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOMNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.AddPersonFreeTimeDescriptorBuilder;
import seedu.address.testutil.DeletePersonFreeTimeDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ROOMNUMBER_AMY = "sw-01-01";
    public static final String VALID_ROOMNUMBER_BOB = "nw-02-02";
    public static final String VALID_TELEGRAM_AMY = "amyBee";
    public static final String VALID_TELEGRAM_BOB = "bobChoo";
    public static final String VALID_BIRTHDAY_AMY = "01/01/2000";
    public static final String VALID_BIRTHDAY_BOB = "02/02/2000";
    public static final String VALID_FREE_TIME_TAG_AMY = "Mon:1000-1200";
    public static final String VALID_FREE_TIME_TAG_BOB = "Wed:1400-2000";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ROOMNUMBER_DESC_AMY = " " + PREFIX_ROOMNUMBER + VALID_ROOMNUMBER_AMY;
    public static final String ROOMNUMBER_DESC_BOB = " " + PREFIX_ROOMNUMBER + VALID_ROOMNUMBER_BOB;
    public static final String TELEGRAM_DESC_AMY = " " + PREFIX_TELEGRAM + VALID_TELEGRAM_AMY;
    public static final String TELEGRAM_DESC_BOB = " " + PREFIX_TELEGRAM + VALID_TELEGRAM_BOB;
    public static final String BIRTHDAY_DESC_AMY = " " + PREFIX_BIRTHDAY + VALID_BIRTHDAY_AMY;
    public static final String BIRTHDAY_DESC_BOB = " " + PREFIX_BIRTHDAY + VALID_BIRTHDAY_BOB;
    public static final String FREE_TIME_TAG_DESC_AMY = " " + PREFIX_FREETIMETAG + VALID_FREE_TIME_TAG_AMY;
    public static final String FREE_TIME_TAG_DESC_BOB = " " + PREFIX_FREETIMETAG + VALID_FREE_TIME_TAG_BOB;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ROOMNUMBER_DESC = " " + PREFIX_ROOMNUMBER; // empty string not allowed for room
    public static final String INVALID_TELEGRAM_DESC = " " + PREFIX_TELEGRAM; // empty string not allowed for telegram
    public static final String INVALID_BIRTHDAY_DESC = " " + PREFIX_BIRTHDAY; // empty string not allowed for birthday
    public static final String INVALID_FREE_TIME_TAG_DESC = " "
            + PREFIX_FREETIMETAG; // empty string not allowed for free time tag

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final AddTimeCommand.EditPersonDescriptor DESC_ADD_TIME_AMY;
    public static final AddTimeCommand.EditPersonDescriptor DESC_ADD_TIME_BOB;
    public static final DeleteTimeCommand.EditPersonDescriptor DESC_DELETE_TIME_AMY;
    public static final DeleteTimeCommand.EditPersonDescriptor DESC_DELETE_TIME_BOB;
    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_ADD_TIME_AMY = new AddPersonFreeTimeDescriptorBuilder().withFreeTimeTags(VALID_FREE_TIME_TAG_AMY).build();
        DESC_ADD_TIME_BOB = new AddPersonFreeTimeDescriptorBuilder().withFreeTimeTags(VALID_FREE_TIME_TAG_BOB).build();
        DESC_DELETE_TIME_AMY = new DeletePersonFreeTimeDescriptorBuilder()
                .withFreeTimeTags(VALID_FREE_TIME_TAG_AMY).build();
        DESC_DELETE_TIME_BOB = new DeletePersonFreeTimeDescriptorBuilder()
                .withFreeTimeTags(VALID_FREE_TIME_TAG_BOB).build();
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withRoomNumber(VALID_ROOMNUMBER_AMY)
                .withTelegram(VALID_TELEGRAM_AMY).withBirthday(VALID_BIRTHDAY_AMY)
                .withFreeTimeTags(VALID_FREE_TIME_TAG_AMY).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withRoomNumber(VALID_ROOMNUMBER_BOB)
                .withTelegram(VALID_TELEGRAM_BOB).withBirthday(VALID_BIRTHDAY_BOB)
                .withFreeTimeTags(VALID_FREE_TIME_TAG_BOB).build();
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
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
