package scm.address.logic.parser;

import static scm.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static scm.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static scm.address.logic.parser.ImportCommandParser.IMPORT_INVALID_COMMAND_FORMAT;
import static scm.address.testutil.Assert.assertThrows;

import java.io.File;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import scm.address.logic.commands.ImportCommand;
import scm.address.logic.parser.exceptions.ParseException;

public class ImportCommandParserTest {
    private static final String IMPORT_SUCCESS_STRING = " f/filename";
    private static final String IMPORT_FAILURE_STRING_EMPTY = "";
    private static final String IMPORT_FAILURE_STRING_WHITESPACE = " ";
    private static final String IMPORT_SUCCESS_FILENAME = "./data/filename.json";
    private final ImportCommandParser importParser = new ImportCommandParser();

    @Test
    public void parse_emptyString_failure() {
        assertThrows(ParseException.class, () -> importParser.parse(""));
    }

    @Test
    public void parse_validFilename_success() {
        HashSet<File> curHashSet = new HashSet<>();
        curHashSet.add(new File(IMPORT_SUCCESS_FILENAME));
        ImportCommand expectedCommand = new ImportCommand(curHashSet);
        assertParseSuccess(importParser, IMPORT_SUCCESS_STRING, expectedCommand);
    }

    @Test
    public void parse_invalidFilename_failure() {
        assertParseFailure(importParser, IMPORT_FAILURE_STRING_EMPTY, IMPORT_INVALID_COMMAND_FORMAT);

        assertParseFailure(importParser, IMPORT_FAILURE_STRING_WHITESPACE, IMPORT_INVALID_COMMAND_FORMAT);
    }
}
