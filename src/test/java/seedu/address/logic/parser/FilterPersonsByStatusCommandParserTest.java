package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterPersonsByStatusCommand;
import seedu.address.model.person.ApplicantStatus;
import seedu.address.model.person.Status;
import seedu.address.model.person.enums.ApplicantState;

public class FilterPersonsByStatusCommandParserTest {
    private FilterPersonsByStatusCommandParser parser = new FilterPersonsByStatusCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                Status.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, "nonsense status", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                Status.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterPersonsByStatusCommand() {
        // no leading and trailing whitespaces
        FilterPersonsByStatusCommand expectedFilterPersonsByStatusCommand =
                new FilterPersonsByStatusCommand(new ApplicantStatus(ApplicantState.STAGE_ONE.toString()));
        assertParseSuccess(parser, ApplicantState.STAGE_ONE.toString(), expectedFilterPersonsByStatusCommand);

        // varying leading and trailing whitespaces
        assertParseSuccess(parser, " resume review    ", expectedFilterPersonsByStatusCommand);
    }
}
