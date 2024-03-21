package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;





/**
 * Sorts all students in the address book based on their number of starts and lists to the user.
 */
public class SortAscendingCommand extends Command {

    public static final String COMMAND_WORD = "sortStarsAscending";
    public static final String MESSAGE_SUCCESS = "Sorted all persons";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateSortedPersonListSortAscending();
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }
}
