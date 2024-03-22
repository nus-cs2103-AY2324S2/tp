package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Factory class for creating Person objects.
 */
public class PersonFactory {
    /**
     * Creates a Person based on the category.
     */
    public static Person createPerson(Name name, Phone phone, Email email,
                                      Address address, Category category, Set<Tag> tags) {
        switch (category.value) {
        case "PARTICIPANT":
            return new Participant(name, phone, email, address, category, tags);
        case "STAFF":
            return new Staff(name, phone, email, address, category, tags);
        case "SPONSOR":
            return new Sponsor(name, phone, email, address, category, tags);
        default:
            throw new IllegalArgumentException("Invalid category");
        }
    }
}
