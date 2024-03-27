package seedu.address.logic.commands.sortfunctions;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * Sorts contacts in address book accordingly to [Keyword]
 * [Keyword] : tag/name
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " [KEYWORDS]\n"
            + "KEYWORDS: tag/name";

    private final SortStrategy sortStrategy;

    /**
     * Get the keyword to know which field to sort by
     *
     * @param input keyword
     */
    public SortCommand(String input) throws ParseException {
        String category = input.trim().toLowerCase();
        if (category.isBlank()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        switch (category) {
        case "tag":
            this.sortStrategy = new SortByTag();
            break;
        case "name":
            this.sortStrategy = new SortByName();
            break;
        default:
            throw new ParseException("Invalid [KEYWORD]\n" + "Keywords supported: NAME/TAG");
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        sortStrategy.sort(model.getAddressBook());
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult("Sorted address book by: " + sortStrategy.getCategory());
    }
}
