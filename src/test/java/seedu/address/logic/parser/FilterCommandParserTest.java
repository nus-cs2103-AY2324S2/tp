package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.person.Grade;
import seedu.address.model.person.GradeSubjectFilterPredicate;
import seedu.address.model.person.Subject;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        // no trailing whitespaces
        FilterCommand expectedFilterCommand =
                new FilterCommand(new GradeSubjectFilterPredicate(new Grade("A"), new Subject()));
        assertParseSuccess(parser, " g/A", expectedFilterCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n g/ \n \t A  \t", expectedFilterCommand);
    }

}
