package scm.address.logic.parser;

import static scm.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static scm.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static scm.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import scm.address.logic.commands.FindCommand;
import scm.address.model.person.AddressContainsKeywordsPredicate;
import scm.address.model.person.NameContainsKeywordsPredicate;
import scm.address.model.person.TagsContainKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")),
                                new AddressContainsKeywordsPredicate(Arrays.asList("Street", "Rd")),
                                new TagsContainKeywordsPredicate(Arrays.asList("CS2103T", "classmate")));
        assertParseSuccess(parser, " n/Alice Bob a/Street Rd t/CS2103T classmate", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser,
                " \n n/Alice \n Bob a/Street \t Rd \t t/CS2103T \t classmate", expectedFindCommand);
    }

}
