package staffconnect.logic.parser;


import static staffconnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static staffconnect.logic.parser.CommandParserTestUtil.assertParseFailure;
import static staffconnect.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import staffconnect.logic.commands.SortCommand;
import staffconnect.model.person.comparators.VenueComparator;

public class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSortCommand() {

        SortCommand expectedSortCommand = new SortCommand(VenueComparator.VENUE_COMPARATOR);

        // no leading and trailing whitespaces
        assertParseSuccess(parser, " v/ ", expectedSortCommand);

        // multiple whitespaces before and after keywords
        assertParseSuccess(parser, "   v/     ", expectedSortCommand);


        assertParseFailure(parser, "   /p     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortCommand.MESSAGE_USAGE));
    }

}
