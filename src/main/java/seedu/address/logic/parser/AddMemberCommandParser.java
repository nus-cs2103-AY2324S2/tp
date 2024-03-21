package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSEMATE;

import java.util.Set;

import seedu.address.logic.commands.AddMemberCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.coursemate.Name;
import seedu.address.model.coursemate.QueryableCourseMate;

/**
 * Parses input arguments and creates a new {@code AddMemberCommand} object.
 */
public class AddMemberCommandParser implements Parser<AddMemberCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddMemberCommand
     * and returns a AddMemberCommand object to execute.
     * @throws ParseException if the user input does not conform the expected format or the group name is used
     */
    public AddMemberCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args, PREFIX_COURSEMATE);

        try {
            Name name = ParserUtil.parseName(argMultiMap.getPreamble());
            Set<QueryableCourseMate> queryableCourseMateSet =
                    ParserUtil.parseQueryableCourseMates(argMultiMap.getAllValues(PREFIX_COURSEMATE));

            // will get caught by the catch clause, leave empty.
            if (queryableCourseMateSet.isEmpty()) {
                throw new ParseException("");
            }
            return new AddMemberCommand(name, queryableCourseMateSet);
        } catch (ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMemberCommand.MESSAGE_USAGE));
        }
    }
}
