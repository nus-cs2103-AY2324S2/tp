package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteEventParserTest {
    private final DeleteEventCommandParser parser = new DeleteEventCommandParser();
    private final Index validPatientIndex = INDEX_FIRST_PATIENT;
    private final Index validEventIndex = INDEX_FIRST_EVENT;

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(""));
        assertThrows(ParseException.class, () -> parser.parse("1"));
        assertThrows(ParseException.class, () -> parser.parse("1" + " "));
        assertThrows(ParseException.class, () -> parser.parse(" " + "1"));
        assertThrows(ParseException.class, () -> parser.parse(" " + " "));
        assertThrows(ParseException.class, () -> parser.parse(validPatientIndex + " "));
        assertThrows(ParseException.class, () -> parser.parse(" " + validEventIndex));
    }

    @Test
    public void parse_validArgs_returnsDeleteEventCommand() throws ParseException {
        String validUserInput;
        DeleteEventCommand expected;

        validUserInput = validPatientIndex.getOneBased() + " e/" + validEventIndex.getOneBased();
        expected = new DeleteEventCommand(validPatientIndex, validEventIndex);
        assertParseSuccess(parser, validUserInput, expected);
    }

    @Test
    public void parse_invalidArgs_returnsDeleteEventommand() {
        String invalidUserInput;

        invalidUserInput = "-1" + " e/" + validEventIndex.getZeroBased();
        String expected = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteEventCommand.MESSAGE_USAGE);
        assertParseFailure(parser, invalidUserInput, expected);

        invalidUserInput = validPatientIndex.getZeroBased() + "d/" + validEventIndex.getZeroBased();
        expected = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteEventCommand.MESSAGE_USAGE);
        assertParseFailure(parser, invalidUserInput, expected);

        invalidUserInput = validPatientIndex.getZeroBased() + " e/" + "-1";
        expected = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteEventCommand.MESSAGE_USAGE);
        assertParseFailure(parser, invalidUserInput, expected);
    }
}
