package seedu.address.model.person.attribute;

class StringAttribute extends Attribute {
    private String value;

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
