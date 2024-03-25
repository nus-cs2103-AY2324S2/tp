package seedu.address.model.exam;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Exam in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidName(String)}
 */
public class Exam {

    public static final String MESSAGE_CONSTRAINTS = "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     * The rest of the string can contain any alphanumeric character and spaces.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String name;
    public final int maxScore;
    /**
     * Constructs an {@code Exam}.
     *
     * @param name A valid name.
     * @param maxScore A valid max score.
     */
    public Exam(String name, int maxScore) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        this.name = name;
        this.maxScore = maxScore;
    }

    public String getName() {
        return name;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public boolean isSameExam(Exam otherExam) {
        if (otherExam == this) {
            return true;
        }

        return otherExam != null
                && otherExam.getName().equals(getName());
    }

    /**
     * Returns if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public static boolean isValidScore(int test) {
        return test >= 0;
    }

    @Override
    public String toString() {
        return name + ": " + maxScore;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Exam)) {
            return false;
        }

        Exam otherExam = (Exam) other;
        return name.equals(otherExam.name)
                && maxScore == otherExam.maxScore;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}