package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;
import seedu.address.model.person.TypePredicate;

import java.util.function.Predicate;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed ";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Show list of the contacts with the given type.\n"
            + "Parameters: TYPE\n"
            + "Example: " + COMMAND_WORD + " housekeeper";
    private final TypePredicate predicate;

    public ListCommand(TypePredicate predicate) {
        this.predicate = predicate;
    }
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(MESSAGE_SUCCESS + "all " + predicate.getType() + "s");
    }
}
