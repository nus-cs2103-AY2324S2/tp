package educonnect.model.student;

import static educonnect.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import educonnect.commons.util.ToStringBuilder;
import educonnect.model.tag.Tag;

/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final Name name;
    private final StudentId studentId;
    private final TelegramHandle telegramHandle;
    private final Email email;

    private final Link link;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, StudentId studentId, Email email, TelegramHandle telegramHandle, Set<Tag> tags) {
        requireAllNonNull(name, studentId, email, telegramHandle, tags);
        this.name = name;
        this.studentId = studentId;
        this.email = email;
        this.telegramHandle = telegramHandle;
        this.link = null;
        this.tags.addAll(tags);
    }

    public Student(Name name, StudentId studentId, Email email, TelegramHandle telegramHandle, Link link, Set<Tag> tags) {
        requireAllNonNull(name, studentId, email, telegramHandle, tags);
        this.name = name;
        this.studentId = studentId;
        this.email = email;
        this.telegramHandle = telegramHandle;
        this.link = link;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public StudentId getStudentId() {
        return studentId;
    }

    public Email getEmail() {
        return email;
    }

    public TelegramHandle getTelegramHandle() {
        return telegramHandle;
    }

    public Link getLink() { return link; }
    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both students have the same name.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getName().equals(getName())
                && otherStudent.getStudentId().equals(getStudentId());
    }

    /**
     * Returns true if both students share same student id or email or telegram handle, these are unique identifiers.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameUniqueIdentifier(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && (otherStudent.getStudentId().equals(getStudentId())
                || otherStudent.getEmail().equals(getEmail())
                || otherStudent.getTelegramHandle().equals(getTelegramHandle()));
    }

    /**
     * Returns true if both students have the same student id.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudentId(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && isSameStudentId(otherStudent.getStudentId());
    }

    /**
     * Returns true if student id is same as current student.
     */
    public boolean isSameStudentId(StudentId otherStudentId) {
        return otherStudentId != null && otherStudentId.equals(getStudentId());
    }

    /**
     * Returns true if both students have the same email.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameEmail(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && isSameEmail(otherStudent.getEmail());
    }

    /**
     * Returns true if email is same as current student.
     */
    public boolean isSameEmail(Email otherEmail) {
        return otherEmail != null && otherEmail.equals(getEmail());
    }

    /**
     * Returns true if both students have the same telegram handle.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameTelegramHandle(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && isSameTelegramHandle(otherStudent.getTelegramHandle());
    }

    /**
     * Returns true if telegram handle is same as current student.
     */
    public boolean isSameTelegramHandle(TelegramHandle otherTelegramHandle) {
        return otherTelegramHandle != null && otherTelegramHandle.equals(getTelegramHandle());
    }

    /**
     * Returns true if both students have the same identity and data fields.
     * This defines a stronger notion of equality between two students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return name.equals(otherStudent.name)
                && studentId.equals(otherStudent.studentId)
                && email.equals(otherStudent.email)
                && telegramHandle.equals(otherStudent.telegramHandle)
                && tags.equals(otherStudent.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, studentId, email, telegramHandle, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("student id", studentId)
                .add("email", email)
                .add("telegram handle", telegramHandle)
                .add("tags", tags)
                .add("link", link)
                .toString();
    }

}
