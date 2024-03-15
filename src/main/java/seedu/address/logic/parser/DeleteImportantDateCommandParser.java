package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IMPORTANT_DATE;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteImportantDateCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses the user's input arguments and creates a new DeleteImportantDateCommand
 */
public class DeleteImportantDateCommandParser implements Parser<DeleteImportantDateCommand> {

    private static final Logger logger = LogsCenter.getLogger(DeleteImportantDateCommandParser.class);

    /**
     * @throws ParseException if the user input does not conform to the expected format
     */
    public DeleteImportantDateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_IMPORTANT_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_IMPORTANT_DATE) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteImportantDateCommand.MESSAGE_USAGE));
        }

        Index patientIndex;
        Index eventIndex;
        try {
            patientIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
            logger.log(Level.INFO, "patient index: " + patientIndex);
            if (argMultimap.getValue(PREFIX_IMPORTANT_DATE).isPresent()) {
                eventIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_IMPORTANT_DATE).get());
                logger.log(Level.INFO, "Event index: " + eventIndex);
            } else {
                return new DeleteImportantDateCommand(patientIndex, null);
            }
            return new DeleteImportantDateCommand(patientIndex, eventIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteImportantDateCommand.MESSAGE_USAGE), pe);
        }
    }
}
