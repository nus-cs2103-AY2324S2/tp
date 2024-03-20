package seedu.address.model.person;


public class Info {
    public final String value;

    /**
     * Constructs a {@code Info}.
     *
     * @param info Information about the person in the address book
     */
    public Info(String info) {
        value = info;
    }

    public Info() {
        value = "";
    }

    public static boolean isValidInfo(String test) {
        return true;
    }

    public String getInfo() { return value; }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Info)) {
            return false;
        }

        Info otherInfo = (Info) other;
        return value.equals(otherInfo.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
