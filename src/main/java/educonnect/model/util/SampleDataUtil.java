package educonnect.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import educonnect.model.AddressBook;
import educonnect.model.ReadOnlyAddressBook;
import educonnect.model.student.Email;
import educonnect.model.student.Name;
import educonnect.model.student.Student;
import educonnect.model.student.StudentId;
import educonnect.model.student.TelegramHandle;
import educonnect.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new StudentId("A1654327X"), new Email("alexyeoh@example.com"),
                new TelegramHandle("@alyeoh"),
                getTagSet("tutorial-1")),

            new Student(new Name("John Smith"), new StudentId("A9876543Y"), new Email("johnsmith@example.com"),
                new TelegramHandle("@johnsmith"),
                getTagSet("tutorial-2", "weak-student")),

            new Student(new Name("Emily Davis"), new StudentId("A5678901Z"), new Email("emilydavis@example.com"),
                new TelegramHandle("@davemily"),
                getTagSet("tutorial-3")),

            new Student(new Name("David Li"), new StudentId("A2345678U"), new Email("lidavid@example.com"),
                new TelegramHandle("@davidlii2"),
                getTagSet("tutorial-1", "strong-student")),

            new Student(new Name("Irfan Ibrahim"), new StudentId("A8901234T"), new Email("irfan@example.com"),
                new TelegramHandle("@ifanhim"),
                getTagSet("tutorial-2")),

            new Student(new Name("Roy Balakrishnan"), new StudentId("A3456789W"), new Email("royb@example.com"),
                new TelegramHandle("@balakrishnan"),
                getTagSet("tutorial-2", "strong-student"))
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
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
