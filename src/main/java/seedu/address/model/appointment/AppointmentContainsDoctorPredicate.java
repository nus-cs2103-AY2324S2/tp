package seedu.address.model.appointment;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Predicate used to test if an Appointment contains specified doctor keywords.
 */
public class AppointmentContainsDoctorPredicate implements Predicate<Appointment> {
    private final List<String> keywords;

    public AppointmentContainsDoctorPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Appointment appointment) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(appointment.getDoctorNric().nric, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppointmentContainsDoctorPredicate)) {
            return false;
        }

        AppointmentContainsDoctorPredicate otherPredicate = (AppointmentContainsDoctorPredicate) other;
        return keywords.equals(otherPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
