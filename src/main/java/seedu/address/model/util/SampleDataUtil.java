package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.BankDetails;
import seedu.address.model.person.Name;
import seedu.address.model.person.PayRate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Sex;
import seedu.address.model.person.WorkHours;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[]{
            new Person(new Name("Alex"), new Name("Yeoh"), new Phone("87438807"), new Sex("m"),
                    new PayRate(14.5), new Address("Blk 30 Geylang Street 29, #06-40"),
                    new BankDetails("9102031012"), new WorkHours(), getTagSet("friends")),
            new Person(new Name("Bernice"), new Name("Yu"), new Phone("99272758"), new Sex("f"),
                    new PayRate(16), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new BankDetails("084102395"), new WorkHours(), getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte"), new Name("Oliveiro"), new Phone("93210283"), new Sex("f"),
                new PayRate(20), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                new BankDetails("146172002492"), new WorkHours(), getTagSet("neighbours")),
            new Person(new Name("David"), new Name("Li"), new Phone("91031282"),
                new Sex("m"), new PayRate(18.5), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    new BankDetails("1803645852"), new WorkHours(), getTagSet("family")),
            new Person(new Name("Irfan"), new Name("Ibrahim"), new Phone("92492021"), new Sex("m"),
                    new PayRate(16.5), new Address("Blk 47 Tampines Street 20, #17-35"),
                    new BankDetails("0052312891"), new WorkHours(), getTagSet("classmates")),
            new Person(new Name("Roy"), new Name("Balakrishnan"), new Phone("92624417"), new Sex("m"),
                new PayRate(10), new Address("Blk 45 Aljunied Street 85, #11-31"),
                    new BankDetails("5501089550"), new WorkHours(), getTagSet("colleagues"))
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
        return Arrays.stream(strings).map(Tag::new).collect(Collectors.toSet());
    }

}
