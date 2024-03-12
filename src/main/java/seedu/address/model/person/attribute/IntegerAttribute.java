package seedu.address.model.person.attribute;

/**
 * Integer attribute with integer value
 */
public class IntegerAttribute extends Attribute {
    private int value;

    /**
     * Constructor for IntegerAttribute
     * @param name name of the attribute
     * @param value value of the attribute
     */
    public IntegerAttribute(String name, int value) {
        super(name);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String getValueAsString() {
        return Integer.toString(value);
    }

    // Function for sorting by integer value
    public int compare(IntegerAttribute other) {
        return Integer.compare(value, other.value);
    }

}
