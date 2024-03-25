package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.SetDeadlineCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.model.project.Task;


/**
 * Parses input arguments and creates a new AddCommand object
 */
public class SetDeadlineCommandParser implements Parser<SetDeadlineCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetDeadlineCommand parse(String args) throws ParseException {
        try {
            if (!args.contains(" /in ") || !args.contains(" /to ")) {
                throw new ParseException(String.format(
                        MESSAGE_INVALID_COMMAND_FORMAT,
                        SetDeadlineCommand.MESSAGE_USAGE));
            }
            String deadline = args.split(" /to")[0].trim();
            String taskAndProject = args.split(" /to")[1].trim();
            String taskName = taskAndProject.split("/in ")[0].trim();
            String projectName = taskAndProject.split("/in ")[1];
            if ((projectName.length() == 0) || (taskName.length() == 0)) {
                throw new ParseException("Please enter the project and task fields");
            }
            Person project = new Person(ParserUtil.parseName(projectName));
            Task newTask = new Task(taskName);
            return new SetDeadlineCommand(deadline, newTask, project);
        } catch (IndexOutOfBoundsException e) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT,
                    SetDeadlineCommand.MESSAGE_USAGE));
        }
    }
}
