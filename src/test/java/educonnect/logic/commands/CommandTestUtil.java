package educonnect.logic.commands;

import static educonnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static educonnect.logic.parser.CliSyntax.PREFIX_LINK;
import static educonnect.logic.parser.CliSyntax.PREFIX_NAME;
import static educonnect.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static educonnect.logic.parser.CliSyntax.PREFIX_TAG;
import static educonnect.logic.parser.CliSyntax.PREFIX_TELEGRAM_HANDLE;
import static educonnect.logic.parser.CliSyntax.PREFIX_TIMETABLE;
import static educonnect.testutil.Assert.assertThrows;
import static educonnect.testutil.TypicalTimetableAndValues.VALID_TIMETABLE_1;
import static educonnect.testutil.TypicalTimetableAndValues.VALID_TIMETABLE_2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import educonnect.commons.core.index.Index;
import educonnect.logic.commands.exceptions.CommandException;
import educonnect.model.AddressBook;
import educonnect.model.Model;
import educonnect.model.student.Student;
import educonnect.model.student.predicates.NameContainsKeywordsPredicate;
import educonnect.testutil.EditStudentDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NAME_JOHN = "John Doer";
    public static final String VALID_STUDENT_ID_AMY = "A0265491U";
    public static final String VALID_STUDENT_ID_BOB = "A0365501X";
    public static final String VALID_STUDENT_ID_JOHN = "A1897496H";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_EMAIL_JOHN = "johndoedoejohn@example.com";
    public static final String VALID_TELEGRAM_HANDLE_AMY = "@amyb";
    public static final String VALID_TELEGRAM_HANDLE_BOB = "@choobobco";
    public static final String VALID_TELEGRAM_HANDLE_JOHN = "@doedoejohnjohn.joe";
    public static final String VALID_LINK_AMY = "https://www.youtube.com/";
    public static final String VALID_LINK_BOB = "https://https://en.wikipedia.org/wiki/Code/";
    public static final String VALID_LINK_JOHN = "https://www.google.com/";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_TAG_SMART = "smart";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String STUDENT_ID_DESC_AMY = " " + PREFIX_STUDENT_ID + VALID_STUDENT_ID_AMY;
    public static final String STUDENT_ID_DESC_BOB = " " + PREFIX_STUDENT_ID + VALID_STUDENT_ID_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String TELEGRAM_HANDLE_DESC_AMY = " " + PREFIX_TELEGRAM_HANDLE + VALID_TELEGRAM_HANDLE_AMY;
    public static final String TELEGRAM_HANDLE_DESC_BOB = " " + PREFIX_TELEGRAM_HANDLE + VALID_TELEGRAM_HANDLE_BOB;
    public static final String LINK_DESC_AMY = " " + PREFIX_LINK + VALID_LINK_AMY;
    public static final String LINK_DESC_BOB = " " + PREFIX_LINK + VALID_LINK_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String TIMETABLE_DESC_VALID1 = " " + PREFIX_TIMETABLE + VALID_TIMETABLE_1;
    public static final String TIMETABLE_DESC_VALID2 = " " + PREFIX_TIMETABLE + VALID_TIMETABLE_2;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_STUDENT_ID_DESC =
        " " + PREFIX_STUDENT_ID + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_TELEGRAM_HANDLE_DESC =
        " " + PREFIX_TELEGRAM_HANDLE; // empty string not allowed for addresses
    public static final String INVALID_LINK = "dhello";
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_TIMETABLE_DESC = " " + PREFIX_TIMETABLE
                                                        + "mon: 13 to 15" // 'to' is not allowed, use '-'
                                                        + "tue: 2pm-4pm " // 'pm' is not recognised, use 0-23 clock
                                                        + "wed: 1-3 4-7 " // no ',' between periods
                                                        + "thu: 24-27 " // numbers above 23 not recognised, use 0-23
                                                        + "fri 12-14"; // no ':' after 'fri'

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditStudentDescriptor DESC_AMY;
    public static final EditCommand.EditStudentDescriptor DESC_BOB;
    public static final EditCommand.EditStudentDescriptor DESC_JOHN;

    static {
        DESC_AMY = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withStudentId(VALID_STUDENT_ID_AMY).withEmail(VALID_EMAIL_AMY)
                .withTelegramHandle(VALID_TELEGRAM_HANDLE_AMY)
                .withLink(VALID_LINK_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withStudentId(VALID_STUDENT_ID_BOB).withEmail(VALID_EMAIL_BOB)
                .withTelegramHandle(VALID_TELEGRAM_HANDLE_BOB)
                .withLink(VALID_LINK_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_JOHN = new EditStudentDescriptorBuilder().withName(VALID_NAME_JOHN)
                .withStudentId(VALID_STUDENT_ID_JOHN).withEmail(VALID_EMAIL_JOHN)
                .withTelegramHandle(VALID_TELEGRAM_HANDLE_JOHN)
                .withLink(VALID_LINK_JOHN)
                .withTags(VALID_TAG_SMART).build();
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
     * - the address book, filtered student list and selected student in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Student> expectedFilteredList = new ArrayList<>(actualModel.getFilteredStudentList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredStudentList());
    }
    /**
     * Potentially buggy as names can be duplicated in the future
     * Updates {@code model}'s filtered list to show only the student at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showStudentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredStudentList().size());

        Student student = model.getFilteredStudentList().get(targetIndex.getZeroBased());
        final String name = student.getName().fullName;
        model.updateFilteredStudentList(List.of(new NameContainsKeywordsPredicate(name)));

        assertEquals(1, model.getFilteredStudentList().size());
    }

}
