package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ShowProjectCommand;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.project.Task;

class ShowProjectCommandParserTest {
    private ShowProjectCommandParser parser = new ShowProjectCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person project = new Person(new Name("project"));
        Task task = new Task("task");

        assertParseSuccess(parser, "project", new ShowProjectCommand("project"));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowProjectCommand.MESSAGE_USAGE);

        // missing project name
        assertParseFailure(parser, "",
                expectedMessage);

    }
}
