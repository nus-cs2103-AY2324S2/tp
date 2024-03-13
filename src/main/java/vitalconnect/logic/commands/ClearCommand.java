package vitalconnect.logic.commands;

import static java.util.Objects.requireNonNull;

import vitalconnect.model.Clinic;
import vitalconnect.model.Model;

/**
 * Clears the clinic.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Clinic has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setClinic(new Clinic());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
