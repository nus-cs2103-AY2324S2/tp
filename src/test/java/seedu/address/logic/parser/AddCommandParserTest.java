package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GROUP_DESC_LAB;
import static seedu.address.logic.commands.CommandTestUtil.GROUP_DESC_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GROUP_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MAJOR_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TELEGRAM_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_YEAR_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MAJOR_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.MAJOR_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_OUTSPOKEN;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_SHY;
import static seedu.address.logic.commands.CommandTestUtil.TELEGRAM_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TELEGRAM_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAJOR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_OUTSPOKEN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_YEAR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.YEAR_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.YEAR_DESC_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.model.group.Group;
import seedu.address.model.person.Email;
import seedu.address.model.person.Major;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withRemark(VALID_REMARK_OUTSPOKEN)
                .withGroups(VALID_GROUP_TUTORIAL)
                        .build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + YEAR_DESC_BOB + MAJOR_DESC_BOB + TELEGRAM_DESC_BOB
                                + REMARK_DESC_OUTSPOKEN + GROUP_DESC_TUTORIAL,
                new AddCommand(expectedPerson));


        // multiple groups - all accepted
        Person expectedPersonMultipleGroups = new PersonBuilder(BOB).withRemark(VALID_REMARK_OUTSPOKEN).withGroups(
                VALID_GROUP_TUTORIAL, VALID_GROUP_LAB)
                .build();
        assertParseSuccess(parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + YEAR_DESC_BOB + MAJOR_DESC_BOB
                        + TELEGRAM_DESC_BOB + REMARK_DESC_OUTSPOKEN + GROUP_DESC_LAB + GROUP_DESC_TUTORIAL,
                new AddCommand(expectedPersonMultipleGroups));
    }

    @Test
    public void parse_repeatedNonGroupValue_failure() {
        String validExpectedPersonString = NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + YEAR_DESC_BOB
                + TELEGRAM_DESC_BOB + MAJOR_DESC_BOB + REMARK_DESC_OUTSPOKEN + GROUP_DESC_TUTORIAL;

        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple phones
        assertParseFailure(parser, PHONE_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple emails
        assertParseFailure(parser, EMAIL_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple years
        assertParseFailure(parser, YEAR_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_YEAR));

        // multiple majors
        assertParseFailure(parser, MAJOR_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_MAJOR));

        // multiple telegrams
        assertParseFailure(parser, TELEGRAM_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TELEGRAM));

        // multiple remarks
        assertParseFailure(parser, REMARK_DESC_SHY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_REMARK));


        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedPersonString + PHONE_DESC_AMY + EMAIL_DESC_AMY + NAME_DESC_AMY + MAJOR_DESC_AMY
                        + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_MAJOR, PREFIX_EMAIL, PREFIX_YEAR,
                        PREFIX_TELEGRAM, PREFIX_REMARK, PREFIX_PHONE));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, INVALID_EMAIL_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid year
        assertParseFailure(parser, INVALID_YEAR_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_YEAR));

        // invalid telegram
        assertParseFailure(parser, INVALID_TELEGRAM_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TELEGRAM));

        // invalid phone
        assertParseFailure(parser, INVALID_PHONE_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid major
        assertParseFailure(parser, INVALID_MAJOR_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_MAJOR));


        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedPersonString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, validExpectedPersonString + INVALID_EMAIL_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid year
        assertParseFailure(parser, validExpectedPersonString + INVALID_YEAR_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_YEAR));

        // invalid telegram
        assertParseFailure(parser, validExpectedPersonString + INVALID_TELEGRAM_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TELEGRAM));

        // invalid phone
        assertParseFailure(parser, validExpectedPersonString + INVALID_PHONE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid major
        assertParseFailure(parser, validExpectedPersonString + INVALID_MAJOR_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_MAJOR));

    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // no remark and zero groups
        Person expectedPerson = new PersonBuilder(AMY).withRemark("").withGroups().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + YEAR_DESC_AMY
                + TELEGRAM_DESC_AMY + MAJOR_DESC_AMY, new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + YEAR_DESC_BOB + TELEGRAM_DESC_BOB + MAJOR_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB
                + YEAR_DESC_BOB + TELEGRAM_DESC_BOB + MAJOR_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB
                + YEAR_DESC_BOB + TELEGRAM_DESC_BOB + MAJOR_DESC_BOB, expectedMessage);

        // missing year prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB
                + VALID_YEAR_BOB + TELEGRAM_DESC_BOB + MAJOR_DESC_BOB, expectedMessage);

        // missing telegram prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB
                + YEAR_DESC_BOB + VALID_TELEGRAM_BOB + MAJOR_DESC_BOB, expectedMessage);

        // missing major prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + YEAR_DESC_BOB + VALID_TELEGRAM_BOB + VALID_MAJOR_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB
                + VALID_YEAR_BOB + VALID_TELEGRAM_BOB + VALID_MAJOR_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + MAJOR_DESC_BOB
                + YEAR_DESC_BOB + TELEGRAM_DESC_BOB + GROUP_DESC_LAB + GROUP_DESC_TUTORIAL, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + MAJOR_DESC_BOB
                + YEAR_DESC_BOB + TELEGRAM_DESC_BOB + GROUP_DESC_LAB + GROUP_DESC_TUTORIAL, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + MAJOR_DESC_BOB
                + YEAR_DESC_BOB + TELEGRAM_DESC_BOB + GROUP_DESC_LAB + GROUP_DESC_TUTORIAL, Email.MESSAGE_CONSTRAINTS);

        // invalid year
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + MAJOR_DESC_BOB
                + INVALID_YEAR_DESC + TELEGRAM_DESC_BOB + GROUP_DESC_LAB + GROUP_DESC_TUTORIAL,
                Email.MESSAGE_CONSTRAINTS);

        // invalid telegram
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + MAJOR_DESC_BOB
                + YEAR_DESC_BOB + INVALID_TELEGRAM_DESC + GROUP_DESC_LAB + GROUP_DESC_TUTORIAL,
                Email.MESSAGE_CONSTRAINTS);

        // invalid major
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_MAJOR_DESC
                + YEAR_DESC_BOB + GROUP_DESC_LAB + GROUP_DESC_TUTORIAL, Major.MESSAGE_CONSTRAINTS);

        // invalid group
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + MAJOR_DESC_BOB
                + YEAR_DESC_BOB + TELEGRAM_DESC_BOB + INVALID_GROUP_DESC + VALID_GROUP_TUTORIAL,
                        Group.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_MAJOR_DESC
                + YEAR_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + MAJOR_DESC_BOB + TELEGRAM_DESC_BOB + GROUP_DESC_LAB + GROUP_DESC_TUTORIAL,

                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
