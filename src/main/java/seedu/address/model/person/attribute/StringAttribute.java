package seedu.address.model.person.attribute;

/**
 * Represents a String-based attribute for a person in the address book.
 * A subclass of {@code Attribute}, it encapsulates a {@code String} value,
 * providing a means to store and retrieve string-based information about a person.
 */
public class StringAttribute extends Attribute {
    private String value;

    /**
     * Constructs a {@code StringAttribute} with a given name and string value.
     *
     * @param name The name of the attribute.
     * @param value The string value of the attribute.
     */
    public StringAttribute(String name, String value) {
        super(name);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String getValueAsString() {
        return value;
    }

    // Function for partial string matching
    public boolean matches(String searchString) {
        return value.contains(searchString);
    }
}
