package educonnect.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import educonnect.model.AddressBook;
import educonnect.model.ReadOnlyAddressBook;
import educonnect.model.student.Email;
import educonnect.model.student.Name;
import educonnect.model.student.Student;
import educonnect.model.student.StudentId;
import educonnect.model.student.TelegramHandle;
import educonnect.model.student.timetable.Period;
import educonnect.model.student.timetable.Timetable;
import educonnect.model.student.timetable.exceptions.OverlapPeriodException;
import educonnect.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {
        Timetable[] timetables = getSampleTimetables();
        return new Student[] {
            new Student(
                    new Name("Alex Yeoh"),
                    new StudentId("A1654327X"),
                    new Email("alexyeoh@example.com"),
                    new TelegramHandle("@alyeoh"),
                    getTagSet("tutorial-1"),
                    timetables[0]),

            new Student(
                    new Name("John Smith"),
                    new StudentId("A9876543Y"),
                    new Email("johnsmith@example.com"),
                    new TelegramHandle("@johnsmith"),
                    getTagSet("tutorial-2", "weak-student"),
                    timetables[1]),

            new Student(
                    new Name("Emily Davis"),
                    new StudentId("A5678901Z"),
                    new Email("emilydavis@example.com"),
                    new TelegramHandle("@davemily"),
                    getTagSet("tutorial-3"),
                    timetables[2]),

            new Student(
                    new Name("David Li"),
                    new StudentId("A2345678U"),
                    new Email("lidavid@example.com"),
                    new TelegramHandle("@davidlii2"),
                    getTagSet("tutorial-1", "strong-student"),
                    timetables[3]),

            new Student(
                    new Name("Irfan Ibrahim"),
                    new StudentId("A8901234T"),
                    new Email("irfan@example.com"),
                    new TelegramHandle("@ifanhim"),
                    getTagSet("tutorial-2"),
                    timetables[4]),

            new Student(
                    new Name("Roy Balakrishnan"),
                    new StudentId("A3456789W"),
                    new Email("royb@example.com"),
                    new TelegramHandle("@balakrishnan"),
                    getTagSet("tutorial-2", "strong-student"),
                    timetables[5])
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

    /**
     * Helper method to generate sample {@code Timetable} objects.
     *
     * @return an array of sample {@code Timetable} objects.
     */
    private static Timetable[] getSampleTimetables() {
        try {
            Timetable emptyTimetable = new Timetable();
            Timetable timetable1 = new Timetable();
            Timetable timetable2 = new Timetable();
            Timetable timetable3 = new Timetable();
            Timetable timetable4 = new Timetable();

            timetable1.addPeriodsToDay(1, new ArrayList<>(
                    List.of(new Period("period1", "13-15"),
                            new Period("period2", "16-18"))));
            timetable1.addPeriodsToDay(4, new ArrayList<>(
                    List.of(new Period("period1", "16-18"),
                            new Period("period2", "13-15"))));
            timetable2.addPeriodsToDay(2, new ArrayList<>(
                    List.of(new Period("period1", "11-13"),
                            new Period("period2", "14-17"))));
            timetable2.addPeriodsToDay(3, new ArrayList<>(
                    List.of(new Period("period1", "12-14"),
                            new Period("period2", "114-16"),
                            new Period("period3", "16-18"))));
            timetable3.addPeriodsToDay(1, new ArrayList<>(
                    List.of(new Period("period1", "13-15"),
                            new Period("period2", "16-18"))));
            timetable3.addPeriodsToDay(2, new ArrayList<>(
                    List.of(new Period("period1", "16-18"),
                            new Period("period2", "13-15"))));
            timetable3.addPeriodsToDay(3, new ArrayList<>(
                    List.of(new Period("period1", "18-20"),
                            new Period("period2", "15-17"))));
            timetable4.addPeriodsToDay(5, new ArrayList<>(
                    List.of(new Period("period1", "12-18"))));
            return new Timetable[]{timetable1, emptyTimetable, timetable2, timetable3, emptyTimetable, timetable4};
        } catch (OverlapPeriodException e) {
            throw new RuntimeException(e);
        }
    }
}
