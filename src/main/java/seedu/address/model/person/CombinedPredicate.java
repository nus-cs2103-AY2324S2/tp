package seedu.address.model.person;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Predicate that combines all the different predicates.
 */
public class CombinedPredicate implements Predicate<Person> {
    private final Predicate<Person> combinedPredicate;
    private final List<Predicate<Person>> predicateList;

    /**
     * Constructs a {@code CombinedPredicate}.
     *
     * @param predicates The list of predicates.
     */
    public CombinedPredicate(Predicate<Person>... predicates) {
        combinedPredicate = Arrays.stream(predicates).reduce(p -> false, Predicate::or);
        predicateList = Arrays.asList(predicates);
    }

    @Override
    public boolean test(Person person) {
        return combinedPredicate.test(person);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CombinedPredicate)) {
            return false;
        }

        CombinedPredicate otherCombinedPredicate = (CombinedPredicate) other;
        return predicateList.equals(otherCombinedPredicate.predicateList);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicateList",
                        predicateList.stream().map(Predicate::toString).collect(Collectors.joining()))
                .toString();
    }
}
