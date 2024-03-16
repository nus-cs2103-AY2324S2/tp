package seedu.address.model.person;

public enum Categories {
    PARTICIPANT, STAFF, SPONSOR;

    public static boolean contains(String test) {
        for (Categories c: Categories.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }
        return false;
    }
}
