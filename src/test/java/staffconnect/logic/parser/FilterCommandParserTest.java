package staffconnect.logic.parser;

import static staffconnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static staffconnect.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static staffconnect.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static staffconnect.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static staffconnect.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static staffconnect.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static staffconnect.logic.parser.CliSyntax.PREFIX_TAG;
import static staffconnect.logic.parser.CommandParserTestUtil.assertParseFailure;
import static staffconnect.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import staffconnect.logic.commands.FilterCommand;
import staffconnect.model.person.PersonHasTagsPredicate;
import staffconnect.model.tag.Tag;

public class FilterCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG; // should have a space before tag prefix

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidTagName_throwsParseException() {
        // tagname is non-alphanumeric (contains '*')
        assertParseFailure(parser, INVALID_TAG_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));

    }

    @Test
    public void parse_validTagName_success() {
        Set<Tag> singleTag = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_FRIEND)));

        // single tag
        // 1 leading and no trailing whitespaces (TAG_DESC_xxx always has 1 leading)
        FilterCommand expectedFilterCommand = new FilterCommand(new PersonHasTagsPredicate(singleTag));
        assertParseSuccess(parser, TAG_DESC_FRIEND, expectedFilterCommand);

        // 1 leading and multiple trailing whitespaces
        // 1 leading, 3 trailing
        assertParseSuccess(parser, TAG_DESC_FRIEND + "   ", expectedFilterCommand);

        // multiple leading and trailing whitespaces
        // 2 leading, 1 trailing
        assertParseSuccess(parser, "  " + TAG_DESC_FRIEND + " ", expectedFilterCommand);
        // 5 leading, 3 trailing
        assertParseSuccess(parser, "     " + TAG_DESC_FRIEND + "   ", expectedFilterCommand);

        // multiple tags
        Set<Tag> multipleTags = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_FRIEND), new Tag(VALID_TAG_HUSBAND)));
        expectedFilterCommand = new FilterCommand(new PersonHasTagsPredicate(multipleTags));

        // 1 leading and no trailing whitespaces
        assertParseSuccess(parser, TAG_DESC_FRIEND + TAG_DESC_HUSBAND, expectedFilterCommand);

        // 1 leading and multiple trailing whitespaces
        // 1 leading, 3 trailing
        assertParseSuccess(parser, TAG_DESC_FRIEND + TAG_DESC_HUSBAND + "   ", expectedFilterCommand);

        // multiple leading and trailing whitespaces
        // 2 leading, 1 trailing
        assertParseSuccess(parser, "  " + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + " ", expectedFilterCommand);
        // 5 leading, 3 trailing
        assertParseSuccess(parser, "     " + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + "   ", expectedFilterCommand);

        // whitespaces in middle
        // 1 leading, 1 middle, 3 trailing
        assertParseSuccess(parser, TAG_DESC_FRIEND + " " + TAG_DESC_HUSBAND + "   ", expectedFilterCommand);
        // 1 leading, 3 middle, 1 trailing
        assertParseSuccess(parser, TAG_DESC_FRIEND + "   " + TAG_DESC_HUSBAND + " ", expectedFilterCommand);
    }

}
