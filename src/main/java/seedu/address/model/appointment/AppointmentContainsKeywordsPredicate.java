package seedu.address.model.appointment;

import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.commons.core.date.Date;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Nric;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class AppointmentContainsKeywordsPredicate implements Predicate<Appointment> {
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
    public boolean test(Appointment appointment) {
        boolean matchesNric = true;
        boolean matchesDate = true;
        boolean matchesTimePeriod = true;

        boolean test = nricFilter.isPresent();

        if (nricFilter.isPresent()) {
            matchesNric = false;
            Nric filterNric = nricFilter.get();
            matchesNric = appointment.getNric().equals(filterNric);
        }

        if (dateFilter.isPresent()) {
            matchesDate = false;
            Date filterDate = dateFilter.get();
            matchesDate = appointment.getDate().equals(filterDate);
//            matchesDate = dateFilter.map(date -> appointment.getDate().equals(date)).orElse(true);
        }

        if (timeFilter.isPresent()) {
            matchesTimePeriod = false;
            Time filterTime = timeFilter.get();
            matchesTimePeriod = appointment.getTimePeriod().getStartTime().equals(filterTime);
//            matchesTimePeriod = timeFilter.map(time -> appointment.getTimePeriod().getStartTime()
//                                .equals(time)).orElse(true);
        }

//        matchesNric = nricFilter.isPresent() && nricFilter.map(nric -> appointment
//                                .getNric().equals(nric)).orElse(true);
//        matchesDate = dateFilter.isPresent() && dateFilter.map(date -> appointment
//                                .getDate().equals(date)).orElse(true);
//        matchesTimePeriod = timeFilter.isPresent() && timeFilter.map(time -> appointment.getTimePeriod().getStartTime()
//                                        .equals(time)).orElse(true);

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
