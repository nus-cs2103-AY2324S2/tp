package staffconnect.model.person.predicates;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import staffconnect.commons.util.ToStringBuilder;
import staffconnect.model.person.Person;
import staffconnect.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Tags} matches any of the tag names given.
 */
public class PersonHasTagsPredicate implements Predicate<Person> {
    private final Set<Tag> tags;

    public PersonHasTagsPredicate(Set<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean test(Person person) {
        if (tags == null) {
            return true;
        }
        // get list of person tags
        List<String> personTags = person.getTags().stream().map(t -> t.tagName.toLowerCase())
                .collect(Collectors.toList());
        // get stream of tags to filter from
        Stream<String> tagsToFilter = tags.stream().map(t -> t.tagName.toLowerCase())
                .collect(Collectors.toList()).stream();
        // check if the person DOES NOT contain any of the tags to filter from, if true
        // then predicate is not satisfied
        return !tagsToFilter.anyMatch(tag -> !personTags.contains(tag));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonHasTagsPredicate)) {
            return false;
        }

        PersonHasTagsPredicate otherPersonHasTagsPredicate = (PersonHasTagsPredicate) other;
        return tags.equals(otherPersonHasTagsPredicate.tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("tag names", tags).toString();
    }
}
