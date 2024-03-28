package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.project.Task;


/**
 * Parses input arguments and creates a new DeleteTaskCommand object
 */
public class DeleteTaskCommandParser implements Parser<DeleteTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTaskCommand
     * and returns an DeleteTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteTaskCommand parse(String args) throws ParseException {
        try {
            if (!args.contains(" /in ")) {
                throw new ParseException(String.format(
                        MESSAGE_INVALID_COMMAND_FORMAT,
                        DeleteTaskCommand.MESSAGE_USAGE));
            }
            String taskName = args.split(" /in")[0];
            String projectName = args.split("/in ")[1];
            Task task = new Task(taskName);
            if ((taskName.length() == 0) || (projectName.length() == 0)) {
                throw new ParseException("Please enter the project and the task field");
            }
            Name name = ParserUtil.parseName(projectName);
            Person person = new Person(name);
            return new DeleteTaskCommand(task, person);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParseException(String.format(
                MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteTaskCommand.MESSAGE_USAGE));
        }
    }
}
