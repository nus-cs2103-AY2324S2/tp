package tutorpro.model.person.student;

import static java.util.Objects.requireNonNull;

import tutorpro.commons.util.AppUtil;

/**
 * Represents a student's educational level in TutorPro.
 * Guarantees: immutable; is valid as declared in {@link #isValidLevel(String)}
 */
public class Level {

    public static final String MESSAGE_CONSTRAINTS = "Levels should only consist of a letter and a number, or be NA";
    public static final String VALIDATION_REGEX = "K[12]|P[123456]|S[1234]|J[12]|UNI|OTHER";

    private String value;

    /**
     * Constructs a {@code Level}
     *
     * @param level A valid level.
     */
    public Level(String level) {
        requireNonNull(level);
        AppUtil.checkArgument(isValidLevel(level), MESSAGE_CONSTRAINTS);
        this.value = level;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof Level)) {
            return false;
        }
        Level otherLevel = (Level) other;
        return value.equals(otherLevel.value);
    }

    /**
     * Returns true if the given String is a valid Level.
     */
    public static boolean isValidLevel(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
