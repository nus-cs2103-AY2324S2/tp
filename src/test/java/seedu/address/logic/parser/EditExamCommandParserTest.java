package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditExamCommand;
import seedu.address.logic.commands.EditExamCommand.EditExamDescriptor;
import seedu.address.model.exam.Exam;
import seedu.address.model.person.Score;

public class EditExamCommandParserTest {

    private EditExamCommandParser parser = new EditExamCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Index targetIndex = Index.fromOneBased(1);
        EditExamDescriptor descriptor = new EditExamDescriptor();
        descriptor.setName("Midterm");
        descriptor.setMaxScore(new Score(100));

        assertParseSuccess(parser, "1 " + PREFIX_NAME + "Midterm " + PREFIX_SCORE + "100",
                new EditExamCommand(targetIndex, descriptor));
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        EditExamDescriptor descriptor = new EditExamDescriptor();
        descriptor.setName("Midterm");
        descriptor.setMaxScore(new Score(100));

        assertParseFailure(parser, "1 " + PREFIX_NAME + "Midterm " + PREFIX_SCORE + "100 "
                + PREFIX_NAME + "Finals " + PREFIX_SCORE + "200",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_SCORE));

        assertParseFailure(parser, "1 " + PREFIX_NAME + "Midterm " + PREFIX_NAME + "Finals "
                + PREFIX_SCORE + "100",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));
    }

    @Test
    public void parse_someFieldsPresent_success() {
        Index targetIndex = Index.fromOneBased(1);
        EditExamDescriptor descriptor = new EditExamDescriptor();
        descriptor.setName("Midterm");

        assertParseSuccess(parser, "1 " + PREFIX_NAME + "Midterm",
                new EditExamCommand(targetIndex, descriptor));
    }

    @Test
    public void parse_noFieldSpecified_failure() {
        assertParseFailure(parser, "1", EditExamCommand.MESSAGE_NOT_EDITED);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1 " + PREFIX_NAME + " ", Exam.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1 " + PREFIX_SCORE + " ", Score.MESSAGE_CONSTRAINTS);
    }
}
