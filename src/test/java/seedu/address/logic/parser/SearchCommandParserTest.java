package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SearchCommand;
import seedu.address.model.person.KeywordPredicate;

public class SearchCommandParserTest {

    private SearchCommandParser parser = new SearchCommandParser();

    @Test
    public void parse_validArgs_returnsFindCommand() {
        String keyword = " ; name : Poochie";
        ArgumentMultimap token = ArgumentTokenizer.tokenize(keyword, PREFIX_NAME);

        // no leading and trailing whitespaces
        SearchCommand expectedSearchCommand =
                new SearchCommand(new KeywordPredicate(token));
        assertParseSuccess(parser, keyword, expectedSearchCommand);
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(SearchCommand.MESSAGE_SEARCH_INVALID_FIELD));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " ;   name :   Poochie",
                String.format(SearchCommand.MESSAGE_SEARCH_INVALID_FIELD));
    }
}
