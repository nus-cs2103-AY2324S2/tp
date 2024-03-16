package seedu.address.model.person.predicates;

import java.util.Arrays;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Defines a Predicate which combines multiple {@code SearchPredicates}.
 */
public class CombinedPredicates implements Predicate<Person> {
    private final SearchPredicate<? extends Object>[] predicates;

    @SafeVarargs
    public CombinedPredicates(SearchPredicate<? extends Object>... predicates) {
        this.predicates = predicates;
    }

    @Override
    public boolean test(Person person) {
        return Arrays.stream(predicates).allMatch(predicate -> predicate.test(person));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CombinedPredicates)) {
            return false;
        }

        CombinedPredicates otherCombinedPredicate = (CombinedPredicates) other;
        return Arrays.equals(predicates, otherCombinedPredicate.predicates);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("predicates", predicates).toString();
    }

}
