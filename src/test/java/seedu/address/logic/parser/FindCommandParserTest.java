package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.NameAndTagContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // Test for searching names using /n prefix
        List<String> nameKeywords = Arrays.asList("Alice", "Bob");
        // No Tags
        List<String> emptyList = Collections.emptyList();
        NameAndTagContainsKeywordsPredicate predicateForNameSearch =
                new NameAndTagContainsKeywordsPredicate(nameKeywords, emptyList);
        FindCommand expectedFindCommandForName = new FindCommand(predicateForNameSearch);

        // Test parsing for name search
        assertParseSuccess(parser, "/n Alice Bob", expectedFindCommandForName);

        // Test for searching tags using /t prefix
        List<String> tagKeywords = Arrays.asList("friend", "colleague");
        NameAndTagContainsKeywordsPredicate predicateForTagSearch =
                new NameAndTagContainsKeywordsPredicate(emptyList, tagKeywords);
        FindCommand expectedFindCommandForTag = new FindCommand(predicateForTagSearch);

        // Test parsing for tag search
        assertParseSuccess(parser, "/t friend colleague", expectedFindCommandForTag);

        // Test for searching both names and tags
        NameAndTagContainsKeywordsPredicate predicateForBothSearch =
                new NameAndTagContainsKeywordsPredicate(nameKeywords, tagKeywords);
        FindCommand expectedFindCommandForBoth = new FindCommand(predicateForBothSearch);

        // Test parsing for both names and tags search
        assertParseSuccess(parser, "/n Alice Bob /t friend colleague", expectedFindCommandForBoth);
    }

}
