package tutorpro.model.person.student;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javafx.scene.layout.Region;
import tutorpro.commons.util.CollectionUtil;
import tutorpro.commons.util.ToStringBuilder;
import tutorpro.model.person.Address;
import tutorpro.model.person.Email;
import tutorpro.model.person.Name;
import tutorpro.model.person.Person;
import tutorpro.model.person.Phone;
import tutorpro.model.tag.Tag;
import tutorpro.ui.StudentCard;
import tutorpro.ui.UiPart;

/**
 * Represents a Student in TutorPro.
 */
public class Student extends Person {

    public static final Tag STUDENT_TAG = new Tag("Student");

    private final Level level;
    private final Set<Subject> subjects = new HashSet<>();


    /**
     * Every field must be present and not null.
     */
    public Student(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                   Level level, Set<Subject> subjects) {
        super(name, phone, email, address, tags);
        addTags(STUDENT_TAG);
        CollectionUtil.requireAllNonNull(level, subjects);
        this.level = level;
        this.subjects.addAll(subjects);
    }

    /**
     * Creates a copy of the given Student.
     * @return The level of the student.
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Returns an immutable subject set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * @return The subjects of the student as an immutable set.
     */
    public Set<Subject> getSubjects() {
        return Collections.unmodifiableSet(subjects);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof Student)) {
            return false;
        } else if (!super.equals(other)) {
            return false;
        }
        Student otherStudent = (Student) other;
        return level.equals(otherStudent.level) && subjects.equals(otherStudent.subjects);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", super.getName())
                .add("phone", super.getPhone())
                .add("email", super.getEmail())
                .add("address", super.getAddress())
                .add("tags", super.getTags())
                .add("level", this.level)
                .add("subjects", this.subjects)
                .toString();
    }

    /**
     * Returns a UI representation of the person.
     * @param displayIndex The index to be displayed on the card.
     */
    @Override
    public UiPart<Region> getCard(int displayIndex) {
        return new StudentCard(this, displayIndex);
    }
}
