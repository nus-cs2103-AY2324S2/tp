package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Tag}s matches any of the keywords given.
 */
public class PersonHasTagPredicate implements Predicate<Person> {

    private final List<Tag> keywords;

    public PersonHasTagPredicate(List<Tag> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> person.getTags().contains(keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonHasTagPredicate)) {
            return false;
        }

        PersonHasTagPredicate otherPersonHasTagPredicate = (PersonHasTagPredicate) other;
        return keywords.equals(otherPersonHasTagPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
