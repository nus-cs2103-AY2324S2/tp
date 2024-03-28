package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENT_PHONES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_ONE_AMY = "11111111";
    public static final String VALID_PHONE_TWO_AMY = "22222222";
    public static final String VALID_PHONE_ONE_BOB = "33333333";
    public static final String VALID_PHONE_TWO_BOB = "44444444";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_STUDENT_ID_AMY = "00001";
    public static final String VALID_STUDENT_ID_BOB = "00002";
    public static final String VALID_TAG_HUSBAND = "Husband";
    public static final String VALID_TAG_FRIEND = "Friend";
    public static final String VALID_CLASS_AMY = "6 A";
    public static final String VALID_CLASS_BOB = "6 A";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_ADD_AMY =
            " " + PREFIX_PARENT_PHONES + VALID_PHONE_ONE_AMY + ", " + VALID_PHONE_TWO_AMY;
    public static final String PHONE_DESC_EDIT_AMY = " " + PREFIX_PARENT_PHONES + VALID_PHONE_ONE_AMY + ", 2";
    public static final String PHONE_DESC_ADD_BOB =
            " " + PREFIX_PARENT_PHONES + VALID_PHONE_ONE_BOB + ", " + VALID_PHONE_TWO_BOB;
    public static final String PHONE_DESC_EDIT_BOB = " " + PREFIX_PARENT_PHONES + VALID_PHONE_ONE_BOB + ", 1";
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String STUDENT_ID_DESC_AMY = " " + PREFIX_STUDENT_ID + VALID_STUDENT_ID_AMY;
    public static final String STUDENT_ID_DESC_BOB = " " + PREFIX_STUDENT_ID + VALID_STUDENT_ID_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String CLASS_DESC_AMY = " " + PREFIX_CLASS + VALID_CLASS_AMY;
    public static final String CLASS_DESC_BOB = " " + PREFIX_CLASS + VALID_CLASS_BOB;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_ADD_DESC = " " + PREFIX_PARENT_PHONES + "911a, " + "912a"; // 'a' not
    // allowed in
    // phones
    public static final String INVALID_PHONE_EDIT_DESC = " " + PREFIX_PARENT_PHONES + "911a, 1";
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_STUDENT_ID_DESC = " " + PREFIX_STUDENT_ID + "12345678A";
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "Hubby*"; // '*' not allowed in tags

    public static final String INVALID_CLASS_DESC = " " + PREFIX_CLASS + "6 A A"; // Max wordcount 2
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_ONE_AMY, "2")
                .withEmail(VALID_EMAIL_AMY)
                .withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_TWO_BOB, "1")
                .withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB)
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
     * Updates {@code model}'s filtered list to show only the person with the given {@code studentId} in the
     * {@code model}'s address book.
     */
    public static void showPersonWithStudentId(Model model, StudentId studentId) {
        boolean personFound = false;
        List<Person> filteredList = model.getFilteredPersonList();
        for (Person person : filteredList) {
            if (person.getStudentId().equals(studentId)) {
                personFound = true;
                final String[] splitName = person.getName().fullName.split("\\s+");
                model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));
                assertEquals(1, model.getFilteredPersonList().size());
                break;
            }
        }
        assertTrue(personFound); // Ensure the person with the specified studentId exists
    }



}
