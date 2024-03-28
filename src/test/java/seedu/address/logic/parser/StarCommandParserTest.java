package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAR;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_STAR;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.StarCommand;
import seedu.address.model.student.Star;

public class StarCommandParserTest {

    private static final String VALID_STAR = " " + PREFIX_STAR + "3";
    private static final String INVALID_STAR = " " + PREFIX_STAR + "-1";

    private StarCommandParser parser = new StarCommandParser();

    @Test
    public void parse_validArgs_returnsStarCommand() {
        assertParseSuccess(parser, "1" + VALID_STAR,
                new StarCommand(Index.fromOneBased(1), new Star(3)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // invalid index
        assertParseFailure(parser, "-1" + VALID_STAR,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, StarCommand.MESSAGE_USAGE));

        // invalid star value
        assertParseFailure(parser, "1" + INVALID_STAR, MESSAGE_INVALID_STAR);

        // no index specified
        assertParseFailure(parser, VALID_STAR,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, StarCommand.MESSAGE_USAGE));

        // no star value specified
        assertParseFailure(parser, "1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, StarCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_STAR,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, StarCommand.MESSAGE_USAGE));

        // no field specified
        assertParseFailure(parser, "1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, StarCommand.MESSAGE_USAGE));

        // no index and no field specified
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, StarCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_STAR,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, StarCommand.MESSAGE_USAGE));

        // zero index
        assertParseFailure(parser, "0" + VALID_STAR,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, StarCommand.MESSAGE_USAGE));

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string" + VALID_STAR,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, StarCommand.MESSAGE_USAGE));

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string" + VALID_STAR,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, StarCommand.MESSAGE_USAGE));
    }
}
