package tutorpro.model.person.student;

import static java.util.Objects.requireNonNull;

import tutorpro.commons.util.AppUtil;

/**
 * Represents a subject that a student is being taught in TutorPro.
 * Guarantees: immutable; is valid as declared in {@link #isValidSubject(String)}
 */
public class Subject {

    public static final String MESSAGE_CONSTRAINTS = "Subjects can take any values, and it should not be blank";
    public static final String VALIDATION_REGEX = "[^\\s].*";


    private String value;

    /**
     * Constructs a {@code Level}
     *
     * @param subject A valid subject.
     */
    public Subject(String subject) {
        requireNonNull(subject);
        AppUtil.checkArgument(isValidSubject(subject), MESSAGE_CONSTRAINTS);
        this.value = subject;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof Subject)) {
            return false;
        }
        Subject otherLevel = (Subject) other;
        return value.equals(otherLevel.value);
    }

    /**
     * Returns true if the given String is a valid Level.
     */
    public static boolean isValidSubject(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
