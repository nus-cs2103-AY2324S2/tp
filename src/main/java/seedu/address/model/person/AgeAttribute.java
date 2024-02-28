package seedu.address.model.person;

class AgeAttribute extends IntegerAttribute {
    public AgeAttribute(String name, int value) {
        super(name, value);
        // Validate age
        if (value < 0 || value > 150) {
            throw new IllegalArgumentException("Invalid age value");
        }
    }
}
