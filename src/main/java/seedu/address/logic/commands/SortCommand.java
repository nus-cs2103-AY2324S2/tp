package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " [KEYWORDS]\n"
            + "KEYWORDS: tag/name";

    private static String category = null;

    /**
     * Get the keyword to know which field to sort by
     *
     * @param input: keyword
     * @throws ParseException
     */
    public SortCommand(String input) throws ParseException {
        category = input.trim();
        if (category.isBlank()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (category.equals("tag")) {
            model.getAddressBook()
                    .getPersons()
                    .sortListByTag();
        } else if (category.equals("name")) {
            model.getAddressBook()
                    .getPersons()
                    .sortListByName();
        }
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult("Sorting tags");
    }
}
