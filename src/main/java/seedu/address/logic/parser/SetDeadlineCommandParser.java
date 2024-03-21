package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;

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
        String deadline = args.split(" of")[0].trim();
        String taskAndProject = args.split(" of")[1].trim();
        String taskName = taskAndProject.split("in ")[0].trim();
        String projectName = taskAndProject.split("in ")[1];
        if (projectName.length() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetDeadlineCommand.MESSAGE_USAGE));
        }
        Person project = new Person(ParserUtil.parseName(projectName));
        Task newTask = new Task(taskName);
        return new SetDeadlineCommand(deadline,newTask, project);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
