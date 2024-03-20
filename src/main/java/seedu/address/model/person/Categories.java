package seedu.address.model.person;

/**
 * Enum for three categories of contacts.
 */
public enum Categories {
    PARTICIPANT, STAFF, SPONSOR;

    /**
     * @param test string to be tested.
     * @return boolean whether enum contains test.
     */
    public static boolean contains(String test) {
        for (Categories c : Categories.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }
        return false;
    }
}
