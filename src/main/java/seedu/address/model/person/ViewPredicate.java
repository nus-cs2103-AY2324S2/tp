package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.core.index.Index;

/**
 * Represents a Predicate used to filter a list of persons to display only the person
 * with the specified index.
 */
public class ViewPredicate implements Predicate<Person> {
    private final Index index;
    private final Person person;

    /**
     * Constructs a ViewPredicate with the specified index and person.
     * @param index The index of the person to be filtered.
     * @param person The person to be displayed.
     */
    public ViewPredicate(Index index, Person person) {
        this.index = index;
        this.person = person;
    }

    /**
     * Tests if a given person matches the filter criteria.
     * @param otherPerson The person to be tested.
     * @return True if the given person matches the filter criteria, false otherwise.
     */
    @Override
    public boolean test(Person otherPerson) {
        // Compare the person's index in the filtered list with the target index
        return otherPerson.equals(person);
    }

    /**
     * Gets the index associated with this predicate.
     * @return The index.
     */
    public Index getIndex() {
        return this.index;
    }

    /**
     * Checks if this ViewPredicate is equal to another object.
     * @param other The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ViewPredicate)) {
            return false;
        }
        ViewPredicate indexPredicate = (ViewPredicate) other;
        return index.equals(indexPredicate.index);
    }

    /**
     * Returns a string representation of the ViewPredicate.
     * @return A string representation of the ViewPredicate.
     */
    @Override
    public String toString() {
        return "Index: " + index;
    }
}
