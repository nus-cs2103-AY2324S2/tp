package seedu.address.model.coursemate;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.skill.Skill;

/**
 * Represents a CourseMate in the contact list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class CourseMate {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final TelegramHandle telegramHandle;

    // Data fields
    private final Set<Skill> skills = new HashSet<>();

    /**
     * Every field except telegramHandle must be present and not null.
     * Telegram handle is optional.
     */
    public CourseMate(Name name, Phone phone, Email email, TelegramHandle telegramHandle,
            Set<Skill> skills) {
        requireAllNonNull(name, phone, email, skills);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.telegramHandle = telegramHandle;
        this.skills.addAll(skills);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    /**
     * Returns an {@code TelegramHandle}, if it exists.
     * Otherwise, returns null.
     */
    public TelegramHandle getTelegramHandle() {
        return telegramHandle;
    }

    /**
     * Returns an immutable skill set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Skill> getSkills() {
        return Collections.unmodifiableSet(skills);
    }

    /**
     * Returns true if both course mates have the same name.
     * This defines a weaker notion of equality between two course mates.
     */
    public boolean isSameCourseMate(CourseMate otherCourseMate) {
        if (otherCourseMate == this) {
            return true;
        }

        return otherCourseMate != null
                && otherCourseMate.getName().equals(getName());
    }

    /**
     * Returns true if both course mates have the same identity and data fields.
     * This defines a stronger notion of equality between two course mates.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CourseMate)) {
            return false;
        }

        CourseMate otherCourseMate = (CourseMate) other;
        return name.equals(otherCourseMate.name)
                && phone.equals(otherCourseMate.phone)
                && email.equals(otherCourseMate.email)
                && (
                    telegramHandle == null
                        ? otherCourseMate.telegramHandle == null
                        : telegramHandle.equals(otherCourseMate.telegramHandle)
                )
                && skills.equals(otherCourseMate.skills);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, telegramHandle, skills);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("telegramHandle", telegramHandle)
                .add("skills", skills)
                .toString();
    }

}
