package seedu.address.model.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleTiming;
import seedu.address.model.tag.Tag;

/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    private final List<ModuleCode> modules = new ArrayList<>();
    private final List<ModuleTiming> moduleTimings = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, Phone phone, Email email, Address address, Set<Tag> tags, List<ModuleCode> modules,
                   List<ModuleTiming> moduleTimings) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.modules.addAll(modules);
        this.moduleTimings.addAll(moduleTimings);
        this.tags.addAll(tags);
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

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns list of modules student is taking
     */
    public List<ModuleCode> getModules() {
        return modules;
    }

    /**
     * Returns list of module timings for the student
     */
    public List<ModuleTiming> getModuleTimings() {
        return moduleTimings;
    }

    /**
     * Checks if module `m` is held within the student
     * @param m Module to check
     * @return true if module is taken by student
     */
    public boolean hasModule(ModuleCode m) {
        return modules.contains(m);
    }

    /**
     * Checks if module `m` is held within the student
     * @param m Module to check
     * @return true if module is taken by student
     */
    public boolean addModule(ModuleCode m) {
        return modules.add(m);
    }

    /**
     * Checks if module `m` is held within the student
     * @param m Module to check
     * @return true if module is taken by student
     */
    public boolean deleteModule(ModuleCode m) {
        return modules.remove(m);
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
                && otherStudent.getName().equals(getName());
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
                && phone.equals(otherStudent.phone)
                && email.equals(otherStudent.email)
                && address.equals(otherStudent.address)
                && tags.equals(otherStudent.tags)
                && modules.equals(otherStudent.modules)
                && moduleTimings.equals(otherStudent.moduleTimings);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, modules, moduleTimings);
    }

    /**
     * Returns a shallow copy of a student instance.
     * @return a shallow copy of a student instance
     */
    public Student copy() {
        return new Student(
                this.name,
                this.phone,
                this.email,
                this.address,
                this.tags,
                this.modules,
                this.moduleTimings
        );
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tags", tags)
                .add("modules", modules)
                .add("moduleTimings", moduleTimings)
                .toString();
    }

}
