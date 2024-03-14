package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SetCourseCommand;

public class SetCourseCommandParserTest {
    private SetCourseCommandParser parser = new SetCourseCommandParser();
    private final String course = "CS2103T";


    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetCourseCommand.MESSAGE_USAGE);

        assertParseFailure(parser, SetCourseCommand.COMMAND_WORD, expectedMessage);

        assertParseFailure(parser, SetCourseCommand.COMMAND_WORD + " " + course, expectedMessage);
    }
}
