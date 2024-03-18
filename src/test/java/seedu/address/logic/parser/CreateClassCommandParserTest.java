package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CC_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CreateClassCommand;
import seedu.address.model.person.Classes;
import seedu.address.model.person.CourseCode;
import seedu.address.testutil.ClassBuilder;

public class CreateClassCommandParserTest {

    private CreateClassCommandParser parser = new CreateClassCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Classes expectedClass = new ClassBuilder().withCC(VALID_CC).build();
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_CC_DESC, new CreateClassCommand(expectedClass));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateClassCommand.MESSAGE_USAGE);

        assertParseFailure(parser, VALID_CC, expectedMessage);

    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, INVALID_CC_DESC, CourseCode.MESSAGE_CONSTRAINTS);
    }
}
