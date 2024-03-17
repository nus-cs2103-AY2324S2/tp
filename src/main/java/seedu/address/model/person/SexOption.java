package seedu.address.model.person;

/**
 * Represents one of sex, Male or Female.
 */
public enum SexOption {
    MALE("Male"),
    FEMALE("Female");

    private final String label;

    SexOption(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
