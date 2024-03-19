package seedu.address.commons.util;

import java.util.function.Predicate;

/**
 * Predicate that always returns true.
 */
public class AlwaysTruePredicate<T> implements Predicate<T> {
    @Override
    public boolean test(T t) {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AlwaysTruePredicate)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "true";
    }
}
