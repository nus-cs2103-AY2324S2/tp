package seedu.edulink.logic.parser;

import static seedu.edulink.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.edulink.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.edulink.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.edulink.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.edulink.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.edulink.logic.commands.CommandTestUtil.ID_DESC_AMY;
import static seedu.edulink.logic.commands.CommandTestUtil.ID_DESC_BOB;
import static seedu.edulink.logic.commands.CommandTestUtil.INTAKE_DESC_AMY;
import static seedu.edulink.logic.commands.CommandTestUtil.INTAKE_DESC_BOB;
import static seedu.edulink.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.edulink.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.edulink.logic.commands.CommandTestUtil.INVALID_ID_DESC;
import static seedu.edulink.logic.commands.CommandTestUtil.INVALID_INTAKE_DESC;
import static seedu.edulink.logic.commands.CommandTestUtil.INVALID_MAJOR_DESC;
import static seedu.edulink.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.edulink.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.edulink.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.edulink.logic.commands.CommandTestUtil.MAJOR_DESC_AMY;
import static seedu.edulink.logic.commands.CommandTestUtil.MAJOR_DESC_BOB;
import static seedu.edulink.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.edulink.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.edulink.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.edulink.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.edulink.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.edulink.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.edulink.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.edulink.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.edulink.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.edulink.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.edulink.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.edulink.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.edulink.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.edulink.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.edulink.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.edulink.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.edulink.testutil.TypicalPersons.AMY;
import static seedu.edulink.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.edulink.logic.Messages;
import seedu.edulink.logic.commands.AddCommand;
import seedu.edulink.model.student.Address;
import seedu.edulink.model.student.Email;
import seedu.edulink.model.student.Id;
import seedu.edulink.model.student.Intake;
import seedu.edulink.model.student.Major;
import seedu.edulink.model.student.Name;
import seedu.edulink.model.student.Phone;
import seedu.edulink.model.student.Student;
import seedu.edulink.model.tag.Tag;
import seedu.edulink.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedStudent = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ID_DESC_BOB + NAME_DESC_BOB
            + PHONE_DESC_BOB + EMAIL_DESC_BOB
            + ADDRESS_DESC_BOB + MAJOR_DESC_BOB + INTAKE_DESC_BOB
            + TAG_DESC_FRIEND, new AddCommand(expectedStudent));


        // multiple tags - all accepted
        Student expectedStudentMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
            .build();
        assertParseSuccess(parser, ID_DESC_BOB
                + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + MAJOR_DESC_BOB + INTAKE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
            new AddCommand(expectedStudentMultipleTags));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedPersonString = ID_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
            + ADDRESS_DESC_BOB + MAJOR_DESC_BOB + INTAKE_DESC_BOB + TAG_DESC_FRIEND;

        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedPersonString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));
        // multiple ids
        assertParseFailure(parser, ID_DESC_AMY + validExpectedPersonString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ID));

        // multiple phones
        assertParseFailure(parser, PHONE_DESC_AMY + validExpectedPersonString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple emails
        assertParseFailure(parser, EMAIL_DESC_AMY + validExpectedPersonString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple addresses
        assertParseFailure(parser, ADDRESS_DESC_AMY + validExpectedPersonString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // multiple fields repeated
        assertParseFailure(parser,
            validExpectedPersonString + PHONE_DESC_AMY + EMAIL_DESC_AMY + NAME_DESC_AMY + ADDRESS_DESC_AMY,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_ADDRESS, PREFIX_EMAIL, PREFIX_PHONE));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedPersonString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, INVALID_EMAIL_DESC + validExpectedPersonString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, INVALID_PHONE_DESC + validExpectedPersonString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid address
        assertParseFailure(parser, INVALID_ADDRESS_DESC + validExpectedPersonString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedPersonString + INVALID_NAME_DESC,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, validExpectedPersonString + INVALID_EMAIL_DESC,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, validExpectedPersonString + INVALID_PHONE_DESC,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid address
        assertParseFailure(parser, validExpectedPersonString + INVALID_ADDRESS_DESC,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Student expectedStudent = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, ID_DESC_AMY + MAJOR_DESC_AMY
                + INTAKE_DESC_AMY + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
            new AddCommand(expectedStudent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
            expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
            expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB,
            expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB,
            expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
            expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, ID_DESC_BOB + MAJOR_DESC_BOB + INTAKE_DESC_BOB
            + INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
            + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid id
        assertParseFailure(parser, INVALID_ID_DESC + MAJOR_DESC_BOB + INTAKE_DESC_BOB
            + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
            + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Id.MESSAGE_CONSTRAINTS);
        // invalid Major
        assertParseFailure(parser, ID_DESC_BOB + INVALID_MAJOR_DESC + INTAKE_DESC_BOB
            + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
            + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Major.MESSAGE_CONSTRAINTS);
        // invalid intake
        assertParseFailure(parser, ID_DESC_BOB + MAJOR_DESC_BOB + INVALID_INTAKE_DESC
            + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
            + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Intake.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, ID_DESC_BOB + MAJOR_DESC_BOB + INTAKE_DESC_BOB
            + NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
            + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, ID_DESC_BOB + MAJOR_DESC_BOB + INTAKE_DESC_BOB
            + NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
            + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, ID_DESC_BOB + MAJOR_DESC_BOB + INTAKE_DESC_BOB
            + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
            + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, ID_DESC_BOB + MAJOR_DESC_BOB + INTAKE_DESC_BOB
            + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
            + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, ID_DESC_BOB + MAJOR_DESC_BOB + INTAKE_DESC_BOB
                + INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
            Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser,
            PREAMBLE_NON_EMPTY + ID_DESC_BOB + MAJOR_DESC_BOB
                + INTAKE_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
