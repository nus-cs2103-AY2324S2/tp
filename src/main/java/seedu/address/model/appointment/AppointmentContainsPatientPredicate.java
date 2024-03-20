package seedu.address.model.appointment;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.PatientNameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

import java.util.List;
import java.util.function.Predicate;

public class AppointmentContainsPatientPredicate implements Predicate<Appointment> {
    private final List<String> keywords;

    public AppointmentContainsPatientPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Appointment appointment) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(appointment.get.fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppointmentContainsPatientPredicate)) {
            return false;
        }

        AppointmentContainsPatientPredicate otherPredicate = (AppointmentContainsPatientPredicate) other;
        return keywords.equals(otherPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}

