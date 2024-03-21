package tutorpro.model.person.parent;

import java.util.HashSet;
import java.util.Set;

import tutorpro.commons.util.CollectionUtil;
import tutorpro.commons.util.ToStringBuilder;
import tutorpro.model.person.Address;
import tutorpro.model.person.Email;
import tutorpro.model.person.Name;
import tutorpro.model.person.Person;
import tutorpro.model.person.Phone;
import tutorpro.model.person.student.Student;
import tutorpro.model.tag.Tag;

/**
 * Represents a Parent in TutorPro.
 */
public class Parent extends Person {

    public static final Tag PARENT_TAG = new Tag("Parent");

    private final Set<Student> students = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Parent(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Set<Student> students) {
        super(name, phone, email, address, tags);
        addTags(PARENT_TAG);
        CollectionUtil.requireAllNonNull(students);
        this.students.addAll(students);
    }
    /**
     * Creates a copy of the given Parent.
     * @return The students of the parent.
     */
    public Set<Student> getStudents() {
        return students;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Parent)) {
            return false;
        }

        Parent otherParent = (Parent) other;
        return otherParent.getName().equals(getName())
                && otherParent.getPhone().equals(getPhone())
                && otherParent.getEmail().equals(getEmail())
                && otherParent.getAddress().equals(getAddress())
                && otherParent.getTags().equals(getTags())
                && otherParent.getStudents().equals(getStudents());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", getName())
                .add("phone", getPhone())
                .add("email", getEmail())
                .add("address", getAddress())
                .add("tags", getTags())
                .add("students", getStudents())
                .toString();
    }
}
