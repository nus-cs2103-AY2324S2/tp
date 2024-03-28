package seedu.address.model.person.attribute;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NameAttributeTest {

    @Test
    public void testConstructor() {
        // Given name and value for the NameAttribute
        String attributeName = "FullName";
        String attributeValue = "John Doe";

        // When creating a new NameAttribute
        NameAttribute nameAttribute = new NameAttribute(attributeName, attributeValue);

        // Then the name and value should be correctly set
        assertEquals(attributeName, nameAttribute.getName(), "Attribute name should be correctly set by constructor");
        assertEquals(attributeValue, nameAttribute.getValue(), "Name value should be correctly set by constructor");
    }

    @Test
    public void testToString() {
        // Given name and value for the NameAttribute
        String attributeName = "FullName";
        String attributeValue = "John Doe";

        // When creating a new NameAttribute
        NameAttribute nameAttribute = new NameAttribute(attributeName, attributeValue);

        // Then the toString method should return the value of the NameAttribute
        assertEquals(attributeValue, nameAttribute.toString(),
                "toString method should return the value of the NameAttribute");
    }

}
