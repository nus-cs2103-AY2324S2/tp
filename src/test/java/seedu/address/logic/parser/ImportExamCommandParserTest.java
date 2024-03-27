package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ImportExamCommand;

public class ImportExamCommandParserTest {
    private ImportExamCommandParser parser = new ImportExamCommandParser();

    @Test
    public void parse_noArgsPassed_failure() {
        assertParseFailure(
                parser, "importExam i/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportExamCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_notCsvFile_failure() {
        assertParseFailure(
                parser, "importExam i/file.json",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportExamCommand.MESSAGE_USAGE));
    }
}
