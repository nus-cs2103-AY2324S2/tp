package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s ID matches the given ID.
 */
public class IdEqualsPredicate implements Predicate<Person> {
    private final String id;

    public IdEqualsPredicate(String id) {
        this.id = id;
    }

    @Override
    public boolean test(Person person) {
        return person.getId().toString().equals(id);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof IdEqualsPredicate)) {
            return false;
        }

        IdEqualsPredicate otherIdEqualsPredicate = (IdEqualsPredicate) other;
        return id.equals(otherIdEqualsPredicate.id);
    }
}
