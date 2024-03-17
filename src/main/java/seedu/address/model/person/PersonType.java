package seedu.address.model.person;

public enum PersonType {
    STUDENT,
    TA;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
