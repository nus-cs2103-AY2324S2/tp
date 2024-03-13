package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.model.person.PersonHasTagPredicate;
import seedu.address.testutil.TestUtil;

public class SortCommandParserTest {
    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSortCommand() throws Exception {
        // no leading and trailing whitespaces
        SortCommand expectedSortCommand =
                new SortCommand(new PersonHasTagPredicate(TestUtil.stringsToTags(Arrays.asList("friends", "TAs"))));
        assertParseSuccess(parser, "friends TAs", expectedSortCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n friends \n \t TAs  \t", expectedSortCommand);
    }

}
