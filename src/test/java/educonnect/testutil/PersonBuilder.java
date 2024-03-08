package educonnect.testutil;

import java.util.HashSet;
import java.util.Set;

import educonnect.model.person.Email;
import educonnect.model.person.Name;
import educonnect.model.person.Person;
import educonnect.model.person.StudentId;
import educonnect.model.person.TelegramHandle;
import educonnect.model.tag.Tag;
import educonnect.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_STUDENT_ID = "A1234567X";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_TELEGRAM_HANDLE = "@Beeam";

    private Name name;
    private StudentId studentId;
    private Email email;
    private TelegramHandle telegramHandle;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        studentId = new StudentId(DEFAULT_STUDENT_ID);
        email = new Email(DEFAULT_EMAIL);
        telegramHandle = new TelegramHandle(DEFAULT_TELEGRAM_HANDLE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        studentId = personToCopy.getStudentId();
        email = personToCopy.getEmail();
        telegramHandle = personToCopy.getTelegramHandle();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withTelegramHandle(String telegramHandle) {
        this.telegramHandle = new TelegramHandle(telegramHandle);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withStudentId(String studentId) {
        this.studentId = new StudentId(studentId);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Person build() {
        return new Person(name, studentId, email, telegramHandle, tags);
    }

}
