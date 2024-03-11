package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        Person p1 = new Person(new Name("Alex Yeoh"), getTagSet("friends"));
        p1.addEntry(new Phone("87438807"));
        p1.addEntry(new Email("alexyeoh@example.com"));
        p1.addEntry(new Address("Blk 30 Geylang Street 29, #06-40"));

        Person p2 = new Person(new Name("Bernice Yu"), getTagSet("colleagues", "friends"));
        p2.addEntry(new Phone("99272758"));
        p2.addEntry(new Email("berniceyu@example.com"));
        p2.addEntry(new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"));

        Person p3 = new Person(new Name("Charlotte Oliveiro"), getTagSet("neighbours"));
        p3.addEntry(new Phone("93210283"));
        p3.addEntry(new Email("charlotte@example.com"));
        p3.addEntry(new Address("Blk 11 Ang Mo Kio Street 74, #11-04"));

        Person p4 = new Person(new Name("David Li"), getTagSet("family"));
        p4.addEntry(new Phone("91031282"));
        p4.addEntry(new Email("lidavid@example.com"));
        p4.addEntry(new Address("Blk 436 Serangoon Gardens Street 26, #16-43"));

        Person p5 = new Person(new Name("Irfan Ibrahim"), getTagSet("classmates"));
        p5.addEntry(new Phone("92492021"));
        p5.addEntry(new Email("irfan@example.com"));
        p5.addEntry(new Address("Blk 47 Tampines Street 20, #17-35"));

        Person p6 = new Person(new Name("Roy Balakrishnan"), getTagSet("colleagues"));
        p6.addEntry(new Phone("92624417"));
        p6.addEntry(new Email("royb@example.com"));
        p6.addEntry(new Address("Blk 45 Aljunied Street 85, #11-31"));
        return new Person[] {
            p1, p2, p3, p4, p5, p6
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
