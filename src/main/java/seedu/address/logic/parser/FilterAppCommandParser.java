package seedu.address.logic.parser;

import static seedu.address.commons.core.date.Date.isValidDate;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.model.appointment.Time.isValidTime;
import static seedu.address.model.person.Nric.isValidNric;

import java.util.Optional;

import seedu.address.commons.core.date.Date;
import seedu.address.logic.commands.FilterAppCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.AppointmentContainsKeywordsPredicate;
import seedu.address.model.appointment.Time;
import seedu.address.model.person.Nric;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FilterAppCommandParser implements Parser<FilterAppCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterAppCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterAppCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_NRIC,
                        PREFIX_DATE, PREFIX_START_TIME, PREFIX_END_TIME);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_NRIC,
                PREFIX_DATE, PREFIX_START_TIME, PREFIX_END_TIME);

        Optional<Nric> nricFilter = Optional.empty();
        Optional<Date> dateFilter = Optional.empty();
        Optional<Time> timeFilter = Optional.empty();

        if (isValidNric(argMultimap.getValue(PREFIX_NRIC).orElse(""))) {
            nricFilter = Optional.of(new Nric(argMultimap.getValue(PREFIX_NRIC).orElse("")));
        }
        if (isValidDate(argMultimap.getValue(PREFIX_DATE).orElse(""))) {
            dateFilter = Optional.of(new Date(argMultimap.getValue(PREFIX_DATE).orElse("")));
        }
        if (isValidTime(argMultimap.getValue(PREFIX_START_TIME).orElse(""))) {
            timeFilter = Optional.of(new Time(argMultimap.getValue(PREFIX_START_TIME).orElse("")));
        }

        AppointmentContainsKeywordsPredicate predicate = new AppointmentContainsKeywordsPredicate(
                nricFilter, dateFilter, timeFilter);

        return new FilterAppCommand(predicate);
    }

}
