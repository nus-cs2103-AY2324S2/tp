package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.MailCommand;
import seedu.address.model.group.GroupContainsKeywordsPredicate;

public class MailCommandParserTest {

    private MailCommandParser parser = new MailCommandParser();

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        // no leading and trailing whitespaces
        MailCommand expectedMailCommand =
                new MailCommand(new GroupContainsKeywordsPredicate(Arrays.asList("TUT10", "LAB05")));
        assertParseSuccess(parser, "TUT10 LAB05", expectedMailCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n TUT10 \n \t LAB05  \t", expectedMailCommand);
    }
}
