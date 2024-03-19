package seedu.address.logic.commands;

import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EMPLOYEES;

public class ListTaskCommand extends Command {

    public static final String COMMAND_WORD = "listtask";

    public static final String MESSAGE_SUCCESS = "Listed all tasks";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);
        return new CommandResult(MESSAGE_SUCCESS, false, false, true, false);
    }
}
