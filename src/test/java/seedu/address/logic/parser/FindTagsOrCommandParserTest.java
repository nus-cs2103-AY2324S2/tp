package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindTagsOrCommand;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagsOrFoundPredicate;
import seedu.address.testutil.TagBuilder;

public class FindTagsOrCommandParserTest {

    private FindTagsOrCommandParser parser = new FindTagsOrCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTagsOrCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "!#@#$", Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validArgs_returnsFindTagsOrCommand() {
        List<String> tagList = List.of("car", "health");
        FindTagsOrCommand expectedCommand =
                new FindTagsOrCommand(new TagsOrFoundPredicate(TagBuilder.build(tagList)));
        assertParseSuccess(parser, "car health", expectedCommand);

        assertParseSuccess(parser, " \n car \n \t health  \t", expectedCommand);
    }

}
