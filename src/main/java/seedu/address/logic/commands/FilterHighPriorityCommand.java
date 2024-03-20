package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Finds and lists all persons in address book who are marked as high priority.
 */
public class FilterHighPriorityCommand extends Command {

    public static final String COMMAND_WORD = "filter-high";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose "
            + "priorities are high and displays them as a list.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Listed contacts with high priority";

    public static final String MESSAGE_NO_CONTACTS_FOUND = "Oops! No contacts found with high priority level.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(person -> person.getPriority().value.equals("high"));
        if (model.getFilteredPersonList().isEmpty()) {
            return new CommandResult(MESSAGE_NO_CONTACTS_FOUND);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
