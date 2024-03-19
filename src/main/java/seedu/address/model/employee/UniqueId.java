package seedu.address.model.employee;

/**
 * This class represents a unique ID for an employee.
 * Todo: Add validation for uid
 */
public class UniqueId {
    private static Integer lastUsedIndex;
    private Integer uid;

    /**
     * Constructor for creating a new UniqueId with a step and value.
     *
     * @param lastUsedIndex The starting value for the ID.
     */
    public UniqueId(int lastUsedIndex) {
        this.uid = 0;
        UniqueId.lastUsedIndex = lastUsedIndex;
    }

    /**
     * Constructor for creating a new UniqueId with a specific ID.
     *
     * @param uid The specific ID.
     */
    public UniqueId(Integer uid) {
        this.uid = uid;
        UniqueId.lastUsedIndex = uid;
    }

    /**
     * Constructor for creating a new UniqueId
     */
    public UniqueId() {
        this.uid = 0;
    }

    /**
     * Generates a new ID. If no ID has been set, it initializes the ID with the
     * last used index.
     * Otherwise, it increments the ID by the step value.
     */
    public void generateNewId() {
        if (uid == null) {
            System.out.println("why no work");
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
            throw new NullPointerException("ID is null");
        }
        return "Unique ID: " + uid;
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
