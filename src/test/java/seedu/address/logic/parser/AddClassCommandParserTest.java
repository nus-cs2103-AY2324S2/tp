package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULECODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALCLASS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddClassCommand;
import seedu.address.model.module.Module;
import seedu.address.model.module.TutorialClass;
import seedu.address.model.person.Name;


public class AddClassCommandParserTest {
    private AddClassCommandParser parser = new AddClassCommandParser();
    private final String nonEmptyTutorialClass = "T09";

    @Test
    public void parse_indexSpecified_success() {
        // have tutorial class
        String moduleCode = "CS2100";
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClassCommand.MESSAGE_USAGE);

        String userInput = PREFIX_MODULECODE + moduleCode + " " + PREFIX_TUTORIALCLASS + nonEmptyTutorialClass;
        AddClassCommand expectedCommand = new AddClassCommand(new Module(new Name(moduleCode),
                new TutorialClass(nonEmptyTutorialClass)));
        assertParseSuccess(parser, userInput, expectedCommand);

        // no tutorial class
        userInput = PREFIX_MODULECODE + moduleCode + " " + PREFIX_TUTORIALCLASS;
        expectedCommand = new AddClassCommand(new Module(new Name(moduleCode),
                new TutorialClass("")));
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

