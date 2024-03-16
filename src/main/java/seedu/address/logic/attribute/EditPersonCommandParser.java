package seedu.address.logic.attribute;

import java.util.Map;

import seedu.address.model.person.Person;


/**
 * This class is responsible for parsing user input strings into commands for editing Person objects.
 * It leverages the EditPersonCommand class to perform the actual command execution, serving as a
 * bridge between raw user input and the structured commands needed to modify Person instances.
 */
public class EditPersonCommandParser {

    private final Map<String, Person> personMap;

    /**
     * Constructs an EditPersonCommandParser with a reference to the existing map of persons.
     * This map links UUID strings to Person objects and is used to look up persons during command execution.
     *
     * @param personMap A map linking person UUID strings to Person objects, not null.
     */
    public EditPersonCommandParser(Map<String, Person> personMap) {
        this.personMap = personMap;
    }


    /**
     * Parses the given user input string into an executable EditPersonCommand. The parsing process
     * interprets the command type (/add or /delete), validates the format of the input, and prepares
     * an EditPersonCommand object for execution.
     *
     * The expected format of the user input is:
     * - For adding an attribute: "/add /uuid [UUID] [AttributeName] [AttributeValue]"
     * - For deleting an attribute: "/delete /uuid [UUID] [AttributeName]"
     *
     * This method validates the input against these formats and constructs an appropriate command.
     *
     * @param userInput The user input string representing a command to edit a Person's attributes.
     * @return An instance of EditPersonCommand prepared for execution based on the provided user input.
     * @throws IllegalArgumentException If the user input does not conform to the expected format or
     *                                  if the operation specified is unsupported.
     */
    public EditPersonCommand parse(String userInput) throws IllegalArgumentException {
        EditPersonCommand command = new EditPersonCommand(personMap);
        command.parseCommand(userInput);
        return command;
    }
}
