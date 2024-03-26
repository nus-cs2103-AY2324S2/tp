package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddScoreCommand;
import seedu.address.model.person.Score;

public class AddScoreCommandParserTest {

    private AddScoreCommandParser parser = new AddScoreCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Index expectedIndex = Index.fromOneBased(1);
        Score expectedScore = new Score(100);

        // whitespace only preamble
        assertParseSuccess(parser, " 1 " + PREFIX_SCORE + "100",
                new AddScoreCommand(expectedIndex, expectedScore));
    }

    @Test
    public void parse_invalidScore_failure() {
        String expectedMessage = Score.MESSAGE_CONSTRAINTS;
        // invalid score
        assertParseFailure(parser, "1 " + PREFIX_SCORE + "abd", expectedMessage);
    }

    @Test
    public void parse_repeatedScore_failure() {
        String expectedMessage = Messages.getErrorMessageForDuplicatePrefixes(PREFIX_SCORE);

        // repeated score prefix
        assertParseFailure(parser, "1 " + PREFIX_SCORE + "100 " + PREFIX_SCORE + "100", expectedMessage);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddScoreCommand.MESSAGE_USAGE);

        // missing index
        assertParseFailure(parser, " " + PREFIX_SCORE + "100", expectedMessage);

        // missing score prefix
        assertParseFailure(parser, "1", expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, "", expectedMessage);
    }
}
