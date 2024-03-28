package staffconnect.logic.parser;

import static staffconnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static staffconnect.logic.commands.CommandTestUtil.AVAILABILITY_DESC_MON;
import static staffconnect.logic.commands.CommandTestUtil.AVAILABILITY_DESC_THUR;
import static staffconnect.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static staffconnect.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static staffconnect.logic.commands.CommandTestUtil.FACULTY_DESC_AMY;
import static staffconnect.logic.commands.CommandTestUtil.FACULTY_DESC_BOB;
import static staffconnect.logic.commands.CommandTestUtil.INVALID_AVAILABILITY_DESC;
import static staffconnect.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static staffconnect.logic.commands.CommandTestUtil.INVALID_FACULTY_DESC;
import static staffconnect.logic.commands.CommandTestUtil.INVALID_MODULE_DESC;
import static staffconnect.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static staffconnect.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static staffconnect.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static staffconnect.logic.commands.CommandTestUtil.INVALID_VENUE_DESC;
import static staffconnect.logic.commands.CommandTestUtil.MODULE_DESC_AMY;
import static staffconnect.logic.commands.CommandTestUtil.MODULE_DESC_BOB;
import static staffconnect.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static staffconnect.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static staffconnect.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static staffconnect.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static staffconnect.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static staffconnect.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static staffconnect.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static staffconnect.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static staffconnect.logic.commands.CommandTestUtil.VALID_AVAILABILITY_MON;
import static staffconnect.logic.commands.CommandTestUtil.VALID_AVAILABILITY_THUR;
import static staffconnect.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static staffconnect.logic.commands.CommandTestUtil.VALID_FACULTY_BOB;
import static staffconnect.logic.commands.CommandTestUtil.VALID_MODULE_BOB;
import static staffconnect.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static staffconnect.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static staffconnect.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static staffconnect.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static staffconnect.logic.commands.CommandTestUtil.VALID_VENUE_BOB;
import static staffconnect.logic.commands.CommandTestUtil.VENUE_DESC_AMY;
import static staffconnect.logic.commands.CommandTestUtil.VENUE_DESC_BOB;
import static staffconnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static staffconnect.logic.parser.CliSyntax.PREFIX_FACULTY;
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
import staffconnect.model.availability.Availability;
import staffconnect.model.person.Email;
import staffconnect.model.person.Faculty;
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
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + MODULE_DESC_BOB + FACULTY_DESC_BOB + VENUE_DESC_BOB
                + TAG_DESC_FRIEND + AVAILABILITY_DESC_MON + AVAILABILITY_DESC_THUR,
                new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();

        assertParseSuccess(parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + MODULE_DESC_BOB
                        + FACULTY_DESC_BOB + VENUE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND
                        + AVAILABILITY_DESC_MON + AVAILABILITY_DESC_THUR,
                new AddCommand(expectedPersonMultipleTags));

        // multiple availabilities - all accepted
        Person expectedPersonMultipleAvailabilities = new PersonBuilder(BOB)
                .withAvailabilities(VALID_AVAILABILITY_MON, VALID_AVAILABILITY_THUR).build();

        assertParseSuccess(parser,
            NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + MODULE_DESC_BOB
                    + FACULTY_DESC_BOB + VENUE_DESC_BOB
                    + TAG_DESC_HUSBAND + TAG_DESC_FRIEND
                    + AVAILABILITY_DESC_MON + AVAILABILITY_DESC_THUR,
                new AddCommand(expectedPersonMultipleAvailabilities));
    }

    @Test
    public void parse_repeatedNonTagAvailabilityValue_failure() {
        String validExpectedPersonString = NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + MODULE_DESC_BOB
                + FACULTY_DESC_BOB + VENUE_DESC_BOB + TAG_DESC_FRIEND;

        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple phones
        assertParseFailure(parser, PHONE_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple emails
        assertParseFailure(parser, EMAIL_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple modules
        assertParseFailure(parser, MODULE_DESC_AMY + validExpectedPersonString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_MODULE));

        // multiple faculties
        assertParseFailure(parser, FACULTY_DESC_AMY + validExpectedPersonString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_FACULTY));

        // multiple venues
        assertParseFailure(parser, VENUE_DESC_AMY + validExpectedPersonString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_VENUE));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedPersonString + PHONE_DESC_AMY + EMAIL_DESC_AMY + FACULTY_DESC_AMY
                        + NAME_DESC_AMY + VENUE_DESC_AMY + MODULE_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_MODULE, PREFIX_FACULTY,
                        PREFIX_VENUE, PREFIX_EMAIL, PREFIX_PHONE));

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

        // invalid module
        assertParseFailure(parser, INVALID_MODULE_DESC + validExpectedPersonString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_MODULE));

        // invalid faculty
        assertParseFailure(parser, INVALID_FACULTY_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_FACULTY));

        // invalid venue
        assertParseFailure(parser, INVALID_VENUE_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_VENUE));

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

        // invalid module
        assertParseFailure(parser, validExpectedPersonString + INVALID_MODULE_DESC,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_MODULE));

        // invalid faculty
        assertParseFailure(parser, validExpectedPersonString + INVALID_FACULTY_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_FACULTY));

        // invalid venue
        assertParseFailure(parser, validExpectedPersonString + INVALID_VENUE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_VENUE));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags and availabilities
        Person expectedPerson = new PersonBuilder(AMY).withTags().withAvailabilities().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + MODULE_DESC_AMY
                        + FACULTY_DESC_AMY + VENUE_DESC_AMY, new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + MODULE_DESC_BOB
                + FACULTY_DESC_BOB + VENUE_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + MODULE_DESC_BOB
                + FACULTY_DESC_BOB + VENUE_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + MODULE_DESC_BOB
                + FACULTY_DESC_BOB + VENUE_DESC_BOB,
                expectedMessage);

        // missing module prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_MODULE_BOB
                + FACULTY_DESC_BOB + VALID_VENUE_BOB,
            expectedMessage);

        // missing faculty prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + MODULE_DESC_BOB
                + VALID_FACULTY_BOB + VENUE_DESC_BOB,
                expectedMessage);

        // missing venue prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + MODULE_DESC_BOB
                + FACULTY_DESC_BOB + VALID_VENUE_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_MODULE_BOB
                + VALID_FACULTY_BOB + VALID_VENUE_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + MODULE_DESC_BOB
                + FACULTY_DESC_BOB + VENUE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + AVAILABILITY_DESC_MON,
                Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + MODULE_DESC_BOB
                + FACULTY_DESC_BOB + VENUE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + AVAILABILITY_DESC_MON,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + MODULE_DESC_BOB
                + FACULTY_DESC_BOB + VENUE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + AVAILABILITY_DESC_MON,
                Email.MESSAGE_CONSTRAINTS);

        // invalid module
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_MODULE_DESC
                + FACULTY_DESC_BOB + VENUE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + AVAILABILITY_DESC_MON,
            Module.MESSAGE_CONSTRAINTS);

        // invalid faculty
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + MODULE_DESC_BOB
                + INVALID_FACULTY_DESC + VENUE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Faculty.MESSAGE_CONSTRAINTS);

        // invalid venue
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + MODULE_DESC_BOB
                + FACULTY_DESC_BOB + INVALID_VENUE_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + AVAILABILITY_DESC_MON,
            Venue.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + MODULE_DESC_BOB
                + FACULTY_DESC_BOB + VENUE_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_FRIEND + AVAILABILITY_DESC_MON,
                Tag.MESSAGE_CONSTRAINTS);

        // invalid availability
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + MODULE_DESC_BOB
                + FACULTY_DESC_BOB + VENUE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + INVALID_AVAILABILITY_DESC,
                Availability.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + MODULE_DESC_BOB
                + FACULTY_DESC_BOB + INVALID_VENUE_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + MODULE_DESC_BOB + FACULTY_DESC_BOB + VENUE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND
                + AVAILABILITY_DESC_MON,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
