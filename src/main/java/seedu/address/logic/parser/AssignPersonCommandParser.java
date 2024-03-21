package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;

import seedu.address.logic.commands.AssignPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.model.project.Task;


/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AssignPersonCommandParser implements Parser<AssignPersonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AssignPersonCommand parse(String args) throws ParseException {
        String member = args.split(" to")[0].trim();
        String taskAndProject = args.split(" to")[1].trim();
        String taskName = taskAndProject.split("in ")[0].trim();
        String projectName = taskAndProject.split("in ")[1].trim();
        if (projectName.length() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignPersonCommand.MESSAGE_USAGE));
        }
        Person project = new Person(ParserUtil.parseName(projectName));
        Task newTask = new Task(taskName);
        return new AssignPersonCommand(member, newTask, project);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
