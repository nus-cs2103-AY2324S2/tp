package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IMPORTANT_DATE;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.DeleteImportantDateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.ImportantDate;

import javax.sound.midi.Soundbank;

import java.sql.SQLOutput;
import java.util.SortedMap;
import java.util.logging.Level;
import java.util.logging.Logger;

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

        if (!arePrefixesPresent(argMultimap, PREFIX_IMPORTANT_DATE) ||
                argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteImportantDateCommand.MESSAGE_USAGE));
        }

        Index patientIndex;
        Index importantDateIndex;
        try {
            patientIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
            importantDateIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_IMPORTANT_DATE).get());
            logger.log(Level.INFO, "patient index: " + patientIndex);
            logger.log(Level.INFO, "importantDateindex: " + importantDateIndex);
            return new DeleteImportantDateCommand(patientIndex, importantDateIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteImportantDateCommand.MESSAGE_USAGE), pe);
        }


    }
}
