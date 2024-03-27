package vitalconnect.logic.commands;

import static java.util.Objects.requireNonNull;
import static vitalconnect.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import vitalconnect.model.Model;

/**
 * Lists all persons in the clinic to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setCurrentPredicate(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredPersonList(model.getCurrentPredicate());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
