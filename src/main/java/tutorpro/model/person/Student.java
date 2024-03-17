package tutorpro.model.person;

import tutorpro.model.tag.Tag;

import java.util.Set;

public class Student extends Person {

    public static final Tag STUDENT_TAG = new Tag("Student");

    public Student(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        addTags(STUDENT_TAG);
    }
}
