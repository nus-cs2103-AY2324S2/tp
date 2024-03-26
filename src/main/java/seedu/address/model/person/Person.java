package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name firstName;
    private final Name lastName;
    private final Phone phone;

    // Data fields
    private final Sex sex;
    private final PayRate payRate;
    private final Address address;
    private final BankDetails bankDetails;
    private final Set<Tag> tags = new HashSet<>();
    private WorkHours hoursWorked;

    /**
     * Every field must be present and not null.
     */
    public Person(Name firstName, Name lastName, Phone phone, Sex sex,
                  PayRate payRate, Address address,
                  BankDetails bankDetails, WorkHours hoursWorked, Set<Tag> tags) {
        requireAllNonNull(firstName, lastName, phone, address, bankDetails, tags);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.sex = sex;
        this.payRate = payRate;
        this.address = address;
        this.bankDetails = bankDetails;
        this.tags.addAll(tags);
        this.hoursWorked = hoursWorked;
    }

    public Name getName() {
        return new Name(firstName + " " + lastName);
    }

    public Name getFirstName() {
        return firstName;
    }

    public Name getLastName() {
        return lastName;
    }

    public Phone getPhone() {
        return phone;
    }

    public Sex getSex() {
        return sex;
    }

    public PayRate getPayRate() {
        return payRate;
    }

    public Address getAddress() {
        return address;
    }

    public BankDetails getBankDetails() {
        return bankDetails;
    }

    public WorkHours getWorkHours() {
        return hoursWorked;
    }

    public void setHoursWorked(WorkHours hoursWorked) {
        this.hoursWorked = hoursWorked;
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
            && otherPerson.phone.equals(phone);
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
        return getName().equals(otherPerson.getName())
            && phone.equals(otherPerson.phone)
            && sex.equals(otherPerson.sex)
            && payRate.equals(otherPerson.payRate)
            && address.equals(otherPerson.address)
            && bankDetails.equals(otherPerson.bankDetails)
            && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(firstName, lastName, phone, sex, payRate, address, bankDetails,
            tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("firstName", firstName)
            .add("lastName", lastName)
            .add("phone", phone)
            .add("sex", sex)
            .add("payRate", payRate)
            .add("address", address)
            .add("bankDetails", bankDetails)
            .add("tags", tags)
            .toString();
    }

}
