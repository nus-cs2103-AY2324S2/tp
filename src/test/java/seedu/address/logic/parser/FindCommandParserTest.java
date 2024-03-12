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
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // Single name keyword
        assertParseSuccess(parser, "n/Alice", new FindCommand(new NameAndTagContainsKeywordsPredicate(Collections.singletonList("Alice"), Collections.emptyList())));

        // Single tag keyword
        assertParseSuccess(parser, "t/friend", new FindCommand(new NameAndTagContainsKeywordsPredicate(Collections.emptyList(), Collections.singletonList("friend"))));

        // Multiple name keywords
        assertParseSuccess(parser, "n/Alice n/Bob", new FindCommand(new NameAndTagContainsKeywordsPredicate(List.of("Alice", "Bob"), Collections.emptyList())));

        // Multiple tag keywords
        assertParseSuccess(parser, "t/friend t/colleague", new FindCommand(new NameAndTagContainsKeywordsPredicate(Collections.emptyList(), List.of("friend", "colleague"))));
    }

    @Test
    public void parse_invalidKeyword_throwsParseException() {
        // Name contains non-alphabetic characters
        assertParseFailure(parser, "n/Alice1", "Name keywords must consist of only alphabets.");

        // Tag contains non-alphabetic characters
        assertParseFailure(parser, "t/friend1", "Tag keywords must consist of only alphabets.");

        // Name contains slash
        assertParseFailure(parser, "n/Al/ice", "Keywords must NOT contain `/`. \n/' is a special character reserved for commands. \n");

        // Tag contains slash
        assertParseFailure(parser, "t/fr/iend", "Keywords must NOT contain `/`. \n/' is a special character reserved for commands. \n");
    }

    @Test
    public void parse_noKeywordsProvided_throwsParseException() {
        // No keywords provided for name
        assertParseFailure(parser, "n/", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // No keywords provided for tag
        assertParseFailure(parser, "t/", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidCommandFormat_throwsParseException() {
        // Invalid prefix
        assertParseFailure(parser, "x/Alice", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // Mixed valid and invalid prefixes
        assertParseFailure(parser, "n/Alice x/Bob", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_duplicatePrefixes_throwsParseException() {
        // Duplicate name prefixes are allowed and treated as AND conditions
        assertParseSuccess(parser, "n/Alice n/Bob", new FindCommand(new NameAndTagContainsKeywordsPredicate(List.of("Aliceria Bobby"), Collections.emptyList())));

        // Duplicate tag prefixes are allowed and treated as AND conditions
        assertParseSuccess(parser, "t/friend t/colleague", new FindCommand(new NameAndTagContainsKeywordsPredicate(Collections.emptyList(), List.of("friend", "colleague"))));
    }

    @Test
    public void parse_unrecognizedInput_throwsParseException() {
        // Unrecognized input after valid command
        assertParseFailure(parser, "n/Alice random text", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // Unrecognized input before valid command
        assertParseFailure(parser, "random text n/Alice", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

}
