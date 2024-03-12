package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.person.attribute.Attribute;
import seedu.address.model.person.attribute.DateAttribute;
import seedu.address.model.person.attribute.IntegerAttribute;
import seedu.address.model.person.attribute.StringAttribute;

public class EditPersonDescriptorTest {

    private EditPersonDescriptor descriptor;

    @BeforeEach
    public void setUp() {
        descriptor = new EditPersonDescriptor();
    }

    @Test
    public void setAttribute_newAttribute_success() {
        String attributeName = "Name";
        Attribute attributeValue = new StringAttribute(attributeName, "John Doe");
        descriptor.setAttribute(attributeName, attributeValue);
        assertTrue(descriptor.getAttributes().containsKey(attributeName), "Attribute should be added");
    }

    @Test
    public void getAttribute_existingAttribute_found() {
        String attributeName = "Birthday";
        Attribute attributeValue = new DateAttribute(attributeName, LocalDate.of(1990, 1, 1));
        descriptor.setAttribute(attributeName, attributeValue);
        Optional<Attribute> retrievedAttribute = descriptor.getAttribute(attributeName);
        assertTrue(retrievedAttribute.isPresent(), "Attribute should exist");
        assertEquals(attributeValue, retrievedAttribute.get(), "Retrieved attribute should match the set attribute");
    }

    @Test
    public void getAttribute_nonExistingAttribute_notFound() {
        String attributeName = "NonExisting";
        Optional<Attribute> retrievedAttribute = descriptor.getAttribute(attributeName);
        assertFalse(retrievedAttribute.isPresent(), "Attribute should not exist");
    }

    @Test
    public void getAttributes_emptyDescriptor_emptyMap() {
        assertTrue(descriptor.getAttributes().isEmpty(), "Attributes map should be empty");
    }

    @Test
    public void getAttributes_nonEmptyDescriptor_nonEmptyMap() {
        String attributeName1 = "Email";
        Attribute attributeValue1 = new StringAttribute(attributeName1, "example@example.com");
        descriptor.setAttribute(attributeName1, attributeValue1);

        String attributeName2 = "Phone";
        Attribute attributeValue2 = new IntegerAttribute("PhoneNumber", 12345678);
        descriptor.setAttribute(attributeName2, attributeValue2);

        Map<String, Attribute> attributes = descriptor.getAttributes();
        assertEquals(2, attributes.size(), "Attributes map should contain two entries");
        assertTrue(attributes.containsKey(attributeName1) && attributes.containsKey(attributeName2),
                "Attributes map should contain the correct keys");
    }
}

