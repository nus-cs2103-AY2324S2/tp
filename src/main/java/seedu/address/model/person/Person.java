package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.attendance.Attendance;
import seedu.address.model.attendance.Week;


/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final ClassGroup classGroup;
    private final Email email;
    private final Optional<Phone> phone;
    private final Optional<Telegram> telegram;
    private final Optional<Github> github;
    private final Notes notes;
    private final Attendance attendance;


    /**
     * Every field must be present and not null.
     */
    public Person(Name name, ClassGroup classGroup, Email email, Optional<Phone> phone,
                  Optional<Telegram> telegram, Optional<Github> github) {
        requireAllNonNull(name, classGroup, email, phone);
        this.name = name;
        this.classGroup = classGroup;
        this.email = email;
        this.phone = phone;
        this.telegram = telegram;
        this.github = github;
        this.attendance = new Attendance();
        this.notes = new Notes();
    }


    /**
     * Every field must be present and not null.
     */
    public Person(Name name, ClassGroup classGroup, Email email, Optional<Phone> phone,
                  Optional<Telegram> telegram, Optional<Github> github, Notes notes) {
        requireAllNonNull(name, classGroup, email, phone);
        this.name = name;
        this.classGroup = classGroup;
        this.email = email;
        this.phone = phone;
        this.telegram = telegram;
        this.github = github;
        this.notes = notes;
        this.attendance = new Attendance();
    }

    public Name getName() {
        return name;
    }

    public Optional<Phone> getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public ClassGroup getClassGroup() {
        return classGroup;
    }

    public Optional<Telegram> getTelegram() {
        return telegram;
    }

    public Optional<Github> getGithub() {
        return github;
    }

    public Notes getNotes() {
        return notes;
    }

    /**
     * Adds a note to a person's notes
     *
     * @param note The note to be added.
     */
    public void addNote(Note note) {
        requireNonNull(note);
        notes.addNote(note);
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public boolean isAbsent(Week week) {
        return attendance.isAbsent(week);
    }

    public void markPresent(Week week) {
        attendance.changeAttendanceStatus(week, Attendance.Status.PRESENT);
    }

    public void markAbsent(Week week) {
        attendance.changeAttendanceStatus(week, Attendance.Status.ABSENT);
    }

    public void resetAttendance() {
        attendance.resetAttendance();
    }

    /**
     * Returns true if both persons have the same Email, Phone (optional), Telegram (optional), or Github (optional).
     * Persons are allowed to have the same name.
     * This defines a weaker notion of equality between two persons.
     *
     * @param otherPerson The Person object whose fields are to be compared.
     * @return True if both persons have either the same Email, Phone (optional), Telegram (optional),
     *     or Github (optional).
     */
    public boolean checkDuplicateField(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }
        if (otherPerson == null) {
            return false;
        }

        boolean isEmailEqual = otherPerson.getEmail().equals(getEmail());
        boolean isBothPhoneNonEmpty = !getPhone().orElse(Phone.EMPTY).isEmpty()
                && !otherPerson.getPhone().orElse(Phone.EMPTY).isEmpty();
        boolean isBothTelegramNonEmpty = !getTelegram().orElse(Telegram.EMPTY).isEmpty()
                && !otherPerson.getTelegram().orElse(Telegram.EMPTY).isEmpty();
        boolean isBothGithubNonEmpty = !getGithub().orElse(Github.EMPTY).isEmpty()
                && !otherPerson.getGithub().orElse(Github.EMPTY).isEmpty();
        boolean isPhoneNonEmptyAndEqual = isBothPhoneNonEmpty && otherPerson.getPhone().equals(getPhone());
        boolean isTelegramNonEmptyAndEqual = isBothTelegramNonEmpty && otherPerson.getTelegram().equals(getTelegram());
        boolean isGithubNonEmptyAndEqual = isBothGithubNonEmpty && otherPerson.getGithub().equals(getGithub());


        return (isEmailEqual
                || isPhoneNonEmptyAndEqual
                || isTelegramNonEmptyAndEqual
                || isGithubNonEmptyAndEqual);
    }

    /**
     * Compares this Person's name with the specified Person's name for order based on their names, ignoring case
     * considerations.
     *
     * <p>Returns a negative integer, zero, or a positive integer as this Person's name is less than, equal to,
     * or greater than the specified Person's name.
     *
     * <p>The comparison is based on lexicographic ordering of the names of the Persons.
     * The comparison is case-insensitive.
     *
     * @param other The Person object whose name is to be compared.
     * @return A negative integer if this Person's name is less than the specified Person's name,
     *     zero if they are equal, or a positive integer if this Person's name is greater than the other Person's name.
     */
    public int compareName(Person other) {
        return name.compare(other.getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && classGroup.equals(otherPerson.classGroup)
                && email.equals(otherPerson.email)
                && phone.equals(otherPerson.phone)
                && telegram.equals(otherPerson.telegram)
                && github.equals(otherPerson.github)
                && attendance.equals(otherPerson.attendance);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, classGroup, email, phone, telegram, github, attendance, notes);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("classGroup", classGroup)
                .add("email", email)
                .add("phone", phone.isPresent() ? phone.get() : "")
                .add("telegram", telegram.isPresent() ? telegram.get() : "")
                .add("github", github.isPresent() ? github.get() : "")
                .add("attendance", attendance)
                .toString();
    }
}
