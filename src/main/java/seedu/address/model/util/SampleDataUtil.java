package seedu.address.model.util;

import java.util.Optional;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.ClassGroup;
import seedu.address.model.person.Email;
import seedu.address.model.person.Github;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Telegram;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new ClassGroup("T10-2"), new Email("alexyeoh@example.com"),
                    Optional.of(new Phone("87438807")), Optional.of(new Telegram("@alex123")),
                    Optional.of(new Github("alexyeoh"))),
            new Person(new Name("Bernice Yu"), new ClassGroup("T11-2"), new Email("berniceyu@example.com"),
                    Optional.of(new Phone("99272758")), Optional.of(new Telegram("@bernice123")),
                    Optional.of(new Github("berniceyu123"))),
            new Person(new Name("Charlotte Oliveiro"), new ClassGroup("T11-2"), new Email("charlotte@example.com"),
                    Optional.of(new Phone("93210283")), Optional.of(new Telegram("@charlotte123")),
                    Optional.of(new Github("charlotte123"))),
            new Person(new Name("David Li"), new ClassGroup("F14-3"), new Email("lidavid@example.com"),
                    Optional.of(new Phone("91031282")), Optional.of(new Telegram("@david123")),
                    Optional.of(new Github("david123"))),
            new Person(new Name("Irfan Ibrahim"), new ClassGroup("A12-3"), new Email("irfan@example.com"),
                    Optional.of(new Phone("92492021")), Optional.of(new Telegram("@irfan123")),
                    Optional.of(new Github("irfan123"))),
            new Person(new Name("Roy Balakrishnan"), new ClassGroup("A05-4"), new Email("royb@example.com"),
                    Optional.of(new Phone("92624417")), Optional.of(new Telegram("@roy123")),
                    Optional.of(new Github("roy123")))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }
}
