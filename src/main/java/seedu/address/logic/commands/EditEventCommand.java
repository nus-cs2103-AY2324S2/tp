package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.EditCommand.createEditedPatient;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT;
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
import seedu.address.model.patient.Event;
import seedu.address.model.patient.Patient;

/**
 * Edits an event for a patient in the address book.
 */
public class EditEventCommand extends Command {
    public static final String COMMAND_WORD = "edite";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits an event for a specific patient using the patient index"
            + " in the patient list and event index.\n"
            + "Parameters: INDEX (must be a positive integer matching that of the Patient in the `list` command)"
            + " e/ [Index of the updated Event]"
            + " n/ [Name of the updated Event]"
            + " d/ [Updated Date / Datetime, in the format DD-MM-YYYY"
            + " / DD-MM-YYYY, HH:mm - HH:mm respectively]\n"
            + "Example: "
            + COMMAND_WORD + " 1 "
            + PREFIX_EVENT + " 1"
            + PREFIX_NAME + " Updated Event "
            + PREFIX_DATETIME + " 12-10-2024";

    public static final String MESSAGE_SUCCESS = "Event %1$s with ID %2$s on %3$s successfully updated "
            + "for Patient %4$s with ID %5$s";

    private final Index patientIndex;
    private final Index eventIndex;
    private final Event eventToUpdate;
    private final EditPatientDescriptor editPatientDescriptor;

    /**
     * Constructs an EditEventCommand to edit the specified {@code eventToUpdate}
     * for {@code eventIndex} to the Patient with id {@code patientIndex}
     * @param patientIndex The id of the patient.
     * @param eventIndex The id of the event.
     * @param eventToUpdate The updated event.
     */
    public EditEventCommand(Index patientIndex, Index eventIndex, Event eventToUpdate) {
        requireAllNonNull(patientIndex, eventIndex, eventToUpdate);
        this.patientIndex = patientIndex;
        this.eventIndex = eventIndex;
        this.eventToUpdate = eventToUpdate;
        this.editPatientDescriptor = new EditPatientDescriptor();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (patientIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToEditEvent = lastShownList.get(patientIndex.getZeroBased());
        Set<Event> events = patientToEditEvent.getEvents();

        if (eventIndex.getZeroBased() >= events.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        List<Event> eventList = new ArrayList<>(events);
        eventList.set(eventIndex.getZeroBased(), eventToUpdate);
        Set<Event> updatedEvents = new HashSet<>(eventList);
        editPatientDescriptor.setEvents(updatedEvents);

        Patient updatedPatient = createEditedPatient(patientToEditEvent, editPatientDescriptor);
        model.setPatient(patientToEditEvent, updatedPatient);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, eventToUpdate.name, eventIndex.getOneBased(),
                eventToUpdate.date, patientToEditEvent.getName(), patientIndex.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EditEventCommand)) {
            return false;
        }

        EditEventCommand otherEditEventCommand = (EditEventCommand) other;
        return patientIndex.equals(otherEditEventCommand.patientIndex)
                && eventIndex.equals(otherEditEventCommand.eventIndex)
                && eventToUpdate.equals(otherEditEventCommand.eventToUpdate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("patientIndex", patientIndex)
                .add("eventIndex", eventIndex)
                .add("eventToUpdate", eventToUpdate)
                .toString();
    }
}
