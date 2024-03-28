package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DOB_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DOB_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DOB_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NRIC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
//import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DOB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddPatientCommand;
import seedu.address.model.person.DoB;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Phone;

public class AddPatientCommandParserTest {
    private AddPatientCommandParser parser = new AddPatientCommandParser();

    //    @Test
    //    public void parse_allFieldsPresent_success() {
    //        Patient expectedPerson = new PatientBuilder(BOB).build();
    //
    //        // whitespace only preamble
    //        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NRIC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB
    //                + PHONE_DESC_BOB, new AddPatientCommand(expectedPerson));
    //    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedPersonString = NRIC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB
                + PHONE_DESC_BOB;

        // multiple names
        assertParseFailure(parser, NRIC_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NRIC));

        // multiple phones
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple emails
        assertParseFailure(parser, DOB_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DOB));

        // multiple addresses
        assertParseFailure(parser, PHONE_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedPersonString + NRIC_DESC_AMY + NAME_DESC_AMY + DOB_DESC_AMY + PHONE_DESC_AMY
                        + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NRIC, PREFIX_NAME,
                        PREFIX_DOB, PREFIX_PHONE));

        // invalid value followed by valid value

        // invalid nric
        assertParseFailure(parser, INVALID_NRIC_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NRIC));

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid dob
        assertParseFailure(parser, INVALID_DOB_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DOB));

        // invalid phone
        assertParseFailure(parser, INVALID_PHONE_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // valid value followed by invalid value

        // invalid nric
        assertParseFailure(parser, validExpectedPersonString + INVALID_NRIC_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NRIC));

        // invalid name
        assertParseFailure(parser, validExpectedPersonString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid dob
        assertParseFailure(parser, validExpectedPersonString + INVALID_DOB_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DOB));

        // invalid phone
        assertParseFailure(parser, validExpectedPersonString + INVALID_PHONE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPatientCommand.MESSAGE_USAGE);

        // missing nric prefix
        assertParseFailure(parser, VALID_NRIC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB,
                expectedMessage);

        // missing name prefix
        assertParseFailure(parser, NRIC_DESC_BOB + VALID_NAME_BOB + DOB_DESC_BOB + PHONE_DESC_BOB,
                expectedMessage);

        // missing dob prefix
        assertParseFailure(parser, NRIC_DESC_BOB + VALID_NAME_BOB + VALID_DOB_BOB + PHONE_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NRIC_DESC_BOB + VALID_NAME_BOB + VALID_DOB_BOB + VALID_PHONE_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NRIC_BOB + VALID_NAME_BOB + VALID_DOB_BOB + VALID_PHONE_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid nric
        assertParseFailure(parser, INVALID_NRIC_DESC + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB,
                Nric.MESSAGE_CONSTRAINTS);

        // invalid name
        assertParseFailure(parser, NRIC_DESC_BOB + INVALID_NAME_DESC + DOB_DESC_BOB + PHONE_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // invalid dob
        assertParseFailure(parser, NRIC_DESC_BOB + NAME_DESC_BOB + INVALID_DOB_DESC + PHONE_DESC_BOB,
                DoB.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NRIC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + INVALID_PHONE_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NRIC_DESC + NAME_DESC_BOB + DOB_DESC_BOB + INVALID_PHONE_DESC,
                Nric.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NRIC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB
                        + PHONE_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPatientCommand.MESSAGE_USAGE));
    }
}
