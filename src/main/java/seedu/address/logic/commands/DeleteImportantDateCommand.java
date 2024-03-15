package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.EditCommand.createEditedPatient;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.DeleteImportantDateCommandParser;
import seedu.address.model.Model;
import seedu.address.model.patient.EditPatientDescriptor;
import seedu.address.model.patient.ImportantDate;
import seedu.address.model.patient.Patient;

/**
 * Deletes a patient identified using it's displayed index from the address book.
 */
public class DeleteImportantDateCommand extends Command {

    public static final String COMMAND_WORD = "deletee";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a specified important date event for a specific patient identified by index number of patient "
            + " in the displayed patient list as well as the event index.\n"
            + "Parameters: INDEX of PATIENT (must be a positive integer matching that of the Patient in the"
            + "`list` command) "
            + "e/ [Index of the Event] (must be a positive integer matching that of the event of the patient. \n"
            + "Example: "
            + COMMAND_WORD + " 1 "
            + "e/ 1 ";

    public static final String MESSAGE_DELETE_IMPORTANT_DATE_SUCCESS = "Deleted Event: %2$s for Patient: %1$s"
            + "successfully";

    private final Index targetPatientIndex;
    private final Index targetEventIndex;
    private final EditPatientDescriptor editPatientDescriptor;

    /**
     * Constructs a DeleteImportantDateCommand to delete the specified {@code importantDate} using
     * {@code targetEventIndex} from the Patient with id {@code targetPatientIndex}
     * @param targetPatientIndex
     * @param targetEventIndex
     */
    public DeleteImportantDateCommand(Index targetPatientIndex, Index targetEventIndex) {
        this.targetPatientIndex = targetPatientIndex;
        this.targetEventIndex = targetEventIndex;
        this.editPatientDescriptor = new EditPatientDescriptor();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (targetPatientIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToDeleteImportantDate = lastShownList.get(targetPatientIndex.getZeroBased());
        Set<ImportantDate> importantDatesSet = patientToDeleteImportantDate.getImportantDates();

        if (targetEventIndex.getZeroBased() >= importantDatesSet.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_IMPORTANT_DATE_DISPLAYED_INDEX);
        }

        Set<ImportantDate> currImportantDatesSet = new HashSet<>(patientToDeleteImportantDate.getImportantDates());
        List<ImportantDate> currImportantDatesList = new ArrayList<>(currImportantDatesSet);
        ImportantDate eventToDelete = currImportantDatesList.get(targetPatientIndex.getZeroBased());
        currImportantDatesList.remove(targetEventIndex.getZeroBased());
        Set<ImportantDate> newImportantDatesSet = new HashSet<>(currImportantDatesList);
        Logger logger = LogsCenter.getLogger(DeleteImportantDateCommandParser.class);
        logger.log(Level.INFO, "old set: " + currImportantDatesSet);
        logger.log(Level.INFO, "new set: " + newImportantDatesSet);

        editPatientDescriptor.setAddress(patientToDeleteImportantDate.getAddress());
        editPatientDescriptor.setEmail(patientToDeleteImportantDate.getEmail());
        editPatientDescriptor.setPhone(patientToDeleteImportantDate.getPhone());
        editPatientDescriptor.setName(patientToDeleteImportantDate.getName());
        editPatientDescriptor.setTags(patientToDeleteImportantDate.getTags());
        editPatientDescriptor.setImportantDate(newImportantDatesSet);

        Patient editedPatient = createEditedPatient(patientToDeleteImportantDate, editPatientDescriptor);
        model.setPatient(patientToDeleteImportantDate, editedPatient);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
        return new CommandResult(String.format(MESSAGE_DELETE_IMPORTANT_DATE_SUCCESS,
                Messages.format(patientToDeleteImportantDate),
                eventToDelete));
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
        return targetPatientIndex.equals(otherDeleteImportantDateCommand.targetPatientIndex)
                && targetEventIndex.equals(otherDeleteImportantDateCommand.targetEventIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetPatientIndex", targetPatientIndex)
                .add("targetEventIndex", targetEventIndex)
                .toString();
    }
}
