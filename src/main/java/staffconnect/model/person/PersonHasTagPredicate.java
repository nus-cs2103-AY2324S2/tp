package staffconnect.model.person;

import java.util.function.Predicate;

import staffconnect.commons.util.ToStringBuilder;
import staffconnect.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Tag} matches any of the tag names given.
 */
public class PersonHasTagPredicate implements Predicate<Person> {
    private final Tag tag; // TODO: change to multiple tags in later iterations

    public PersonHasTagPredicate(Tag tag) {
        this.tag = tag;
    }

    @Override
    public boolean test(Person person) {
        return person.getTags().stream()
                .anyMatch(t -> t.tagName.equalsIgnoreCase(tag.tagName));
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
        return tag.equals(otherPersonHasTagPredicate.tag);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("tag name", tag).toString();
    }
}
