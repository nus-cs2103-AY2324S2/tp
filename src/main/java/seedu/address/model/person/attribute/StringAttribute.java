package seedu.address.model.person.attribute;

/**
 * Represents a String-based attribute for a person in the address book.
 * A subclass of {@code Attribute}, it encapsulates a {@code String} value,
 * providing a means to store and retrieve string-based information about a person.
 */
public class StringAttribute extends Attribute {
    private String value;

    /**
     * Constructs a new StringAttribute instance with a specified name and value.
     *
     * @param name The name of the attribute, which serves as an identifier.
     * @param value The value of the attribute, representing the data stored within.
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
