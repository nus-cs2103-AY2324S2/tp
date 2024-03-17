package seedu.address.model.person;

public enum PersonType {
    STUDENT,
    TA;

    public static final String MESSAGE_CONSTRAINTS = "Person type should only be 'stu' or 'ta'";
    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
