package seedu.internhub.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Tag} matches the tag given.
 */
public class MatchingTagPredicate implements Predicate<Person> {

    private final String tag;

    public MatchingTagPredicate(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean test(Person person) {
        return (tag).equals(person.getTag().getTagShort());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MatchingTagPredicate)) {
            return false;
        }

        MatchingTagPredicate otherMatchingTagPredicate = (MatchingTagPredicate) other;
        return tag.equals(otherMatchingTagPredicate.tag);
    }

    @Override
    public String toString() {
        return "tag " + tag;
    }
}
