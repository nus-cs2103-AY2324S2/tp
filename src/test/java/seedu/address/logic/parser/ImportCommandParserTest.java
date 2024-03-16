package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.io.File;
import java.util.HashSet;

public class ImportCommandParserTest {
    private static final String IMPORT_SUCCESS_STRING = "import f/filename";
    private static final String IMPORT_SUCCESS_FILENAME = "./data/filename.json";
    private final ImportCommandParser importParser = new ImportCommandParser();

    @Test
    public void parse_emptyString_failure() {
        assertThrows(ParseException.class, () -> importParser.parse(""));
    }

    @Test
    public void parse_emptyString_success() {
        try {
            ImportCommand importCommand = importParser.parse(IMPORT_SUCCESS_STRING);
            HashSet<File> curHashSet = new HashSet<>();
            curHashSet.add(new File(IMPORT_SUCCESS_FILENAME));
            ImportCommand expectedCommand = new ImportCommand(curHashSet);
            assertEquals(importCommand, expectedCommand);
        } catch (ParseException exc) {
            assertTrue(false);
        }
    }

    @Test
    public void arePrefixesPresent_emptyPrefix_success() {

    }
}
