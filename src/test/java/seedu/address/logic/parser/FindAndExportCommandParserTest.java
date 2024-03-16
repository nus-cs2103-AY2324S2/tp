package seedu.address.logic.parser;

import seedu.address.logic.parser.exceptions.ParseException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindAndExportCommand;

public class FindAndExportCommandParserTest {

    private final FindAndExportCommandParser parser = new FindAndExportCommandParser();

    @Test
    public void parse_allFieldsPresent_success() throws Exception {
        final String tag = "friends";
        final String name = "John";
        final String address = "123 Street";
        final String filename = "export.txt";

        FindAndExportCommand expectedCommand = new FindAndExportCommand(tag, name, address, filename);

        assertEquals(expectedCommand, parser.parse("friends n/John a/123 Street o/export.txt"));

        assertEquals(new FindAndExportCommand(tag, null, null, "default.csv"),
                parser.parse("friends"));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        assertThrows(ParseException.class, () -> parser.parse("n/John"));
    }
}
