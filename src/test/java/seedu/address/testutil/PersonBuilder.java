package seedu.address.testutil;

import java.util.Optional;

import seedu.address.model.attendance.Attendance;
import seedu.address.model.person.ClassGroup;
import seedu.address.model.person.Email;
import seedu.address.model.person.Github;
import seedu.address.model.person.Name;
import seedu.address.model.person.Notes;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Telegram;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_TELEGRAM = "@amybee";
    public static final String DEFAULT_GITHUB = "amybee";
    public static final String DEFAULT_CLASS_GROUP = "B01";

    private Name name;
    private Optional<Phone> phone;
    private Email email;
    private ClassGroup classGroup;
    private Optional<Telegram> telegram;
    private Optional<Github> github;
    private Notes notes;
    private Attendance attendance;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = Optional.of(new Phone(DEFAULT_PHONE));
        email = new Email(DEFAULT_EMAIL);
        github = Optional.of(new Github(DEFAULT_GITHUB));
        telegram = Optional.of(new Telegram(DEFAULT_TELEGRAM));
        classGroup = new ClassGroup(DEFAULT_CLASS_GROUP);
        notes = new Notes();
        attendance = new Attendance();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        github = personToCopy.getGithub();
        telegram = personToCopy.getTelegram();
        classGroup = personToCopy.getClassGroup();
        notes = personToCopy.getNotes();
        attendance = personToCopy.getAttendance();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = Optional.of(new Phone(phone));
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
        return new Person(name, classGroup, email, phone, telegram, github);
    }

    /**
     * Sets the {@code ClassGroup} of the {@code Person} that we are building.
     */
    public PersonBuilder withClassGroup(String classGroup) {
        this.classGroup = new ClassGroup(classGroup);
        return this;
    }

    /**
     * Sets the {@code Telegram} of the {@code Person} that we are building.
     */
    public PersonBuilder withTelegram(String telegram) {
        this.telegram = Optional.of(new Telegram(telegram));
        return this;
    }

    /**
     * Sets the {@code Github} of the {@code Person} that we are building.
     */
    public PersonBuilder withGithub(String github) {
        this.github = Optional.of(new Github(github));
        return this;
    }
}
