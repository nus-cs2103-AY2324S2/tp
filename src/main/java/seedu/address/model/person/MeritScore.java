package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * This Merit Score is the class of reputation of a borrower
 */
public class MeritScore {

    public static final String MESSAGE_CONSTRAINTS =
            "Merit Score must be >= 0";

    /**
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "-?\\d+";

    public final int meritScore;

    /**
     * Constructs a {@code MeritScore}.
     *
     * @param meritScore A valid merit score.
     */
    public MeritScore(int meritScore) {
        requireNonNull(meritScore);
        checkArgument(isValidMeritScore(String.valueOf(meritScore)), MESSAGE_CONSTRAINTS);
        this.meritScore = meritScore;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidMeritScore(String test) {

        return test.matches(VALIDATION_REGEX);
    }

    public MeritScore incrementScore() {
        return new MeritScore(this.meritScore + 1);
    }

    public MeritScore decrementScore() {
        return new MeritScore(this.meritScore - 1);
    }

    public int getScore() {
        return meritScore;
    }

    @Override
    public String toString() {
        return String.valueOf(meritScore);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MeritScore)) {
            return false;
        }

        MeritScore otherName = (MeritScore) other;
        return meritScore == otherName.meritScore;
    }

    @Override
    public int hashCode() {
        Integer score = meritScore;
        return score.hashCode();
    }

}
