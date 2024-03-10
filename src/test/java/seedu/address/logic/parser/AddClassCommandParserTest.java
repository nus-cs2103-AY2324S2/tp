package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_CLASS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddClassCommand;
import seedu.address.model.person.Name;
import seedu.address.model.module.Module;
import seedu.address.model.module.TutorialClass;

public class AddClassCommandParserTest {
    private AddClassCommandParser parser = new AddClassCommandParser();
    private final String nonEmptyTutorialClass = "T09";

    @Test
    public void parse_indexSpecified_success() {
        // have tutorial class
        String module_code = "CS2100";
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClassCommand.MESSAGE_USAGE);

        String userInput = PREFIX_MODULE_CODE + module_code + " " + PREFIX_TUTORIAL_CLASS + nonEmptyTutorialClass;
        AddClassCommand expectedCommand = new AddClassCommand(new Module(new Name(module_code),
                new TutorialClass(new Name(nonEmptyTutorialClass))));
        assertParseSuccess(parser, userInput, expectedCommand);

        // no tutorial class
        userInput =  PREFIX_MODULE_CODE + module_code + " " + PREFIX_TUTORIAL_CLASS;
        expectedCommand = new AddClassCommand(new Module(new Name(module_code),
                new TutorialClass(new Name(""))));
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClassCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, AddClassCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, AddClassCommand.COMMAND_WORD + " " + nonEmptyTutorialClass, expectedMessage);
    }
}

