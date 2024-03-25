package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Comparator;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.MoneyOwed;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.InvalidSortTypeException;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {
    @Override
    public SortCommand parse(String args) throws ParseException {
        String sortType = args.replaceFirst(SortCommand.COMMAND_WORD, "").trim();
        if (sortType.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        return new SortCommand(getComparator(sortType), sortType);
    }

    private Comparator<Person> getComparator(String type) throws InvalidSortTypeException {
        switch (type.toLowerCase()) {
        case SortCommand.BIRTHDAY_SORT_TYPE:
            return Birthday.BIRTHDAY_COMPARATOR;
        case SortCommand.NAME_SORT_TYPE:
            return Name.NAME_COMPARATOR;
        case SortCommand.MONEY_SORT_TYPE:
            return MoneyOwed.MONEY_COMPARATOR;
        case SortCommand.CLEAR_SORT_TYPE:
            return null;
        default:
            throw new InvalidSortTypeException(type);
        }
    }
}
