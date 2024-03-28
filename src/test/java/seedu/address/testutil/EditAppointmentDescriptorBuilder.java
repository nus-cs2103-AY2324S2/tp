package seedu.address.testutil;

import seedu.address.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDate;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditAppointmentDescriptorBuilder {

    private EditAppointmentDescriptor descriptor;

    public EditAppointmentDescriptorBuilder() {
        descriptor = new EditAppointmentDescriptor();
    }

    public EditAppointmentDescriptorBuilder(EditAppointmentDescriptor descriptor) {
        this.descriptor = new EditAppointmentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditAppointmentDescriptor} with fields containing {@code appointment}'s details
     */
    public EditAppointmentDescriptorBuilder(Appointment appointment) {
        descriptor = new EditAppointmentDescriptor();
        descriptor.setDate(appointment.getAppointmentDate());
        descriptor.setDoctorNric(appointment.getDoctorNric());
        descriptor.setPatientNric(appointment.getPatientNric());
    }

    /**
     * Sets the {@code Date} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withDate(String date) {
        descriptor.setDate(new AppointmentDate(date));
        return this;
    }

    /**
     * Builds the descriptor.
     */
    public EditAppointmentDescriptor build() {
        return descriptor;
    }
}
