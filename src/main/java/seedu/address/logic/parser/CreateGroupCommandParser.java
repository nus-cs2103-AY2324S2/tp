package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSEMATE;

import java.util.Set;

import seedu.address.logic.commands.CreateGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.Name;

/**
 * Parses input arguments and creates a new {@code CreateGroupCommand} object.
 */
public class CreateGroupCommandParser implements Parser<CreateGroupCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the CreateGroupCommand
     * and returns a CreateGroupCommand object to execute.
     * @throws ParseException if the user input does not conform the expected format or the group name is used
     */
    public CreateGroupCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args, PREFIX_COURSEMATE);

        Name name = ParserUtil.parseName(argMultiMap.getPreamble());
        Set<CourseMate> courseMateList = ParserUtil.parseCourseMates(argMultiMap.getAllValues(PREFIX_COURSEMATE));

        // TODO: check if group already exists

        return new CreateGroupCommand(name, courseMateList);
    }
}
