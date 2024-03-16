package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindOrderCommand;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.AddressContainsKeywordsPredicate;
import seedu.address.model.person.Email;
import seedu.address.model.person.MatchingEmailPredicate;
import seedu.address.model.order.MatchingOrderIndexPredicate;
import seedu.address.model.person.MatchingPhonePredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Phone;

public class FindCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE + "\n" +
                    FindOrderCommand.MESSAGE_USAGE);

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, INVALID_NAME_DESC + VALID_NAME_AMY, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_oneFieldSpecifiedOnce_success() {
        // name
        String userInput = NAME_DESC_AMY;
        FindPersonCommand expectedCommand = new FindPersonCommand(
                new NameContainsKeywordsPredicate(Arrays.asList(VALID_NAME_AMY)));
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = PHONE_DESC_AMY;
        expectedCommand = new FindPersonCommand(
                new MatchingPhonePredicate(Arrays.asList(VALID_PHONE_AMY)));
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = EMAIL_DESC_AMY;
        expectedCommand = new FindPersonCommand(
                new MatchingEmailPredicate(Arrays.asList(VALID_EMAIL_AMY)));
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = ADDRESS_DESC_AMY;
        expectedCommand = new FindPersonCommand(
                new AddressContainsKeywordsPredicate(Arrays.asList(VALID_ADDRESS_AMY)));
        assertParseSuccess(parser, userInput, expectedCommand);

        // order
        userInput = " " + PREFIX_ORDER + "13";
        FindOrderCommand expectedOrderCommand = new FindOrderCommand(
                new MatchingOrderIndexPredicate(13));
        assertParseSuccess(parser, userInput, expectedOrderCommand);
    }

    @Test
    public void parse_oneFieldSpecifiedMoreThanOnce_success() {
        // name
        String userInput = NAME_DESC_AMY + NAME_DESC_BOB;
        FindPersonCommand expectedCommand = new FindPersonCommand(
                new NameContainsKeywordsPredicate(Arrays.asList(VALID_NAME_AMY, VALID_NAME_BOB)));
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = PHONE_DESC_AMY + PHONE_DESC_BOB;
        expectedCommand = new FindPersonCommand(
                new MatchingPhonePredicate(Arrays.asList(VALID_PHONE_AMY, VALID_PHONE_BOB)));
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = EMAIL_DESC_AMY + EMAIL_DESC_BOB;
        expectedCommand = new FindPersonCommand(
                new MatchingEmailPredicate(Arrays.asList(VALID_EMAIL_AMY, VALID_EMAIL_BOB)));
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = ADDRESS_DESC_AMY + ADDRESS_DESC_BOB;
        expectedCommand = new FindPersonCommand(
                new AddressContainsKeywordsPredicate(Arrays.asList(VALID_ADDRESS_AMY, VALID_ADDRESS_BOB)));
        assertParseSuccess(parser, userInput, expectedCommand);

        // order TODO
        /* userInput = " " + PREFIX_ORDER + "13" + " " + PREFIX_ORDER + "14";
        FindOrderCommand expectedOrderCommand = new FindOrderCommand(
                new MatchingOrderIndexPredicate(13));
        assertParseSuccess(parser, userInput, expectedOrderCommand);*/
    }
}
