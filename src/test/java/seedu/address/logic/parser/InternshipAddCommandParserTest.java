package seedu.address.logic.parser;

import static seedu.address.logic.InternshipMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.InternshipCommandTestUtil.APPLICATION_STATUS_DESC_AMY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.APPLICATION_STATUS_DESC_BOB;
import static seedu.address.logic.commands.InternshipCommandTestUtil.COMPANY_NAME_DESC_AMY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.COMPANY_NAME_DESC_BOB;
import static seedu.address.logic.commands.InternshipCommandTestUtil.CONTACT_EMAIL_DESC_AMY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.CONTACT_EMAIL_DESC_BOB;
import static seedu.address.logic.commands.InternshipCommandTestUtil.CONTACT_NAME_DESC_AMY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.CONTACT_NAME_DESC_BOB;
import static seedu.address.logic.commands.InternshipCommandTestUtil.CONTACT_NUMBER_DESC_AMY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.CONTACT_NUMBER_DESC_BOB;
import static seedu.address.logic.commands.InternshipCommandTestUtil.DESCRIPTION_DESC_AMY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.DESCRIPTION_DESC_BOB;
import static seedu.address.logic.commands.InternshipCommandTestUtil.INVALID_APPLICATION_STATUS_DESC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.INVALID_COMPANY_NAME_DESC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.INVALID_CONTACT_EMAIL_DESC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.INVALID_CONTACT_NAME_DESC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.INVALID_CONTACT_NUMBER_DESC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.INVALID_LOCATION_DESC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.INVALID_ROLE_DESC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.LOCATION_DESC_AMY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.LOCATION_DESC_BOB;
import static seedu.address.logic.commands.InternshipCommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.InternshipCommandTestUtil.ROLE_DESC_AMY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.ROLE_DESC_BOB;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_APPLICATION_STATUS_BOB;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_COMPANY_NAME_BOB;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.InternshipCommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.InternshipCommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalInternships.AMY;
import static seedu.address.testutil.TypicalInternships.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.InternshipMessages;
import seedu.address.logic.commands.InternshipAddCommand;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.ContactEmail;
import seedu.address.model.internship.ContactName;
import seedu.address.model.internship.ContactNumber;
import seedu.address.model.internship.Description;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Location;
import seedu.address.model.internship.Role;
import seedu.address.testutil.InternshipBuilder;

