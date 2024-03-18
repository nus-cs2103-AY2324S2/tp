package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.EditCommand.createEditedPatient;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

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
 * Adds an important date to the specified patient (based on index from the last shown patient list)
 */
public class AddEventCommand extends Command {
    public static final String COMMAND_WORD = "adde";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an Important Date for a Patient. "
            + "Parameters: INDEX (must be a positive integer matching that of the Patient in the `list` command) "
            + PREFIX_NAME + " [Name of the Event that falls on this Date] "
            + PREFIX_DATETIME + " [Date / Datetime, in the format DD-MM-YYYY"
            + " / DD-MM-YYYY, HH:mm - HH:mm respectively]\n"
            + "Example: "
            + COMMAND_WORD + " 1 "
            + PREFIX_NAME + " Father Birthday "
            + PREFIX_DATETIME + "29-09-1789";

    public static final String MESSAGE_SUCCESS = "Event %1$s successfully added for Patient %2$s with ID %3$s for %4$s";

    private final Index index;
    private final Event dateToAdd;
    private final EditPatientDescriptor editPatientDescriptor;


    /**
     * Constructs an AddEventCommand to add the specified {@code event}
     * to the Patient with id {@code index}
     *
     * @param index
     * @param event
     */
    public AddEventCommand(Index index, Event event) {
        requireAllNonNull(index, event);

        this.index = index;
        this.dateToAdd = event;
        this.editPatientDescriptor = new EditPatientDescriptor();
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(index.getZeroBased());

        Set<Event> newEventsList = new HashSet<>(patientToEdit.getEvents());
        newEventsList.add(this.dateToAdd);
        editPatientDescriptor.setEvents(newEventsList);

        Patient editedPatient = createEditedPatient(patientToEdit, editPatientDescriptor);
        model.setPatient(patientToEdit, editedPatient);
        model.updateFilteredPatientList(Model.PREDICATE_SHOW_ALL_PATIENTS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, dateToAdd.name, editedPatient.getName(),
                index.getOneBased(), dateToAdd.date));
    }

    /**
     * Returns true if both add important date commands have the same index and important date to add.
     *
     * @param other Another object to compare to.
     * @return True if the other object is an AddEventCommand with the same index and important date to add.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddEventCommand)) {
            return false;
        }

        AddEventCommand otherAddEventCommand = (AddEventCommand) other;
        return index.equals(otherAddEventCommand.index)
                && dateToAdd.equals(otherAddEventCommand.dateToAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("event", dateToAdd)
                .toString();
    }
}
