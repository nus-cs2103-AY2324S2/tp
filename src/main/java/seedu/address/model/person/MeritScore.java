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

    /**
     * Instead of Stirng, convert merit score into int and return it.
     * This function also checks whether merit score is able to parse as int.
     * @return int version of merit score
     */
    public int getMeritScoreInt() {
        // Check if meritScore is null or empty
        if (meritScore == null || meritScore.isEmpty()) {
            throw new IllegalArgumentException("meritScore is null or empty");
        }

        // Check if meritScore consists only of digits
        if (!meritScore.matches("\\d+")) {
            throw new IllegalArgumentException("meritScore contains non-numeric characters");
        }

        try {
            // Parse meritScore as an integer
            return Integer.parseInt(meritScore);
        } catch (NumberFormatException e) {
            // Handle the case where meritScore cannot be parsed as an integer
            throw new IllegalArgumentException("meritScore cannot be parsed as an integer: " + meritScore);
        }
    }

    /**
     * Increases the merit score by 1.
     *
     * @return new incremented MeritScore
     */
    public MeritScore incrementScore() {
        int score = Integer.parseInt(meritScore);
        score++;
        return new MeritScore(String.valueOf(score));
    }

    /**
     * Decreases the merit score by 1.
     *
     * @return new decremented MeritScore
     */
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
        return meritScore.equals(otherName.meritScore);
    }

    @Override
    public int hashCode() {
        // Integer score = meritScore;
        // return score.hashCode();
        return meritScore.hashCode();
    }

}
