package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALLERGIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNTRY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATEOFBIRTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ImmuniMate;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Nric;
import seedu.address.model.person.NricContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.UpdatePersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    public static final String VALID_NRIC_AMY = "T0182991C";
    public static final String VALID_NRIC_BOB = "S9518552M";
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_DATEOFBIRTH_AMY = "2001-01-01";
    public static final String VALID_DATEOFBIRTH_BOB = "2002-02-02";
    public static final String VALID_SEX_AMY = "F";
    public static final String VALID_SEX_BOB = "M";
    public static final String VALID_STATUS_AMY = "HEALTHY";
    public static final String VALID_STATUS_BOB = "UNWELL";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_COUNTRY_AMY = "Norway";
    public static final String VALID_COUNTRY_BOB = "Indonesia";
    public static final String VALID_ALLERGIES_AMY = "peanuts";
    public static final String VALID_ALLERGIES_BOB = "pollen";
    public static final String VALID_BLOODTYPE_AMY = "B+";
    public static final String VALID_BLOODTYPE_BOB = "B+";
    public static final String VALID_CONDITION_AMY = "diabetes";
    public static final String VALID_CONDITION_BOB = "high blood pressure";
    public static final String VALID_DATEOFADMISSION_AMY = "2023-01-01";
    public static final String VALID_DATEOFADMISSION_BOB = "2023-02-02";
    public static final String VALID_DIAGNOSIS_AMY = "good";
    public static final String VALID_DIAGNOSIS_BOB = "not good";
    public static final String VALID_SYMPTOM_AMY = "runny nose";
    public static final String VALID_SYMPTOM_BOB = "sore throat";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NRIC_DESC_AMY = " " + PREFIX_NRIC + VALID_NRIC_AMY;
    public static final String NRIC_DESC_BOB = " " + PREFIX_NRIC + VALID_NRIC_BOB;
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String DATEOFBIRTH_DESC_AMY = " " + PREFIX_DATEOFBIRTH + VALID_DATEOFBIRTH_AMY;
    public static final String DATEOFBIRTH_DESC_BOB = " " + PREFIX_DATEOFBIRTH + VALID_DATEOFBIRTH_BOB;
    public static final String SEX_DESC_AMY = " " + PREFIX_SEX + VALID_SEX_AMY;
    public static final String SEX_DESC_BOB = " " + PREFIX_SEX + VALID_SEX_BOB;
    public static final String STATUS_DESC_AMY = " " + PREFIX_STATUS + VALID_STATUS_AMY;
    public static final String STATUS_DESC_BOB = " " + PREFIX_STATUS + VALID_STATUS_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String COUNTRY_DESC_AMY = " " + PREFIX_COUNTRY + VALID_COUNTRY_AMY;
    public static final String COUNTRY_DESC_BOB = " " + PREFIX_COUNTRY + VALID_COUNTRY_BOB;
    public static final String ALLERGIES_DESC_AMY = " " + PREFIX_ALLERGIES + VALID_ALLERGIES_AMY;
    public static final String ALLERGIES_DESC_BOB = " " + PREFIX_ALLERGIES + VALID_ALLERGIES_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String NON_EXISTENT_NRIC = "S1234576A";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final UpdateCommand.UpdatePersonDescriptor DESC_AMY;
    public static final UpdateCommand.UpdatePersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new UpdatePersonDescriptorBuilder().withNric(VALID_NRIC_AMY).withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withAddress(VALID_ADDRESS_AMY).withDateOfBirth(VALID_DATEOFBIRTH_AMY)
                .withSex(VALID_SEX_AMY).withStatus(VALID_STATUS_AMY).build();
        DESC_BOB = new UpdatePersonDescriptorBuilder().withNric(VALID_NRIC_BOB).withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withAddress(VALID_ADDRESS_BOB).withDateOfBirth(VALID_DATEOFBIRTH_BOB)
                .withSex(VALID_SEX_BOB).withStatus(VALID_STATUS_BOB).build();
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
        ImmuniMate expectedAddressBook = new ImmuniMate(actualModel.getImmuniMate());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getImmuniMate());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().toString().split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code Nric} in the
     * {@code model}'s system.
     */
    public static void showPersonWithNric(Model model, Nric nric) {
        assertTrue(model.hasPerson(Person.createPersonWithNric(nric)));

        Person person = model.getFilteredPersonList().filtered(p -> p.getNric().equals(nric)).get(0);
        model.updateFilteredPersonList(new NricContainsKeywordsPredicate(nric.toString()));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
