package seedu.address.model.person;

import seedu.address.model.tag.Tag;

import java.util.function.Predicate;

public class PersonContainsTagPredicate implements Predicate<Person> {

    private final Tag tag;

    public PersonContainsTagPredicate(Tag tag) {
        this.tag = tag;
    }

    @Override
    public boolean test(Person person) {
        return person.getTags().contains(tag);
    }
}
