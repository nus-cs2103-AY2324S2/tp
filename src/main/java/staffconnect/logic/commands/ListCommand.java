package staffconnect.logic.commands;

import static java.util.Objects.requireNonNull;
import static staffconnect.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import staffconnect.model.Model;

/**
 * Lists all persons in the staff book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
