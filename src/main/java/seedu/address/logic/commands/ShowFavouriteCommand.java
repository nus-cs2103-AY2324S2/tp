package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_FAVOURITES;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ShowFavouriteCommand extends Command {

    public static final String COMMAND_WORD = "showfav";

    public static final String MESSAGE_SUCCESS = "Displayed all favourites";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_FAVOURITES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
