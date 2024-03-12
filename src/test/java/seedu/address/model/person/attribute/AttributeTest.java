package seedu.address.model.person.attribute;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AttributeTest {

    private static class ConcreteAttribute extends Attribute {
        private String value;

        public ConcreteAttribute(String name, String value) {
            super(name);
            this.value = value;
        }

        @Override
        public String getValueAsString() {
            return value;
        }
    }

    private ConcreteAttribute attribute;

    @BeforeEach
    public void setUp() {
        attribute = new ConcreteAttribute("TestName", "TestValue");
    }

    @Test
    public void getName_returnsCorrectName() {
        assertEquals("TestName", attribute.getName(), "The name should match the one set in constructor.");
    }

    @Test
    public void getValueAsString_returnsCorrectValue() {
        assertEquals("TestValue", attribute.getValueAsString(), "The value should match the one set in constructor.");
    }

    @Test
    public void testFromStringWithInteger() {
        String name = "testInt";
        String value = "123";
        Attribute result = Attribute.fromString(name, value);
        assertTrue(result instanceof IntegerAttribute, "The result should be an instance of IntegerAttribute");
        assertEquals(name, result.getName(), "Attribute name should match");
        assertEquals(Integer.parseInt(value), ((IntegerAttribute) result).getValue(), "Attribute value should match");
    }

    @Test
    public void testFromStringWithDate() {
        String name = "testDate";
        String value = "2020-01-01";
        Attribute result = Attribute.fromString(name, value);
        assertTrue(result instanceof DateAttribute, "The result should be an instance of DateAttribute");
        assertEquals(name, result.getName(), "Attribute name should match");
        assertEquals(LocalDate.parse(value), ((DateAttribute) result).getValue(), "Attribute value should match");
    }

    @Test
    public void testFromStringWithString() {
        String name = "testString";
        String value = "notAnIntOrDate";
        Attribute result = Attribute.fromString(name, value);
        assertTrue(result instanceof StringAttribute, "The result should be an instance of StringAttribute");
        assertEquals(name, result.getName(), "Attribute name should match");
        assertEquals(value, ((StringAttribute) result).getValue(), "Attribute value should match");
    }

    @Test
    public void testFromStringWithInvalidDate() {
        String name = "testInvalidDate";
        String value = "2020-02-30"; // This is an invalid date
        Attribute result = Attribute.fromString(name, value);
        assertTrue(result instanceof StringAttribute, "Should fallback to StringAttribute on invalid date");
        assertEquals(value, ((StringAttribute) result).getValue(), "Attribute value should match");
    }
}
