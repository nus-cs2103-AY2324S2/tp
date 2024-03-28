package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.appointment.AppointmentView;
import seedu.address.model.patient.NameContainsKeywordsPredicate;
import seedu.address.model.patient.Patient;
import seedu.address.testutil.EditPatientDescriptorBuilder;
import seedu.address.ui.ViewMode;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NRIC_AMY = "T0123456A";
    public static final String VALID_NRIC_BOB = "T0123456B";
    public static final String VALID_DOB_AMY = "2001-01-01";
    public static final String VALID_DOB_BOB = "2001-01-02";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_APPOINTMENT_DATE_AMY = "2024-03-16";
    public static final String VALID_APPOINTMENT_DATE_BOB = "2024-03-17";
    public static final String VALID_APPOINTMENT_START_TIME_AMY = "11:00";
    public static final String VALID_APPOINTMENT_END_TIME_AMY = "11:30";
    public static final String VALID_APPOINTMENT_START_TIME_BOB = "15:00";
    public static final String VALID_APPOINTMENT_END_TIME_BOB = "16:00";
    public static final String VALID_APPOINTMENT_TYPE_AMY = "X-RAY";
    public static final String VALID_APPOINTMENT_TYPE_BOB = "Health Check-up";
    public static final String VALID_APPOINTMENT_NOTE_AMY = "Fractured her ankle";
    public static final String VALID_APPOINTMENT_NOTE_BOB = "Yearly check in";
    public static final String VALID_APPOINTMENT_MARK_AMY = "true";
    public static final String VALID_APPOINTMENT_MARK_BOB = "false";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String NRIC_DESC_AMY = " " + PREFIX_NRIC + VALID_NRIC_AMY;
    public static final String NRIC_DESC_BOB = " " + PREFIX_NRIC + VALID_NRIC_BOB;
    public static final String DOB_DESC_AMY = " " + PREFIX_DOB + VALID_DOB_AMY;
    public static final String DOB_DESC_BOB = " " + PREFIX_DOB + VALID_DOB_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String DATE_DESC_APPOINTMENT_AMY = " " + PREFIX_DATE + VALID_APPOINTMENT_DATE_AMY;
    public static final String DATE_DESC_APPOINTMENT_BOB = " " + PREFIX_DATE + VALID_APPOINTMENT_DATE_BOB;
    public static final String START_TIME_DESC_APPOINTMENT_AMY = " " + PREFIX_START_TIME
            + VALID_APPOINTMENT_START_TIME_AMY;
    public static final String END_TIME_DESC_APPOINTMENT_AMY = " " + PREFIX_END_TIME
            + VALID_APPOINTMENT_END_TIME_AMY;
    public static final String START_TIME_DESC_APPOINTMENT_BOB = " " + PREFIX_START_TIME
            + VALID_APPOINTMENT_START_TIME_BOB;
    public static final String END_TIME_DESC_APPOINTMENT_BOB = " " + PREFIX_END_TIME
            + VALID_APPOINTMENT_END_TIME_BOB;
    public static final String TYPE_DESC_APPOINTMENT_AMY = " " + PREFIX_TAG + VALID_APPOINTMENT_TYPE_AMY;
    public static final String TYPE_DESC_APPOINTMENT_BOB = " " + PREFIX_TAG + VALID_APPOINTMENT_TYPE_BOB;
    public static final String NOTE_DESC_APPOINTMENT_AMY = " " + PREFIX_NOTE + VALID_APPOINTMENT_NOTE_AMY;
    public static final String NOTE_DESC_APPOINTMENT_BOB = " " + PREFIX_NOTE + VALID_APPOINTMENT_NOTE_BOB;
    public static final String MARK_DESC_APPOINTMENT_AMY = " " + PREFIX_NOTE + VALID_APPOINTMENT_MARK_AMY;
    public static final String MARK_DESC_APPOINTMENT_BOB = " " + PREFIX_NOTE + VALID_APPOINTMENT_MARK_BOB;


    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_NRIC_DESC = " " + PREFIX_NRIC + "t0123456A"; // first letter must be capital
    public static final String INVALID_DOB_DESC = " " + PREFIX_DOB + "2025-01-01"; // must be before today's date
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_DATE_DESC = " " + PREFIX_DATE + "2024-32-32"; // exceeds month and day range
    public static final String INVALID_TIME_DESC = " " + PREFIX_START_TIME + "24:00"; // exceeds 24 hour clock range
    public static final String INVALID_START_TIME_DESC = " " + PREFIX_START_TIME + "11:30"; // is after end time
    public static final String INVALID_END_TIME_DESC = " " + PREFIX_END_TIME + "11:00"; // is before start time
    public static final String INVALID_APPOINTMENT_TYPE_DESC = " " + PREFIX_TAG + "  "; // only white spaces
    public static final String INVALID_APPOINTMENT_NOTE_DESC = " " + PREFIX_NOTE + "@@"; // non-alphanumeric
    public static final String INVALID_APPOINTMENT_MARK_DESC = " " + PREFIX_NOTE + "abc"; // not true or false
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final String MISSING_NRIC = "G0123456A";

    public static final EditPatientCommand.EditPatientDescriptor DESC_AMY;
    public static final EditPatientCommand.EditPatientDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPatientDescriptorBuilder().withName(VALID_NAME_AMY)
                .withDateOfBirth(VALID_DOB_AMY).withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB)
                .withDateOfBirth(VALID_DOB_BOB).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
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
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage} and boolean {@code isOverallCommand}.
     */
    public static void assertOverallCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, ViewMode.OVERALL);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered patient list, filtered appointment list and
     * - selected patient in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Patient> expectedFilteredPatientList = new ArrayList<>(actualModel.getFilteredPatientList());
        List<AppointmentView> expectedFilteredAppointmentViewList =
                new ArrayList<>(actualModel.getFilteredAppointmentViewList());
        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredPatientList, actualModel.getFilteredPatientList());
        assertEquals(expectedFilteredAppointmentViewList, actualModel.getFilteredAppointmentViewList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the patient at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPatientAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPatientList().size());

        Patient patient = model.getFilteredPatientList().get(targetIndex.getZeroBased());
        final String[] splitName = patient.getName().fullName.split("\\s+");
        model.updateFilteredPatientList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPatientList().size());
    }
}
