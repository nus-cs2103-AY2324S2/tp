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

    public final String meritScore;

    /**
     * Constructs a {@code MeritScore}.
     *
     * @param meritScore A valid merit score.
     */
    public MeritScore(int meritScore) {
        this.meritScore = String.valueOf(meritScore);
    }

    /**
     * Constructs a {@code MeritScore} from String.
     *
     * @param meritScore A valid merit score.
     */
    public MeritScore(String meritScore) {
        requireNonNull(meritScore);
        checkArgument(isValidMeritScore(meritScore), MESSAGE_CONSTRAINTS);
        this.meritScore = meritScore;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidMeritScore(String test) {

        return test.matches(VALIDATION_REGEX);
    }

    public MeritScore incrementScore() {
        int score = Integer.parseInt(meritScore);
        score++;
        return new MeritScore(String.valueOf(score));
    }

    public MeritScore decrementScore() {
        int score = Integer.parseInt(meritScore);
        score--;
        return new MeritScore(String.valueOf(score));
    }

    public String getScore() {
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
        // Integer score = meritScore;
        // return score.hashCode();
        return meritScore.hashCode();
    }

}
