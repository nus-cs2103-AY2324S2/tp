package seedu.address.model.person.attribute;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StringAttributeTest {
    private StringAttribute stringAttributeOne;
    private StringAttribute stringAttributeTwo;

    @BeforeEach
    public void setUp() {
        stringAttributeOne = new StringAttribute("TestAttributeOne", "TestValueOne");
        stringAttributeTwo = new StringAttribute("TestAttributeTwo", "TestValueTwo");
    }

    @Test
    public void getValue_returnsCorrectValue() {
        assertEquals("TestValueOne", stringAttributeOne.getValue());
        assertEquals("TestValueTwo", stringAttributeTwo.getValue());
    }

    @Test
    public void getValueAsString_returnsCorrectValue() {
        assertEquals("TestValueOne", stringAttributeOne.getValueAsString());
        assertEquals("TestValueTwo", stringAttributeTwo.getValueAsString());
    }

    @Test void matches_returnsCorrectValue() {
        assertEquals(true, stringAttributeOne.matches("Test"));
        assertEquals(false, stringAttributeOne.matches("TestValueTwo"));
    }
}
