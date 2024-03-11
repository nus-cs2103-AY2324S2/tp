package seedu.address.model.person;

/**
 * Enumeration representing different types of entities in the system.
 * This enum defines two constants: {@code PATIENT} and {@code DOCTOR},
 * which represent distinct roles or types of entities within the system.
 * The {@code toString()} method is overridden to return the name of the enum constant.
 */
public enum Type {
    PATIENT,
    DOCTOR;

    public String toString() {
        return name();
    }
}
