package seedu.address.model.patient;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Stores the details to edit the patient with. Each non-empty field value will replace the
 * corresponding field value of the patient.
 */
public class EditPatientDescriptor {
    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private Set<ImportantDate> importantDate;

    public EditPatientDescriptor() {
    }

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public EditPatientDescriptor(EditPatientDescriptor toCopy) {
        setName(toCopy.name);
        setPhone(toCopy.phone);
        setEmail(toCopy.email);
        setAddress(toCopy.address);
        setTags(toCopy.tags);
        setImportantDate(toCopy.importantDate);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, phone, email, address, importantDate);
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Optional<Name> getName() {
        return Optional.ofNullable(name);
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Optional<Phone> getPhone() {
        return Optional.ofNullable(phone);
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Optional<Email> getEmail() {
        return Optional.ofNullable(email);
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }

    /**
     * Sets {@code tags} to this object's {@code tags}.
     * A defensive copy of {@code tags} is used internally.
     */
    public void setTags(Set<Tag> tags) {
        this.tags = (tags != null) ? new HashSet<>(tags) : null;
    }

    /**
     * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code tags} is null.
     */
    public Optional<Set<Tag>> getTags() {
        return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
    }

    public Optional<Set<ImportantDate>> getImportantDates() {
        return importantDate != null ? Optional.of(Collections.unmodifiableSet(importantDate)) : Optional.empty();
    }

    public void setImportantDate(Set<ImportantDate> importantDate) {
        this.importantDate = importantDate != null ? new HashSet<>(importantDate) : null;
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditPatientDescriptor)) {
            return false;
        }

        EditPatientDescriptor otherEditPatientDescriptor = (EditPatientDescriptor) other;
        return Objects.equals(name, otherEditPatientDescriptor.name)
                && Objects.equals(phone, otherEditPatientDescriptor.phone)
                && Objects.equals(email, otherEditPatientDescriptor.email)
                && Objects.equals(address, otherEditPatientDescriptor.address)
                && Objects.equals(tags, otherEditPatientDescriptor.tags)
                && Objects.equals(importantDate, otherEditPatientDescriptor.importantDate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tags", tags)
                .add("importantDate", importantDate)
                .toString();
    }
}
