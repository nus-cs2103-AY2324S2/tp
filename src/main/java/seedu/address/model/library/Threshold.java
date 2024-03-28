package seedu.address.model.library;

import seedu.address.model.person.MeritScore;

/**
 * Acts as the limit for {@link seedu.address.model.person.MeritScore} during borrowing.
 * Default threshold is -3.
 */
public class Threshold {
    private final int threshold;

    /**
     * @param threshold The limit for Merit Score.
     */
    public Threshold(int threshold) {
        this.threshold = threshold;
    }

    /**
     * Constructs threshold with a default value of -3.
     */
    public Threshold() {
        this.threshold = -3;
    }

    public boolean isLessThanOrEqualTo(MeritScore meritScore) {
        return threshold <= meritScore.getMeritScoreInt();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Threshold)) {
            return false;
        }

        Threshold otherThreshold = (Threshold) other;
        return threshold == otherThreshold.threshold;
    }

    @Override
    public String toString() {
        return String.valueOf(threshold);
    }
}
