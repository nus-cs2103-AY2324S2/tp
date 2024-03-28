package seedu.edulink.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.edulink.model.AddressBook;
import seedu.edulink.model.ReadOnlyAddressBook;
import seedu.edulink.model.student.Address;
import seedu.edulink.model.student.Email;
import seedu.edulink.model.student.Id;
import seedu.edulink.model.student.Intake;
import seedu.edulink.model.student.Major;
import seedu.edulink.model.student.Name;
import seedu.edulink.model.student.Phone;
import seedu.edulink.model.student.Student;
import seedu.edulink.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSamplePersons() {
        return new Student[] {
            new Student(new Id("A0265901E"), new Major("Physics"), new Intake("2020"),
                new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Student(new Id("A0289011E"), new Major("Chemistry"), new Intake("2022"),
                new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Student(new Id("A2209151E"), new Major("Computer Science"), new Intake("2020"),
                new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Student(new Id("A0912894P"), new Major("Physics"), new Intake("2020"), new Name("David Li"),
                new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Student(new Id("A1251351L"), new Major("Medicine"), new Intake("2019"), new Name("Irfan Ibrahim"),
                new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Student sampleStudent : getSamplePersons()) {
            sampleAb.addPerson(sampleStudent);
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
