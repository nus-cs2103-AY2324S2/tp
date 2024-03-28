package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.UniquePatientList;


/**
 * A list of appointmentViews that enforces uniqueness between its elements and does not allow nulls.
 * An appointmentView is considered unique by comparing using
 * {@code AppointmentView#isSameAppointment(AppointmentView)}.
 * As such, adding and updating of appointments uses AppointmentView#isSameAppointment(AppointmentView) for equality
 * to ensure that the appointmentView being added or updated is unique and not duplicated.
 * However, the removal of an appointmentView uses Patient#equals(Object) to ensure that the
 * appointmentView with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see AppointmentView#isSameAppointmentView(AppointmentView)
 */
public class AppointmentViewList implements Iterable<AppointmentView> {

    private final ObservableList<AppointmentView> internalList = FXCollections.observableArrayList();
    private final ObservableList<AppointmentView> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);


    public void setAppointmentViews(UniquePatientList patientList, List<Appointment> appointmentList) {
        requireAllNonNull(patientList, appointmentList);
        internalList.clear();

        for (Patient patient : patientList) {
            Nric patientNric = patient.getNric();

            for (Appointment appointment : appointmentList) {
                Nric appointmentNric = appointment.getNric();

                // Check if the NRICs match
                if (patientNric.equals(appointmentNric)) {
                    AppointmentView appointmentView = new AppointmentView(patient.getName(), appointment);
                    internalList.add(appointmentView);
                }
            }
        }
        sortList();
    }


    public void setAppointmentViews(UniquePatientList patientList, AppointmentList appointmentList) {
        requireAllNonNull(patientList, appointmentList);
        internalList.clear();

        for (Patient patient : patientList) {
            Nric patientNric = patient.getNric();

            for (Appointment appointment : appointmentList) {
                Nric appointmentNric = appointment.getNric();

                // Check if the NRICs match
                if (patientNric.equals(appointmentNric)) {
                    AppointmentView appointmentView = new AppointmentView(patient.getName(), appointment);
                    internalList.add(appointmentView);
                }
            }
        }
        sortList();
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<AppointmentView> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<AppointmentView> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppointmentViewList)) {
            return false;
        }

        AppointmentViewList otherAppointmentList = (AppointmentViewList) other;
        return internalList.equals(otherAppointmentList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code appointmentViews} contains only unique appointmentViews.
     */
    private boolean appointmentViewsAreUnique(List<AppointmentView> appointments) {
        for (int i = 0; i < appointments.size() - 1; i++) {
            for (int j = i + 1; j < appointments.size(); j++) {
                if (appointments.get(i).isSameAppointmentView(appointments.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Sort internal list according to date and time
     */
    private void sortList() {
        internalList.sort(
            Comparator.comparing((AppointmentView appointmentView) -> appointmentView.getAppointment().getDate())
                .thenComparing((AppointmentView appointmentView) -> appointmentView.getAppointment().getTimePeriod()));
    }
}
