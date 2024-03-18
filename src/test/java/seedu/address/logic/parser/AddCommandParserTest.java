package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalStartups.AMY;
import static seedu.address.testutil.TypicalStartups.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.model.startup.Address;
import seedu.address.model.startup.Email;
import seedu.address.model.startup.FundingStage;
import seedu.address.model.startup.Industry;
import seedu.address.model.startup.Name;
import seedu.address.model.startup.Phone;
import seedu.address.model.startup.Startup;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.StartupBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Startup expectedStartup = new StartupBuilder(BOB).withTags(CommandTestUtil.VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, CommandTestUtil.PREAMBLE_WHITESPACE
                + CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.INDUSTRY_DESC_BOB + CommandTestUtil.FUNDING_DESC_BOB
                + CommandTestUtil.ADDRESS_DESC_BOB
                + CommandTestUtil.TAG_DESC_FRIEND, new AddCommand(expectedStartup));


        // multiple tags - all accepted
        Startup expectedStartupMultipleTags = new StartupBuilder(BOB).withTags(
            CommandTestUtil.VALID_TAG_FRIEND, CommandTestUtil.VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser,
                CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.EMAIL_DESC_BOB + CommandTestUtil.ADDRESS_DESC_BOB
                + CommandTestUtil.INDUSTRY_DESC_BOB + CommandTestUtil.FUNDING_DESC_BOB
                + CommandTestUtil.TAG_DESC_HUSBAND + CommandTestUtil.TAG_DESC_FRIEND,
                new AddCommand(expectedStartupMultipleTags));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedStartupString = CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.INDUSTRY_DESC_BOB + CommandTestUtil.FUNDING_DESC_BOB
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.ADDRESS_DESC_BOB + CommandTestUtil.TAG_DESC_FRIEND;

        // multiple names
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_AMY + validExpectedStartupString,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_NAME));

        // multiple phones
        assertParseFailure(parser, CommandTestUtil.PHONE_DESC_AMY + validExpectedStartupString,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_PHONE));

        // multiple emails
        assertParseFailure(parser, CommandTestUtil.EMAIL_DESC_AMY + validExpectedStartupString,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_EMAIL));

        // multiple addresses
        assertParseFailure(parser, CommandTestUtil.ADDRESS_DESC_AMY + validExpectedStartupString,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_ADDRESS));

        // multiple funding stage
        assertParseFailure(parser, CommandTestUtil.FUNDING_DESC_AMY + validExpectedStartupString,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_FUNDING_STAGE));

        // multiple industry
        assertParseFailure(parser, CommandTestUtil.INDUSTRY_DESC_AMY + validExpectedStartupString,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_INDUSTRY));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedStartupString + CommandTestUtil.PHONE_DESC_AMY
                        + CommandTestUtil.EMAIL_DESC_AMY + CommandTestUtil.NAME_DESC_AMY
                        + CommandTestUtil.ADDRESS_DESC_AMY,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_NAME,
                    CliSyntax.PREFIX_ADDRESS, CliSyntax.PREFIX_EMAIL, CliSyntax.PREFIX_PHONE));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, CommandTestUtil.INVALID_NAME_DESC + validExpectedStartupString,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, CommandTestUtil.INVALID_EMAIL_DESC + validExpectedStartupString,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, CommandTestUtil.INVALID_PHONE_DESC + validExpectedStartupString,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_PHONE));

        // invalid address
        assertParseFailure(parser, CommandTestUtil.INVALID_ADDRESS_DESC + validExpectedStartupString,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_ADDRESS));

        // invalid industry
        assertParseFailure(parser, CommandTestUtil.INVALID_INDUSTRY_DESC + validExpectedStartupString,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_INDUSTRY));

        // invalid funding stage
        assertParseFailure(parser, CommandTestUtil.INVALID_FUNDING_DESC + validExpectedStartupString,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_FUNDING_STAGE));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedStartupString + CommandTestUtil.INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, validExpectedStartupString + CommandTestUtil.INVALID_EMAIL_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, validExpectedStartupString + CommandTestUtil.INVALID_PHONE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_PHONE));

        // invalid address
        assertParseFailure(parser, validExpectedStartupString + CommandTestUtil.INVALID_ADDRESS_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_ADDRESS));

        // invalid industry
        assertParseFailure(parser, validExpectedStartupString + CommandTestUtil.INVALID_INDUSTRY_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_INDUSTRY));

        // invalid funding stage
        assertParseFailure(parser, validExpectedStartupString + CommandTestUtil.INVALID_FUNDING_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_FUNDING_STAGE));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Startup expectedStartup = new StartupBuilder(AMY).withTags().build();
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_AMY
                + CommandTestUtil.INDUSTRY_DESC_AMY
                + CommandTestUtil.FUNDING_DESC_AMY + CommandTestUtil.PHONE_DESC_AMY
                + CommandTestUtil.EMAIL_DESC_AMY + CommandTestUtil.ADDRESS_DESC_AMY,
                new AddCommand(expectedStartup));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, CommandTestUtil.VALID_NAME_BOB
                + CommandTestUtil.FUNDING_DESC_BOB + CommandTestUtil.INDUSTRY_DESC_BOB
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.ADDRESS_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.FUNDING_DESC_BOB + CommandTestUtil.INDUSTRY_DESC_BOB
                + CommandTestUtil.VALID_PHONE_BOB
                + CommandTestUtil.EMAIL_DESC_BOB + CommandTestUtil.ADDRESS_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.FUNDING_DESC_BOB + CommandTestUtil.INDUSTRY_DESC_BOB
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.VALID_EMAIL_BOB
                + CommandTestUtil.ADDRESS_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.FUNDING_DESC_BOB + CommandTestUtil.INDUSTRY_DESC_BOB
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.VALID_ADDRESS_BOB,
                expectedMessage);

        // missing industry prefix
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB
            + CommandTestUtil.FUNDING_DESC_BOB + CommandTestUtil.ADDRESS_DESC_BOB
            + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_BOB
            + CommandTestUtil.VALID_ADDRESS_BOB,
            expectedMessage);

        // missing funding stage prefix
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB
            + CommandTestUtil.ADDRESS_DESC_BOB + CommandTestUtil.INDUSTRY_DESC_BOB
            + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_BOB
            + CommandTestUtil.VALID_ADDRESS_BOB,
            expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, CommandTestUtil.VALID_NAME_BOB
                + CommandTestUtil.FUNDING_DESC_BOB + CommandTestUtil.INDUSTRY_DESC_BOB
                + CommandTestUtil.VALID_PHONE_BOB + CommandTestUtil.VALID_EMAIL_BOB
                + CommandTestUtil.VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, CommandTestUtil.INVALID_NAME_DESC
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.ADDRESS_DESC_BOB
                + CommandTestUtil.FUNDING_DESC_BOB + CommandTestUtil.INDUSTRY_DESC_BOB
                + CommandTestUtil.TAG_DESC_HUSBAND
                + CommandTestUtil.TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.INVALID_PHONE_DESC + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.ADDRESS_DESC_BOB
                + CommandTestUtil.FUNDING_DESC_BOB + CommandTestUtil.INDUSTRY_DESC_BOB
                + CommandTestUtil.TAG_DESC_HUSBAND
                + CommandTestUtil.TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.INVALID_EMAIL_DESC
                + CommandTestUtil.ADDRESS_DESC_BOB
                + CommandTestUtil.FUNDING_DESC_BOB + CommandTestUtil.INDUSTRY_DESC_BOB
                + CommandTestUtil.TAG_DESC_HUSBAND
                + CommandTestUtil.TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.INVALID_ADDRESS_DESC
                + CommandTestUtil.FUNDING_DESC_BOB + CommandTestUtil.INDUSTRY_DESC_BOB
                + CommandTestUtil.TAG_DESC_HUSBAND
                + CommandTestUtil.TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid funding stage
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB
              + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_BOB
              + CommandTestUtil.ADDRESS_DESC_BOB + CommandTestUtil.INVALID_FUNDING_DESC
                + CommandTestUtil.INDUSTRY_DESC_BOB
              + CommandTestUtil.TAG_DESC_HUSBAND
              + CommandTestUtil.TAG_DESC_FRIEND, FundingStage.MESSAGE_CONSTRAINTS);

        // invalid industry
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB
              + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_BOB
              + CommandTestUtil.ADDRESS_DESC_BOB
              + CommandTestUtil.FUNDING_DESC_BOB + CommandTestUtil.INVALID_INDUSTRY_DESC
              + CommandTestUtil.TAG_DESC_HUSBAND
              + CommandTestUtil.TAG_DESC_FRIEND, Industry.MESSAGE_CONSTRAINTS);


        // invalid tag
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.ADDRESS_DESC_BOB
                + CommandTestUtil.FUNDING_DESC_BOB + CommandTestUtil.INDUSTRY_DESC_BOB
                + CommandTestUtil.INVALID_TAG_DESC
                + CommandTestUtil.VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, CommandTestUtil.INVALID_NAME_DESC
                + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.FUNDING_DESC_BOB + CommandTestUtil.INDUSTRY_DESC_BOB
                + CommandTestUtil.EMAIL_DESC_BOB + CommandTestUtil.INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, CommandTestUtil.PREAMBLE_NON_EMPTY
                + CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.FUNDING_DESC_BOB + CommandTestUtil.INDUSTRY_DESC_BOB
                + CommandTestUtil.ADDRESS_DESC_BOB + CommandTestUtil.TAG_DESC_HUSBAND + CommandTestUtil.TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
