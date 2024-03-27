package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RESERVATIONS;

import seedu.address.model.Model;

/**
 * Lists all reservations in the address book to the user.
 */
public class RsvListCommand extends Command {
    public static final String COMMAND_WORD = "rsvlist";

    public static final String MESSAGE_SUCCESS = "Listed all reservations";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredReservationList(PREDICATE_SHOW_ALL_RESERVATIONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
