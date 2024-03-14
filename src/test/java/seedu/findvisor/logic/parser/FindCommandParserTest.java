package seedu.findvisor.logic.parser;

import static seedu.findvisor.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.findvisor.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.findvisor.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.findvisor.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.findvisor.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.findvisor.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.findvisor.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.findvisor.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.findvisor.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.findvisor.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.findvisor.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.findvisor.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.findvisor.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.findvisor.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.findvisor.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.findvisor.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.findvisor.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.findvisor.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.findvisor.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.findvisor.logic.Messages;
import seedu.findvisor.logic.commands.FindCommand;
import seedu.findvisor.model.person.Email;
import seedu.findvisor.model.person.EmailEqualsKeywordPredicate;
import seedu.findvisor.model.person.Name;
import seedu.findvisor.model.person.NameContainsKeywordPredicate;
import seedu.findvisor.model.person.Phone;
import seedu.findvisor.model.person.PhoneEqualsKeywordPredicate;
import seedu.findvisor.model.tag.Tag;
import seedu.findvisor.model.tag.TagsContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "  ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }


    @Test
    public void parse_validArgs_returnsFindCommand() {
        // parse name
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordPredicate("Bob Choo"));
        assertParseSuccess(parser, NAME_DESC_BOB, expectedFindCommand);

        // multiple whitespaces before and after name keyword
        String paddedKeyword = "\n \t " + NAME_DESC_BOB + "\n \t";
        assertParseSuccess(parser, paddedKeyword, expectedFindCommand);

        // parse email
        expectedFindCommand = new FindCommand(new EmailEqualsKeywordPredicate("amy@example.com"));
        assertParseSuccess(parser, EMAIL_DESC_AMY, expectedFindCommand);

        // parse phone
        expectedFindCommand = new FindCommand(new PhoneEqualsKeywordPredicate("22222222"));
        assertParseSuccess(parser, PHONE_DESC_BOB, expectedFindCommand);

        // parse multiple tags
        expectedFindCommand = new FindCommand(new TagsContainsKeywordsPredicate(
                Arrays.asList(new String[]{"friend", "husband"})));
        assertParseSuccess(parser, TAG_DESC_FRIEND + TAG_DESC_HUSBAND, expectedFindCommand);

    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        assertParseFailure(parser, NAME_DESC_BOB + NAME_DESC_AMY,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        assertParseFailure(parser, EMAIL_DESC_AMY + EMAIL_DESC_BOB,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        assertParseFailure(parser, PHONE_DESC_AMY + PHONE_DESC_BOB,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));
    }

    @Test
    public void parse_invalidArgs_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);
    }

}
