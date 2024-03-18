package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
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
import seedu.address.logic.parser.DeleteEventCommandParser;
import seedu.address.model.Model;
import seedu.address.model.patient.EditPatientDescriptor;
import seedu.address.model.patient.Event;
import seedu.address.model.patient.Patient;

/**
 * Deletes a patient identified using it's displayed index from the address book.
 */
public class DeleteEventCommand extends Command {

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

    public static final String MESSAGE_DELETE_EVENT_SUCCESS = "Deleted Event: %2$s for Patient: %1$s"
            + "successfully";

    private final Index targetPatientIndex;
    private final Index targetEventIndex;
    private final EditPatientDescriptor editPatientDescriptor;

    /**
     * Constructs a DeleteEventCommand to delete the specified {@code event} using
     * {@code targetEventIndex} from the Patient with id {@code targetPatientIndex}
     * @param targetPatientIndex
     * @param targetEventIndex
     */
    public DeleteEventCommand(Index targetPatientIndex, Index targetEventIndex) {
        requireAllNonNull(targetPatientIndex, targetEventIndex);
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

        Patient patientToDeleteEvent = lastShownList.get(targetPatientIndex.getZeroBased());
        Set<Event> eventSet = patientToDeleteEvent.getEvents();

        if (targetEventIndex.getZeroBased() >= eventSet.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Set<Event> currEventSet = new HashSet<>(patientToDeleteEvent.getEvents());
        List<Event> currEventList = new ArrayList<>(currEventSet);
        Event eventToDelete = currEventList.get(targetEventIndex.getZeroBased());
        currEventList.remove(targetEventIndex.getZeroBased());
        Set<Event> newEventSet = new HashSet<>(currEventList);
        Logger logger = LogsCenter.getLogger(DeleteEventCommandParser.class);
        logger.log(Level.INFO, "old set: " + currEventSet);
        logger.log(Level.INFO, "new set: " + newEventSet);

        editPatientDescriptor.setName(patientToDeleteEvent.getName());
        editPatientDescriptor.setTags(patientToDeleteEvent.getTags());
        editPatientDescriptor.setPatientHospitalId(patientToDeleteEvent.getPatientHospitalId());
        editPatientDescriptor.setPreferredName(patientToDeleteEvent.getPreferredName());
        editPatientDescriptor.setFoodPreference(patientToDeleteEvent.getFoodPreference());
        editPatientDescriptor.setFamilyCondition(patientToDeleteEvent.getFamilyCondition());
        editPatientDescriptor.setHobby(patientToDeleteEvent.getHobby());
        editPatientDescriptor.setName(patientToDeleteEvent.getName());
        editPatientDescriptor.setTags(patientToDeleteEvent.getTags());
        editPatientDescriptor.setEvents(newEventSet);

        Patient editedPatient = createEditedPatient(patientToDeleteEvent, editPatientDescriptor);
        model.setPatient(patientToDeleteEvent, editedPatient);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
        return new CommandResult(String.format(MESSAGE_DELETE_EVENT_SUCCESS,
                Messages.format(patientToDeleteEvent),
                eventToDelete));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteEventCommand)) {
            return false;
        }

        DeleteEventCommand otherDeleteEventCommand = (DeleteEventCommand) other;
        return targetPatientIndex.equals(otherDeleteEventCommand.targetPatientIndex)
                && targetEventIndex.equals(otherDeleteEventCommand.targetEventIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetPatientIndex", targetPatientIndex)
                .add("targetEventIndex", targetEventIndex)
                .toString();
    }
}
