package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.model.person.Id;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        DeleteCommand expectedCommand = new DeleteCommand(new Id(VALID_ID_AMY));

        assertParseSuccess(parser, ID_DESC_AMY, expectedCommand);

        assertParseSuccess(parser, "  " + ID_DESC_AMY, expectedCommand);

        assertParseSuccess(parser, ID_DESC_AMY + "  ", expectedCommand);

        assertParseSuccess(parser, " /id     " + VALID_ID_AMY, expectedCommand);

        assertParseSuccess(parser, " /id " + VALID_ID_AMY + "    ", expectedCommand);
    }

    @Test
    public void parse_invalidFormat_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE);

        // Content after "-" is missing.
        assertParseFailure(parser, "", expectedMessage);

        // /id prefix is missing.
        assertParseFailure(parser, VALID_ID_AMY, expectedMessage);

        // There is any content other than spaces before the first prefix.
        assertParseFailure(parser, "delete" + ID_DESC_AMY, expectedMessage);
    }

    @Test
    public void parse_invalidArgument_failure() {
        String expectedMessage = String.format(Id.MESSAGE_CONSTRAINTS);

        // There is white space in the middle of id.
        assertParseFailure(parser, " /id a bc", expectedMessage);
    }
}
