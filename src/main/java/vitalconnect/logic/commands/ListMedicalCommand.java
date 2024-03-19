package vitalconnect.logic.commands;

import vitalconnect.logic.commands.exceptions.CommandException;
import vitalconnect.model.Model;

import static java.util.Objects.requireNonNull;
import static vitalconnect.model.Model.PREDICATE_SHOW_ALL_PERSONS;

public class ListMedicalCommand extends Command{
    public static final String COMMAND_WORD = "listmi";

    public static final String MESSAGE_SUCCESS = "Listed all persons medical information";

    /**
     * @param model {@code Model} which the command should operate on.
     * @return {@code CommandResult} that describes the result of executing this command
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
