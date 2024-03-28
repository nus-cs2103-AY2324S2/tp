package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.FormClass;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Phone("99149687"),
                    new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), new StudentId("00001"),
                    getTagSet("friends"),
                    new FormClass("1A")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Phone("86898742"), new Email("berniceyu"
                    + "@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new StudentId("00002"),
                    getTagSet("colleagues", "friends"),
                    new FormClass("1A")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Phone("92532563"), new Email(
                    "charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new StudentId("00003"),
                    getTagSet("neighbours"),
                    new FormClass("1A")),
            new Person(new Name("David Li"), new Phone("91031282"), new Phone("83496437"), new Email("lidavid"
                    + "@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new StudentId("00004"),
                    getTagSet("family"),
                    new FormClass("1A")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Phone("88548559"), new Email("irfan"
                    + "@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new StudentId("00005"),
                    getTagSet("classmates"),
                    new FormClass("1A")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Phone("99811124"), new Email("royb"
                    + "@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), new StudentId("00006"),
                    getTagSet("colleagues"),
                new FormClass("1A"))
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
