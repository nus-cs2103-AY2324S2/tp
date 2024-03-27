package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.COURSE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.COURSE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.COURSE_DESC_CHARLIE;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_CHARLIE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COURSE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ROLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_CHARLIE;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_CHARLIE;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_CHARLIE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_CLASSMATE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CLASSMATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.CHARLIE;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Course;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Disabled
    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(
                parser,
                PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ROLE_DESC_BOB + ADDRESS_DESC_BOB + COURSE_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_CLASSMATE)
                .build();
        assertParseSuccess(
                parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROLE_DESC_BOB
                + ADDRESS_DESC_BOB + COURSE_DESC_BOB + TAG_DESC_CLASSMATE + TAG_DESC_FRIEND,
                new AddCommand(expectedPersonMultipleTags));
    }

    @Disabled
    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedPersonString = NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND;

        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple phones
        assertParseFailure(parser, PHONE_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple emails
        assertParseFailure(parser, EMAIL_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple roles
        assertParseFailure(parser, ROLE_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE));

        // multiple addresses
        assertParseFailure(parser, ADDRESS_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // multiple courses
        assertParseFailure(parser, COURSE_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COURSE));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedPersonString + PHONE_DESC_AMY + EMAIL_DESC_AMY + NAME_DESC_AMY + ADDRESS_DESC_AMY
                        + validExpectedPersonString,
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

        // invalid role
        assertParseFailure(parser, INVALID_ROLE_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE));

        // invalid address
        assertParseFailure(parser, ADDRESS_DESC_EMPTY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // invalid course
        assertParseFailure(parser, INVALID_COURSE_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COURSE));

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

        // invalid role
        assertParseFailure(parser, validExpectedPersonString + INVALID_ROLE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE));

        // invalid address
        assertParseFailure(parser, validExpectedPersonString + ADDRESS_DESC_EMPTY,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // invalid course
        assertParseFailure(parser, validExpectedPersonString + INVALID_COURSE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COURSE));
    }

    @Disabled
    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(
                parser,
                VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ROLE_DESC_BOB + ADDRESS_DESC_BOB + COURSE_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(
                parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB
                + ROLE_DESC_BOB + ADDRESS_DESC_BOB + COURSE_DESC_BOB,
                expectedMessage);

        // missing role prefix
        assertParseFailure(
                parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + VALID_ROLE_BOB + ADDRESS_DESC_BOB + COURSE_DESC_BOB,
                expectedMessage);

        // missing course prefix
        assertParseFailure(
                parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ROLE_DESC_BOB + ADDRESS_DESC_BOB + VALID_COURSE_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(
                parser,
                VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB
                + VALID_ROLE_BOB + VALID_ADDRESS_BOB + VALID_COURSE_BOB,
                expectedMessage);
    }

    @Disabled
    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(
                parser,
                INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROLE_DESC_BOB
                + ADDRESS_DESC_BOB + COURSE_DESC_BOB + TAG_DESC_CLASSMATE + TAG_DESC_FRIEND,
                Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(
                parser,
                NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ROLE_DESC_BOB
                + ADDRESS_DESC_BOB + COURSE_DESC_BOB + TAG_DESC_CLASSMATE + TAG_DESC_FRIEND,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(
                parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ROLE_DESC_BOB
                + ADDRESS_DESC_BOB + COURSE_DESC_BOB + TAG_DESC_CLASSMATE + TAG_DESC_FRIEND,
                Email.MESSAGE_CONSTRAINTS);

        // invalid role
        assertParseFailure(
                parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ROLE_DESC
                + ADDRESS_DESC_BOB + COURSE_DESC_BOB + TAG_DESC_CLASSMATE + TAG_DESC_FRIEND,
                Role.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(
                parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROLE_DESC_BOB
                + ADDRESS_DESC_EMPTY + COURSE_DESC_BOB + TAG_DESC_CLASSMATE + TAG_DESC_FRIEND,
                Address.MESSAGE_CONSTRAINTS);

        // invalid course
        assertParseFailure(
                parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROLE_DESC_BOB
                + ADDRESS_DESC_BOB + INVALID_COURSE_DESC + TAG_DESC_CLASSMATE + TAG_DESC_FRIEND,
                Course.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(
                parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND,
                Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_EMPTY,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_CLASSMATE + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_address_failure() {
        // role is professor, address is not provided
        assertParseFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ROLE_DESC_AMY
                + COURSE_DESC_AMY, Address.MESSAGE_CONSTRAINTS_PROFESSOR);

        // role is professor, empty address provided
        assertParseFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ROLE_DESC_AMY
                + ADDRESS_DESC_EMPTY + COURSE_DESC_AMY, Address.MESSAGE_CONSTRAINTS);

        // role is not professor, empty address provided
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROLE_DESC_BOB
                + ADDRESS_DESC_EMPTY + COURSE_DESC_BOB, Address.MESSAGE_CONSTRAINTS);
    }

    @Disabled
    @Test
    public void parse_address_success() {
        // role is professor, address is provided
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ROLE_DESC_AMY
                        + ADDRESS_DESC_AMY + COURSE_DESC_AMY + TAG_DESC_FRIEND,
                new AddCommand(new PersonBuilder(AMY).build()));

        // role is not professor, address is provided
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROLE_DESC_BOB
                        + ADDRESS_DESC_BOB + COURSE_DESC_BOB + TAG_DESC_FRIEND + TAG_DESC_CLASSMATE,
                new AddCommand(new PersonBuilder(BOB).build()));

        // role is not professor, address is not provided
        assertParseSuccess(parser, NAME_DESC_CHARLIE + PHONE_DESC_CHARLIE + EMAIL_DESC_CHARLIE
                        + ROLE_DESC_CHARLIE + COURSE_DESC_CHARLIE + TAG_DESC_FRIEND,
                new AddCommand(new PersonBuilder(CHARLIE).build()));
    }
}
