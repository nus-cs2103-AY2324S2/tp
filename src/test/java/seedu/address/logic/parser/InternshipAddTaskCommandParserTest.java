package seedu.address.logic.parser;

import static seedu.address.logic.InternshipMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.InternshipCommandTestUtil.TASK_DESC_AMY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_TASK_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.address.logic.parser.InternshipCommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.InternshipCommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.InternshipTypicalIndexes.INDEX_SECOND_INTERNSHIP;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.InternshipAddTaskCommand;
import seedu.address.model.internship.Task;

public class InternshipAddTaskCommandParserTest {
    private static final String SAMPLE_TASK = "Sample task";
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, InternshipAddTaskCommand.MESSAGE_USAGE);
    private InternshipAddTaskCommandParser parser = new InternshipAddTaskCommandParser();
    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, SAMPLE_TASK, MESSAGE_INVALID_FORMAT);

        // no task specified
        assertParseFailure(parser, "1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                InternshipAddTaskCommand.MESSAGE_EMPTY_TASK));

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + PREFIX_TASK + SAMPLE_TASK, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + PREFIX_TASK + SAMPLE_TASK, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_INTERNSHIP;

        String userInput = targetIndex.getOneBased() + TASK_DESC_AMY;

        InternshipAddTaskCommand expectedCommand = new InternshipAddTaskCommand(targetIndex, new Task(VALID_TASK_AMY));

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
