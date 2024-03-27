package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDate;
import seedu.address.model.person.Nric;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "editappt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the appointment identified "
            + "by the index number used in the displayed appointment list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_DATE + "DATE] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DATE + "2024-04-09";

    public static final String MESSAGE_EDIT_APPOINTMENT_SUCCESS = "Edited Appointment: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment already exists in the address book.";

    private final Index index;
    private final EditAppointmentDescriptor editAppointmentDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editAppointmentDescriptor details to edit the person with
     */
    public EditAppointmentCommand(Index index, EditAppointmentDescriptor editAppointmentDescriptor) {
        requireNonNull(index);
        requireNonNull(editAppointmentDescriptor);

        this.index = index;
        this.editAppointmentDescriptor = new EditAppointmentDescriptor(editAppointmentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToEdit = lastShownList.get(index.getZeroBased());
        Appointment editedAppointment = createEditedAppointment(appointmentToEdit, editAppointmentDescriptor);

        if (!appointmentToEdit.isSameAppointment(editedAppointment) && model.hasAppointment(editedAppointment)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        model.setAppointment(appointmentToEdit, editedAppointment);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_APPOINTMENT_SUCCESS, Messages.format(editedAppointment)));
    }

    /**
     * Creates and returns a {@code Appointment} with the details of {@code appointmentToEdit}
     * edited with {@code editAppointmentDescriptor}.
     */
    private static Appointment createEditedAppointment(Appointment appointmentToEdit,
                                                       EditAppointmentDescriptor editAppointmentDescriptor) {
        assert appointmentToEdit != null;

        Nric doctorNric = appointmentToEdit.getDoctorNric();
        Nric patientNric = appointmentToEdit.getPatientNric();
        AppointmentDate updatedDate = editAppointmentDescriptor
                .getDate().orElse(appointmentToEdit.getAppointmentDate());

        return new Appointment(doctorNric, patientNric, updatedDate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditAppointmentCommand)) {
            return false;
        }

        EditAppointmentCommand otherEditAppointmentCommand = (EditAppointmentCommand) other;
        return index.equals(otherEditAppointmentCommand.index)
                && editAppointmentDescriptor.equals(otherEditAppointmentCommand.editAppointmentDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editAppointmentDescriptor", editAppointmentDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the appointment with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditAppointmentDescriptor {
        private Nric doctorNric;
        private Nric patientNric;
        private AppointmentDate apptdate;

        public EditAppointmentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditAppointmentDescriptor(EditAppointmentDescriptor toCopy) {
            setDate(toCopy.apptdate);
            setDoctorNric(toCopy.doctorNric);
            setPatientNric(toCopy.patientNric);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(apptdate);
        }

        public void setDate(AppointmentDate date) {
            this.apptdate = date;
        }

        public Optional<AppointmentDate> getDate() {
            return Optional.ofNullable(apptdate);
        }

        public void setDoctorNric(Nric nric) {
            this.doctorNric = nric;
        }

        public Optional<Nric> getDoctorNric() {
            return Optional.ofNullable(doctorNric);
        }

        public void setPatientNric(Nric nric) {
            this.patientNric = nric;
        }

        public Optional<Nric> getPatientNric() {
            return Optional.ofNullable(patientNric);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditAppointmentDescriptor)) {
                return false;
            }

            EditAppointmentDescriptor otherEditAppointmentDescriptor = (EditAppointmentDescriptor) other;
            return Objects.equals(apptdate, otherEditAppointmentDescriptor.apptdate)
                    && Objects.equals(doctorNric, otherEditAppointmentDescriptor.doctorNric)
                    && Objects.equals(patientNric, otherEditAppointmentDescriptor.patientNric);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("apptdate", apptdate)
                    .toString();
        }
    }
}
