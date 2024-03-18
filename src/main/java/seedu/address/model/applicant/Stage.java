package seedu.address.model.applicant;

/**
 * An object that represents the stage that an
 * applicant is in during a hiring process.
 */
public class Stage {
    public final String stageName;

    public Stage(String stageName) {
        this.stageName = stageName;
    }

    @Override
    public String toString() {
        return stageName;
    }

    @Override
    public int hashCode() {
        return stageName == null ? 0 : stageName.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Stage)) {
            return false;
        }

        Stage otherStage = (Stage) other;
        return stageName.equals(otherStage.stageName);
    }
}
