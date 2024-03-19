package seedu.address.model.employee;

/**
 * This class represents a unique ID for an employee.
 */
public class UniqueId {
    // private Integer uid;

    // public UniqueId() {
    // this.uid = 0;
    // }

    // public UniqueId(Integer uid) {
    // this.uid = uid;
    // }

    // public Integer getUidValue() {
    // return uid;
    // }

    private Integer uid;
    public static Integer lastUsedIndex;

    /**
     * Constructor for creating a new UniqueId with a step and value.
     *
     * @param step          The increment value for each new ID.
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
        // Todo: Add validation for uid
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
        UniqueId.lastUsedIndex = this.uid;
    }

    /**
     * @return The current ID.
     */
    public Integer getUidValue() {
        return uid;
    }

    /**
     * Checks if a given UniqueId is valid.
     *
     * @param uid The UniqueId to check.
     * @return True if the UniqueId is not null and its value is greater than or
     *         equal to the last used index.
     */
    public boolean isValidUid(UniqueId uid) {
        if (uid == null || uid.getUidValue() == null) {
            return false;
        }
        return uid.getUidValue() >= lastUsedIndex;
    }

    /**
     * Checks if a given UniqueId is valid, given String.
     *
     * @param uid The UniqueId to check.
     * @return True if the UniqueId is not null and its value is greater than or
     *         equal to the last used index.
     */
    public boolean isValidUid(String uid) {
        if (uid == null || uid.isEmpty()) {
            return false;
        }
        try {
            Integer newUid = Integer.parseInt(uid);
            return checkIfValid(newUid, lastUsedIndex);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Sets the ID to a given value.
     *
     * @param uid The new ID value as a string.
     * @throws NumberFormatException If the given string cannot be parsed to an
     *                               integer or if the new ID is not valid.
     */
    public void setUid(String uid) {
        if (uid == null || uid.isEmpty()) {
            throw new NumberFormatException("Null or Empty ID");
        }
        try {
            Integer newUid = Integer.parseInt(uid);
            if (!isValidUid(new UniqueId(newUid))) {
                throw new NumberFormatException("Invalid ID");
            }
            this.uid = newUid;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid ID");
        }
    }

    /**
     * Checks if a given ID is valid.
     *
     * @param uid           The ID to check.
     * @param lastUsedIndex The last used index.
     * @return True if the ID is not null and is greater than or equal to the last
     *         used index.
     */
    public static boolean checkIfValid(Integer uid, Integer lastUsedIndex) {
        return uid != null && uid >= lastUsedIndex && uid > 0;
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
            // Integer newLastUsedIndex = Integer.parseInt(lastUsedIndex);
            // if (newLastUsedIndex > step) {
            // throw new IllegalStateException("Last Used Index is greater than Step");
            // }
            UniqueId.lastUsedIndex = lastUsedIndex;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid Last Used Index");
        }
    }

    /**
     * @return The ID as a string. If the ID is null, it returns the string
     *         "null".
     */
    @Override
    public String toString() {
        if (uid == null) {
            throw new NullPointerException("ID is null");
        }
        return uid.toString();
    }

    /**
     * Checks if this UniqueId is equal to another object.
     *
     * @param other The object to compare with.
     * @return True if the other object is a UniqueId and its value is equal to
     *         this
     *         UniqueId's value.
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
        return otherId.getUidValue() != null &&
                otherId.getUidValue().equals(getUidValue());
    }
}
