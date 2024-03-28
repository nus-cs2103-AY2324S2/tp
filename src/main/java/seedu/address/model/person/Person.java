package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.language.ProgrammingLanguage;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final CompanyName companyName;
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Salary salary;
    private final Info info;
    private final Set<Tag> tags = new HashSet<>();
    private final InterviewTime dateTime;
    private final Set<ProgrammingLanguage> programmingLanguages = new HashSet<>();
    private final Integer priority; // default priority level
    /**
     * Every field must be present and not null.
     */

    public Person(
            CompanyName companyName, Name name, Phone phone, Email email, Address address,
            InterviewTime dateTime, Salary salary, Info info, Set<Tag> tags,
            Set<ProgrammingLanguage> programmingLanguages, Integer priority) {
        requireAllNonNull(name, phone, email, address, salary, tags);
        this.companyName = companyName;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.dateTime = dateTime;
        this.salary = salary;
        this.info = info;
        this.tags.addAll(tags);
        this.programmingLanguages.addAll(programmingLanguages);
        this.priority = priority;
    }
    public CompanyName getCompanyName() {
        return companyName;
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

    public InterviewTime getDateTime() {
        return dateTime;
    }
    public Salary getSalary() {
        return salary;
    }
    public Info getInfo() {
        return info;
    }
    public Integer getPriority() {
        return priority;
    }

    /**
     * Returns true if a given string is a valid priority level.
     */
    public static boolean isValidPriority(String test) {
        String validRegex = "^[0-4]$";
        return test.matches(validRegex);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable set of programming languages.
     * This set cannot be modified and any attempt to do so will result in an {@code UnsupportedOperationException}.
     *
     * @return An immutable set of programming languages.
     */
    public Set<ProgrammingLanguage> getProgrammingLanguages() {
        return Collections.unmodifiableSet(programmingLanguages);
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
        return companyName.equals(otherPerson.companyName)
                && name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && tags.equals(otherPerson.tags)
                && programmingLanguages.equals(otherPerson.programmingLanguages);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(companyName, name, phone, email, address, tags, dateTime, programmingLanguages);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("company name", companyName)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("interview-time", dateTime)
                .add("salary", salary)
                .add("info", info)
                .add("tags", tags)
                .add("programming-languages", programmingLanguages)
                .add("priority", priority)
                .toString();
    }

}
