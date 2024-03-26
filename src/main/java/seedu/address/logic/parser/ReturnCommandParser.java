package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKLIST;

import java.util.NoSuchElementException;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.ReturnCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.book.Book;

/**
 * Parses input arguments and creates a new ReturnCommand object
 */
public class ReturnCommandParser implements Parser<ReturnCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ReturnCommand
     * and returns a ReturnCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ReturnCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_BOOKLIST);

        Index index;
        String bookTitle;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            bookTitle = argMultimap.getValue(PREFIX_BOOKLIST).get();
        } catch (IllegalValueException | NoSuchElementException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReturnCommand.MESSAGE_USAGE), ive);
        }

        return new ReturnCommand(index, new Book(bookTitle));
    }
}
