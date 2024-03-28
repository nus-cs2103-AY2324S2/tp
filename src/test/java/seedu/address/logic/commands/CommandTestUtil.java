package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NUSID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;
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
import seedu.address.testutil.GroupPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NUSID_AMY = "E1234567";
    public static final String VALID_NUSID_BOB = "E7654321";
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_TAG_AMY = "Student";
    public static final String VALID_TAG_BOB = "Professor";
    public static final String VALID_GROUP_HUSBAND = "husband";
    public static final String VALID_GROUP_FRIEND = "friend";
    public static final String VALID_SCHEDULE_AMY = "10-10-2020";
    public static final String VALID_SCHEDULE_BOB = "11/11/2020";
    public static final String VALID_REMARK_AMY = "Meeting at 3pm";
    public static final String VALID_REMARK_BOB = "Lunch with Amy";

    public static final String NUSID_DESC_AMY = " " + PREFIX_NUSID + VALID_NUSID_AMY;
    public static final String NUSID_DESC_BOB = " " + PREFIX_NUSID + VALID_NUSID_BOB;
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String TAG_DESC_AMY = " " + PREFIX_TAG + VALID_TAG_AMY;
    public static final String TAG_DESC_BOB = " " + PREFIX_TAG + VALID_TAG_BOB;
    public static final String GROUP_DESC_FRIEND = " " + PREFIX_GROUP + VALID_GROUP_FRIEND;
    public static final String GROUP_DESC_HUSBAND = " " + PREFIX_GROUP + VALID_GROUP_HUSBAND;
    public static final String SCHEDULE_DESC_AMY = " " + PREFIX_SCHEDULE + VALID_SCHEDULE_AMY;
    public static final String SCHEDULE_DESC_BOB = " " + PREFIX_SCHEDULE + VALID_SCHEDULE_BOB;
    public static final String REMARK_DESC_AMY = " " + PREFIX_REMARK + VALID_REMARK_AMY;
    public static final String REMARK_DESC_BOB = " " + PREFIX_REMARK + VALID_REMARK_BOB;
    public static final String INVALID_NUSID_DESC = " " + PREFIX_NUSID + "e1234567"; // 'e' not allowed in id
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG; // empty string not allowed for tags
    public static final String INVALID_GROUP_DESC = " " + PREFIX_GROUP + "hubby*"; // '*' not allowed in groups
    public static final String INVALID_SCHEDULE_DESC = " " + PREFIX_SCHEDULE + "2024-12-12"; // wrong date format

    public static final String INVALID_NUSID = "e1234567"; // lowercase not accepted
    public static final String INVALID_SCHEDULE = "12-31-2020"; // Incorrect month


    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;

    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    public static final GroupCommand.GroupPersonDescriptor DESC_AMY_GROUP;

    public static final GroupCommand.GroupPersonDescriptor DESC_BOB_GROUP;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withTag(VALID_TAG_AMY)
                .withGroups(VALID_GROUP_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withTag(VALID_TAG_BOB)
                .withGroups(VALID_GROUP_HUSBAND, VALID_GROUP_FRIEND).build();
        DESC_AMY_GROUP = new GroupPersonDescriptorBuilder().withTag(VALID_TAG_AMY)
                .withGroups(VALID_GROUP_HUSBAND).build();
        DESC_BOB_GROUP = new GroupPersonDescriptorBuilder().withTag(VALID_TAG_BOB)
                .withGroups(VALID_GROUP_FRIEND).build();
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
