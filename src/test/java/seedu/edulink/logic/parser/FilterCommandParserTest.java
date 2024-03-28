package seedu.edulink.logic.parser;

import static seedu.edulink.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.edulink.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.edulink.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.edulink.logic.commands.FilterCommand;
import seedu.edulink.model.student.TagsContainQueryTagsPredicate;
import seedu.edulink.model.tag.Tag;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyTag_throwsParseException() {
        assertParseFailure(parser, "t/ ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyPrefix_throwsParseException() {
        assertParseFailure(parser, "TA",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // Filter - 1 tag
        Tag testTag1 = new Tag("TA");
        Set<Tag> queryTags = new HashSet<>();
        queryTags.add(testTag1);

        FilterCommand expectedFilterCommand =
                new FilterCommand(new TagsContainQueryTagsPredicate(queryTags));
        assertParseSuccess(parser, "filter t/TA", expectedFilterCommand);

        // Filter - 2 tags
        Tag testTag2 = new Tag("Smart");
        queryTags.add(testTag2);
        assertParseSuccess(parser, "filter t/TA t/Smart", expectedFilterCommand);

        // Filter - White Spaces
        assertParseSuccess(parser, "filter t/ TA t/      Smart      ", expectedFilterCommand);
    }


}