public class InternshipAddCommandParserTest {
    private InternshipAddCommandParser parser = new InternshipAddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Internship expectedInternship = new InternshipBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + COMPANY_NAME_DESC_BOB + CONTACT_NAME_DESC_BOB
                + CONTACT_EMAIL_DESC_BOB + CONTACT_NUMBER_DESC_BOB + LOCATION_DESC_BOB
                + APPLICATION_STATUS_DESC_BOB + DESCRIPTION_DESC_BOB + ROLE_DESC_BOB,
                new InternshipAddCommand(expectedInternship));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedInternshipString = COMPANY_NAME_DESC_BOB + INVALID_CONTACT_NAME_DESC
                + CONTACT_EMAIL_DESC_BOB + CONTACT_NUMBER_DESC_BOB + LOCATION_DESC_BOB
                + APPLICATION_STATUS_DESC_BOB + DESCRIPTION_DESC_BOB + ROLE_DESC_BOB;

        // multiple company names
        assertParseFailure(parser, COMPANY_NAME_DESC_AMY + validExpectedInternshipString,
                InternshipMessages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY));

        // multiple contact names
        assertParseFailure(parser, CONTACT_NAME_DESC_AMY + validExpectedInternshipString,
                InternshipMessages.getErrorMessageForDuplicatePrefixes(PREFIX_CONTACT_NAME));

        // multiple contact emails
        assertParseFailure(parser, CONTACT_EMAIL_DESC_AMY + validExpectedInternshipString,
                InternshipMessages.getErrorMessageForDuplicatePrefixes(PREFIX_CONTACT_EMAIL));

        // multiple contact numbers
        assertParseFailure(parser, CONTACT_NUMBER_DESC_AMY + validExpectedInternshipString,
                InternshipMessages.getErrorMessageForDuplicatePrefixes(PREFIX_CONTACT_NUMBER));

        // multiple locations
        assertParseFailure(parser, LOCATION_DESC_AMY + validExpectedInternshipString,
                InternshipMessages.getErrorMessageForDuplicatePrefixes(PREFIX_LOCATION));

        // multiple application statuses
        assertParseFailure(parser, APPLICATION_STATUS_DESC_AMY + validExpectedInternshipString,
                InternshipMessages.getErrorMessageForDuplicatePrefixes(PREFIX_STATUS));

        // multiple descriptions
        assertParseFailure(parser, DESCRIPTION_DESC_AMY + validExpectedInternshipString,
                InternshipMessages.getErrorMessageForDuplicatePrefixes(PREFIX_DESCRIPTION));

        // multiple roles
        assertParseFailure(parser, ROLE_DESC_AMY + validExpectedInternshipString,
                InternshipMessages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE));


        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedInternshipString + COMPANY_NAME_DESC_BOB + INVALID_CONTACT_NAME_DESC
                        + CONTACT_EMAIL_DESC_BOB + CONTACT_NUMBER_DESC_BOB + LOCATION_DESC_BOB
                        + APPLICATION_STATUS_DESC_BOB + DESCRIPTION_DESC_BOB + ROLE_DESC_BOB
                        + validExpectedInternshipString,
                InternshipMessages.getErrorMessageForDuplicatePrefixes(
                        PREFIX_COMPANY, PREFIX_CONTACT_NAME, PREFIX_CONTACT_EMAIL, PREFIX_CONTACT_NUMBER,
                        PREFIX_LOCATION, PREFIX_STATUS, PREFIX_DESCRIPTION, PREFIX_ROLE));

        // invalid value followed by valid value

        // invalid company name
        assertParseFailure(parser, INVALID_COMPANY_NAME_DESC + validExpectedInternshipString,
                InternshipMessages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY));

        // invalid contact name
        assertParseFailure(parser, INVALID_CONTACT_NAME_DESC + validExpectedInternshipString,
                InternshipMessages.getErrorMessageForDuplicatePrefixes(PREFIX_CONTACT_NAME));

        // invalid contact email
        assertParseFailure(parser, INVALID_CONTACT_EMAIL_DESC + validExpectedInternshipString,
                InternshipMessages.getErrorMessageForDuplicatePrefixes(PREFIX_CONTACT_EMAIL));

        // invalid contact number
        assertParseFailure(parser, INVALID_CONTACT_NUMBER_DESC + validExpectedInternshipString,
                InternshipMessages.getErrorMessageForDuplicatePrefixes(PREFIX_CONTACT_NUMBER));

        // invalid location
        assertParseFailure(parser, INVALID_LOCATION_DESC + validExpectedInternshipString,
                InternshipMessages.getErrorMessageForDuplicatePrefixes(PREFIX_LOCATION));

        // invalid application status
        assertParseFailure(parser, INVALID_APPLICATION_STATUS_DESC + validExpectedInternshipString,
                InternshipMessages.getErrorMessageForDuplicatePrefixes(PREFIX_STATUS));

        // invalid description
        assertParseFailure(parser, INVALID_DESCRIPTION_DESC + validExpectedInternshipString,
                InternshipMessages.getErrorMessageForDuplicatePrefixes(PREFIX_DESCRIPTION));

        // invalid role
        assertParseFailure(parser, INVALID_ROLE_DESC + validExpectedInternshipString,
                InternshipMessages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE));


        // valid value followed by invalid value

        // invalid company name
        assertParseFailure(parser, validExpectedInternshipString + INVALID_COMPANY_NAME_DESC,
                InternshipMessages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY));

        // invalid contact name
        assertParseFailure(parser, validExpectedInternshipString + INVALID_CONTACT_NAME_DESC,
                InternshipMessages.getErrorMessageForDuplicatePrefixes(PREFIX_CONTACT_NAME));

        // invalid contact email
        assertParseFailure(parser, validExpectedInternshipString + INVALID_CONTACT_EMAIL_DESC,
                InternshipMessages.getErrorMessageForDuplicatePrefixes(PREFIX_CONTACT_EMAIL));

        // invalid contact number
        assertParseFailure(parser, validExpectedInternshipString + INVALID_CONTACT_NUMBER_DESC,
                InternshipMessages.getErrorMessageForDuplicatePrefixes(PREFIX_CONTACT_NUMBER));

        // invalid location
        assertParseFailure(parser, validExpectedInternshipString + INVALID_LOCATION_DESC,
                InternshipMessages.getErrorMessageForDuplicatePrefixes(PREFIX_LOCATION));

        // invalid application status
        assertParseFailure(parser, validExpectedInternshipString + INVALID_APPLICATION_STATUS_DESC,
                InternshipMessages.getErrorMessageForDuplicatePrefixes(PREFIX_STATUS));

        // invalid description
        assertParseFailure(parser, validExpectedInternshipString + INVALID_DESCRIPTION_DESC,
                InternshipMessages.getErrorMessageForDuplicatePrefixes(PREFIX_DESCRIPTION));

        // invalid role
        assertParseFailure(parser, validExpectedInternshipString + INVALID_ROLE_DESC,
                InternshipMessages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // No optional fields filled in
        Internship expectedInternship = new InternshipBuilder(AMY).build();
        assertParseSuccess(parser, COMPANY_NAME_DESC_AMY + CONTACT_NAME_DESC_AMY + CONTACT_EMAIL_DESC_AMY
                + CONTACT_NUMBER_DESC_AMY + LOCATION_DESC_AMY + APPLICATION_STATUS_DESC_AMY + DESCRIPTION_DESC_AMY
                + ROLE_DESC_AMY, new InternshipAddCommand(expectedInternship));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, InternshipAddCommand.MESSAGE_USAGE);

        // missing company name prefix
        assertParseFailure(parser, VALID_COMPANY_NAME_BOB + DESCRIPTION_DESC_BOB + APPLICATION_STATUS_DESC_BOB,
                expectedMessage);

        // missing description prefix
        assertParseFailure(parser, COMPANY_NAME_DESC_BOB + VALID_DESCRIPTION_BOB + APPLICATION_STATUS_DESC_BOB,
                expectedMessage);

        // missing status prefix
        assertParseFailure(parser, COMPANY_NAME_DESC_BOB + DESCRIPTION_DESC_BOB + VALID_APPLICATION_STATUS_BOB,
                expectedMessage);


        // all prefixes missing
        assertParseFailure(parser, VALID_COMPANY_NAME_BOB + VALID_DESCRIPTION_BOB
                + VALID_APPLICATION_STATUS_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid company name
        assertParseFailure(parser, INVALID_COMPANY_NAME_DESC + CONTACT_NAME_DESC_BOB + CONTACT_EMAIL_DESC_BOB
                + CONTACT_NUMBER_DESC_BOB + LOCATION_DESC_BOB + APPLICATION_STATUS_DESC_BOB + DESCRIPTION_DESC_BOB
                + ROLE_DESC_BOB, CompanyName.MESSAGE_CONSTRAINTS);

        // invalid contact name
        assertParseFailure(parser, COMPANY_NAME_DESC_BOB + INVALID_CONTACT_NAME_DESC + CONTACT_EMAIL_DESC_BOB
                + CONTACT_NUMBER_DESC_BOB + LOCATION_DESC_BOB + APPLICATION_STATUS_DESC_BOB + DESCRIPTION_DESC_BOB
                + ROLE_DESC_BOB, ContactName.MESSAGE_CONSTRAINTS);

        // invalid contact email
        assertParseFailure(parser, COMPANY_NAME_DESC_BOB + CONTACT_NAME_DESC_BOB + INVALID_CONTACT_EMAIL_DESC
                + CONTACT_NUMBER_DESC_BOB + LOCATION_DESC_BOB + APPLICATION_STATUS_DESC_BOB + DESCRIPTION_DESC_BOB
                + ROLE_DESC_BOB, ContactEmail.MESSAGE_CONSTRAINTS);

        // invalid contact number
        assertParseFailure(parser, COMPANY_NAME_DESC_BOB + CONTACT_NAME_DESC_BOB + CONTACT_EMAIL_DESC_BOB
                + INVALID_CONTACT_NUMBER_DESC + LOCATION_DESC_BOB + APPLICATION_STATUS_DESC_BOB + DESCRIPTION_DESC_BOB
                + ROLE_DESC_BOB, ContactNumber.MESSAGE_CONSTRAINTS);

        // invalid location
        assertParseFailure(parser, COMPANY_NAME_DESC_BOB + CONTACT_NAME_DESC_BOB + CONTACT_EMAIL_DESC_BOB
                + CONTACT_NUMBER_DESC_BOB + INVALID_LOCATION_DESC + APPLICATION_STATUS_DESC_BOB + DESCRIPTION_DESC_BOB
                + ROLE_DESC_BOB, Location.MESSAGE_CONSTRAINTS);

        // invalid application status
        assertParseFailure(parser, COMPANY_NAME_DESC_BOB + CONTACT_NAME_DESC_BOB + CONTACT_EMAIL_DESC_BOB
                + CONTACT_NUMBER_DESC_BOB + LOCATION_DESC_BOB + INVALID_APPLICATION_STATUS_DESC + DESCRIPTION_DESC_BOB
                + ROLE_DESC_BOB, ApplicationStatus.MESSAGE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, COMPANY_NAME_DESC_BOB + CONTACT_NAME_DESC_BOB + CONTACT_EMAIL_DESC_BOB
                + CONTACT_NUMBER_DESC_BOB + LOCATION_DESC_BOB + APPLICATION_STATUS_DESC_BOB + INVALID_DESCRIPTION_DESC
                + ROLE_DESC_BOB, Description.MESSAGE_CONSTRAINTS);

        // invalid role
        assertParseFailure(parser, COMPANY_NAME_DESC_BOB + CONTACT_NAME_DESC_BOB + CONTACT_EMAIL_DESC_BOB
                + CONTACT_NUMBER_DESC_BOB + LOCATION_DESC_BOB + APPLICATION_STATUS_DESC_BOB + DESCRIPTION_DESC_BOB
                + INVALID_ROLE_DESC, Role.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, COMPANY_NAME_DESC_BOB + INVALID_CONTACT_NAME_DESC + CONTACT_EMAIL_DESC_BOB
                + CONTACT_NUMBER_DESC_BOB + LOCATION_DESC_BOB + APPLICATION_STATUS_DESC_BOB + DESCRIPTION_DESC_BOB
                + INVALID_ROLE_DESC, ContactName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + COMPANY_NAME_DESC_BOB + INVALID_CONTACT_NAME_DESC
                        + CONTACT_EMAIL_DESC_BOB + CONTACT_NUMBER_DESC_BOB + LOCATION_DESC_BOB
                        + APPLICATION_STATUS_DESC_BOB + DESCRIPTION_DESC_BOB + ROLE_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, InternshipAddCommand.MESSAGE_USAGE));
    }
}
