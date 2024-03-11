package seedu.address.model.person.attribute;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.attribute.PhoneNumberAttribute;

public class PhoneNumberAttributeTest {

    @Test
    public void testConstructor() {
        // Example phone number
        int phoneNumberValue = 123456789;
        PhoneNumberAttribute phoneNumberAttribute = new PhoneNumberAttribute("TestPhone", phoneNumberValue);

        assertEquals("TestPhone", phoneNumberAttribute.getName(),
                "Name should be correctly set by constructor");
        assertEquals(phoneNumberValue, phoneNumberAttribute.getValue(),
                "Phone number value should be correctly set by constructor");
    }

}
