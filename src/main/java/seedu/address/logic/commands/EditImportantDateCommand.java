package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.EditCommand.createEditedPatient;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IMPORTANT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.EditPatientDescriptor;
import seedu.address.model.patient.ImportantDate;
import seedu.address.model.patient.Patient;

/**
 * Edits a specific important date for a patient in the address book.
 */
public class EditImportantDateCommand extends Command {
    public static final String COMMAND_WORD = "edite";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits a specified important date for a specific patient using the patient index"
            + " in the patient list and event index.\n"
            + "Parameters: INDEX (must be a positive integer matching that of the Patient in the `list` command)"
            + " e/ [Index of the updated Event]"
            + " n/ [Name of the updated Event]"
            + " d/ [Updated Date / Datetime, in the format DD-MM-YYYY"
            + " / DD-MM-YYYY, HH:mm - HH:mm respectively]\n"
            + "Example: "
            + COMMAND_WORD + " 1 "
            + PREFIX_IMPORTANT_DATE + " 1"
            + PREFIX_NAME + " Updated Event "
            + PREFIX_DATETIME + " 12-10-2024";

    public static final String MESSAGE_SUCCESS = "Event %1$s with ID %2$s successfully updated "
            + "for Patient %3$s with ID %4$s for %5$s";

    private final Index patientIndex;
    private final Index eventIndex;
    private final ImportantDate dateToUpdate;
    private final EditPatientDescriptor editPatientDescriptor;

    /**
     * Constructs an EditImportantDateCommand to edit the specified {@code importantDate}
     * for {@code eventIndex} to the Patient with id {@code patientIndex}
     * @param patientIndex The id of the patient.
     * @param eventIndex The id of the important date.
     * @param dateToUpdate The updated important date.
     */
    public EditImportantDateCommand(Index patientIndex, Index eventIndex, ImportantDate dateToUpdate) {
        requireAllNonNull(patientIndex, eventIndex, dateToUpdate);
        this.patientIndex = patientIndex;
        this.eventIndex = eventIndex;
        this.dateToUpdate = dateToUpdate;
        this.editPatientDescriptor = new EditPatientDescriptor();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (patientIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToEditImportantDate = lastShownList.get(patientIndex.getZeroBased());
        Set<ImportantDate> importantDatesSet = patientToEditImportantDate.getImportantDates();

        if (eventIndex.getZeroBased() >= importantDatesSet.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_IMPORTANT_DATE_DISPLAYED_INDEX);
        }

        List<ImportantDate> importantDatesList = new ArrayList<>(importantDatesSet);
        importantDatesList.set(eventIndex.getZeroBased(), dateToUpdate);
        Set<ImportantDate> updatedImportantDates = new HashSet<>(importantDatesList);
        editPatientDescriptor.setImportantDate(updatedImportantDates);

        Patient updatedPatient = createEditedPatient(patientToEditImportantDate, editPatientDescriptor);
        model.setPatient(patientToEditImportantDate, updatedPatient);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, dateToUpdate.name, eventIndex.getOneBased(),
                patientToEditImportantDate.getName(), patientIndex.getOneBased(), dateToUpdate.importantDate));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EditImportantDateCommand)) {
            return false;
        }

        EditImportantDateCommand otherEditImportantDateCommand = (EditImportantDateCommand) other;
        return patientIndex.equals(otherEditImportantDateCommand.patientIndex)
                && eventIndex.equals(otherEditImportantDateCommand.eventIndex)
                && dateToUpdate.equals(otherEditImportantDateCommand.dateToUpdate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("patientIndex", patientIndex)
                .add("eventIndex", eventIndex)
                .add("importantDate", dateToUpdate)
                .toString();
    }
}
