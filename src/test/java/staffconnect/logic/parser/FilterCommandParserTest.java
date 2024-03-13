package staffconnect.logic.parser;

import static staffconnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static staffconnect.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static staffconnect.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static staffconnect.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static staffconnect.logic.parser.CliSyntax.PREFIX_TAG;
import static staffconnect.logic.parser.CommandParserTestUtil.assertParseFailure;
import static staffconnect.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import staffconnect.logic.commands.FilterCommand;
import staffconnect.model.person.PersonHasTagPredicate;
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
        // no whitespace before PREFIX_TAG
        assertParseFailure(parser, PREFIX_TAG + VALID_TAG_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        // tagname is non-alphanumeric (contains '*')
        assertParseFailure(parser, INVALID_TAG_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));

    }

    @Test
    public void parse_validTagName_success() {
        // 1 leading and no trailing whitespaces
        FilterCommand expectedFilterCommand = new FilterCommand(new PersonHasTagPredicate(new Tag(VALID_TAG_FRIEND)));
        assertParseSuccess(parser, TAG_DESC_FRIEND, expectedFilterCommand);

        // 1 leading and multiple trailing whitespaces
        assertParseSuccess(parser, TAG_DESC_FRIEND + "   ", expectedFilterCommand); // 1 leading, 3 trailing

        // multiple leading and trailing whitespaces
        assertParseSuccess(parser, "  " + TAG_DESC_FRIEND + " ", expectedFilterCommand); // 2 leading, 1 trailing
        assertParseSuccess(parser, "     " + TAG_DESC_FRIEND + "   ", expectedFilterCommand); // 5 leading, 3 trailing
    }

}
