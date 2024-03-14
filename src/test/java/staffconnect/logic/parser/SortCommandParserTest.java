package staffconnect.logic.parser;


import static staffconnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static staffconnect.logic.parser.CommandParserTestUtil.assertParseFailure;
import static staffconnect.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import staffconnect.logic.commands.SortCommand;
import staffconnect.model.person.Person;

public class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSortCommand() {
        // no leading and trailing whitespaces
        SortCommand expectedSortCommand = new SortCommand(new Comparator<Person>() {
            @Override
            public int compare(Person person1, Person person2) {
                return person1.getVenue().toString().compareToIgnoreCase(person2.getVenue().toString());
            }
        });
        assertParseSuccess(parser, " /n", expectedSortCommand);

        // multiple whitespaces before and after keywords
        assertParseSuccess(parser, "   /n     ", expectedSortCommand);

        //different attribute called
        assertParseSuccess(parser, "   /p     ", expectedSortCommand);
    }

}
