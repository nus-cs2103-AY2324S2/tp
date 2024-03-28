package seedu.address.model.employee;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * This class represents a unique ID for an employee.
 * Todo: Add validation for uid
 */
public class UniqueId {
    private static final String VALIDATION_REGEX = "\\d+";

    private static Integer lastUsedIndex;
    private Integer uid;

    /**
     * Constructor for creating a new UniqueId
     */
    public UniqueId() {
        this.uid = 0;
    }

    /**
     * Constructor for creating a new UniqueId with an integer number
     *
     * @param intUid The specific ID.
     * @throws IllegalStateException If the ID is null, empty or less than 0.
     */
    public UniqueId(Integer intUid) throws IllegalStateException, IllegalValueException {
        this.uid = intUid;
        UniqueId.lastUsedIndex = intUid;
    }

    /**
     * Constructor for creating a new UniqueId with a string number
     *
     * @param strUid The specific ID.
     * @throws IllegalStateException If the ID is null, empty or less than 0.
     */
    public UniqueId(String strUid) throws IllegalStateException {
        this.uid = Integer.parseInt(strUid);
        UniqueId.lastUsedIndex = Integer.parseInt(strUid);
    }

    /**
     * Generates a new ID. If no ID has been set, it initializes the ID with the
     * last used index.
     * Otherwise, it increments the ID by the step value.
     */
    public void generateNewId() {
        if (uid == null) {
            throw new NullPointerException("ID is null while generating");
        }
        this.uid = ++lastUsedIndex;
    }

    /**
     * @return The current ID.
     */
    public Integer getUidValue() {
        return uid;
    }

    /**
     * Checks if the given unique ID is valid.
     *
     * @param intUid The unique ID to be checked.
     * @return true if the unique ID is valid, false otherwise.
     */
    public static boolean isValidUid(Integer intUid) {
        if (intUid == null || intUid < 0) {
            return false;
        }
        return true;
    }

    /**
     * Checks if a given string is a valid unique identifier (UID).
     *
     * @param strUid The string to be checked.
     * @return true if the string is a valid UID, false otherwise.
     */
    public static boolean isValidUid(String strUid) {
        if (strUid == null || strUid.isEmpty() || !strUid.matches(VALIDATION_REGEX)) {
            return false;
        }
        return true;
    }

    /**
     * Sets the last used index.
     *
     * @param lastUsedIndex The new last used index.
     * @throws NumberFormatException If the last used index is not valid.
     * @throws IllegalStateException If the last used index is greater than the
     *                               step.
     */
    public static void setLastUsedIndex(Integer lastUsedIndex) {
        if (lastUsedIndex == null) {
            throw new NumberFormatException("Null or Empty Last Used Index");
        }
        try {
            UniqueId.lastUsedIndex = lastUsedIndex;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid Last Used Index");
        }
    }

    /**
     * @return The ID as a string. If the ID is null
     * @throws NullPointerException if uid is null
     */
    @Override
    public String toString() {
        if (uid == null) {
            throw new NullPointerException("ID is null while converting to string");
        }
        return uid.toString();
    }

    /**
     * Checks if this UniqueId is equal to another object.
     *
     * @param other The object to compare with.
     * @return True if the other object is a UniqueId and its value is equal to
     *         this UniqueId's value.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof UniqueId)) {
            return false;
        }

        UniqueId otherId = (UniqueId) other;
        return otherId.getUidValue() != null
                && otherId.getUidValue().equals(getUidValue());
    }
}
