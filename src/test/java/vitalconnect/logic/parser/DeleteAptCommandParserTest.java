package vitalconnect.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static vitalconnect.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import vitalconnect.commons.core.index.Index;
import vitalconnect.logic.commands.DeleteAptCommand;
import vitalconnect.logic.parser.exceptions.ParseException;


public class DeleteAptCommandParserTest {

    private final DeleteAptCommandParser parser = new DeleteAptCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteAptCommand() throws Exception {
        Index validIndex = Index.fromOneBased(1);
        String userInput = "1";
        DeleteAptCommand expectedCommand = new DeleteAptCommand(validIndex);

        assertEquals(validIndex, expectedCommand.getIndex());

        assertEquals(parser.parse(userInput).getIndex(), expectedCommand.getIndex());
    }

    @Test
    public void parse_missingIndex_throwsParseException() {
        String userInput = "/name John Doe";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_invalidIndexFormat_throwsParseException() {
        String userInput = "notAnIndex";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }
}
