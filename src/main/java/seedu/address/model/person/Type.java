package seedu.address.model.person;

public enum Type {
    PATIENT,
    DOCTOR;

    public String toString() {
        return name();
    }
}
