package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKLIST;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.BorrowCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.BookList;

public class BorrowCommandParser implements Parser<BorrowCommand> {
    public BorrowCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_BOOKLIST);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BorrowCommand.MESSAGE_USAGE), ive);
        }

        String bookTitle = argMultimap.getValue(PREFIX_BOOKLIST).orElse("");

        return new BorrowCommand(index, new BookList(bookTitle));
    }
}
