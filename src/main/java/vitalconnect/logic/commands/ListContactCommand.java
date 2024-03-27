package vitalconnect.logic.commands;

import static java.util.Objects.requireNonNull;
import static vitalconnect.model.Model.PREDICATE_SHOW_ALL_CONTACT;

import vitalconnect.model.Model;

/**
 * List all the contact information of a person with their name
 */
public class ListContactCommand extends Command {
    public static final String COMMAND_WORD = "listc";

    public static final String MESSAGE_SUCCESS = "Listed all persons with their contact information";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setCurrentPredicate(PREDICATE_SHOW_ALL_CONTACT);
        model.updateFilteredPersonList(model.getCurrentPredicate());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
