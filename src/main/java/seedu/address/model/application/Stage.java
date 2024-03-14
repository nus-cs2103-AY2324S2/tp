package seedu.address.model.application;

/**
 * An object that represents the stage that an
 * applicant is in during a hiring process.
 */
public class Stage {
    private final String stageName;

    public Stage(String stageName) {
        this.stageName = stageName;
    }

    public String getStageName() {
        return stageName;
    }

    @Override
    public String toString() {
        return getStageName();
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
        if (!(other instanceof seedu.address.model.application.Stage)) {
            return false;
        }

        seedu.address.model.application.Stage otherStage = (seedu.address.model.application.Stage) other;
        return stageName.equals(otherStage.stageName);
    }
}
