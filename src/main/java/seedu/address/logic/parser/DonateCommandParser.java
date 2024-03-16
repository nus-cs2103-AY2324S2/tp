package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.DonateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.BookList;

/**
 * Parses input arguments and creates a new DonateCommand object
 */
public class DonateCommandParser implements Parser<DonateCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DonateCommand
     * and returns an DonateCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DonateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_BOOKLIST);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DonateCommand.MESSAGE_USAGE), ive);
        }

        String bookTitle = argMultimap.getValue(PREFIX_BOOKLIST).orElse("");
        if (bookTitle.equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DonateCommand.MESSAGE_USAGE));
        }

        return new DonateCommand(index, new BookList(bookTitle));
    }
}
