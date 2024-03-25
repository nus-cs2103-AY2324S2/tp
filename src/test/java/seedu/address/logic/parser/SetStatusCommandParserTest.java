package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SetStatusCommand;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.project.Task;

class SetStatusCommandParserTest {
    private SetStatusCommandParser parser = new SetStatusCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person project = new Person(new Name("project"));
        Task task = new Task("task");

        assertParseSuccess(parser, "complete /to task /in project", new SetStatusCommand("complete", task, project));

    }

    @Test
    public void parse_missingFields_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetStatusCommand.MESSAGE_USAGE);

        // missing task and project name
        assertParseFailure(parser, "complete",
                expectedMessage);

        // missing project name
        assertParseFailure(parser, "complete /to task",
                expectedMessage);

    }
}
