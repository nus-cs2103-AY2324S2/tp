package seedu.address.model.person.attribute;

/**
 * Attribute with type and value
 */
public abstract class Attribute {
    protected String name;

    public Attribute(String name) {
        this.name = name;
    }

    public abstract String getValueAsString();

    public String getName() {
        return name;
    }
}
