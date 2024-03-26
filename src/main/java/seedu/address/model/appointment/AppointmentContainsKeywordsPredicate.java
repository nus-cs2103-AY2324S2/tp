package seedu.address.model.appointment;

import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.commons.core.date.Date;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Nric;

/**
 * Tests that a {@code Appointment}'s {@code NRIC}, {@code Date}, {@code Time} matches any of the filters given.
 */
public class AppointmentContainsKeywordsPredicate implements Predicate<AppointmentView> {
    private final Optional<Nric> nricFilter;
    private final Optional<Date> dateFilter;
    private final Optional<Time> timeFilter;

    /**
     * Constructs a {@code AppointmentContainsKeywordsPredicate}.
     *
     * @param nricFilter
     * @param dateFilter
     * @param timeFilter
     */
    public AppointmentContainsKeywordsPredicate(Optional<Nric> nricFilter,
                                                Optional<Date> dateFilter,
                                                Optional<Time> timeFilter) {
        this.nricFilter = nricFilter;
        this.dateFilter = dateFilter;
        this.timeFilter = timeFilter;
    }

    @Override
    public boolean test(AppointmentView appointment) {
        boolean matchesNric = true;
        boolean matchesDate = true;
        boolean matchesTimePeriod = true;

        if (nricFilter.isPresent()) {
            Nric filterNric = nricFilter.get();
            matchesNric = appointment.getAppointment().getNric().equals(filterNric);
        }

        if (dateFilter.isPresent()) {
            Date filterDate = dateFilter.get();
            matchesDate = appointment.getAppointment().getDate().equals(filterDate);
        }

        if (timeFilter.isPresent()) {
            Time filterTime = timeFilter.get();
            matchesTimePeriod = appointment.getAppointment().getTimePeriod().getStartTime().compareTo(filterTime) == 1
                    || appointment.getAppointment().getTimePeriod().getStartTime().equals(filterTime);
        }

        return matchesNric && matchesDate && matchesTimePeriod;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppointmentContainsKeywordsPredicate)) {
            return false;
        }

        AppointmentContainsKeywordsPredicate otherNameContainsKeywordsPredicate =
                (AppointmentContainsKeywordsPredicate) other;

        return nricFilter.equals(otherNameContainsKeywordsPredicate.nricFilter)
                && dateFilter.equals(otherNameContainsKeywordsPredicate.dateFilter)
                && timeFilter.equals(otherNameContainsKeywordsPredicate.timeFilter);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("nricFilter", nricFilter)
                .add("dateFilter", dateFilter)
                .add("timeFilter", timeFilter)
                .toString();
    }
}
