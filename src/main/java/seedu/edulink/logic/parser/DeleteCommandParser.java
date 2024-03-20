package seedu.edulink.logic.parser;

import static seedu.edulink.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_ID;

import seedu.edulink.commons.core.index.Index;
import seedu.edulink.logic.commands.DeleteCommand;
import seedu.edulink.logic.parser.exceptions.ParseException;
import seedu.edulink.model.student.Id;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_ID);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ID);

        if (argMultimap.getValue(PREFIX_ID).isPresent()) {
            String queryId = argMultimap.getValue(PREFIX_ID).get().trim();
            if (queryId.isEmpty()) {
                throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, Id.MESSAGE_CONSTRAINTS));
            }

            try {
                Id id = ParserUtil.parseId(queryId);
                return new DeleteCommand(id);
            } catch (ParseException pe) {
                throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, Id.MESSAGE_CONSTRAINTS), pe);
            }
        }

        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
