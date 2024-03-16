package seedu.address.model.person;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} is from any of the classGroups given or all
 */
public class ListCommandPredicate implements Predicate<Person> {

    private final Optional<List<ClassGroup>> classGroups;

    public ListCommandPredicate(Optional<List<ClassGroup>> classGroups) {
        this.classGroups = classGroups;
    }

    public Optional<List<ClassGroup>> getClassGroups() {
        return classGroups;
    }

    /**
     * If the classGroups is not empty, tests that a {@code Person}'s {@code ClassGroup} matches any of the
     * keywords given. If the classGroups is empty, all persons are shown, so return true.
     * @param person The person to be tested
     */
    @Override
    public boolean test(Person person) {
        return classGroups.map(groups -> groups.stream()
                .anyMatch(keyword -> person.getClassGroup().toString().startsWith(keyword.toString())))
                .orElse(true);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ListCommandPredicate)) {
            return false;
        }

        ListCommandPredicate otherPredicate = (ListCommandPredicate) other;
        return classGroups.equals(otherPredicate.classGroups);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("classGroups", classGroups).toString();
    }
}
