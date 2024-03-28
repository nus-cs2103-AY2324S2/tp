package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Email;
import seedu.address.model.person.IdentityCardNumber;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Sex;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"),
                new Email("alexyeoh@example.com"), new IdentityCardNumber("S1234567A"), new Age("30"),
                new Sex("M"), new Address("Blk 30 Geylang Street 29, #06-40"),
                new Note("No medical conditions"),
                getEmptyTagSet()),
            new Person(new Name("Bernice Yu"), new Phone("99272758"),
                new Email("berniceyu@example.com"), new IdentityCardNumber("S1234567B"), new Age(31),
                new Sex("F"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Note("Asthma"),
                getEmptyTagSet()),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                new Email("charlotte@example.com"), new IdentityCardNumber("S1234567C"), new Age("32"),
                new Sex("F"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Note("Diabetes"),
                getEmptyTagSet()),
            new Person(new Name("David Li"), new Phone("91031282"),
                new Email("lidavid@example.com"), new IdentityCardNumber("S1234567D"), new Age(33), new Sex("M"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Note("No medical conditions"),
                getEmptyTagSet()),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"),
                new Email("irfan@example.com"), new IdentityCardNumber("S1234567E"), new Age("34"),
                new Sex("M"), new Address("Blk 47 Tampines Street 20, #17-35"), new Note("High blood pressure"),
                getEmptyTagSet()),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"),
                new Email("royb@example.com"), new IdentityCardNumber("S1234567F"), new Age(35),
                new Sex("M"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                new Note("No medical conditions"),
                getEmptyTagSet()),
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

    public static Set<Tag> getEmptyTagSet() {
        return new HashSet<Tag>();
    }

}
