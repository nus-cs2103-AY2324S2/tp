package seedu.address.logic.commands.appointment;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;

/**
 * Performs a fuzzy search across {@code Appointment} and its associated
 * {@code Person}.
 * Tests that an {@code Appointment}'s {@code AppointmentTime} matches any of
 * the keywords given, AND that its corresponding {@code Person}'s name matches
 * any of the keywords given.
 * If no appointmentTime matches are found, but name(s) are found,
 * return all appointments corresponding to the name(s).
 * If no name matches are found, but appointmentTime matches are found.
 * If both appointmentTime matches and name matches are found, return their
 * intersection.
 */
public class AppointmentContainsKeywordsPredicate implements Predicate<Appointment> {

    private final Model model;
    private final List<String> keywords;

    private AppointmentContainsKeywordsPredicate(Model model, List<String> keywords) {
        this.model = model;
        this.keywords = keywords;
    }

    /**
     * Factory method to create a function that produces an instance of
     * {@code AppointmentContainsKeywordsPredicate} with the specified keywords.
     * As model is binded late, this faciliates the deferred instantiation of the
     * predicate.
     *
     * @param keywords The list of keywords used to filter appointments.
     * @return A function taking a {@code Model} and returning a new instance of
     * {@code AppointmentContainsKeywordsPredicate}.
     */
    public static Function<Model, AppointmentContainsKeywordsPredicate> build(List<String> keywords) {
        return model -> new AppointmentContainsKeywordsPredicate(model, keywords);
    }

    /**
     * Current implementation just tests that the {@code Appointment}'s associated
     * {@code Person}'s name matches.
     * TODO: implement matching that somehow distinguishes between appointmentTime
     * and person name. This could be done with d/.
     */
    @Override
    public boolean test(Appointment appointment) {
        // If name matches, and appointment matches, return their intersection

        // If no name matches, then return appointment matches

        // If no appointment matches, then return name matches
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        model.getPersonById(appointment.getPersonId()).getName().fullName, keyword));
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

        AppointmentContainsKeywordsPredicate otherAppointmentContainsKeywordsPredicate = (AppointmentContainsKeywordsPredicate) other;
        return keywords.equals(otherAppointmentContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }

}