package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;
import java.util.stream.Collectors;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.Model;
import seedu.address.model.person.ListCommandPredicate;

/**
 * Lists students in the TA Toolkit to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "ls";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List all students or students from a specific "
            + "class group given the class group identifier and displays them as a list with index numbers.\n"
            + "Parameters: [CLASSGROUP_ID] (must be a valid class group identifier)\n"
            + "Example: " + COMMAND_WORD + " F14-3";

    public static final String MESSAGE_LIST_ALL_SUCCESS = "Listed all %1$d students";

    public static final String MESSAGE_LIST_CLASS_SUCCESS = "Listed %1$d students from class groups: %2$s";

    private static final Logger logger = LogsCenter.getLogger(ListCommand.class);

    private final ListCommandPredicate predicate;

    /**
     * Creates a ListCommand to list all students or students from specific class groups.
     *
     * @param predicate Predicate to filter students by a list of class groups
     */
    public ListCommand(ListCommandPredicate predicate) {
        this.predicate = predicate;
        logger.info("ListCommand created with predicate: " + predicate.toString());
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);

        if (predicate.getClassGroups().isEmpty()) {
            String msg = String.format(MESSAGE_LIST_ALL_SUCCESS, model.getFilteredPersonList().size());
            return new CommandResult(msg);
        } else {
            String classGroupsString = predicate.getClassGroups().get()
                    .stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(", "));
            String msg = String.format(MESSAGE_LIST_CLASS_SUCCESS,
                    model.getFilteredPersonList().size(), classGroupsString);
            return new CommandResult(msg);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ListCommand)) {
            return false;
        }

        ListCommand otherListCommand = (ListCommand) other;
        return predicate.equals(otherListCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
