package seedu.realodex.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_ADDRESS_CAPS;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_EMAIL_CAPS;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_FAMILY;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_FAMILY_CAPS;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_INCOME;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_INCOME_CAPS;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_NAME_CAPS;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_PHONE_CAPS;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_REMARK_CAPS;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_TAG_CAPS;
import static seedu.realodex.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.realodex.commons.core.index.Index;
import seedu.realodex.logic.commands.exceptions.CommandException;
import seedu.realodex.model.Model;
import seedu.realodex.model.Realodex;
import seedu.realodex.model.person.NameContainsKeyphrasePredicate;
import seedu.realodex.model.person.Person;
import seedu.realodex.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY_FIRST_LETTER_CAPS = "Amy Bee";

    public static final String VALID_NAME_AMY_NON_CAPS = "amy bee";
    public static final String VALID_NAME_AMY_VARYING_CAPS = "aMY bEe";
    public static final String VALID_NAME_BOB_FIRST_LETTER_CAPS = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_INCOME_AMY = "20000";
    public static final String VALID_INCOME_BOB = "30000";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_FAMILY_AMY = "4";
    public static final String VALID_FAMILY_BOB = "4";
    public static final String VALID_TAG_AMY = "buyer";
    public static final String VALID_TAG_BOB = "seller";
    public static final String VALID_REMARK_AMY = "This is a remark";
    public static final String VALID_REMARK_BOB = "Testing remark";

    public static final String NAME_DESC_AMY_CAPS = " " + PREFIX_NAME + VALID_NAME_AMY_FIRST_LETTER_CAPS;
    public static final String NAME_DESC_AMY_NON_CAPS = " " + PREFIX_NAME + VALID_NAME_AMY_NON_CAPS;
    public static final String NAME_DESC_AMY_VARYING_CAPS = " " + PREFIX_NAME + VALID_NAME_AMY_VARYING_CAPS;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB_FIRST_LETTER_CAPS;
    public static final String NAME_DESC_BOB_PREFIX_CAPS = " " + PREFIX_NAME_CAPS + VALID_NAME_BOB_FIRST_LETTER_CAPS;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String PHONE_DESC_BOB_PREFIX_CAPS = " " + PREFIX_PHONE_CAPS + VALID_PHONE_BOB;
    public static final String INCOME_DESC_AMY = " " + PREFIX_INCOME + VALID_INCOME_AMY;
    public static final String INCOME_DESC_BOB = " " + PREFIX_INCOME + VALID_INCOME_BOB;
    public static final String INCOME_DESC_BOB_PREFIX_CAPS = " " + PREFIX_INCOME_CAPS + VALID_INCOME_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String EMAIL_DESC_BOB_PREFIX_CAPS = " " + PREFIX_EMAIL_CAPS + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String ADDRESS_DESC_BOB_PREFIX_CAPS = " " + PREFIX_ADDRESS_CAPS + VALID_ADDRESS_BOB;
    public static final String FAMILY_DESC_AMY = " " + PREFIX_FAMILY + VALID_FAMILY_AMY;
    public static final String FAMILY_DESC_BOB = " " + PREFIX_FAMILY + VALID_FAMILY_BOB;
    public static final String FAMILY_DESC_BOB_PREFIX_CAPS = " " + PREFIX_FAMILY_CAPS + VALID_FAMILY_BOB;
    public static final String TAG_DESC_BOB = " " + PREFIX_TAG + VALID_TAG_BOB;
    public static final String TAG_DESC_BOB_PREFIX_CAPS = " " + PREFIX_TAG_CAPS + VALID_TAG_BOB;
    public static final String TAG_DESC_AMY = " " + PREFIX_TAG + VALID_TAG_AMY;
    public static final String REMARK_DESC_AMY = " " + PREFIX_REMARK + VALID_REMARK_AMY;
    public static final String REMARK_DESC_BOB = " " + PREFIX_REMARK + VALID_REMARK_BOB;
    public static final String REMARK_DESC_BOB_PREFIX_CAPS = " " + PREFIX_REMARK_CAPS + VALID_REMARK_BOB;
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_INCOME_DESC = " " + PREFIX_INCOME + "-1"; // Income must be positive, >= 0
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_FAMILY_DESC = " " + PREFIX_FAMILY + "0"; // Family size must be at least 1
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "friend"; // 'friend' not allowed in tags
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";
    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY_FIRST_LETTER_CAPS)
                .withPhone(VALID_PHONE_AMY)
                .withIncome(VALID_INCOME_AMY)
                .withEmail(VALID_EMAIL_AMY)
                .withAddress(VALID_ADDRESS_AMY)
                .withFamily(VALID_FAMILY_AMY)
                .withTags(VALID_TAG_BOB)
                .withRemark(VALID_REMARK_AMY)
                .build();
        DESC_BOB = new EditPersonDescriptorBuilder()
                .withName(VALID_NAME_BOB_FIRST_LETTER_CAPS)
                .withPhone(VALID_PHONE_BOB)
                .withIncome(VALID_INCOME_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .withFamily(VALID_FAMILY_BOB)
                .withTags(VALID_TAG_AMY, VALID_TAG_BOB)
                .withRemark(VALID_REMARK_BOB)
                .build();
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
     * - the realodex, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Realodex expectedRealodex = new Realodex(actualModel.getRealodex());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedRealodex, actualModel.getRealodex());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s realodex.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String fullName = person.getName().fullName;
        model.updateFilteredPersonList(new NameContainsKeyphrasePredicate(fullName));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
