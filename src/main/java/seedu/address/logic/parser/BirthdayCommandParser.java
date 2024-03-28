package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY_DATE;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.BirthdayCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Birthday;

/**
 * Parses input arguments and creates a new BirthdayCommand object
 */
public class BirthdayCommandParser implements Parser<BirthdayCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code BirthdayCommand}
     * and returns a {@code BirthdayCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public BirthdayCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_BIRTHDAY_DATE);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    BirthdayCommand.MESSAGE_USAGE), ive);
        }

        String birthday = argMultimap.getValue(PREFIX_BIRTHDAY_DATE).orElse("");

        return new BirthdayCommand(index, new Birthday(birthday));
    }
}
