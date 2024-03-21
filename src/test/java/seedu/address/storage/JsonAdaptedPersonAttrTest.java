package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;
import seedu.address.model.person.attribute.Attribute;
import seedu.address.model.person.attribute.NameAttribute;
import seedu.address.model.person.attribute.StringAttribute;


public class JsonAdaptedPersonAttrTest {

    private static final String VALID_ATTRIBUTE_NAME = "Age";
    private static final String VALID_ATTRIBUTE_VALUE = "30";
    private static final String VALID_UUID = "00000000-0000-0000-0000-000000000001";
    private static final JsonAdaptedAttribute VALID_ATTRIBUTE =
            new JsonAdaptedAttribute(VALID_ATTRIBUTE_NAME, VALID_ATTRIBUTE_VALUE);
    private static final List<JsonAdaptedAttribute> VALID_ATTRIBUTES = new ArrayList<>();

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        // Create a list of valid attributes
        VALID_ATTRIBUTES.add(VALID_ATTRIBUTE);

        // Create JsonAdaptedPersonAttr with valid attributes and tags
        JsonAdaptedPersonAttr jsonAdaptedPersonAttr = new JsonAdaptedPersonAttr(VALID_UUID, VALID_ATTRIBUTES);

        // Convert to model type and check if conversion was successful
        Person person = jsonAdaptedPersonAttr.toModelType();
        assertEquals(1, person.getAttributes().size());
    }

    @Test
    public void toModelType_invalidAttribute_throwsIllegalValueException() {
        // Create JsonAdaptedAttribute with invalid type
        JsonAdaptedAttribute jsonAdaptedAttribute = new JsonAdaptedAttribute("", VALID_ATTRIBUTE_VALUE);
        List<JsonAdaptedAttribute> invalidAttributes = new ArrayList<>();
        invalidAttributes.add(jsonAdaptedAttribute);

        // Create JsonAdaptedPersonAttr with invalid attributes
        JsonAdaptedPersonAttr jsonAdaptedPersonAttr = new JsonAdaptedPersonAttr(VALID_UUID, invalidAttributes);

        // Verify that IllegalValueException is thrown when converting to model type
        assertThrows(IllegalValueException.class, jsonAdaptedPersonAttr::toModelType);
    }

    @Test
    public void constructor_validPerson_success() {
        // Create a Person with attributes and tags
        Attribute[] attributes = new Attribute[2];
        attributes[0] = new NameAttribute("Name", "John Doe");
        attributes[1] = new StringAttribute("Email", "john@example.com");


        Person person = new Person(attributes);

        // Convert the Person to JsonAdaptedPersonAttr
        JsonAdaptedPersonAttr jsonAdaptedPersonAttr = new JsonAdaptedPersonAttr(person);

        // Check if attributes are converted correctly
        assertEquals(2, jsonAdaptedPersonAttr.getAttributes().size());
    }

}
