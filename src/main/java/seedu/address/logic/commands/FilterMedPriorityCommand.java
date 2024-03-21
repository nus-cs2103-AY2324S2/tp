package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Finds and lists all persons in address book who are marked as medium priority.
 */
public class FilterMedPriorityCommand extends Command {

    public static final String COMMAND_WORD = "filter-med";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose "
            + "priorities are medium and displays them as a list.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Listed contacts with medium priority";

    public static final String MESSAGE_NO_CONTACTS_FOUND = "Oops! No contacts found with medium priority level.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(person -> person.getPriority().value.equals("med"));
        if (model.getFilteredPersonList().isEmpty()) {
            return new CommandResult(MESSAGE_NO_CONTACTS_FOUND);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
