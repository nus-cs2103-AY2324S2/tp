package seedu.address.model.person;
/**
 * Represents a Person's id in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidId(String)}
 */
public class Id {
    public static final String MESSAGE_CONSTRAINTS =
            "Ids should only contain numbers";

    /**
     * id is current stored with #000000 format
     */
    public final String id;

    public Id(String id) {
        this.id = id;
    }

    public Id(int id) {
        this.id = Integer.toString(id);
    }
    /**
     * Checks if the given id is a valid Id.
     * @param test Input id to validate.
     * @return Result of validation.
     */
    public static boolean isValidId(String test) {
        try {
            Integer.parseInt(test);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public int getInt() {
        String results = id.substring(1);
        return Integer.parseInt(results);
    }


    @Override
    public String toString() {
        return this.id;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Id) {
            Id otherId = (Id) other;
            return id.equals(otherId.id);
        } else {
            return false;
        }
    }

}
