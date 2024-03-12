package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULECODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALCLASS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddClassCommand;
import seedu.address.model.module.ModuleCode;

public class AddClassCommandParserTest {
    private final AddClassCommandParser parser = new AddClassCommandParser();
    private final String nonEmptyTutorialClass = "T09";

    @Test
    public void parse_tutorialTest() {
        // have tutorial class
        String moduleCode = "CS2100";
        String expectedMessage = ModuleCode.MESSAGE_CONSTRAINTS;

        String userInput = AddClassCommand.COMMAND_WORD + " " + PREFIX_MODULECODE + " " + moduleCode
                + " " + PREFIX_TUTORIALCLASS + " " + nonEmptyTutorialClass;
        AddClassCommand expectedCommand = new AddClassCommand(new ModuleCode(moduleCode),
                nonEmptyTutorialClass);
        assertParseSuccess(parser, userInput, expectedCommand);

        // no tutorial class
        userInput = PREFIX_MODULECODE + moduleCode + " " + PREFIX_TUTORIALCLASS;
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = ModuleCode.MESSAGE_CONSTRAINTS;

        // no parameters
        assertParseFailure(parser, AddClassCommand.COMMAND_WORD + "", expectedMessage);

        // no module stated
        assertParseFailure(parser, AddClassCommand.COMMAND_WORD + " "
                + PREFIX_MODULECODE + " " + PREFIX_TUTORIALCLASS + nonEmptyTutorialClass, expectedMessage);
    }
}

