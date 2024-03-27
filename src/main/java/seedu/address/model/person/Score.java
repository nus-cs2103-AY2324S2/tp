package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Exam's score in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidScore(String)}
 */
public class Score {

    public static final String MESSAGE_CONSTRAINTS =
            "Scores should be numeric, and it should not be blank or less than zero.";

    /*
     * The score must be a non-negative integer.
     */
    public static final String VALIDATION_REGEX = "\\d+";

    public final int value;

    /**
     * Constructs a {@code Score}.
     *
     * @param score A valid score.
     */
    public Score(int score) {
        requireNonNull(score);
        checkArgument(isValidScore(score), MESSAGE_CONSTRAINTS);
        this.value = score;
    }

    /**
     * Returns true if a given string is a valid score.
     */
    public static boolean isValidScore(int test) {
        return test >= 0;
    }

    public int getScore() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Score)) {
            return false;
        }

        Score otherScore = (Score) other;
        return value == otherScore.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

}
