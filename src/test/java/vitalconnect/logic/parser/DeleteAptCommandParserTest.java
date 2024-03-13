package vitalconnect.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static vitalconnect.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import vitalconnect.logic.commands.DeleteAptCommand;
import vitalconnect.logic.parser.exceptions.ParseException;


public class DeleteAptCommandParserTest {

    private final DeleteAptCommandParser parser = new DeleteAptCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteAptCommand() throws Exception {
        String patientName = "John Doe";
        int validIndex = 1;
        String userInput = validIndex + " /name " + patientName;
        DeleteAptCommand expectedCommand = new DeleteAptCommand(validIndex, patientName);

        assertEquals(validIndex, expectedCommand.getIndex());
        assertEquals(patientName, expectedCommand.getPatientName());


        assertEquals(parser.parse(userInput).getPatientName(), expectedCommand.getPatientName());
        assertEquals(parser.parse(userInput).getIndex(), expectedCommand.getIndex());
    }

    @Test
    public void parse_missingIndex_throwsParseException() {
        String userInput = "/name John Doe";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_missingName_throwsParseException() {
        int validIndex = 1;
        String userInput = validIndex + " /name ";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_invalidIndexFormat_throwsParseException() {
        String userInput = "notAnIndex /name John Doe";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_invalidArgumentsOrder_throwsParseException() {
        String userInput = "/name John Doe 1";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }
}
