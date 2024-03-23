package scm.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import scm.address.logic.commands.FindAndExportCommand;
import scm.address.logic.parser.exceptions.ParseException;

public class FindAndExportCommandParserTest {

    private final FindAndExportCommandParser parser = new FindAndExportCommandParser();

    @Test
    public void parse_validArgs_returnsFindAndExportCommand() throws ParseException {
        String input = "friends n/John a/123 Main St f/output.json";
        FindAndExportCommand expectedCommand = new FindAndExportCommand("friends",
                "John", "123 Main St", "output.json");

        FindAndExportCommand resultCommand = parser.parse(input);

        assertEquals(expectedCommand.getTag(), resultCommand.getTag());
        assertEquals(expectedCommand.getName(), resultCommand.getName());
        assertEquals(expectedCommand.getAddress(), resultCommand.getAddress());
        assertEquals(expectedCommand.getFilename(), resultCommand.getFilename());
    }
}
