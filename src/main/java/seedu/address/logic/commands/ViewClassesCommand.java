package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.person.Classes;

/**
 * Lists all Classes in the UniqueClassList to the user.
 */
public class ViewClassesCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_SUCCESS = "Here are your classes: ";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        String formattedClassList = model.getFormattedClassList();
        return new CommandResult(MESSAGE_SUCCESS + formattedClassList);
    }
}

