package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddSchedCommand;
import seedu.address.logic.commands.DeleteSchedCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteSchedCommandParser implements Parser<DeleteSchedCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteSchedCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_SPECIFIC_PARTICIPANTS);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteSchedCommand.MESSAGE_USAGE), pe);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_SPECIFIC_PARTICIPANTS)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteSchedCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_SPECIFIC_PARTICIPANTS);

        ArrayList<Index> deletePersonIndexArrayList;
        try {
            deletePersonIndexArrayList = ParserUtil.parseIndexArrayList(argMultimap.getValue(PREFIX_SPECIFIC_PARTICIPANTS).get());
            return new DeleteSchedCommand(index, deletePersonIndexArrayList);
        } catch (ParseException pe) {
            if (argMultimap.getValue(PREFIX_SPECIFIC_PARTICIPANTS).get().equals("all")) {
                return new DeleteSchedCommand(index);
            }
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteSchedCommand.MESSAGE_USAGE), pe);
        }

    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
