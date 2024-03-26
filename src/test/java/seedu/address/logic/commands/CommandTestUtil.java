package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BANKDETAILS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIRSTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LASTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAYRATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
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
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_FIRSTNAME_AMY = "Amy";
    public static final String VALID_LASTNAME_AMY = "Bee";
    public static final String VALID_FIRSTNAME_BOB = "Bob";
    public static final String VALID_LASTNAME_BOB = "Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_SEX_AMY = "f";
    public static final String VALID_SEX_BOB = "m";
    public static final double VALID_PAYRATE_AMY = 14.0;
    public static final double VALID_PAYRATE_BOB = 18.5;
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_BANKDETAILS_AMY = "8374219837";
    public static final String VALID_BANKDETAILS_BOB = "3214213212";
    public static final String VALID_TAG_WAITER = "waiter";
    public static final String VALID_TAG_COOK = "cook";
    public static final int VALID_WORK_HOURS_AMY = 10;
    public static final int VALID_WORK_HOURS_BOB = 20;

    public static final String FIRSTNAME_DESC_AMY = " " + PREFIX_FIRSTNAME + VALID_FIRSTNAME_AMY;
    public static final String LASTNAME_DESC_AMY = " " + PREFIX_LASTNAME + VALID_LASTNAME_AMY;
    public static final String FIRSTNAME_DESC_BOB = " " + PREFIX_FIRSTNAME + VALID_FIRSTNAME_BOB;
    public static final String LASTNAME_DESC_BOB = " " + PREFIX_LASTNAME + VALID_LASTNAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String SEX_DESC_AMY = " " + PREFIX_SEX + VALID_SEX_AMY;
    public static final String SEX_DESC_BOB = " " + PREFIX_SEX + VALID_SEX_BOB;
    public static final String PAYRATE_DESC_AMY = " " + PREFIX_PAYRATE + VALID_PAYRATE_AMY;
    public static final String PAYRATE_DESC_BOB = " " + PREFIX_PAYRATE + VALID_PAYRATE_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String BANKDETAILS_DESC_AMY = " " + PREFIX_BANKDETAILS + VALID_BANKDETAILS_AMY;
    public static final String BANKDETAILS_DESC_BOB = " " + PREFIX_BANKDETAILS + VALID_BANKDETAILS_BOB;

    public static final String TAG_DESC_COOK = " " + PREFIX_TAG + VALID_TAG_COOK;
    public static final String TAG_DESC_WAITER = " " + PREFIX_TAG + VALID_TAG_WAITER;

    public static final String INVALID_FIRSTNAME_DESC = " " + PREFIX_FIRSTNAME + "James&"; // '&' not allowed in
    public static final String INVALID_LASTNAME_DESC = " " + PREFIX_LASTNAME + "Char.les"; // '&' not allowed in
    public static final String INVALID_SEX_DESC = " " + PREFIX_SEX + "u"; // 'u' is not an allowed sex
    public static final String INVALID_PAYRATE_DESC = " " + PREFIX_PAYRATE + "ut"; //'ut' is not an allowed payrate
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_BANKDETAILS_DESC = " " + PREFIX_BANKDETAILS
        + "3213-421321"; // '-' not allowed in bank details
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses


    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";


    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withFirstName(VALID_FIRSTNAME_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withTags(VALID_TAG_COOK).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withFirstName(VALID_LASTNAME_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withTags(VALID_TAG_WAITER, VALID_TAG_COOK).build();
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
        final String[] splitName = person.getName().value.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
