package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOLT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_BOLT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.BoltCommand;
import seedu.address.model.student.Bolt;

public class BoltCommandParserTest {

    private static final String VALID_BOLT = " " + PREFIX_BOLT + "3";
    private static final String INVALID_BOLT = " " + PREFIX_BOLT + "-1";

    private BoltCommandParser parser = new BoltCommandParser();

    @Test
    public void parse_validArgs_returnsBoltCommand() {
        assertParseSuccess(parser, "1" + VALID_BOLT,
                new BoltCommand(Index.fromOneBased(1), new Bolt(3)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // invalid index
        assertParseFailure(parser, "-1" + VALID_BOLT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, BoltCommand.MESSAGE_USAGE));

        // invalid star value
        assertParseFailure(parser, "1" + INVALID_BOLT, MESSAGE_INVALID_BOLT);

        // no index specified
        assertParseFailure(parser, VALID_BOLT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, BoltCommand.MESSAGE_USAGE));

        // no star value specified
        assertParseFailure(parser, "1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, BoltCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_BOLT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, BoltCommand.MESSAGE_USAGE));

        // no field specified
        assertParseFailure(parser, "1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, BoltCommand.MESSAGE_USAGE));

        // no index and no field specified
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, BoltCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_BOLT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, BoltCommand.MESSAGE_USAGE));

        // zero index
        assertParseFailure(parser, "0" + VALID_BOLT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, BoltCommand.MESSAGE_USAGE));

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string" + VALID_BOLT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, BoltCommand.MESSAGE_USAGE));

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string" + VALID_BOLT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, BoltCommand.MESSAGE_USAGE));
    }
}
