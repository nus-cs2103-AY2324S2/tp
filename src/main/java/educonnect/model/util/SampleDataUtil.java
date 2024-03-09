package educonnect.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import educonnect.model.AddressBook;
import educonnect.model.ReadOnlyAddressBook;
import educonnect.model.person.Email;
import educonnect.model.person.Name;
import educonnect.model.person.Person;
import educonnect.model.person.StudentId;
import educonnect.model.person.TelegramHandle;
import educonnect.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new StudentId("A1234567X"), new Email("alexyeoh@example.com"),
                new TelegramHandle("@alyeoh"),
                getTagSet("tutorial-1")),

            new Person(new Name("John Smith"), new StudentId("A9876543Y"), new Email("johnsmith@example.com"),
                new TelegramHandle("@johnsmith"),
                getTagSet("tutorial-2", "weak-student")),

            new Person(new Name("Emily Davis"), new StudentId("A5678901Z"), new Email("emilydavis@example.com"),
                new TelegramHandle("@davemily"),
                getTagSet("tutorial-3")),

            new Person(new Name("David Li"), new StudentId("A2345678U"), new Email("lidavid@example.com"),
                new TelegramHandle("@davidlii2"),
                getTagSet("tutorial-1", "strong-student")),

            new Person(new Name("Irfan Ibrahim"), new StudentId("A8901234T"), new Email("irfan@example.com"),
                new TelegramHandle("@ifanhim"),
                getTagSet("tutorial-2")),

            new Person(new Name("Roy Balakrishnan"), new StudentId("A3456789W"), new Email("royb@example.com"),
                new TelegramHandle("@balakrishnan"),
                getTagSet("tutorial-2", "strong-student"))
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
