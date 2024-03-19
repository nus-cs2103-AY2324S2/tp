package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.FAMILY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.FAMILY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INCOME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INCOME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FAMILY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INCOME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FAMILY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INCOME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FAMILY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INCOME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Family;
import seedu.address.model.person.Income;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

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
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedPersonString = NAME_DESC_BOB + PHONE_DESC_BOB + INCOME_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + FAMILY_DESC_BOB + TAG_DESC_BOB;

        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedPersonString,
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

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedPersonString + PHONE_DESC_AMY + INCOME_DESC_AMY + EMAIL_DESC_AMY
                        + NAME_DESC_AMY + ADDRESS_DESC_AMY + FAMILY_DESC_AMY
                        + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_INCOME, PREFIX_ADDRESS, PREFIX_EMAIL,
                                                             PREFIX_PHONE, PREFIX_FAMILY));

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
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser,
                           VALID_NAME_BOB + PHONE_DESC_BOB + INCOME_DESC_BOB + EMAIL_DESC_BOB
                                   + ADDRESS_DESC_BOB + FAMILY_DESC_BOB + TAG_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser,
                           NAME_DESC_BOB + VALID_PHONE_BOB + INCOME_DESC_BOB
                                   + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + FAMILY_DESC_BOB + TAG_DESC_BOB,
                expectedMessage);

        // missing income prefix
        assertParseFailure(parser,
                           NAME_DESC_BOB + PHONE_DESC_BOB + VALID_INCOME_BOB
                                   + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + FAMILY_DESC_BOB + TAG_DESC_BOB,
                           expectedMessage);

        // missing email prefix
        assertParseFailure(parser,
                           NAME_DESC_BOB + PHONE_DESC_BOB + INCOME_DESC_BOB
                                   + VALID_EMAIL_BOB + ADDRESS_DESC_BOB + FAMILY_DESC_BOB + TAG_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser,
                           NAME_DESC_BOB + PHONE_DESC_BOB + INCOME_DESC_BOB
                                   + EMAIL_DESC_BOB + VALID_ADDRESS_BOB + FAMILY_DESC_BOB + TAG_DESC_BOB,
                expectedMessage);

        // missing family prefix
        assertParseFailure(parser,
                           NAME_DESC_BOB + PHONE_DESC_BOB + INCOME_DESC_BOB
                                   + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + VALID_FAMILY_BOB + TAG_DESC_BOB,
                           expectedMessage);

        // missing tag prefix
        assertParseFailure(parser,
                           VALID_NAME_BOB + VALID_PHONE_BOB + INCOME_DESC_BOB
                                   + VALID_EMAIL_BOB + VALID_ADDRESS_BOB + FAMILY_DESC_BOB,
                           expectedMessage);

        // all prefixes missing
        assertParseFailure(parser,
                           VALID_NAME_BOB + VALID_PHONE_BOB + INCOME_DESC_BOB
                                   + VALID_EMAIL_BOB + VALID_ADDRESS_BOB + FAMILY_DESC_BOB + VALID_TAG_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        System.out.println(INVALID_NAME_DESC + PHONE_DESC_BOB + INCOME_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + FAMILY_DESC_BOB + TAG_DESC_BOB
                + CommandTestUtil.TAG_DESC_BOB);

        // invalid name
        assertParseFailure(parser,
                           INVALID_NAME_DESC + PHONE_DESC_BOB + INCOME_DESC_BOB + EMAIL_DESC_BOB
                                   + ADDRESS_DESC_BOB + FAMILY_DESC_BOB + TAG_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser,
                           NAME_DESC_BOB + INVALID_PHONE_DESC + INCOME_DESC_BOB + EMAIL_DESC_BOB
                                   + ADDRESS_DESC_BOB + FAMILY_DESC_BOB
                                   + TAG_DESC_BOB, Phone.MESSAGE_CONSTRAINTS);

        // invalid income
        assertParseFailure(parser,
                           NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_INCOME_DESC + EMAIL_DESC_BOB
                                   + ADDRESS_DESC_BOB + FAMILY_DESC_BOB
                                   + TAG_DESC_BOB, Income.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser,
                           NAME_DESC_BOB + PHONE_DESC_BOB
                                   + INCOME_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB + FAMILY_DESC_BOB
                                   + CommandTestUtil.TAG_DESC_BOB + TAG_DESC_BOB, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser,
                           NAME_DESC_BOB + PHONE_DESC_BOB + INCOME_DESC_BOB
                                   + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC + FAMILY_DESC_BOB
                                   + CommandTestUtil.TAG_DESC_BOB + TAG_DESC_BOB, Address.MESSAGE_CONSTRAINTS);

        // invalid family
        assertParseFailure(parser,
                           NAME_DESC_BOB + PHONE_DESC_BOB + INCOME_DESC_BOB + EMAIL_DESC_BOB
                                   + ADDRESS_DESC_BOB + INVALID_FAMILY_DESC
                                   + TAG_DESC_BOB, Family.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser,
                           NAME_DESC_BOB + PHONE_DESC_BOB + INCOME_DESC_BOB
                                   + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + FAMILY_DESC_BOB
                                   + INVALID_TAG_DESC + TAG_DESC_BOB, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser,
                           INVALID_NAME_DESC + PHONE_DESC_BOB + INCOME_DESC_BOB
                                   + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC + FAMILY_DESC_BOB + TAG_DESC_BOB,
                           Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser,
                           PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + INCOME_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + FAMILY_DESC_BOB + CommandTestUtil.TAG_DESC_BOB + TAG_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
