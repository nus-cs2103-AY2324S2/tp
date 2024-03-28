package seedu.findvisor.model.person;

import java.util.function.Predicate;

/**
 * Represents a Predicate that is able to evaluate a {@code Person} field.
 */
public interface PersonPredicate extends Predicate<Person> {

    /**
     * Returns a description of the predicate condition.
     *
     * @return a String representing the description of the predicate condition.
     */
    public String getPredicateDescription();

}
