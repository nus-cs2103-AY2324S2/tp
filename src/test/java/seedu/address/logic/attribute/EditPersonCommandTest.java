package seedu.address.logic.attribute;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.model.person.attribute.Attribute;
import seedu.address.model.person.attribute.NameAttribute;
import seedu.address.model.person.attribute.StringAttribute;

class EditPersonCommandTest {

    private Map<String, Person> personMap;
    private EditPersonCommand editPersonCommand;

    @BeforeEach
    void setUp() {
        personMap = new HashMap<>();

        // Create attributes for a person
        Attribute name = new NameAttribute("Name", "John Doe");
        Attribute phone = new StringAttribute("Phone", "123456789");
        Attribute email = new StringAttribute("Email", "johndoe@example.com");
        Attribute address = new StringAttribute("Address", "123 Main St");

        // Add attributes to an array
        Attribute[] attributes = new Attribute[]{name, phone, email, address};

        // Instantiate a Person with the specified attributes
        Person person = new Person(attributes);

        // Use the UUID from the person object as it's generated in the constructor
        String uuidString = person.getUuidString();

        // Add the person to the map
        personMap.put(uuidString, person);

        // Instantiate the EditPersonCommand with the person map
        editPersonCommand = new EditPersonCommand(personMap);
    }


    @Test
    void testAddAttribute() {
        String uuid = personMap.keySet().iterator().next();
        String command = "cmd /add /uuid " + uuid + " attributeName attributeValue";
        editPersonCommand.parseCommand(command);

        Person person = personMap.get(uuid);
        assertTrue(person.hasAttribute("attributeName"), "Person should have the added attribute.");

        Attribute attribute = person.getAttribute("attributeName");
        assertEquals("attributeValue", attribute.getValueAsString(), "Attribute value should match the one provided.");
    }

    @Test
    void testDeleteAttribute() {
        // Assuming person already has an attribute named "attributeName"
        String uuid = personMap.keySet().iterator().next();
        Person person = personMap.get(uuid);
        person.updateAttribute(new StringAttribute("attributeName", "attributeValue"));

        String command = "cmd /delete /uuid " + uuid + " attributeName";
        editPersonCommand.parseCommand(command);

        assertFalse(person.hasAttribute("attributeName"), "Person should not have the deleted attribute.");
    }

    @Test
    void testInvalidCommandFormat() {
        String command = "cmd /add";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            editPersonCommand.parseCommand(command);
        });

        String expectedMessage = "Invalid command format.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUnsupportedOperation() {
        String uuid = personMap.keySet().iterator().next();
        String command = "cmd /unsupported " + uuid + " attributeName attributeValue";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            editPersonCommand.parseCommand(command);
        });

        String expectedMessage = "Unsupported operation";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}
