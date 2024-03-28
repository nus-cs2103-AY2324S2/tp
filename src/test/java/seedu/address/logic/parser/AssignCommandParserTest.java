package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_HEAD;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AssignCommand;

public class AssignCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignCommand.MESSAGE_USAGE);

    private AssignCommandParser parser = new AssignCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_ROLE_HEAD, MESSAGE_INVALID_FORMAT);

        // no role specified
        assertParseFailure(parser, "1", AssignCommand.MESSAGE_NOT_ASSIGNED);

        // no index and no role specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

}
