package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.grade.Grade;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.timeslots.Timeslots;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                    getTimeslotSet("Saturday 3pm-5pm"), getGradeSet("ca1: 90")),
            new Student(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getTimeslotSet("Tuesday 7pm-9pm", "Wednesday 3pm-5pm"), getGradeSet("ca1: 50", "ca2: 80")),
            new Student(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getTimeslotSet("Monday 8am-10am"), getGradeSet("eoy: 70")),
            new Student(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getTimeslotSet("Thursday 7:30pm-9:30pm"), getGradeSet("midterms: 0")),
            new Student(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                    getTimeslotSet("Sunday 1pm-4pm"), getGradeSet("finals: 100")),
            new Student(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getTimeslotSet("Friday 4pm-5:30pm"), getGradeSet("ca2: 39"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);
        }
        return sampleAb;
    }

    /**
     * Returns a timeslot set containing the list of strings given.
     */
    public static Set<Timeslots> getTimeslotSet(String... strings) {
        return Arrays.stream(strings)
                .map(Timeslots::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a grade set containing the list of strings given.
     */
    public static Set<Grade> getGradeSet(String... strings) {
        return Arrays.stream(strings)
                .map(Grade::new)
                .collect(Collectors.toSet());
    }

}
