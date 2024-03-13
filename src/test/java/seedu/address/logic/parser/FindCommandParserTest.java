package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_ALPHABET_ONLY;
import static seedu.address.logic.Messages.MESSAGE_CANNOT_BE_EMPTY;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.Collections;

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
        assertParseSuccess(parser, " " + PREFIX_NAME + "Alice",
                new FindCommand(new NameAndTagContainsKeywordsPredicate(Collections.singletonList("Alice"),
                        Collections.emptyList())));

        // Multiple name keywords
        assertParseSuccess(parser, " " + PREFIX_NAME + "Alice " + PREFIX_NAME + "Bob",
                new FindCommand(new NameAndTagContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"),
                        Collections.emptyList())));

        // Single tag keyword
        assertParseSuccess(parser, " " + PREFIX_TAG + "friend",
                new FindCommand(new NameAndTagContainsKeywordsPredicate(Collections.emptyList(),
                        Collections.singletonList("friend"))));

        // Multiple tag keywords
        assertParseSuccess(parser, " " + PREFIX_TAG + "friend " + PREFIX_TAG + "colleague",
                new FindCommand(new NameAndTagContainsKeywordsPredicate(Collections.emptyList(),
                        Arrays.asList("friend", "colleague"))));

        // Combined name and tag keywords
        assertParseSuccess(parser, " " + PREFIX_NAME + "Alice " + PREFIX_TAG + "friend",
                new FindCommand(new NameAndTagContainsKeywordsPredicate(Collections.singletonList("Alice"),
                        Collections.singletonList("friend"))));
    }

    @Test
    public void parse_nonAlphabeticNameKeyword_throwsParseException() {
        assertParseFailure(parser, " n/Alice1", String.format(MESSAGE_ALPHABET_ONLY, PREFIX_NAME));
    }

    @Test
    public void parse_nonAlphabeticTagKeyword_throwsParseException() {
        assertParseFailure(parser, " t/friend1", String.format(MESSAGE_ALPHABET_ONLY, PREFIX_TAG));
    }

    @Test
    public void parse_emptyNameKeyword_throwsParseException() {
        assertParseFailure(parser, " " + PREFIX_NAME + " ",
                String.format(MESSAGE_CANNOT_BE_EMPTY, PREFIX_NAME));
    }

    @Test
    public void parse_emptyTagKeyword_throwsParseException() {
        assertParseFailure(parser, " " + PREFIX_TAG + " ",
                String.format(MESSAGE_CANNOT_BE_EMPTY, PREFIX_TAG));
    }

    @Test
    public void parse_noFieldsProvided_throwsParseException() {
        // No name or tag provided
        assertParseFailure(parser, "  ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_preamblePresent_throwsParseException() {
        // Preamble before valid prefixes
        assertParseFailure(parser, "RandomText " + PREFIX_NAME + "Alice",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }
}
