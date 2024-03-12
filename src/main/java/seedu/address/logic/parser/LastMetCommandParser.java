package seedu.address.logic.parser;


import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LASTMET;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.DateUtil;
import seedu.address.logic.commands.LastMetCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.LastMet;

/**
 * Parses input arguments and creates a new LastMetCommand object
 */
public class LastMetCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the LastMetCommand
     * and returns an LastMetCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public LastMetCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_LASTMET);

        if (!arePrefixesPresent(argMultimap, PREFIX_LASTMET)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LastMetCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_LASTMET);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LastMetCommand.MESSAGE_USAGE), ive);
        }

        String lastMetString = argMultimap.getValue(PREFIX_LASTMET).orElse("");

        try {
            LastMet lastMet = convertStringToLastMet(lastMetString);
            return new LastMetCommand(index, lastMet);
        } catch (DateTimeParseException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LastMetCommand.MESSAGE_USAGE), ive);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private LastMet convertStringToLastMet(String input) {
        LocalDate formattedInput = DateUtil.parseStringToDate(input);
        return new LastMet(formattedInput);
    }
}
