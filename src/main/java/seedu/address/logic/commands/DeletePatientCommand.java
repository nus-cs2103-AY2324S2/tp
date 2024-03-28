package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.Nric;

/**
 * Deletes a patient identified by their NRIC.
 */
public class DeletePatientCommand extends Command {

    public static final String COMMAND_WORD = "deletePatient";

    public static final String COMMAND_WORD_ALT = "dp";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the patient identified by the NRIC number.\n"
            + "Parameters: NRIC (must be a existing NRIC in database)\n"
            + "Example: " + COMMAND_WORD + " S1234567A";

    public static final String MESSAGE_DELETE_PATIENT_SUCCESS = "Deleted Patient: %1$s";

    private final Nric targetNric;

    public DeletePatientCommand(Nric targetNric) {
        this.targetNric = targetNric;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasPatientWithNric(targetNric)) {
            throw new CommandException(Messages.MESSAGE_PATIENT_NRIC_NOT_FOUND);
        }
        String message = String.format(MESSAGE_DELETE_PATIENT_SUCCESS,
                Messages.format(model.getPatientWithNric(targetNric)));

        model.deleteAppointmentsWithNric(targetNric);
        model.deletePatientWithNric(targetNric);
        return new CommandResult(message);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeletePatientCommand)) {
            return false;
        }

        DeletePatientCommand otherDeletePatientCommand = (DeletePatientCommand) other;
        return targetNric.equals(otherDeletePatientCommand.targetNric);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetNric", targetNric)
                .toString();
    }
}
