package seedu.address.model.patient;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Phone} matches the number given.
 */
public class PhoneMatchesPredicate implements Predicate<Patient> {
    private final Phone phone;

    public PhoneMatchesPredicate(Phone phone) {
        this.phone = phone;
    }

    @Override
    public boolean test(Patient patient) {
        if (patient == null) {
            return false;
        }
        return patient.getPhone().equals(this.phone);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PhoneMatchesPredicate)) {
            return false;
        }

        PhoneMatchesPredicate otherPhoneMatchesPredicate = (PhoneMatchesPredicate) other;
        return phone.equals(otherPhoneMatchesPredicate.phone);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("phone", phone).toString();
    }
}
