package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddExamCommand;
import seedu.address.model.exam.Exam;
import seedu.address.model.person.Score;

public class AddExamCommandParserTest {
    private AddExamCommandParser parser = new AddExamCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Exam expectedExam = new Exam("Midterm", new Score(100));

        // whitespace only preamble
        assertParseSuccess(parser, " " + PREFIX_NAME + "Midterm " + PREFIX_SCORE + "100",
                new AddExamCommand(expectedExam));
    }

    @Test
    public void parse_repeatedValue_failure() {

        // repeated name prefix
        assertParseFailure(parser, " " + PREFIX_NAME + "Midterm " + PREFIX_NAME + "Final " + PREFIX_SCORE + "100",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // repeated score prefix
        assertParseFailure(parser, " " + PREFIX_NAME + "Midterm " + PREFIX_SCORE + "100 " + PREFIX_SCORE + "200",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_SCORE));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, " " + PREFIX_NAME + " " + PREFIX_SCORE + "100",
                Exam.MESSAGE_CONSTRAINTS);

        // invalid name
        assertParseFailure(parser, " " + PREFIX_NAME + "Midterm& " + PREFIX_SCORE + "100",
                Exam.MESSAGE_CONSTRAINTS);

        // invalid score
        assertParseFailure(parser, " " + PREFIX_NAME + "Midterm " + PREFIX_SCORE + " ",
                Score.MESSAGE_CONSTRAINTS);

        // invalid score
        assertParseFailure(parser, " " + PREFIX_NAME + "Midterm " + PREFIX_SCORE + "abc",
                Score.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddExamCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, " " + PREFIX_SCORE + "100", expectedMessage);

        // missing score prefix
        assertParseFailure(parser, " " + PREFIX_NAME + "Midterm", expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, "", expectedMessage);
    }
}
