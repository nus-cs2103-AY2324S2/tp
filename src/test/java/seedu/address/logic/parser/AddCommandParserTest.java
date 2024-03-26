package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.BANKDETAILS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.BANKDETAILS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.FIRSTNAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.FIRSTNAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FIRSTNAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LASTNAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PAYRATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SEX_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LASTNAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.LASTNAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PAYRATE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PAYRATE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.SEX_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SEX_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_COOK;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_WAITER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FIRSTNAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LASTNAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PAYRATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEX_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_COOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_WAITER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BANKDETAILS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIRSTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LASTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAYRATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

//import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Name;
import seedu.address.model.person.PayRate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Sex;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;


public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_COOK).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + FIRSTNAME_DESC_BOB + LASTNAME_DESC_BOB + PHONE_DESC_BOB
                + SEX_DESC_BOB + PAYRATE_DESC_BOB + BANKDETAILS_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_COOK,
            new AddCommand(expectedPerson));


        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_COOK, VALID_TAG_WAITER)
            .build();
        assertParseSuccess(parser,
            FIRSTNAME_DESC_BOB + LASTNAME_DESC_BOB + PHONE_DESC_BOB + SEX_DESC_BOB
                + PAYRATE_DESC_BOB + ADDRESS_DESC_BOB
                + BANKDETAILS_DESC_BOB + TAG_DESC_WAITER + TAG_DESC_COOK,
            new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedPersonString = FIRSTNAME_DESC_BOB + LASTNAME_DESC_BOB
            + PHONE_DESC_BOB + SEX_DESC_BOB + PAYRATE_DESC_BOB
            + ADDRESS_DESC_BOB + BANKDETAILS_DESC_BOB + TAG_DESC_COOK;

        // multiple first names
        assertParseFailure(parser, FIRSTNAME_DESC_AMY + validExpectedPersonString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_FIRSTNAME));

        // multiple last names
        assertParseFailure(parser, LASTNAME_DESC_AMY + validExpectedPersonString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_LASTNAME));

        // multiple phones
        assertParseFailure(parser, PHONE_DESC_AMY + validExpectedPersonString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple sexes
        assertParseFailure(parser, SEX_DESC_AMY + validExpectedPersonString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_SEX));

        // multiple employment types
        assertParseFailure(parser, PAYRATE_DESC_AMY + validExpectedPersonString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PAYRATE));

        // multiple addresses
        assertParseFailure(parser, ADDRESS_DESC_AMY + validExpectedPersonString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // multiple bank details
        assertParseFailure(parser, BANKDETAILS_DESC_AMY + validExpectedPersonString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_BANKDETAILS));

        // multiple fields repeated
        assertParseFailure(parser,
            validExpectedPersonString + PHONE_DESC_AMY + FIRSTNAME_DESC_AMY + ADDRESS_DESC_AMY,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_FIRSTNAME,
                PREFIX_ADDRESS, PREFIX_PHONE));

        // invalid value followed by valid value

        // invalid first name
        assertParseFailure(parser, INVALID_FIRSTNAME_DESC + validExpectedPersonString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_FIRSTNAME));

        // invalid last name
        assertParseFailure(parser, INVALID_LASTNAME_DESC + validExpectedPersonString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_LASTNAME));

        // invalid phone
        assertParseFailure(parser, INVALID_PHONE_DESC + validExpectedPersonString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // valid value followed by invalid value

        // invalid phone
        assertParseFailure(parser, validExpectedPersonString + INVALID_PHONE_DESC,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser,
            FIRSTNAME_DESC_AMY + LASTNAME_DESC_AMY + PHONE_DESC_AMY
                + SEX_DESC_AMY + ADDRESS_DESC_AMY + BANKDETAILS_DESC_AMY
                + PAYRATE_DESC_AMY, new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing firstname prefix
        assertParseFailure(parser, VALID_FIRSTNAME_BOB + LASTNAME_DESC_BOB + PHONE_DESC_BOB
                + SEX_DESC_BOB + PAYRATE_DESC_BOB + ADDRESS_DESC_BOB,
            expectedMessage);

        // missing lastname prefix
        assertParseFailure(parser, FIRSTNAME_DESC_BOB + VALID_LASTNAME_BOB + PHONE_DESC_BOB
                + SEX_DESC_BOB + PAYRATE_DESC_BOB + ADDRESS_DESC_BOB,
            expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, FIRSTNAME_DESC_BOB + LASTNAME_DESC_BOB + VALID_PHONE_BOB
                + SEX_DESC_BOB + PAYRATE_DESC_BOB + ADDRESS_DESC_BOB,
            expectedMessage);

        // missing sex prefix
        assertParseFailure(parser, FIRSTNAME_DESC_BOB + LASTNAME_DESC_BOB + PHONE_DESC_BOB
                + VALID_SEX_BOB + PAYRATE_DESC_BOB,
            expectedMessage);

        // missing employment type prefix
        assertParseFailure(parser, FIRSTNAME_DESC_BOB + LASTNAME_DESC_BOB + PHONE_DESC_BOB
                + SEX_DESC_BOB + VALID_PAYRATE_BOB,
            expectedMessage);

        // missing address prefix
        assertParseFailure(parser, FIRSTNAME_DESC_BOB + LASTNAME_DESC_BOB + PHONE_DESC_BOB
                + VALID_ADDRESS_BOB,
            expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_FIRSTNAME_BOB + VALID_LASTNAME_BOB + VALID_PHONE_BOB
                + VALID_SEX_BOB + VALID_PAYRATE_BOB,
            expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_FIRSTNAME_DESC + LASTNAME_DESC_BOB + PHONE_DESC_BOB
            + SEX_DESC_BOB + PAYRATE_DESC_BOB + ADDRESS_DESC_BOB
            + TAG_DESC_WAITER + TAG_DESC_COOK, Name.MESSAGE_CONSTRAINTS);

        // invalid name
        assertParseFailure(parser, FIRSTNAME_DESC_BOB + INVALID_LASTNAME_DESC + PHONE_DESC_BOB
            + SEX_DESC_BOB + PAYRATE_DESC_BOB + ADDRESS_DESC_BOB
            + TAG_DESC_WAITER + TAG_DESC_COOK, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, FIRSTNAME_DESC_BOB + LASTNAME_DESC_BOB + INVALID_PHONE_DESC
            + SEX_DESC_BOB + PAYRATE_DESC_BOB + ADDRESS_DESC_BOB
            + TAG_DESC_WAITER + TAG_DESC_COOK, Phone.MESSAGE_CONSTRAINTS);

        // invalid sex
        assertParseFailure(parser, FIRSTNAME_DESC_BOB + LASTNAME_DESC_BOB + PHONE_DESC_BOB
            + INVALID_SEX_DESC + PAYRATE_DESC_BOB + ADDRESS_DESC_BOB
            + TAG_DESC_WAITER + TAG_DESC_COOK, Sex.MESSAGE_CONSTRAINTS);

        // invalid employment type
        assertParseFailure(parser, FIRSTNAME_DESC_BOB + LASTNAME_DESC_BOB + PHONE_DESC_BOB
            + SEX_DESC_BOB + INVALID_PAYRATE_DESC + ADDRESS_DESC_BOB
            + TAG_DESC_WAITER + TAG_DESC_COOK, PayRate.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, FIRSTNAME_DESC_BOB + LASTNAME_DESC_BOB + PHONE_DESC_BOB
            + SEX_DESC_BOB + PAYRATE_DESC_BOB + ADDRESS_DESC_BOB
            + INVALID_TAG_DESC + VALID_TAG_COOK, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_FIRSTNAME_DESC + LASTNAME_DESC_BOB + PHONE_DESC_BOB
                + SEX_DESC_BOB + INVALID_PAYRATE_DESC,
            Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + FIRSTNAME_DESC_BOB + LASTNAME_DESC_BOB
                + PHONE_DESC_BOB + SEX_DESC_BOB + PAYRATE_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_WAITER + TAG_DESC_COOK,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
