package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.person.Entry;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        Person p1 = new Person(new Entry("Name", "Alex Yeoh"), getTagSet("friends"));
        p1.addEntry(new Entry("Phone","87438807"));
        p1.addEntry(new Entry("Email","alexyeoh@example.com"));
        p1.addEntry(new Entry("Address","Blk 30 Geylang Street 29, #06-40"));

        Person p2 = new Person(new Entry("Name", "Bernice Yu"), getTagSet("colleagues", "friends"));
        p2.addEntry(new Entry("Phone", "99272758"));
        p2.addEntry(new Entry("Email", "berniceyu@example.com"));
        p2.addEntry(new Entry("Address", "Blk 30 Lorong 3 Serangoon Gardens, #07-18"));

        Person p3 = new Person(new Entry("Name", "Charlotte Oliveiro"), getTagSet("neighbours"));
        p3.addEntry(new Entry("Phone", "93210283"));
        p3.addEntry(new Entry("Email", "charlotte@example.com"));
        p3.addEntry(new Entry("Address", "Blk 11 Ang Mo Kio Street 74, #11-04"));

        Person p4 = new Person(new Entry("Name", "David Li"), getTagSet("family"));
        p4.addEntry(new Entry("Phone", "91031282"));
        p4.addEntry(new Entry("Email", "lidavid@example.com"));
        p4.addEntry(new Entry("Address", "Blk 436 Serangoon Gardens Street 26, #16-43"));

        Person p5 = new Person(new Entry("Name", "Irfan Ibrahim"), getTagSet("classmates"));
        p5.addEntry(new Entry("Phone", "92492021"));
        p5.addEntry(new Entry("Email", "irfan@example.com"));
        p5.addEntry(new Entry("Address", "Blk 47 Tampines Street 20, #17-35"));

        Person p6 = new Person(new Entry("Name", "Roy Balakrishnan"), getTagSet("colleagues"));
        p6.addEntry(new Entry("Phone", "92624417"));
        p6.addEntry(new Entry("Email", "royb@example.com"));
        p6.addEntry(new Entry("Address", "Blk 45 Aljunied Street 85, #11-31"));
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
