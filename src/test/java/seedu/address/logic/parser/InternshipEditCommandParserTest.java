package seedu.address.logic.parser;

import static seedu.address.logic.InternshipMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.InternshipCommandTestUtil.APPLICATION_STATUS_DESC_AMY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.COMPANY_NAME_DESC_AMY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.COMPANY_NAME_DESC_BOB;
import static seedu.address.logic.commands.InternshipCommandTestUtil.CONTACT_EMAIL_DESC_AMY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.CONTACT_NAME_DESC_AMY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.CONTACT_NAME_DESC_BOB;
import static seedu.address.logic.commands.InternshipCommandTestUtil.CONTACT_NUMBER_DESC_AMY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.CONTACT_NUMBER_DESC_BOB;
import static seedu.address.logic.commands.InternshipCommandTestUtil.DESCRIPTION_DESC_AMY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.INVALID_COMPANY_NAME_DESC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.INVALID_LOCATION_DESC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.INVALID_ROLE_DESC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.INVALID_CONTACT_NAME_DESC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.INVALID_CONTACT_EMAIL_DESC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.INVALID_CONTACT_NUMBER_DESC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.INVALID_APPLICATION_STATUS_DESC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.LOCATION_DESC_AMY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.ROLE_DESC_AMY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_APPLICATION_STATUS_AMY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_COMPANY_NAME_AMY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_CONTACT_EMAIL_AMY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_CONTACT_NAME_AMY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_CONTACT_NUMBER_AMY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_CONTACT_NUMBER_BOB;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_DESCRIPTION_AMY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_LOCATION_AMY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_ROLE_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.InternshipCommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.InternshipCommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.InternshipTypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.address.testutil.InternshipTypicalIndexes.INDEX_SECOND_INTERNSHIP;
import static seedu.address.testutil.InternshipTypicalIndexes.INDEX_THIRD_INTERNSHIP;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.InternshipMessages;
import seedu.address.logic.commands.InternshipEditCommand;
import seedu.address.logic.commands.InternshipEditCommand.EditInternshipDescriptor;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.ContactEmail;
import seedu.address.model.internship.ContactName;
import seedu.address.model.internship.ContactNumber;
import seedu.address.model.internship.Description;
import seedu.address.model.internship.Location;
import seedu.address.model.internship.Role;
import seedu.address.testutil.EditInternshipDescriptorBuilder;

