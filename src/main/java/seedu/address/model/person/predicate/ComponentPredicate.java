package seedu.address.model.person.predicate;

import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * A predicate to filter a specific component of a Person class that can be treated like a string.
 * <p>
 *     In the case of singular values, this means the predicate has to match.
 *     However, with aggregate values such as tags, it's a success if any one of the elements match.
 * </p>
 */
public interface ComponentPredicate extends Predicate<Person> {}
