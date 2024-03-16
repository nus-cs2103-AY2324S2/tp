package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.group.Group;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final NusId nusId;
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Tag tag;
    private final Set<Group> groups = new HashSet<>();
    private final Schedule schedule;
    private final Remark remark;

    /**
     * Every field must be present and not null.
     */
    public Person(NusId nusId, Name name, Phone phone, Email email, Tag tag, Set<Group> groups,
                  Schedule schedule, Remark remark) {
        requireAllNonNull(nusId, name, phone, email, tag, groups, schedule, remark);
        this.nusId = nusId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.tag = tag;
        this.groups.addAll(groups);
        this.schedule = schedule;
        this.remark = remark;
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

    public Tag getTag() {
        return tag;
    }

    public NusId getNusId() {
        return nusId;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public Remark getRemark() {
        return remark;
    }

    /**
     * Returns an immutable group set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Group> getGroups() {
        return Collections.unmodifiableSet(groups);
    }

    /**
     * Returns true if both persons have the same nusId.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getNusId().equals(getNusId());
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
        // A person's identity does not depend on schedule or remark
        return name.equals(otherPerson.name)
                && nusId.equals(otherPerson.nusId)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && tag.equals(otherPerson.tag)
                && groups.equals(otherPerson.groups);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(nusId, name, phone, email, tag, groups, schedule, remark);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("nusId", nusId)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("tag", tag)
                .add("groups", groups)
                .add("schedule", schedule)
                .add("remark", remark)
                .toString();
    }

}
