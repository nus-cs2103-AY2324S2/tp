package seedu.address.model.person.attribute;

/**
 * String attribute with string value
 */
public class StringAttribute extends Attribute {
    private String value;
    /**
     * Constructor for StringAttribute
     *
     * @param name name of the attribute
     * @param value value of the attribute
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
