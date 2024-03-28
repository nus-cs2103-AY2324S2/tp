package seedu.realodex.logic.parser;

import static seedu.realodex.logic.Messages.MESSAGE_MISSING_PREFIXES;
import static seedu.realodex.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB_PREFIX_CAPS;
import static seedu.realodex.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.EMAIL_DESC_BOB_PREFIX_CAPS;
import static seedu.realodex.logic.commands.CommandTestUtil.FAMILY_DESC_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.FAMILY_DESC_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.FAMILY_DESC_BOB_PREFIX_CAPS;
import static seedu.realodex.logic.commands.CommandTestUtil.INCOME_DESC_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.INCOME_DESC_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.INCOME_DESC_BOB_PREFIX_CAPS;
import static seedu.realodex.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.realodex.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.realodex.logic.commands.CommandTestUtil.INVALID_FAMILY_DESC;
import static seedu.realodex.logic.commands.CommandTestUtil.INVALID_INCOME_DESC;
import static seedu.realodex.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.realodex.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.realodex.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.realodex.logic.commands.CommandTestUtil.NAME_DESC_AMY_CAPS;
import static seedu.realodex.logic.commands.CommandTestUtil.NAME_DESC_AMY_NON_CAPS;
import static seedu.realodex.logic.commands.CommandTestUtil.NAME_DESC_AMY_VARYING_CAPS;
import static seedu.realodex.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.NAME_DESC_BOB_PREFIX_CAPS;
import static seedu.realodex.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.PHONE_DESC_BOB_PREFIX_CAPS;
import static seedu.realodex.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.realodex.logic.commands.CommandTestUtil.REMARK_DESC_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.REMARK_DESC_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.REMARK_DESC_BOB_PREFIX_CAPS;
import static seedu.realodex.logic.commands.CommandTestUtil.TAG_DESC_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.TAG_DESC_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.TAG_DESC_BOB_PREFIX_CAPS;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_FAMILY_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_INCOME_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_NAME_BOB_FIRST_LETTER_CAPS;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_TAG_AMY;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_TAG_BOB;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_FAMILY;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_INCOME;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.realodex.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.realodex.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.realodex.testutil.TypicalPersons.AMY_NAME_CAPS;
import static seedu.realodex.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.realodex.logic.Messages;
import seedu.realodex.logic.commands.AddCommand;
import seedu.realodex.logic.commands.CommandTestUtil;
import seedu.realodex.model.person.Address;
import seedu.realodex.model.person.Email;
import seedu.realodex.model.person.Family;
import seedu.realodex.model.person.Income;
import seedu.realodex.model.person.Name;
import seedu.realodex.model.person.Person;
import seedu.realodex.model.person.Phone;
import seedu.realodex.model.tag.Tag;
import seedu.realodex.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser,
                           PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + INCOME_DESC_BOB + EMAIL_DESC_BOB
                                   + ADDRESS_DESC_BOB + FAMILY_DESC_BOB + TAG_DESC_BOB + REMARK_DESC_BOB,
                new AddCommand(expectedPerson));


        // two valid tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_BOB, VALID_TAG_AMY)
                .build();
        assertParseSuccess(parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + INCOME_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + FAMILY_DESC_BOB
                        + TAG_DESC_BOB + TAG_DESC_AMY + REMARK_DESC_BOB,
                new AddCommand(expectedPersonMultipleTags));

        // three valid tags - all accepted
        expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_BOB, VALID_TAG_AMY)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB
                                   + PHONE_DESC_BOB
                                   + INCOME_DESC_BOB
                                   + EMAIL_DESC_BOB
                                   + ADDRESS_DESC_BOB
                                   + FAMILY_DESC_BOB
                                   + TAG_DESC_BOB
                                   + TAG_DESC_AMY
                                   + TAG_DESC_BOB
                                   + REMARK_DESC_BOB,
                           new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_prefixesCapitalized_success() {
        Person expectedPerson = new PersonBuilder(BOB).build();

        // name prefix caps
        assertParseSuccess(parser,
                           PREAMBLE_WHITESPACE + NAME_DESC_BOB_PREFIX_CAPS
                                   + PHONE_DESC_BOB + INCOME_DESC_BOB + EMAIL_DESC_BOB
                                   + ADDRESS_DESC_BOB + FAMILY_DESC_BOB + TAG_DESC_BOB + REMARK_DESC_BOB,
                           new AddCommand(expectedPerson));

        // phone prefix caps
        assertParseSuccess(parser,
                           NAME_DESC_BOB + PHONE_DESC_BOB_PREFIX_CAPS + INCOME_DESC_BOB + EMAIL_DESC_BOB
                                   + ADDRESS_DESC_BOB + FAMILY_DESC_BOB + TAG_DESC_BOB + REMARK_DESC_BOB,
                           new AddCommand(expectedPerson));

        // income prefix caps
        assertParseSuccess(parser,
                           NAME_DESC_BOB + PHONE_DESC_BOB + INCOME_DESC_BOB_PREFIX_CAPS + EMAIL_DESC_BOB
                                   + ADDRESS_DESC_BOB + FAMILY_DESC_BOB + TAG_DESC_BOB + REMARK_DESC_BOB,
                           new AddCommand(expectedPerson));

        // email prefix caps
        assertParseSuccess(parser,
                           NAME_DESC_BOB + PHONE_DESC_BOB + INCOME_DESC_BOB + EMAIL_DESC_BOB_PREFIX_CAPS
                                   + ADDRESS_DESC_BOB + FAMILY_DESC_BOB + TAG_DESC_BOB + REMARK_DESC_BOB,
                           new AddCommand(expectedPerson));

        // address prefix caps
        assertParseSuccess(parser,
                           NAME_DESC_BOB + PHONE_DESC_BOB + INCOME_DESC_BOB + EMAIL_DESC_BOB
                                   + ADDRESS_DESC_BOB_PREFIX_CAPS + FAMILY_DESC_BOB + TAG_DESC_BOB + REMARK_DESC_BOB,
                           new AddCommand(expectedPerson));

        // family prefix caps
        assertParseSuccess(parser,
                           NAME_DESC_BOB + PHONE_DESC_BOB + INCOME_DESC_BOB + EMAIL_DESC_BOB
                                   + ADDRESS_DESC_BOB + FAMILY_DESC_BOB_PREFIX_CAPS + TAG_DESC_BOB + REMARK_DESC_BOB,
                           new AddCommand(expectedPerson));

        // tag prefix caps
        assertParseSuccess(parser,
                           NAME_DESC_BOB + PHONE_DESC_BOB + INCOME_DESC_BOB + EMAIL_DESC_BOB
                                   + ADDRESS_DESC_BOB + FAMILY_DESC_BOB + TAG_DESC_BOB_PREFIX_CAPS + REMARK_DESC_BOB,
                           new AddCommand(expectedPerson));

        // remark prefix caps
        assertParseSuccess(parser,
                           NAME_DESC_BOB + PHONE_DESC_BOB + INCOME_DESC_BOB + EMAIL_DESC_BOB
                                   + ADDRESS_DESC_BOB + FAMILY_DESC_BOB + TAG_DESC_BOB + REMARK_DESC_BOB_PREFIX_CAPS,
                           new AddCommand(expectedPerson));

        // all fields prefix caps
        assertParseSuccess(parser,
                           NAME_DESC_BOB_PREFIX_CAPS + PHONE_DESC_BOB_PREFIX_CAPS
                                   + INCOME_DESC_BOB_PREFIX_CAPS + EMAIL_DESC_BOB_PREFIX_CAPS
                                   + ADDRESS_DESC_BOB_PREFIX_CAPS + FAMILY_DESC_BOB_PREFIX_CAPS
                                   + TAG_DESC_BOB_PREFIX_CAPS + REMARK_DESC_BOB_PREFIX_CAPS,
                           new AddCommand(expectedPerson));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedPersonString = NAME_DESC_BOB + PHONE_DESC_BOB + INCOME_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + FAMILY_DESC_BOB + TAG_DESC_BOB + REMARK_DESC_BOB;

        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY_CAPS + validExpectedPersonString,
                           Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple phones
        assertParseFailure(parser, PHONE_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        //multiple incomes
        assertParseFailure(parser, INCOME_DESC_AMY + validExpectedPersonString,
                           Messages.getErrorMessageForDuplicatePrefixes(PREFIX_INCOME));

        // multiple emails
        assertParseFailure(parser, EMAIL_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple addresses
        assertParseFailure(parser, ADDRESS_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // multiple family
        assertParseFailure(parser, FAMILY_DESC_AMY + validExpectedPersonString,
                           Messages.getErrorMessageForDuplicatePrefixes(PREFIX_FAMILY));

        //multiple remarks
        assertParseFailure(parser, REMARK_DESC_AMY + validExpectedPersonString,
                           Messages.getErrorMessageForDuplicatePrefixes(PREFIX_REMARK));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedPersonString + PHONE_DESC_AMY + INCOME_DESC_AMY + EMAIL_DESC_AMY
                        + NAME_DESC_AMY_CAPS + ADDRESS_DESC_AMY + FAMILY_DESC_AMY + REMARK_DESC_AMY
                        + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_INCOME, PREFIX_ADDRESS, PREFIX_EMAIL,
                                                             PREFIX_PHONE, PREFIX_FAMILY, PREFIX_REMARK));

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

        // invalid income
        assertParseFailure(parser, INVALID_INCOME_DESC + validExpectedPersonString,
                           Messages.getErrorMessageForDuplicatePrefixes(PREFIX_INCOME));

        // invalid address
        assertParseFailure(parser, INVALID_ADDRESS_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // invalid family
        assertParseFailure(parser, INVALID_FAMILY_DESC + validExpectedPersonString,
                           Messages.getErrorMessageForDuplicatePrefixes(PREFIX_FAMILY));

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

        // invalid income
        assertParseFailure(parser, validExpectedPersonString + INVALID_INCOME_DESC,
                           Messages.getErrorMessageForDuplicatePrefixes(PREFIX_INCOME));

        // invalid address
        assertParseFailure(parser, validExpectedPersonString + INVALID_ADDRESS_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // invalid family
        assertParseFailure(parser, validExpectedPersonString + INVALID_FAMILY_DESC,
                           Messages.getErrorMessageForDuplicatePrefixes(PREFIX_FAMILY));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedCommandFormatMessage = AddCommand.MESSAGE_USAGE;
        // missing name prefix
        assertParseFailure(parser,
                           VALID_NAME_BOB_FIRST_LETTER_CAPS + PHONE_DESC_BOB + INCOME_DESC_BOB + EMAIL_DESC_BOB
                                   + ADDRESS_DESC_BOB + FAMILY_DESC_BOB + TAG_DESC_BOB,
                           MESSAGE_MISSING_PREFIXES + "n/NAME\n" + expectedCommandFormatMessage);

        // missing phone prefix
        assertParseFailure(parser,
                           NAME_DESC_BOB + VALID_PHONE_BOB + INCOME_DESC_BOB
                                   + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + FAMILY_DESC_BOB + TAG_DESC_BOB,
                           MESSAGE_MISSING_PREFIXES + "p/PHONE\n" + expectedCommandFormatMessage);

        // missing income prefix
        assertParseFailure(parser,
                           NAME_DESC_BOB + PHONE_DESC_BOB + VALID_INCOME_BOB
                                   + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + FAMILY_DESC_BOB + TAG_DESC_BOB,
                           MESSAGE_MISSING_PREFIXES + "i/INCOME\n" + expectedCommandFormatMessage);

        // missing email prefix
        assertParseFailure(parser,
                           NAME_DESC_BOB + PHONE_DESC_BOB + INCOME_DESC_BOB
                                   + VALID_EMAIL_BOB + ADDRESS_DESC_BOB + FAMILY_DESC_BOB + TAG_DESC_BOB,
                           MESSAGE_MISSING_PREFIXES + "e/EMAIL\n" + expectedCommandFormatMessage);

        // missing address prefix
        assertParseFailure(parser,
                           NAME_DESC_BOB + PHONE_DESC_BOB + INCOME_DESC_BOB
                                   + EMAIL_DESC_BOB + VALID_ADDRESS_BOB + FAMILY_DESC_BOB + TAG_DESC_BOB,
                           MESSAGE_MISSING_PREFIXES + "a/ADDRESS\n" + expectedCommandFormatMessage);

        // missing family prefix
        assertParseFailure(parser,
                           NAME_DESC_BOB + PHONE_DESC_BOB + INCOME_DESC_BOB
                                   + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + VALID_FAMILY_BOB + TAG_DESC_BOB,
                           MESSAGE_MISSING_PREFIXES + "f/FAMILY\n" + expectedCommandFormatMessage);

        // missing tag prefix
        assertParseFailure(parser,
                           NAME_DESC_BOB + PHONE_DESC_BOB + INCOME_DESC_BOB
                                   + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + FAMILY_DESC_BOB,
                           MESSAGE_MISSING_PREFIXES + "t/TAG\n" + expectedCommandFormatMessage);

        // all prefixes missing
        assertParseFailure(parser,
                           VALID_NAME_BOB_FIRST_LETTER_CAPS + VALID_PHONE_BOB + INCOME_DESC_BOB
                                   + VALID_EMAIL_BOB + VALID_ADDRESS_BOB + FAMILY_DESC_BOB + VALID_TAG_BOB,
                           MESSAGE_MISSING_PREFIXES
                                   + "n/NAME, a/ADDRESS, p/PHONE, e/EMAIL, t/TAG\n" + expectedCommandFormatMessage);
    }

    @Test
    public void parse_optionalFieldMissing_success() {
        Person expectedPerson = new PersonBuilder(BOB).withRemark("").build();
        assertParseSuccess(parser, NAME_DESC_BOB
                + PHONE_DESC_BOB
                + INCOME_DESC_BOB
                + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + FAMILY_DESC_BOB
                + TAG_DESC_BOB, new AddCommand(expectedPerson));

        expectedPerson = new PersonBuilder(AMY_NAME_CAPS).build();
        assertParseSuccess(parser, NAME_DESC_AMY_CAPS
                + PHONE_DESC_AMY
                + INCOME_DESC_AMY
                + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY
                + FAMILY_DESC_AMY
                + TAG_DESC_AMY, new AddCommand(expectedPerson));
    }

    @Test
    public void parse_nonCapitalizedName_success() {
        Person expectedPerson = new PersonBuilder(AMY_NAME_CAPS).build();
        String validExpectedNonCapitalizedAmyString =
                NAME_DESC_AMY_NON_CAPS + PHONE_DESC_AMY + INCOME_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY + FAMILY_DESC_AMY + TAG_DESC_AMY;
        assertParseSuccess(parser, validExpectedNonCapitalizedAmyString, new AddCommand(expectedPerson));

        String validExpectedVaryingCapitalizedAmyString =
                NAME_DESC_AMY_VARYING_CAPS + PHONE_DESC_AMY + INCOME_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY + FAMILY_DESC_AMY + TAG_DESC_AMY;
        assertParseSuccess(parser, validExpectedVaryingCapitalizedAmyString, new AddCommand(expectedPerson));
    }

    @Test
    public void parse_invalidSingleValue_failure() {
        String expectedFailureMessage = "Error parsing %s: ";

        // invalid name
        String expectedFailureMessageFormatted =
                String.format(expectedFailureMessage, "name") + Name.MESSAGE_CONSTRAINTS;
        assertParseFailure(parser,
                           INVALID_NAME_DESC
                                   + PHONE_DESC_BOB
                                   + INCOME_DESC_BOB + EMAIL_DESC_BOB
                                   + ADDRESS_DESC_BOB + FAMILY_DESC_BOB
                                   + TAG_DESC_BOB, expectedFailureMessageFormatted);

        // invalid phone
        expectedFailureMessageFormatted = String.format(expectedFailureMessage, "phone") + Phone.MESSAGE_CONSTRAINTS;
        assertParseFailure(parser,
                           NAME_DESC_BOB + INVALID_PHONE_DESC
                                   + INCOME_DESC_BOB + EMAIL_DESC_BOB
                                   + ADDRESS_DESC_BOB + FAMILY_DESC_BOB
                                   + TAG_DESC_BOB, expectedFailureMessageFormatted);

        // invalid income
        expectedFailureMessageFormatted = String.format(expectedFailureMessage, "income") + Income.MESSAGE_CONSTRAINTS;
        assertParseFailure(parser,
                           NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_INCOME_DESC + EMAIL_DESC_BOB
                                   + ADDRESS_DESC_BOB + FAMILY_DESC_BOB
                                   + TAG_DESC_BOB, expectedFailureMessageFormatted);

        // invalid email
        expectedFailureMessageFormatted = String.format(expectedFailureMessage, "email") + Email.MESSAGE_CONSTRAINTS;
        assertParseFailure(parser,
                           NAME_DESC_BOB + PHONE_DESC_BOB
                                   + INCOME_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB + FAMILY_DESC_BOB
                                   + CommandTestUtil.TAG_DESC_BOB + TAG_DESC_BOB, expectedFailureMessageFormatted);

        // invalid address
        expectedFailureMessageFormatted = String.format(expectedFailureMessage, "address")
                + Address.MESSAGE_CONSTRAINTS;
        assertParseFailure(parser,
                           NAME_DESC_BOB + PHONE_DESC_BOB + INCOME_DESC_BOB
                                   + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC + FAMILY_DESC_BOB
                                   + CommandTestUtil.TAG_DESC_BOB + TAG_DESC_BOB, expectedFailureMessageFormatted);

        // invalid family
        expectedFailureMessageFormatted = String.format(expectedFailureMessage, "family") + Family.MESSAGE_CONSTRAINTS;
        assertParseFailure(parser,
                           NAME_DESC_BOB + PHONE_DESC_BOB + INCOME_DESC_BOB + EMAIL_DESC_BOB
                                   + ADDRESS_DESC_BOB + INVALID_FAMILY_DESC
                                   + TAG_DESC_BOB, expectedFailureMessageFormatted);

        // invalid tag
        expectedFailureMessageFormatted = String.format(expectedFailureMessage, "tags") + Tag.MESSAGE_CONSTRAINTS;
        assertParseFailure(parser,
                           NAME_DESC_BOB + PHONE_DESC_BOB + INCOME_DESC_BOB
                                   + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + FAMILY_DESC_BOB
                                   + INVALID_TAG_DESC + TAG_DESC_BOB, expectedFailureMessageFormatted);

    }

    @Test
    public void parse_invalidMultipleValue_failure() {
        String expectedFailureMessage = "Error parsing %s: ";

        // two invalid values both failures reported
        String expectedFailureMessageFormatted =
                String.format(expectedFailureMessage, "name")
                        + Name.MESSAGE_CONSTRAINTS
                        + "\n"
                        + String.format(expectedFailureMessage, "address")
                        + Address.MESSAGE_CONSTRAINTS;

        assertParseFailure(parser,
                           INVALID_NAME_DESC + PHONE_DESC_BOB + INCOME_DESC_BOB
                                   + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC + FAMILY_DESC_BOB + TAG_DESC_BOB,
                           expectedFailureMessageFormatted);

        // three invalid values both failures reported
        expectedFailureMessageFormatted =
                String.format(expectedFailureMessage, "name")
                        + Name.MESSAGE_CONSTRAINTS
                        + "\n"
                        + String.format(expectedFailureMessage, "phone")
                        + Phone.MESSAGE_CONSTRAINTS
                        + "\n"
                        + String.format(expectedFailureMessage, "address")
                        + Address.MESSAGE_CONSTRAINTS;

        assertParseFailure(parser,
                           INVALID_NAME_DESC + INVALID_PHONE_DESC + INCOME_DESC_BOB
                                   + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC + FAMILY_DESC_BOB + TAG_DESC_BOB,
                           expectedFailureMessageFormatted);
    }
}
