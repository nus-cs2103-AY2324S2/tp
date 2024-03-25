package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKLIST;

import java.util.NoSuchElementException;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.BorrowCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.book.Book;

/**
 * Parses input arguments and creates a new BorrowCommand object
 */
public class BorrowCommandParser implements Parser<BorrowCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the BorrowCommand
     * and returns an BorrowCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public BorrowCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_BOOKLIST);

        Index index;
        String bookTitle;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            bookTitle = argMultimap.getValue(PREFIX_BOOKLIST).get();
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BorrowCommand.MESSAGE_USAGE), ive);
        } catch (NoSuchElementException nee) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BorrowCommand.MESSAGE_USAGE), nee);
        }

        return new BorrowCommand(index, new Book(bookTitle));
    }
}
