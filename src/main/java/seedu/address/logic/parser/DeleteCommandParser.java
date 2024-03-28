package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NUSID;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NUSID, PREFIX_GROUP);
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NUSID, PREFIX_GROUP);
        try {

            if (argMultimap.getValue(PREFIX_NUSID).isPresent() && argMultimap.getValue(PREFIX_GROUP).isPresent()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
            }
            else if (argMultimap.getValue(PREFIX_GROUP).isPresent()) {
                return new DeleteCommand(ParserUtil.parseGroup(argMultimap.getValue(PREFIX_GROUP).get()));
            }
            else if (argMultimap.getValue(PREFIX_NUSID).isPresent()) {
                return new DeleteCommand(ParserUtil.parseNusId(argMultimap.getValue(PREFIX_NUSID).get()));

            }

            else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
            }




        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
