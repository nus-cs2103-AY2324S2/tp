package educonnect.testutil;

import java.util.HashSet;
import java.util.Set;

import educonnect.model.student.Email;
import educonnect.model.student.Name;
import educonnect.model.student.Student;
import educonnect.model.student.StudentId;
import educonnect.model.student.TelegramHandle;
import educonnect.model.student.timetable.Timetable;
import educonnect.model.tag.Tag;
import educonnect.model.util.SampleDataUtil;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_STUDENT_ID = "A1234567X";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_TELEGRAM_HANDLE = "@Beeam";
    public static final Timetable DEFAULT_TIMETABLE = TypicalTimetableAndValues.DEFAULT_EMPTY_TIMETABLE;

    public static final String ALTERNATE_NAME = "Bob Builder";
    public static final String ALTERNATE_STUDENT_ID = "A9876543U";
    public static final String ALTERNATE_EMAIL = "builderbob@gmail.com";
    public static final String ALTERNATE_TELEGRAM_HANDLE = "@bobthebuilder";
    public static final Timetable ALTERNATE_TIMETABLE = TypicalTimetableAndValues.VALID_TIMETABLE_1;

    private Name name;
    private StudentId studentId;
    private Email email;
    private TelegramHandle telegramHandle;
    private Set<Tag> tags;
    private Timetable timetable;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        studentId = new StudentId(DEFAULT_STUDENT_ID);
        email = new Email(DEFAULT_EMAIL);
        telegramHandle = new TelegramHandle(DEFAULT_TELEGRAM_HANDLE);
        tags = new HashSet<>();
        timetable = DEFAULT_TIMETABLE;
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        studentId = studentToCopy.getStudentId();
        email = studentToCopy.getEmail();
        telegramHandle = studentToCopy.getTelegramHandle();
        tags = new HashSet<>(studentToCopy.getTags());
        timetable = studentToCopy.getTimetable();
    }

    /**
     * Creates a {@code StudentBuilder} with the alternate default details.
     */
    public StudentBuilder alternate() {
        return new StudentBuilder()
                .withName(ALTERNATE_NAME)
                .withTelegramHandle(ALTERNATE_TELEGRAM_HANDLE)
                .withEmail(ALTERNATE_EMAIL)
                .withStudentId(ALTERNATE_STUDENT_ID)
                .withTimetable(ALTERNATE_TIMETABLE);
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Student} that we are building.
     */
    public StudentBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code TelegramHandle} of the {@code Student} that we are building.
     */
    public StudentBuilder withTelegramHandle(String telegramHandle) {
        this.telegramHandle = new TelegramHandle(telegramHandle);
        return this;
    }

    /**
     * Sets the {@code StudentId} of the {@code Student} that we are building.
     */
    public StudentBuilder withStudentId(String studentId) {
        this.studentId = new StudentId(studentId);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Timetable} of the {@code Student} that we are building.
     */
    public StudentBuilder withTimetable(Timetable timetable) {
        this.timetable = timetable;
        return this;
    }

    public Student build() {
        return new Student(name, studentId, email, telegramHandle, tags, timetable);
    }

}
