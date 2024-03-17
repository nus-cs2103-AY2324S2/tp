package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AssignCommand;

public class AssignCommandParserTest {
    private static final String INVALID_TO = " " + PREFIX_TO + "a";

    private static final String TO_ONE = " " + PREFIX_TO + "1";
    private static final String TO_TWO = " " + PREFIX_TO + "2";

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignCommand.MESSAGE_USAGE);

    private AssignCommandParser parser = new AssignCommandParser();

    @Test
    public void parse_validArgs_success() {
        assertParseSuccess(parser, "1" + TO_ONE, new AssignCommand(INDEX_FIRST, INDEX_FIRST));
    }

    @Test
    public void parse_repeatedTo_failure() {
        assertParseFailure(parser, "1" + TO_ONE + TO_TWO,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TO));

        // invalid value followed by valid value

        assertParseFailure(parser, "1" + INVALID_TO + TO_ONE,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TO));

        // valid value followed by invalid value

        assertParseFailure(parser, "1" + TO_ONE + INVALID_TO,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TO));
    }

    @Test
    public void parse_missingParts_failure() {
        // no task index specified
        assertParseFailure(parser, TO_ONE, MESSAGE_INVALID_FORMAT);

        // no person index specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no task index and no person index specified
        assertParseFailure(parser, " ", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-1" + TO_ONE, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + TO_ONE, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 a" + TO_ONE, MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ a" + TO_ONE, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidTo_failure() {
        // negative index
        assertParseFailure(parser, "1 " + PREFIX_TO + "-1", MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "1 " + PREFIX_TO + "0", MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as to
        assertParseFailure(parser, "1 " + TO_ONE + " a", MESSAGE_INVALID_FORMAT);
    }


}
