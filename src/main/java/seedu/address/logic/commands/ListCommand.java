package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.stream.Collectors;

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

    private final ListCommandPredicate predicate;

    public ListCommand(ListCommandPredicate predicate) {
        this.predicate = predicate;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);

        if (predicate.getClassGroups().isEmpty()) {
            return new CommandResult(
                    String.format(MESSAGE_LIST_ALL_SUCCESS, model.getFilteredPersonList().size()));
        } else {
            String classGroupsString = predicate.getClassGroups().get()
                    .stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(", "));
            return new CommandResult(String.format(MESSAGE_LIST_CLASS_SUCCESS,
                    model.getFilteredPersonList().size(), classGroupsString));
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
