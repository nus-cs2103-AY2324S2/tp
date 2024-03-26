package seedu.address.model.person;

/**
 * Represents a Person's Pin in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidNote(String)}
 */
public class Pin {

    private Boolean isPin;

    /**
     * Constructs an {@code Note}.
     *
     * @param note A valid note.
     */
    public Pin() {
        isPin = false;
    }

    public void setPin() {
        this.isPin = true;
    }

    public void setUnpin() {
        this.isPin = false;
    }

    public boolean getIsPinned() {
        return this.isPin;
    }

    @Override
    public String toString() {
        return isPin ? "true" : "false";
    }
}
