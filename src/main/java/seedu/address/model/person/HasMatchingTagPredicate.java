package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s tag set contains all the filter tag(s).
 */
public class HasMatchingTagPredicate implements Predicate<Person> {
    private final List<String> filterTags;

    public HasMatchingTagPredicate(List<String> filterTags) {
        this.filterTags = filterTags;
    }

    @Override
    public boolean test(Person person) {
        // Check if person has all filterTags
        return filterTags.stream()
                .allMatch(filterTag -> person.getTags().stream()
                        .anyMatch(personTag -> personTag.tagName.equalsIgnoreCase(filterTag)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof HasMatchingTagPredicate)) {
            return false;
        }

        HasMatchingTagPredicate otherHasMatchingTagPredicate = (HasMatchingTagPredicate) other;
        return filterTags.equals(otherHasMatchingTagPredicate.filterTags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("filter tags", filterTags).toString();
    }

}
