package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSEMATE;

import seedu.address.logic.commands.DeleteGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.coursemate.Name;

/**
 * Parses input arguments and creates a new {@code DeleteGroupCommand} object.
 */
public class DeleteGroupCommandParser implements Parser<DeleteGroupCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteGroupCommand
     * and returns a DeleteGroupCommand object to execute.
     * @throws ParseException if the user input does not conform the expected format or the group name is used
     */
    public DeleteGroupCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args, PREFIX_COURSEMATE);

        Name groupName = ParserUtil.parseName(argMultiMap.getPreamble());
        return new DeleteGroupCommand(groupName);
    }
}
