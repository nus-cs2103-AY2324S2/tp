package seedu.address.logic.parser;

import static seedu.address.logic.InternshipMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.InternshipCommandTestUtil.DEADLINE_DESC_AMY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_DEADLINE_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SELECT_TASK;
import static seedu.address.logic.parser.InternshipCommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.InternshipCommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.InternshipParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.InternshipTypicalIndexes.INDEX_SECOND_INTERNSHIP;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.InternshipAddDeadlineCommand;
import seedu.address.model.internship.Deadline;

public class InternshipAddDeadlineCommandParserTest {
    private static final Index INDEX_SECOND_TASK = Index.fromOneBased(2);
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, InternshipAddDeadlineCommand.MESSAGE_USAGE);
    private InternshipAddDeadlineCommandParser parser = new InternshipAddDeadlineCommandParser();

    @Test
    public void parse_missingParts_failure() {
        Index internshipIndex = INDEX_SECOND_INTERNSHIP;
        Index taskIndex = INDEX_SECOND_TASK;

        String userInputWithoutIndex = String.format("%s %d %s", PREFIX_SELECT_TASK,
                taskIndex.getOneBased(), DEADLINE_DESC_AMY);
        String userInputWithoutPrefixSelectTask = String.format("%d %d %s", internshipIndex.getOneBased(),
                taskIndex.getOneBased(), DEADLINE_DESC_AMY);
        String userInputWithoutTaskIndex = String.format("%d %s %s", internshipIndex.getOneBased(),
                PREFIX_SELECT_TASK, DEADLINE_DESC_AMY);
        String userInputWithoutPrefixDeadline = String.format("%d %s %d %s", internshipIndex.getOneBased(),
                PREFIX_SELECT_TASK, taskIndex.getOneBased(), VALID_DEADLINE_AMY);
        String userInputWithoutDeadlineText = String.format("%d %s %d %s", internshipIndex.getOneBased(),
                PREFIX_SELECT_TASK, taskIndex.getOneBased(), PREFIX_DEADLINE);

        // no index specified
        assertParseFailure(parser, userInputWithoutIndex, MESSAGE_INVALID_FORMAT);

        // no prefix select task
        assertParseFailure(parser, userInputWithoutPrefixSelectTask, MESSAGE_INVALID_FORMAT);

        // no task index specified
        assertParseFailure(parser, userInputWithoutTaskIndex, MESSAGE_INVALID_INDEX);

        // no prefix deadline
        assertParseFailure(parser, userInputWithoutPrefixDeadline, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                InternshipAddDeadlineCommand.MESSAGE_USAGE));

        // no deadline text
        assertParseFailure(parser, userInputWithoutDeadlineText, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                Deadline.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        Index internshipIndex = INDEX_SECOND_INTERNSHIP;
        Index taskIndex = INDEX_SECOND_TASK;
        String userInputNegativeInternshipIndex = String.format("%d %s %d %s", -5, PREFIX_SELECT_TASK,
                taskIndex.getOneBased(), DEADLINE_DESC_AMY);
        String userInputZeroInternshipIndex = String.format("%d %s %d %s", 0, PREFIX_SELECT_TASK,
                taskIndex.getOneBased(), DEADLINE_DESC_AMY);
        String userInputNegativeTaskIndex = String.format("%d %s %d %s", internshipIndex.getOneBased(),
                PREFIX_SELECT_TASK, -5, DEADLINE_DESC_AMY);
        String userInputZeroTaskIndex = String.format("%d %s %d %s", internshipIndex.getOneBased(), PREFIX_SELECT_TASK,
                0, DEADLINE_DESC_AMY);
        String userInputWithInvalidDeadline = String.format("%d %s %d %s", internshipIndex.getOneBased(),
                PREFIX_SELECT_TASK, 1, INVALID_DEADLINE_DESC);
        // negative index
        assertParseFailure(parser, userInputNegativeInternshipIndex, MESSAGE_INVALID_INDEX);

        // zero index
        assertParseFailure(parser, userInputZeroInternshipIndex, MESSAGE_INVALID_INDEX);

        // negative index
        assertParseFailure(parser, userInputNegativeTaskIndex, MESSAGE_INVALID_INDEX);

        // zero index
        assertParseFailure(parser, userInputZeroTaskIndex, MESSAGE_INVALID_INDEX);

        // invalid deadline
        assertParseFailure(parser, userInputWithInvalidDeadline, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                Deadline.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index internshipIndex = INDEX_SECOND_INTERNSHIP;
        Index taskIndex = INDEX_SECOND_TASK;

        String userInput = String.format("%d %s %d %s", internshipIndex.getOneBased(), PREFIX_SELECT_TASK,
                taskIndex.getOneBased(), DEADLINE_DESC_AMY);

        InternshipAddDeadlineCommand expectedCommand = new InternshipAddDeadlineCommand(internshipIndex, taskIndex,
                new Deadline(VALID_DEADLINE_AMY));

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
