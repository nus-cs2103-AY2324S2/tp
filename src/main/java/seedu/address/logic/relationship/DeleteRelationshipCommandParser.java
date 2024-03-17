package seedu.address.logic.relationship;

import java.util.Map;

import seedu.address.model.person.Person;
import seedu.address.model.person.relationship.RelationshipManager;

/**
 * Parses user input into a DeleteRelationshipCommand.
 */
public class DeleteRelationshipCommandParser {
    private final Map<String, Person> personMap;
    private final RelationshipManager relationshipManager;

    /**
     * Creates a new DeleteRelationshipCommandParser with the given person map and relationship manager.
     *
     * @param personMap           A map linking UUID strings to Person objects.
     * @param relationshipManager The RelationshipManager instance managing the relationships.
     */
    public DeleteRelationshipCommandParser(Map<String, Person> personMap, RelationshipManager relationshipManager) {
        this.personMap = personMap;
        this.relationshipManager = relationshipManager;
    }

    /**
     * Parses the given user input and returns a DeleteRelationshipCommand.
     *
     * @param userInput The user input to parse.
     * @return The DeleteRelationshipCommand corresponding to the user input.
     * @throws IllegalArgumentException If the user input is invalid.
     */
    public DeleteRelationshipCommand parse(String userInput) throws IllegalArgumentException {
        DeleteRelationshipCommand command = new DeleteRelationshipCommand(personMap, relationshipManager);
        command.parseCommand(userInput);
        return command;
    }
}
