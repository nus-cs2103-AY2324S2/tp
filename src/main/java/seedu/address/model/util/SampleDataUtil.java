package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.CourseName;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyCourseName;
import seedu.address.model.course.Course;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.NusNet;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.weeknumber.WeekNumber;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new NusNet("E1234567"), new Address("Blk 30 Geylang Street 29, #06-40"),
                    getWeekNumberSet("1", "2"), getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new NusNet("e2345678"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getWeekNumberSet("1", "3", "4"), getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new NusNet("E3456789"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new NusNet("e0000001"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getWeekNumberSet("6", "7"), getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new NusNet("E0000002"), new Address("Blk 47 Tampines Street 20, #17-35"),
                    getWeekNumberSet("1", "2", "3", "4", "5", "6"), getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new NusNet("e0000003"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getWeekNumberSet("1"), getTagSet("colleagues"))
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
     * Returns a WeekNumber set containing the list of strings given.
     */
    public static Set<WeekNumber> getWeekNumberSet(String... strings) {
        return Arrays.stream(strings)
                .map(WeekNumber::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    public static ReadOnlyCourseName getSampleCourseName() {
        CourseName courseName = new CourseName();
        courseName.setCourse(new Course("XX1234Y"));
        return courseName;
    }

}
