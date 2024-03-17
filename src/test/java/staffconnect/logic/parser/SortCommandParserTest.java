package staffconnect.logic.parser;


import static staffconnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static staffconnect.logic.parser.CliSyntax.PREFIX_FACULTY;
import static staffconnect.logic.parser.CliSyntax.PREFIX_MODULE;
import static staffconnect.logic.parser.CliSyntax.PREFIX_NAME;
import static staffconnect.logic.parser.CliSyntax.PREFIX_PHONE;
import static staffconnect.logic.parser.CliSyntax.PREFIX_VENUE;
import static staffconnect.logic.parser.CommandParserTestUtil.assertParseFailure;
import static staffconnect.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static staffconnect.model.person.comparators.FacultyComparator.FACULTY_COMPARATOR;
import static staffconnect.model.person.comparators.ModuleComparator.MODULE_COMPARATOR;
import static staffconnect.model.person.comparators.NameComparator.NAME_COMPARATOR;
import static staffconnect.model.person.comparators.PhoneComparator.PHONE_COMPARATOR;
import static staffconnect.model.person.comparators.VenueComparator.VENUE_COMPARATOR;

import org.junit.jupiter.api.Test;

import staffconnect.logic.commands.SortCommand;

public class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {

        assertParseFailure(parser, "l/", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));


    }

    @Test
    public void parse_validArgs_returnsSortCommand() {

        SortCommand expectedSortCommand = new SortCommand(VENUE_COMPARATOR);

        // no leading and no trailing whitespaces
        assertParseSuccess(parser, "" + PREFIX_VENUE, expectedSortCommand);

        // 1 leading and no trailing whitespaces
        assertParseSuccess(parser, " " + PREFIX_VENUE, expectedSortCommand);

        // no leading and 1 trailing whitespaces
        assertParseSuccess(parser, PREFIX_VENUE + " ", expectedSortCommand);

        // multiple whitespaces before and after keywords
        assertParseSuccess(parser, "   " + PREFIX_VENUE + "    ", expectedSortCommand);

    }

    @Test
    public void parse_validArgs_returnsSortCorrectAttribute() {

        assertParseSuccess(parser, "" + PREFIX_NAME, new SortCommand(NAME_COMPARATOR));
        assertParseSuccess(parser, "" + PREFIX_PHONE, new SortCommand(PHONE_COMPARATOR));
        assertParseSuccess(parser, "" + PREFIX_MODULE, new SortCommand(MODULE_COMPARATOR));
        assertParseSuccess(parser, "" + PREFIX_FACULTY, new SortCommand(FACULTY_COMPARATOR));
        assertParseSuccess(parser, "" + PREFIX_VENUE, new SortCommand(VENUE_COMPARATOR));

    }


}
