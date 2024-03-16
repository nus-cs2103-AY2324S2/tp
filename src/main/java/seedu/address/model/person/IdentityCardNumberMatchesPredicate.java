package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code IdentityCardNumber} matches the given IC number.
 */
public class IdentityCardNumberMatchesPredicate implements Predicate<Person> {
    private final IdentityCardNumber targetIcNumber;

    public IdentityCardNumberMatchesPredicate(IdentityCardNumber targetIcNumber) {
        this.targetIcNumber = targetIcNumber;
    }

    @Override
    public boolean test(Person person) {
        return person.getIdentityCardNumber().equals(targetIcNumber);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof IdentityCardNumberMatchesPredicate)) {
            return false;
        }

        IdentityCardNumberMatchesPredicate predicate = (IdentityCardNumberMatchesPredicate) other;
        return targetIcNumber.equals(predicate.targetIcNumber);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }

}

