package tutorpro.model.person;

import java.util.Set;

import tutorpro.model.tag.Tag;

/**
 * Represents a Student in TutorPro.
 */
public class Student extends Person {

    public static final Tag STUDENT_TAG = new Tag("Student");

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        addTags(STUDENT_TAG);
    }
}
