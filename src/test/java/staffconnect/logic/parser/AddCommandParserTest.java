package staffconnect.logic.parser;

import static staffconnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static staffconnect.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static staffconnect.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static staffconnect.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static staffconnect.logic.commands.CommandTestUtil.INVALID_MODULE_DESC;
import static staffconnect.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static staffconnect.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static staffconnect.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static staffconnect.logic.commands.CommandTestUtil.MODULE_DESC_AMY;
import static staffconnect.logic.commands.CommandTestUtil.MODULE_DESC_BOB;
import static staffconnect.logic.commands.CommandTestUtil.INVALID_VENUE_DESC;
import static staffconnect.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static staffconnect.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static staffconnect.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static staffconnect.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static staffconnect.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static staffconnect.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static staffconnect.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static staffconnect.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static staffconnect.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static staffconnect.logic.commands.CommandTestUtil.VALID_MODULE_BOB;
import static staffconnect.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static staffconnect.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static staffconnect.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static staffconnect.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static staffconnect.logic.commands.CommandTestUtil.VALID_VENUE_BOB;
import static staffconnect.logic.commands.CommandTestUtil.VENUE_DESC_AMY;
import static staffconnect.logic.commands.CommandTestUtil.VENUE_DESC_BOB;
import static staffconnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static staffconnect.logic.parser.CliSyntax.PREFIX_MODULE;
import static staffconnect.logic.parser.CliSyntax.PREFIX_NAME;
import static staffconnect.logic.parser.CliSyntax.PREFIX_PHONE;
import static staffconnect.logic.parser.CliSyntax.PREFIX_VENUE;
import static staffconnect.logic.parser.CommandParserTestUtil.assertParseFailure;
import static staffconnect.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static staffconnect.testutil.TypicalPersons.AMY;
import static staffconnect.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import staffconnect.logic.Messages;
import staffconnect.logic.commands.AddCommand;
import staffconnect.model.person.Email;
import staffconnect.model.person.Module;
import staffconnect.model.person.Name;
import staffconnect.model.person.Person;
import staffconnect.model.person.Phone;
import staffconnect.model.person.Venue;
import staffconnect.model.tag.Tag;
import staffconnect.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + VENUE_DESC_BOB + MODULE_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VENUE_DESC_BOB
                        + MODULE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedPersonString = NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + VENUE_DESC_BOB + MODULE_DESC_BOB + TAG_DESC_FRIEND;

        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple phones
        assertParseFailure(parser, PHONE_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple emails
        assertParseFailure(parser, EMAIL_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple venues
        assertParseFailure(parser, VENUE_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_VENUE));

        // multiple modules
        assertParseFailure(parser, MODULE_DESC_AMY + validExpectedPersonString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_MODULE));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedPersonString + PHONE_DESC_AMY + EMAIL_DESC_AMY + NAME_DESC_AMY
                        + VENUE_DESC_AMY + MODULE_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_VENUE,
                        PREFIX_EMAIL, PREFIX_PHONE, PREFIX_MODULE));

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

        // invalid venue
        assertParseFailure(parser, INVALID_VENUE_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_VENUE));

        // invalid module
        assertParseFailure(parser, INVALID_MODULE_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_MODULE));

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

        // invalid venue
        assertParseFailure(parser, validExpectedPersonString + INVALID_VENUE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_VENUE));

        // invalid module
        assertParseFailure(parser, validExpectedPersonString + INVALID_MODULE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_MODULE));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + VENUE_DESC_AMY + MODULE_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + VENUE_DESC_BOB + MODULE_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB
                + VENUE_DESC_BOB + MODULE_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB
                + VENUE_DESC_BOB + MODULE_DESC_BOB,
                expectedMessage);

        // missing venue prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + VALID_VENUE_BOB + MODULE_DESC_BOB,
                expectedMessage);

        // missing module prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + VALID_VENUE_BOB + VALID_MODULE_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB
                + VALID_VENUE_BOB + VALID_MODULE_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + VENUE_DESC_BOB + MODULE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + VENUE_DESC_BOB + MODULE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + VENUE_DESC_BOB + MODULE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Email.MESSAGE_CONSTRAINTS);

        // invalid venue
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_VENUE_DESC + MODULE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
            Venue.MESSAGE_CONSTRAINTS);

        // invalid module
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + VENUE_DESC_BOB + INVALID_MODULE_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Module.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + VENUE_DESC_BOB + MODULE_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_FRIEND,
                Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_VENUE_DESC + MODULE_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + VENUE_DESC_BOB + MODULE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
