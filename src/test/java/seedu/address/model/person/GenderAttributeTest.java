package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GenderAttributeTest {

    @Test
    public void testConstructorAndGetter() {
        GenderAttribute maleGenderAttribute = new GenderAttribute("Gender", GenderAttribute.Gender.MALE);
        assertEquals(GenderAttribute.Gender.MALE, maleGenderAttribute.getGender(),
                "Constructor should correctly set gender to MALE");

        GenderAttribute femaleGenderAttribute = new GenderAttribute("Gender", GenderAttribute.Gender.FEMALE);
        assertEquals(GenderAttribute.Gender.FEMALE, femaleGenderAttribute.getGender(),
                "Constructor should correctly set gender to FEMALE");
    }

    @Test
    public void testGetValueAsString() {
        GenderAttribute genderAttribute = new GenderAttribute("Gender", GenderAttribute.Gender.MALE);
        assertEquals("MALE", genderAttribute.getValueAsString(),
                "getValueAsString should return 'MALE' for male gender");

        genderAttribute.setGender(GenderAttribute.Gender.FEMALE);
        assertEquals("FEMALE", genderAttribute.getValueAsString(),
                "getValueAsString should return 'FEMALE' after setting gender to female");
    }

    @Test
    public void testSetGender() {
        GenderAttribute genderAttribute = new GenderAttribute("Gender", GenderAttribute.Gender.MALE);
        genderAttribute.setGender(GenderAttribute.Gender.FEMALE);
        assertEquals(GenderAttribute.Gender.FEMALE, genderAttribute.getGender(),
                "setGender should correctly change gender to FEMALE");
    }
}

