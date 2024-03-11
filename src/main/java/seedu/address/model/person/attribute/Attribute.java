package seedu.address.model.person.attribute;

abstract class Attribute {
    protected String name;

    public Attribute(String name) {
        this.name = name;
    }

    public abstract String getValueAsString();

    public String getName() {
        return name;
    }
}
