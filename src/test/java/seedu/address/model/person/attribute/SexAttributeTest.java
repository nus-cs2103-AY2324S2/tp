package seedu.address.model.person.attribute;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.attribute.SexAttribute;

public class SexAttributeTest {

    @Test
    public void testConstructorAndGetter() {
        SexAttribute maleSexAttribute = new SexAttribute("Gender", SexAttribute.Gender.MALE);
        assertEquals(SexAttribute.Gender.MALE, maleSexAttribute.getGender(),
                "Constructor should correctly set gender to MALE");

        SexAttribute femaleSexAttribute = new SexAttribute("Gender", SexAttribute.Gender.FEMALE);
        assertEquals(SexAttribute.Gender.FEMALE, femaleSexAttribute.getGender(),
                "Constructor should correctly set gender to FEMALE");
    }

    @Test
    public void testGetValueAsString() {
        SexAttribute sexAttribute = new SexAttribute("Gender", SexAttribute.Gender.MALE);
        assertEquals("MALE", sexAttribute.getValueAsString(),
                "getValueAsString should return 'MALE' for male gender");

        sexAttribute.setGender(SexAttribute.Gender.FEMALE);
        assertEquals("FEMALE", sexAttribute.getValueAsString(),
                "getValueAsString should return 'FEMALE' after setting gender to female");
    }

    @Test
    public void testSetGender() {
        SexAttribute sexAttribute = new SexAttribute("Gender", SexAttribute.Gender.MALE);
        sexAttribute.setGender(SexAttribute.Gender.FEMALE);
        assertEquals(SexAttribute.Gender.FEMALE, sexAttribute.getGender(),
                "setGender should correctly change gender to FEMALE");
    }
}