public class InternshipEditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, InternshipEditCommand.MESSAGE_USAGE);

    private InternshipEditCommandParser parser = new InternshipEditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_COMPANY_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", InternshipEditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + COMPANY_NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + COMPANY_NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_COMPANY_NAME_DESC, CompanyName.MESSAGE_CONSTRAINTS);
        // invalid name
        assertParseFailure(parser, "1" + INVALID_LOCATION_DESC, Location.MESSAGE_CONSTRAINTS);
        //invalid location
        assertParseFailure(parser, "1" + INVALID_DESCRIPTION_DESC, Description.MESSAGE_CONSTRAINTS);
        //invalid description
        assertParseFailure(parser, "1" + INVALID_ROLE_DESC, Role.MESSAGE_CONSTRAINTS);
        //invalid role
        assertParseFailure(parser, "1" + INVALID_CONTACT_NAME_DESC, ContactName.MESSAGE_CONSTRAINTS);
        //invalid contact name
        assertParseFailure(parser, "1" + INVALID_CONTACT_EMAIL_DESC, ContactEmail.MESSAGE_CONSTRAINTS);
        //invalid contact email
        assertParseFailure(parser, "1" + INVALID_CONTACT_NUMBER_DESC, ContactNumber.MESSAGE_CONSTRAINTS);
        //invalid contact number
        assertParseFailure(parser, "1" + INVALID_APPLICATION_STATUS_DESC,
                ApplicationStatus.MESSAGE_CONSTRAINTS);
        //invalid application status

        // invalid company name followed by valid email
        assertParseFailure(parser, "1" + INVALID_COMPANY_NAME_DESC + CONTACT_EMAIL_DESC_AMY,
                CompanyName.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_COMPANY_NAME_DESC + INVALID_CONTACT_EMAIL_DESC
                + VALID_CONTACT_NUMBER_AMY + VALID_COMPANY_NAME_AMY, CompanyName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_INTERNSHIP;

        String userInput = targetIndex.getOneBased() + COMPANY_NAME_DESC_AMY + LOCATION_DESC_AMY
                + DESCRIPTION_DESC_AMY + ROLE_DESC_AMY + CONTACT_NAME_DESC_AMY + CONTACT_EMAIL_DESC_AMY
                + CONTACT_NUMBER_DESC_AMY + APPLICATION_STATUS_DESC_AMY;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withCompanyName(VALID_COMPANY_NAME_AMY).withLocation(VALID_LOCATION_AMY)
                .withDescription(VALID_DESCRIPTION_AMY).withRole(VALID_ROLE_AMY)
                .withContactName(VALID_CONTACT_NAME_AMY).withContactEmail(VALID_CONTACT_EMAIL_AMY)
                .withContactNumber(VALID_CONTACT_NUMBER_AMY).withApplicationStatus(VALID_APPLICATION_STATUS_AMY)
                .build();
        
        InternshipEditCommand expectedCommand = new InternshipEditCommand(targetIndex, descriptor);
        
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + CONTACT_NUMBER_DESC_BOB + CONTACT_EMAIL_DESC_AMY;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withContactNumber(VALID_CONTACT_NUMBER_BOB)
                .withContactEmail(VALID_CONTACT_EMAIL_AMY).build();
        InternshipEditCommand expectedCommand = new InternshipEditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + COMPANY_NAME_DESC_AMY;
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withCompanyName(VALID_COMPANY_NAME_AMY).build();
        InternshipEditCommand expectedCommand = new InternshipEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // location
        userInput = targetIndex.getOneBased() + LOCATION_DESC_AMY;
        descriptor = new EditInternshipDescriptorBuilder().withLocation(VALID_LOCATION_AMY).build();
        expectedCommand = new InternshipEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
        userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_AMY;
        descriptor = new EditInternshipDescriptorBuilder().withDescription(VALID_DESCRIPTION_AMY).build();
        expectedCommand = new InternshipEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // role
        userInput = targetIndex.getOneBased() + ROLE_DESC_AMY;
        descriptor = new EditInternshipDescriptorBuilder().withRole(VALID_ROLE_AMY).build();
        expectedCommand = new InternshipEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // contact name
        userInput = targetIndex.getOneBased() + CONTACT_NAME_DESC_AMY;
        descriptor = new EditInternshipDescriptorBuilder().withContactName(VALID_CONTACT_NAME_AMY).build();
        expectedCommand = new InternshipEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // contact email
        userInput = targetIndex.getOneBased() + CONTACT_EMAIL_DESC_AMY;
        descriptor = new EditInternshipDescriptorBuilder().withContactEmail(VALID_CONTACT_EMAIL_AMY).build();
        expectedCommand = new InternshipEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // contact number
        userInput = targetIndex.getOneBased() + CONTACT_NUMBER_DESC_AMY;
        descriptor = new EditInternshipDescriptorBuilder().withContactNumber(VALID_CONTACT_NUMBER_AMY).build();
        expectedCommand = new InternshipEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // application status
        userInput = targetIndex.getOneBased() + APPLICATION_STATUS_DESC_AMY;
        descriptor = new EditInternshipDescriptorBuilder().withApplicationStatus(VALID_APPLICATION_STATUS_AMY).build();
        expectedCommand = new InternshipEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()

        // valid followed by invalid
        Index targetIndex = INDEX_FIRST_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + INVALID_CONTACT_NUMBER_DESC + CONTACT_NUMBER_DESC_BOB;

        assertParseFailure(parser, userInput, InternshipMessages
                .getErrorMessageForDuplicatePrefixes(PREFIX_CONTACT_NUMBER));

        // invalid followed by valid
        userInput = targetIndex.getOneBased() + CONTACT_NUMBER_DESC_BOB + INVALID_CONTACT_NUMBER_DESC;

        assertParseFailure(parser, userInput, InternshipMessages
                .getErrorMessageForDuplicatePrefixes(PREFIX_CONTACT_NUMBER));

        // mulltiple valid fields repeated
        userInput = targetIndex.getOneBased() + COMPANY_NAME_DESC_AMY + CONTACT_NUMBER_DESC_BOB
                + CONTACT_NUMBER_DESC_BOB + COMPANY_NAME_DESC_BOB + CONTACT_EMAIL_DESC_AMY + CONTACT_NUMBER_DESC_BOB
                + CONTACT_EMAIL_DESC_AMY + CONTACT_NAME_DESC_BOB;

        assertParseFailure(parser, userInput, InternshipMessages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY,
                        PREFIX_CONTACT_NUMBER, PREFIX_CONTACT_EMAIL));

        // multiple invalid values
        userInput = targetIndex.getOneBased() + INVALID_CONTACT_NUMBER_DESC + INVALID_ROLE_DESC
                + INVALID_CONTACT_EMAIL_DESC + INVALID_CONTACT_NUMBER_DESC + INVALID_ROLE_DESC
                + INVALID_CONTACT_EMAIL_DESC;

        assertParseFailure(parser, userInput, InternshipMessages
                .getErrorMessageForDuplicatePrefixes(PREFIX_CONTACT_NUMBER, PREFIX_ROLE, PREFIX_CONTACT_EMAIL));
    }
}
