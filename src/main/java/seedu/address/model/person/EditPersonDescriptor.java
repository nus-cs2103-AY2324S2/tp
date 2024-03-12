package seedu.address.model.person;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import seedu.address.model.person.attribute.Attribute;

/**
 * Represents a descriptor for editing a person's attributes in the address book.
 * This descriptor allows for the modification of attributes of a person.
 * Each attribute is identified by a string name and is associated with a specific {@link Attribute} instance.
 * Attributes that can be edited include, but are not limited to, name, phone, email, and address.
 */
public class EditPersonDescriptor {
    // Assuming each attribute in your model extends some Attribute class or interface
    private Map<String, Attribute> attributes;

    public EditPersonDescriptor() {
        this.attributes = new HashMap<>();
    }

    // Set a specific attribute by name
    public void setAttribute(String attributeName, Attribute attributeValue) {
        attributes.put(attributeName, attributeValue);
    }

    // Retrieve a specific attribute by name, if it exists
    public Optional<Attribute> getAttribute(String attributeName) {
        return Optional.ofNullable(attributes.get(attributeName));
    }

    // Retrieve all attributes
    public Map<String, Attribute> getAttributes() {
        return attributes;
    }

    // Add methods to check if any attribute is edited, to copy from another descriptor, etc.
    // You might need to implement additional methods depending on your application's requirements
}

