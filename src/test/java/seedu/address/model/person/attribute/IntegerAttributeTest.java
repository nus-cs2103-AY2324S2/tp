package seedu.address.model.person.attribute;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.model.person.attribute.IntegerAttribute;

public class IntegerAttributeTest {

    private IntegerAttribute attributeOne;
    private IntegerAttribute attributeTwo;
    private IntegerAttribute attributeThree;

    @BeforeEach
    public void setUp() {
        attributeOne = new IntegerAttribute("TestAttributeOne", 5);
        attributeTwo = new IntegerAttribute("TestAttributeTwo", 10);
        attributeThree = new IntegerAttribute("TestAttributeThree", 5);
    }

    @Test
    public void testGetValue() {
        assertEquals(5, attributeOne.getValue());
        assertEquals(10, attributeTwo.getValue());
    }

    @Test
    public void testGetValueAsString() {
        assertEquals("5", attributeOne.getValueAsString());
        assertEquals("10", attributeTwo.getValueAsString());
    }

    @Test
    public void testCompare() {
        assertEquals(0, attributeOne.compare(attributeThree)); // same value
        assertTrue(attributeOne.compare(attributeTwo) < 0); // attributeOne < attributeTwo
        assertTrue(attributeTwo.compare(attributeOne) > 0); // attributeTwo > attributeOne
    }

    @Test
    public void testConstructor() {
        assertEquals("TestAttributeOne", attributeOne.getName()); //test the getName method
        assertEquals(5, attributeOne.getValue());
    }
}

