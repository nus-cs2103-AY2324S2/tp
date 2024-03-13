package vitalconnect.logic.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static vitalconnect.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import vitalconnect.logic.commands.CreateAptCommand;
import vitalconnect.logic.parser.exceptions.ParseException;


public class CreateAptCommandParserTest {

    private final CreateAptCommandParser parser = new CreateAptCommandParser();

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    @Test
    public void parse_validArgs_returnsCreateAptCommand() throws Exception {
        String patientName = "John Doe";
        String dateTimeStr = "02/02/2024 1330";
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);

        String userInput = patientName + " /time " + dateTimeStr;
        CreateAptCommand expectedCommand = new CreateAptCommand(patientName, dateTimeStr);

        assertEquals(parser.parse(userInput).getPatientName(), expectedCommand.getPatientName());
        assertEquals(parser.parse(userInput).getDateTimeStr(), expectedCommand.getDateTimeStr());
    }

    @Test
    public void parseCommand_createApt_missingDateTime_throwsParseException() {
        String userInput = "John Doe";
        assertThrows(ParseException.class, () -> parser.parse(userInput));

    }

    @Test
    public void parseCommand_createApt_missingName_throwsParseException() {
        String userInput ="02/02/2024 1330";
        assertThrows(ParseException.class, () -> parser.parse(userInput));

    }

    @Test
    public void parseCommand_createApt_invalidDateTimeFormat_throwsParseException() {
        String name = "John Doe";
        String time = "02-02-2024 1330";
        String userInput =name + time;
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

}