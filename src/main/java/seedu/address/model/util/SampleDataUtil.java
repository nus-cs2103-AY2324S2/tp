package seedu.address.model.util;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Course;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), Optional.of(new Phone("87438807")), new Email("alexyeoh@example.com"),
                new Role("STUDENT"), new Address("Blk 30 Geylang Street 29, #06-40"),
                new Course("CS2103T"), getTagSet("friends")),
            new Person(new Name("Bernice Yu"), Optional.of(new Phone("99272758")), new Email("berniceyu@example.com"),
                new Role("STUDENT"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                new Course("CS2103"), getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), Optional.of(new Phone("93210283")),
                new Email("charlotte@example.com"), new Role("TA"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                new Course("DSA1101"), getTagSet("neighbours")),
            new Person(new Name("David Li"), Optional.of(new Phone("91031282")), new Email("lidavid@example.com"),
                new Role("TA"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                new Course("MA2001"), getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), Optional.of(new Phone("92492021")), new Email("irfan@example.com"),
                new Role("PROFESSOR"), new Address("Blk 47 Tampines Street 20, #17-35"),
                new Course("EL1101E"), getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), Optional.of(new Phone("92624417")), new Email("royb@example.com"),
                new Role("PROFESSOR"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                new Course("ACC1701X"), getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
