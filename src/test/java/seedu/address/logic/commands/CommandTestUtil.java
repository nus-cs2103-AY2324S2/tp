package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FAMILY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FOOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOBBY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRENAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.patient.EditPatientDescriptor;
import seedu.address.model.patient.NameContainsKeywordsPredicate;
import seedu.address.model.patient.Patient;
import seedu.address.testutil.EditPatientDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_ID_AMY = "12345";
    public static final String VALID_ID_BOB = "12234";
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PREFERRED_NAME_AMY = "Amy";
    public static final String VALID_PREFERRED_NAME_BOB = "Bob";
    public static final String VALID_FOOD_AMY = "Dim sum";
    public static final String VALID_FOOD_BOB = "Chicken rice";
    public static final String VALID_FAMILY_CONDITION_AMY = "2 sons migrated to Korea";
    public static final String VALID_FAMILY_CONDITION_BOB = "Always quarrels with daughter";
    public static final String VALID_HOBBY_AMY = "Watch Hong Kong Drama";
    public static final String VALID_HOBBY_BOB = "Interested in calligraphy";
    public static final String VALID_TAG_DIABETES = "diabetes";
    public static final String VALID_TAG_DEPRESSION = "depression";
    public static final String VALID_IMPORTANT_DATE_NAME = "Birthday";
    public static final String VALID_IMPORTANT_DATE = "20-02-2022";
    public static final String VALID_IMPORTANT_DATETIME = "20-02-2022, 12:12 - 15:15";

    public static final String ID_DESC_AMY = " " + PREFIX_PID + VALID_ID_AMY;
    public static final String ID_DESC_BOB = " " + PREFIX_PID + VALID_ID_BOB;
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PREFERRED_NAME_DESC_AMY = " " + PREFIX_PRENAME + VALID_PREFERRED_NAME_AMY;
    public static final String PREFERRED_NAME_DESC_BOB = " " + PREFIX_PRENAME + VALID_PREFERRED_NAME_BOB;
    public static final String FOOD_DESC_BOB = " " + PREFIX_FOOD + VALID_FOOD_BOB;
    public static final String FOOD_DESC_AMY = " " + PREFIX_FOOD + VALID_FOOD_AMY;
    public static final String FAMILY_DESC_AMY = " " + PREFIX_FAMILY + VALID_FAMILY_CONDITION_AMY;
    public static final String FAMILY_DESC_BOB = " " + PREFIX_FAMILY + VALID_FAMILY_CONDITION_BOB;
    public static final String HOBBY_DESC_AMY = " " + PREFIX_HOBBY + VALID_HOBBY_AMY;
    public static final String HOBBY_DESC_BOB = " " + PREFIX_HOBBY + VALID_HOBBY_BOB;
    public static final String TAG_DESC_DIABETES = " " + PREFIX_TAG + VALID_TAG_DIABETES;
    public static final String TAG_DESC_DEPRESSION = " " + PREFIX_TAG + VALID_TAG_DEPRESSION;
    public static final String IMPORTANT_DATE_DESC_DATE = " " + PREFIX_NAME + VALID_IMPORTANT_DATE_NAME + " "
        + PREFIX_DATETIME + VALID_IMPORTANT_DATE;
    public static final String IMPORTANT_DATE_DESC_DATETIME = " " + PREFIX_NAME + VALID_IMPORTANT_DATE_NAME + " "
        + PREFIX_DATETIME + VALID_IMPORTANT_DATETIME;

    public static final String INVALID_ID_DESC = " " + PREFIX_PID + "10 a"; // only digits are allowed in ID
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James& Lee Kuang"; // '&' not allowed in names
    // '&' not allowed in preferred name
    public static final String INVALID_PREFERRED_NAME_DESC = " " + PREFIX_PRENAME + "James&";
    public static final String INVALID_FOOD_DESC = " " + PREFIX_FOOD; // empty string not allowed for food description
    // empty string not allowed for family condition
    public static final String INVALID_FAMILY_DESC = " " + PREFIX_FAMILY;
    public static final String INVALID_HOBBY_DESC = " " + PREFIX_HOBBY; // empty string not allowed for hobby
    // '*' and spacing not allowed in tags
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hearing loss*";
    public static final String INVALID_IMPORTANT_DATE_DESC = " " + PREFIX_NAME + VALID_IMPORTANT_DATE_NAME
        + PREFIX_DATETIME + "Invalid";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditPatientDescriptor DESC_AMY;
    public static final EditPatientDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPatientDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPreferredName(VALID_PREFERRED_NAME_AMY).withFoodPreference(VALID_FOOD_AMY)
                .withFamilyCondition(VALID_FAMILY_CONDITION_AMY).withHobby(VALID_HOBBY_AMY)
                .withTags(VALID_TAG_DIABETES).build();
        DESC_BOB = new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPreferredName(VALID_PREFERRED_NAME_BOB).withFoodPreference(VALID_FOOD_BOB)
                .withFamilyCondition(VALID_FAMILY_CONDITION_BOB).withHobby(VALID_HOBBY_BOB)
                .withTags(VALID_TAG_DEPRESSION, VALID_TAG_DIABETES).build();
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
     * - the address book, filtered patient list and selected patient in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Patient> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPatientList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPatientList());
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
