package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person} has a tag that matches the given tag name.
 */
public class TagEqualsPredicate implements Predicate<Person> {
    private final String tagName;

    public TagEqualsPredicate(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public boolean test(Person person) {
        return person.getTags().stream()
                .anyMatch(tag -> tag.tagName.equals(tagName));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagEqualsPredicate)) {
            return false;
        }

        TagEqualsPredicate otherTagEqualsPredicate = (TagEqualsPredicate) other;
        return tagName.equals(otherTagEqualsPredicate.tagName);
    }
}
