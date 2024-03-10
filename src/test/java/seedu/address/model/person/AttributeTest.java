package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
