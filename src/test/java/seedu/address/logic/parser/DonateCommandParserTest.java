package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DonateCommand;
import seedu.address.model.person.BookList;
import seedu.address.testutil.TypicalIndexes;


public class DonateCommandParserTest {
    private static final String bookTitle = "Some title";
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DonateCommand.MESSAGE_USAGE);
    private DonateCommandParser parser = new DonateCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, CliSyntax.PREFIX_BOOKLIST + bookTitle, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // only consists of spaces
        assertParseFailure(parser, "     ", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5", MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0", MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsPresent_success() {
        // book title with no space in front and at back
        assertParseSuccess(parser, "9 " + CliSyntax.PREFIX_BOOKLIST + bookTitle,
                new DonateCommand(TypicalIndexes.INDEX_KEPLER, new BookList(bookTitle)));

        // book title with spaces in front and at back
        assertParseSuccess(parser, "9 " + CliSyntax.PREFIX_BOOKLIST + "    " + bookTitle + "    ",
                new DonateCommand(TypicalIndexes.INDEX_KEPLER, new BookList(bookTitle)));
    }
}
