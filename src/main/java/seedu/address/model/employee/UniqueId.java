package seedu.address.model.employee;

public class UniqueId {
    private Integer uid;
    private Integer step;
    private Integer base;

    public UniqueId(int step, int base) {
        this.step = step;
        this.base = base;
        this.uid = null;
    }

    public UniqueId(Integer uid) {
        this.uid = uid;
        this.step = null;
        this.base = null;
    }

    /*
     * Set the ID to the next available ID
     */
    public void generateNewId() {
        if (uid == null) {
            uid = base + step;
        }
        step++;
    }

    /*
     * Get the current ID
     */
    public Integer getUidValue() {
        return uid;
    }

    public boolean isValidUid(UniqueId uid) {
        // Check if uid is null
        if (uid == null) {
            return false;
        }
        if (uid.getUidValue() == null) {
            return false;
        }
        return uid.getUidValue() >= base;
    }

    /*
     * Convert string to integer and set it as the ID,
     */
    public void setUid(String uid) {
        try {
            this.uid = Integer.parseInt(uid);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid ID");
        }
    }

    /*
     * Represent ID as a string
     */
    @Override
    public String toString() {
        return uid.toString();
    }

    /*
     * Check if two UniqueId objects are equal
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
        return otherId.getUidValue().equals(getUidValue());
    }
}
