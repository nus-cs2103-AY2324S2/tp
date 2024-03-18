package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TUTORIAL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.addstudenttoclasscommands.AddStudentToClassByEmailCommand;
import seedu.address.logic.commands.addstudenttoclasscommands.AddStudentToClassByIdCommand;
import seedu.address.logic.commands.addstudenttoclasscommands.AddStudentToClassCommand;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.TutorialClass;

/**
 * As we are only doing white-box testing, our test cases do not cover path
 * variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take
 * the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class AddStudentToClassCommandParserTest {

    private AddStudentToClassCommandParser parser = new AddStudentToClassCommandParser();

    @Test
    public void parse_validArgs_returnsAddStudentToClassCommand() {

        String commandSuffix = MODULE_DESC_AMY + TUTORIAL_DESC_AMY;

        // Add by email
        AddStudentToClassByEmailCommand expectedAddByEmailCommand = new AddStudentToClassByEmailCommand(BOB.getEmail(),
                new ModuleCode(VALID_MODULE_AMY), new TutorialClass(VALID_TUTORIAL_AMY));
        assertParseSuccess(parser, EMAIL_DESC_BOB + commandSuffix, expectedAddByEmailCommand);

        // Add by student Id
        AddStudentToClassByIdCommand expectedAddByIdCommand = new AddStudentToClassByIdCommand(BOB.getStudentId(),
                new ModuleCode(VALID_MODULE_AMY), new TutorialClass(VALID_TUTORIAL_AMY));
        assertParseSuccess(parser, STUDENT_ID_DESC_BOB + commandSuffix, expectedAddByIdCommand);

    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentToClassCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "a", expectedMessage);
    }
}
