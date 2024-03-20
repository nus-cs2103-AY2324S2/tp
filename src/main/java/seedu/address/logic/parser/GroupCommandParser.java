package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENTID;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.GroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.Group;
import seedu.address.model.person.StudentId;

/**
 * Parses a command with the given parameters.
 */
public class GroupCommandParser implements Parser<GroupCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GroupCommand
     * and returns an GroupCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GroupCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_GROUP, PREFIX_STUDENTID);

        if (!arePrefixesPresent(argMultimap, PREFIX_GROUP, PREFIX_STUDENTID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_GROUP);
        //set with one group. for compatibility with edit command.
        Set<Group> group = ParserUtil.parseGroups(argMultimap.getAllValues(PREFIX_GROUP));
        Set<StudentId> studentIds = ParserUtil.parseStudentIds(argMultimap.getAllValues(PREFIX_STUDENTID));
        return new GroupCommand(group, studentIds);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
