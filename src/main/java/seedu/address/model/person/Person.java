package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.policy.Policy;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Birthday birthday;
    private final Priority priority;
    private final LastMet lastMet;
    private final Schedule schedule;
    private final Set<Tag> tags = new HashSet<>();
    private final PolicyList policyList;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Birthday birthday, Priority priority,
                  Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, birthday, priority, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.priority = priority;
        this.lastMet = new LastMet(LocalDate.now());
        this.schedule = new Schedule(LocalDateTime.now(), true);
        this.tags.addAll(tags);
        this.policyList = new PolicyList();
    }

    /**
     * Person constructor used for subsequent LastMet, Schedule and Mark Commands.
     */
    public Person(Name name, Phone phone, Email email, Address address, Birthday birthday,
        Priority priority, LastMet lastmet, Schedule schedule, Set<Tag> tags, PolicyList policyList) {
        requireAllNonNull(name, phone, email, address, birthday, tags, policyList);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.priority = priority;
        this.lastMet = checkNullLastMet(lastmet);
        this.schedule = checkNullSchedule(schedule);
        this.tags.addAll(tags);
        this.policyList = policyList;
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

    public Address getAddress() {
        return address;
    }

    public Birthday getBirthday() {
        return birthday;
    }

    public Priority getPriority() {
        return priority;
    }

    public LastMet getLastMet() {
        return lastMet;
    }

    public Schedule getSchedule() {
        return schedule;
    }
    public PolicyList getPolicyList() {
        return policyList.getPolicyListClone();
    }

    private LastMet checkNullLastMet(LastMet lastmet) {
        if (lastmet == null) {
            return new LastMet(LocalDate.now());
        } else {
            return lastmet;
        }
    }

    private Schedule checkNullSchedule(Schedule schedule) {
        if (schedule == null) {
            return new Schedule(LocalDateTime.now(), true);
        } else {
            return schedule;
        }
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if person has a policy in their policyList that has conflicting policyId as testPolicy.
     * @param testPolicy Policy to be tested for conflicting policyId
     * @return true if person has a policy in their policyList that has conflicting policyId as testPolicy
     */
    public boolean isConflictingPolicyId(Policy testPolicy) {
        assert testPolicy != null;
        return policyList.hasConflictingPolicyId(testPolicy);
    }

    /**
     * Returns true if person has any policy in their policyList that has policyId.
     * @param policyId Policy ID to be tested for existence
     * @return true if person has any policy in their policyList that has policyId
     */
    public boolean hasPolicyID(String policyId) {
        assert policyId != null;
        return policyList.hasPolicy(policyId);
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
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && birthday.equals(otherPerson.birthday)
                && priority.equals(otherPerson.priority)
                && tags.equals(otherPerson.tags)
                && policyList.equals(otherPerson.policyList);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, birthday, priority, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("birthday", birthday)
                .add("priority", priority)
                .add("tags", tags)
                .add("policies", policyList)
                .toString();
    }

    /**
     * Returns true if the person has an overdue lastMet and there is no active schedule, false otherwise.
     * @return true if the person has an overdue lastMet and there is no active schedule.
     */
    public boolean isOverDueLastMet() {
        return lastMet.getIsOverdue() && schedule.getIsDone();
    }
    /**
     * Returns true if the person has an active schedule, false otherwise.
     * @return true if the person has an active schedule.
     */
    public boolean hasActiveSchedule() {
        return !schedule.getIsDone();
    }

    /**
     * Returns the string representation of the overdue person for GUI.
     * @return The string representation of the overdue person
     */
    public String overdueLastMetStringFormat() {
        return String.format(name + " - %dd ago", lastMet.getPeriodGap());
    }
    /**
     * Returns the string representation of the scheduled person for GUI.
     * @return The string representation of the scheduled person
     */
    public String scheduleStringFormat() {
        return schedule.getScheduleDateString() + " - " + name + (schedule.getIsMissed() ? " (Missed)" : "");
    }
}
