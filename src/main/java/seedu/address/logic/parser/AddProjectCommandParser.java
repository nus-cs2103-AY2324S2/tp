package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.AddProjectCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;


/**
 * Parses input arguments and creates a new AddProjectCommand object
 */
public class AddProjectCommandParser implements Parser<AddProjectCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddProjectCommand
     * and returns an AddProjectCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddProjectCommand parse(String args) throws ParseException {
        try {
            String projectName = args;
            if (projectName.length() == 0) {
                throw new ParseException("Please enter the project field.");
            }
            Name name = ParserUtil.parseName(projectName);
            Person person = new Person(name);
            return new AddProjectCommand(person);
        } catch (Exception e) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT,
                    AddProjectCommand.MESSAGE_USAGE));
        }
    }
}
