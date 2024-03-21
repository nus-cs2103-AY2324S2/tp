package seedu.address.model.util;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.AddressBook;
import seedu.address.model.ClassBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyClassBook;
import seedu.address.model.person.AttendanceStatus;
import seedu.address.model.person.Classes;
import seedu.address.model.person.CourseCode;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.model.tag.Attendance;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new StudentId("A1111111D"),
                getAttendanceSet("02-02-2024")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new StudentId("A2222222D"),
                getAttendanceSet("02-02-2024")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new StudentId("A3333333D"),
                getAttendanceSet("02-02-2024")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new StudentId("A4444444D"),
                getAttendanceSet("02-02-2024")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new StudentId("A5555555D"),
                getAttendanceSet("02-02-2024")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new StudentId("A6666666D"),
                getAttendanceSet("02-02-2024"))
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
    public static Set<Attendance> getAttendanceSet(String... strings) {

        Set<Attendance> set = new HashSet<>();
        for (String i : strings) {
            set.add(new Attendance(new AttendanceStatus(i, "1")));
        }
        return set;
    }


    public static Classes[] getSampleClasses() {
        return new Classes[] {
            new Classes(new CourseCode("CS2103T")),
            new Classes(new CourseCode("CS2101"))
        };
    }
    public static ReadOnlyClassBook getSampleClassBook() {
        ClassBook sampleCb = new ClassBook();
        for (Classes sampleClasses : getSampleClasses()) {
            sampleCb.createClass(sampleClasses);
        }
        return sampleCb;
    }

}
