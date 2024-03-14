package seedu.address.model.applicant;

/**
 * Represents an Applicant's stage in the address book.
 * Guarantees: immutable;
 */
public class Stage {
    public final String value;
    public Stage(String stage) {
        this.value = stage;
    }

    @Override
    public String toString() {
        return value;
    }
}
