package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

import java.util.function.Predicate;

public class PersonContainsTagPredicate implements Predicate<Person> {

    private final Tag tag;

    public PersonContainsTagPredicate(Tag tag) {
        this.tag = tag;
    }

    @Override
    public boolean test(Person person) {
        return person.getTags().stream().anyMatch(keyword ->
                StringUtil.containsWordIgnoreCase(tag.tagName, keyword.tagName));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonContainsTagPredicate)) {
            return false;
        }

        PersonContainsTagPredicate personContainsTagPredicate =
                (PersonContainsTagPredicate) other;
        return tag.equals(personContainsTagPredicate.tag);
    }
}
