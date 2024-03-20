package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class TypePredicate implements Predicate<Person> {
    private final String type;

    public TypePredicate(String type) {
        this.type = type;
    }

    @Override
    public boolean test(Person person) {
        if (type.equals("housekeeper")) {
            return !person.isClient();
        } else if (type.equals("client")) {
            return person.isClient();
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TypePredicate)) {
            return false;
        }

        TypePredicate otherTypePredicate = (TypePredicate) other;
        return type.equals(otherTypePredicate.type);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("type", type).toString();
    }
}
