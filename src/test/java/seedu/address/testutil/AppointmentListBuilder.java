package seedu.address.testutil;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentList;

/**
 * A utility class to help with building AppointmentList objects.
 */
public class AppointmentListBuilder {

    private AppointmentList appointmentList;

    public AppointmentListBuilder() {
        appointmentList = new AppointmentList();
    }

    public AppointmentListBuilder(AppointmentList appointmentList) {
        this.appointmentList = appointmentList;
    }

    /**
     * Adds a new {@code Appointment} to the {@code Appointment} that we are building.
     */
    public AppointmentListBuilder withAppointment(Appointment appointment) {
        appointmentList.addAppointment(appointment);
        return this;
    }

    public AppointmentList build() {
        return appointmentList;
    }
}
