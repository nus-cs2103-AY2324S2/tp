package seedu.address.model.util;

import seedu.address.model.ImmuniMate;
import seedu.address.model.ReadOnlyImmuniMate;
import seedu.address.model.person.Address;
import seedu.address.model.person.DateOfBirth;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Sex;
import seedu.address.model.person.Status;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Nric("A1234567B"), new Name("Alex Yeoh"), new Phone("87438807"),
                    new Address("Blk 30 Geylang Street 29, #06-40"), new DateOfBirth("1977-04-03"),
                    new Sex("M"), new Status("HEALTHY")),
            new Person(new Nric("A1234568B"), new Name("Bernice Yu"), new Phone("99272758"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new DateOfBirth("1977-04-03"),
                    new Sex("F"), new Status("UNWELL")),
            new Person(new Nric("A1234569B"), new Name("Charlotte Oliveiro"), new Phone("93210283"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new DateOfBirth("2001-04-03"),
                    new Sex("F"), new Status("HEALTHY")),
            new Person(new Nric("A1234560B"), new Name("David Li"), new Phone("91031282"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new DateOfBirth("1987-11-03"),
                    new Sex("M"), new Status("PENDING")),
            new Person(new Nric("A1234561B"), new Name("Irfan Ibrahim"), new Phone("92492021"),
                    new Address("Blk 47 Tampines Street 20, #17-35"), new DateOfBirth("1970-12-03"),
                    new Sex("M"), new Status("UNWELL")),
            new Person(new Nric("A1234562B"), new Name("Roy Balakrishnan"), new Phone("92624417"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"), new DateOfBirth("1987-04-03"),
                    new Sex("M"), new Status("PENDING"))
        };
    }

    public static ReadOnlyImmuniMate getSampleAddressBook() {
        ImmuniMate sampleAb = new ImmuniMate();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

}
