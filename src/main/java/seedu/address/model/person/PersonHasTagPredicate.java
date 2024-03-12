package seedu.address.model.person;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

import java.util.List;
import java.util.function.Predicate;

public class PersonHasTagPredicate implements Predicate<Person> {

    private final List<String> keywords;

    public PersonHasTagPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> person.getTags().contains(new Tag(keyword)));
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
