package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.BENSON;

import org.junit.jupiter.api.Test;


public class JsonAdaptedPersonTest {
    // private static final String INVALID_NAME = "R@chel";
    // private static final String INVALID_PHONE = "+651234";
    // private static final String INVALID_ADDRESS = " ";
    // private static final String INVALID_EMAIL = "example.com";
    // private static final String INVALID_TAG = "#friend";

    // private static final String VALID_NAME = BENSON.getName().toString();


    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }



}
