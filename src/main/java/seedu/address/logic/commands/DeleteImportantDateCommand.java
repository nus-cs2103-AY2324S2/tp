package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.Patient;

/**
 * Deletes a patient identified using it's displayed index from the address book.
 */
public class DeleteImportantDateCommand extends Command {

    public static final String COMMAND_WORD = "deleteID";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a specified important date for the a specific patient identified by the index number used "
            + "in the displayed patient list as well as the event index.\n"
            + "Parameters: INDEX of PATIENT (must be a positive integer matching that of the Patient in the"
            + "`list` command) "
            + "e/ [Index of the Event] (must be a positive integer matching that of the event of the patient. \n"
            + "Example: "
            + COMMAND_WORD + " 1 "
            + "e/ 1 ";

    public static final String MESSAGE_DELETE_IMPORTANT_DATE_SUCCESS = "Deleted Important Date: %2$s for Patient: %2$s";

    private final Index targetPatientIndex;
    private final Index targetImportantDateIndex;

    public DeleteImportantDateCommand(Index targetPatientIndex, Index targetEventIndex) {
        this.targetPatientIndex = targetEventIndex;
        this.targetImportantDateIndex = targetPatientIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (targetPatientIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToDeleteImportantDate = lastShownList.get(targetPatientIndex.getZeroBased());

        if (targetImportantDateIndex.getZeroBased() >= patientToDeleteImportantDate.getImportantDates().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_IMPORTANT_DATE_DISPLAYED_INDEX);
        }
        return new CommandResult(String.format(MESSAGE_DELETE_IMPORTANT_DATE_SUCCESS,
                Messages.format(patientToDeleteImportantDate)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteImportantDateCommand)) {
            return false;
        }

        DeleteImportantDateCommand otherDeleteImportantDateCommand = (DeleteImportantDateCommand) other;
        return targetImportantDateIndex.equals(otherDeleteImportantDateCommand.targetImportantDateIndex)
                && targetPatientIndex.equals(otherDeleteImportantDateCommand.targetPatientIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetPatientIndex", targetPatientIndex)
                .add("targetImportantDateIndex", targetImportantDateIndex)
                .toString();
    }
}
