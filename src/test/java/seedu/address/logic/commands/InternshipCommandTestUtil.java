package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.InternshipData;
import seedu.address.model.InternshipModel;
import seedu.address.model.internship.CompanyNameContainsKeywordsPredicate;
import seedu.address.model.internship.Internship;
import seedu.address.testutil.EditInternshipDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class InternshipCommandTestUtil {
    public static final String VALID_COMPANY_NAME_AMY = "Microsoft";
    public static final String VALID_COMPANY_NAME_BOB = "Facebook";
    public static final String VALID_LOCATION_AMY = "remote";
    public static final String VALID_LOCATION_BOB = "Overseas";
    public static final String VALID_DESCRIPTION_AMY = "Develop new microsoft web applications";
    public static final String VALID_DESCRIPTION_BOB = "Product Management Intern";
    public static final String VALID_ROLE_AMY = "Application Engineer";
    public static final String VALID_ROLE_BOB = "Intern";
    public static final String VALID_CONTACT_NAME_AMY = "John Doe";
    public static final String VALID_CONTACT_NAME_BOB = "Jane Doe";
    public static final String VALID_CONTACT_EMAIL_AMY = "johndoe@gmail.com";
    public static final String VALID_CONTACT_EMAIL_BOB = "janedoe@gmail.com";
    public static final String VALID_CONTACT_NUMBER_AMY = "91001274";
    public static final String VALID_CONTACT_NUMBER_BOB = "12345678";
    public static final String VALID_APPLICATION_STATUS_AMY = "Pending";
    public static final String VALID_APPLICATION_STATUS_BOB = "Pending";
    public static final String VALID_REMARK_AMY = "3 Leetcode questions";
    public static final String VALID_REMARK_BOB = "Has a behavioural interview";

    public static final String COMPANY_NAME_DESC_AMY = " " + PREFIX_COMPANY + VALID_COMPANY_NAME_AMY;
    public static final String COMPANY_NAME_DESC_BOB = " " + PREFIX_COMPANY + VALID_COMPANY_NAME_BOB;
    public static final String LOCATION_DESC_AMY = " " + PREFIX_LOCATION + VALID_LOCATION_AMY;
    public static final String LOCATION_DESC_BOB = " " + PREFIX_LOCATION + VALID_LOCATION_BOB;
    public static final String DESCRIPTION_DESC_AMY = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_AMY;
    public static final String DESCRIPTION_DESC_BOB = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_BOB;
    public static final String ROLE_DESC_AMY = " " + PREFIX_ROLE + VALID_ROLE_AMY;
    public static final String ROLE_DESC_BOB = " " + PREFIX_ROLE + VALID_ROLE_BOB;
    public static final String CONTACT_NAME_DESC_AMY = " " + PREFIX_CONTACT_NAME + VALID_CONTACT_NAME_AMY;
    public static final String CONTACT_NAME_DESC_BOB = " " + PREFIX_CONTACT_NAME + VALID_CONTACT_NAME_BOB;
    public static final String CONTACT_EMAIL_DESC_AMY = " " + PREFIX_CONTACT_EMAIL + VALID_CONTACT_EMAIL_AMY;
    public static final String CONTACT_EMAIL_DESC_BOB = " " + PREFIX_CONTACT_EMAIL + VALID_CONTACT_EMAIL_BOB;
    public static final String CONTACT_NUMBER_DESC_AMY = " " + PREFIX_CONTACT_NUMBER + VALID_CONTACT_NUMBER_AMY;
    public static final String CONTACT_NUMBER_DESC_BOB = " " + PREFIX_CONTACT_NUMBER + VALID_CONTACT_NUMBER_BOB;
    public static final String APPLICATION_STATUS_DESC_AMY = " " + PREFIX_STATUS + VALID_APPLICATION_STATUS_AMY;
    public static final String APPLICATION_STATUS_DESC_BOB = " " + PREFIX_STATUS + VALID_APPLICATION_STATUS_BOB;

    public static final String INVALID_COMPANY_NAME_DESC = " " + PREFIX_COMPANY + "Google&";
    // '&' not allowed in names
    public static final String INVALID_LOCATION_DESC = " " + PREFIX_LOCATION + "Mountain View";
    // Locations have to be either local, overseas, or remote
    public static final String INVALID_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION;
    // empty string not allowed for descriptions
    public static final String INVALID_ROLE_DESC = " " + PREFIX_ROLE + " "; // empty string not allowed for roles
    public static final String INVALID_CONTACT_NAME_DESC = " " + PREFIX_CONTACT_NAME + "John&";
    // '&' not allowed in names
    public static final String INVALID_CONTACT_EMAIL_DESC = " " + PREFIX_CONTACT_EMAIL + "johndoe!gmail";
    // missing '@' symbol
    public static final String INVALID_CONTACT_NUMBER_DESC = " " + PREFIX_CONTACT_NUMBER + "98765432a";
    // 'a' not allowed in contact numbers
    public static final String INVALID_APPLICATION_STATUS_DESC = " " + PREFIX_STATUS + "WAITING";
    // Application statuses have to be either to_apply, pending, rejected, accepted, or ongoing

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditInternshipDescriptorBuilder DESC_AMY;
    public static final EditInternshipDescriptorBuilder DESC_BOB;

    static {
        DESC_AMY = new EditInternshipDescriptorBuilder().withCompanyName(VALID_COMPANY_NAME_AMY)
                .withLocation(VALID_LOCATION_AMY).withDescription(VALID_DESCRIPTION_AMY).withRole(VALID_ROLE_AMY)
                .withContactName(VALID_CONTACT_NAME_AMY).withContactEmail(VALID_CONTACT_EMAIL_AMY)
                .withContactNumber(VALID_CONTACT_NUMBER_AMY).withApplicationStatus(VALID_APPLICATION_STATUS_AMY);
        DESC_BOB = new EditInternshipDescriptorBuilder().withCompanyName(VALID_COMPANY_NAME_BOB)
                .withLocation(VALID_LOCATION_BOB).withDescription(VALID_DESCRIPTION_BOB).withRole(VALID_ROLE_BOB)
                .withContactName(VALID_CONTACT_NAME_BOB).withContactEmail(VALID_CONTACT_EMAIL_BOB)
                .withContactNumber(VALID_CONTACT_NUMBER_BOB).withApplicationStatus(VALID_APPLICATION_STATUS_BOB);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(InternshipCommand command, InternshipModel actualModel,
                                            CommandResult expectedCommandResult, InternshipModel expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(InternshipCommand, InternshipModel,
     * CommandResult, InternshipModel)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(InternshipCommand command, InternshipModel actualModel,
                                            String expectedMessage, InternshipModel expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the internship data, filtered internship list and selected internship in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(InternshipCommand command, InternshipModel actualModel,
                                            String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        InternshipData expectedInternshipData = new InternshipData(actualModel.getInternshipData());
        List<Internship> expectedFilteredList = new ArrayList<>(actualModel.getFilteredInternshipList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedInternshipData, actualModel.getInternshipData());
        assertEquals(expectedFilteredList, actualModel.getFilteredInternshipList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the internship at the given {@code targetIndex} in the
     * {@code model}'s internship data.
     */
    public static void showInternshipAtIndex(InternshipModel model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredInternshipList().size());

        Internship internship = model.getFilteredInternshipList().get(targetIndex.getZeroBased());
        final String[] splitName = internship.getCompanyName().companyName.split("\\s+");
        model.updateFilteredInternshipList(new CompanyNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredInternshipList().size());
    }

}
