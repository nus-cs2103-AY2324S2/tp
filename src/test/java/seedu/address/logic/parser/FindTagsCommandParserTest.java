package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindTagsCommand;
import seedu.address.model.patient.TagContainsKeywordsPredicate;

public class FindTagsCommandParserTest {

    private FindTagsCommandParser parser = new FindTagsCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTagsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindTagsCommand() {
        // no leading and trailing whitespaces
        FindTagsCommand expectedFindTagsCommand =
                new FindTagsCommand(new TagContainsKeywordsPredicate(Arrays.asList("depression", "diabetes")));
        assertParseSuccess(parser, "depression diabetes", expectedFindTagsCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n depression \n \t diabetes  \t", expectedFindTagsCommand);
    }
}
