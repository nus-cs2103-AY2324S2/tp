package seedu.address.logic.commands;

import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLASSES;

public class ListClassesCommand extends Command{
    public static final String COMMAND_WORD = "list_classes";

    public static final String MESSAGE_SUCCESS = "List of all classes available";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredClassList(PREDICATE_SHOW_ALL_CLASSES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
