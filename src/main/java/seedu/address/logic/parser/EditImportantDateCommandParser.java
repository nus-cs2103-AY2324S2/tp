package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IMPORTANT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.EditImportantDateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.ImportantDate;

/**
 * Parses the user's input arguments and creates a new EditImportantDateCommand
 */
public class EditImportantDateCommandParser implements Parser<EditImportantDateCommand> {

    /**
     * Returns a new EditImportantDateCommand instance.
     * @param args User's input.
     * @return A new EditImportantDateCommand.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    public EditImportantDateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_IMPORTANT_DATE,
                PREFIX_NAME, PREFIX_DATETIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_IMPORTANT_DATE, PREFIX_NAME, PREFIX_DATETIME)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditImportantDateCommand.MESSAGE_USAGE));
        }

        Index patientIndex;
        Index eventIndex;
        ImportantDate importantDate;

        try {
            patientIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
            eventIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_IMPORTANT_DATE).get());
        } catch (IllegalValueException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditImportantDateCommand.MESSAGE_USAGE), e);
        }

        importantDate = ParserUtil.parseImportantDate(argMultimap.getValue(PREFIX_NAME).get(),
                argMultimap.getValue(PREFIX_DATETIME).get());

        return new EditImportantDateCommand(patientIndex, eventIndex, importantDate);
    }
}

