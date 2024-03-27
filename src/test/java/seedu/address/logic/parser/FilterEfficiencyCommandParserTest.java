package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterEfficiencyCommand;
import seedu.address.model.person.Efficiency;
import seedu.address.model.person.PersonLessThanEfficiencyPredicate;

public class FilterEfficiencyCommandParserTest {
    private FilterEfficiencyCommandParser parser = new FilterEfficiencyCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterEfficiencyCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nonIntegerArgs_throwsParseException() {
        assertParseFailure(parser, "abc", "The input efficiency should be an integer.");
    }

    @Test
    public void parse_outOfRangeArgs_throwsParseException() {
        assertParseFailure(parser, "150", Efficiency.MESSAGE_CONSTRAINTS);
    }


    @Test
    public void parse_validArgs_returnsFilterEfficiencyCommand() {
        // no leading and trailing whitespaces
        FilterEfficiencyCommand expectedCommand =
                new FilterEfficiencyCommand(new PersonLessThanEfficiencyPredicate(20));
        assertParseSuccess(parser, "20", expectedCommand);

        // multiple whitespaces between argument
        assertParseSuccess(parser, " \n 20 \n", expectedCommand);
    }
}
