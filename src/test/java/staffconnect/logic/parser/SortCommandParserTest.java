package staffconnect.logic.parser;


import static staffconnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static staffconnect.logic.parser.CliSyntax.PREFIX_VENUE;
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

        // no leading and no trailing whitespaces
        assertParseSuccess(parser, "" + PREFIX_VENUE, expectedSortCommand);

        // 1 leading and no trailing whitespaces
        assertParseSuccess(parser, " " + PREFIX_VENUE, expectedSortCommand);

        // no leading and 1 trailing whitespaces
        assertParseSuccess(parser, PREFIX_VENUE + " ", expectedSortCommand);

        // multiple whitespaces before and after keywords
        assertParseSuccess(parser, "   " + PREFIX_VENUE + "    ", expectedSortCommand);

    }

}
