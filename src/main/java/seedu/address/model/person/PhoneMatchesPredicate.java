package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Phone} matches the number given.
 */
public class PhoneMatchesPredicate implements Predicate<Person> {
    private final Phone phone;

    public PhoneMatchesPredicate(Phone phone) {
        this.phone = phone;
    }

    @Override
    public boolean test(Person person) {
        return person.getPhone().equals(this.phone);
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
