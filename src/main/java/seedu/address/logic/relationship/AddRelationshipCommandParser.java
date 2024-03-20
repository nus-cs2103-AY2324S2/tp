package seedu.address.logic.relationship;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new AddRelationshipCommand object
 */
public class AddRelationshipCommandParser {
    /**
     * Parses a userInput into the arguments to add a relationship to AB3
     * @param userInput user-input command
     * @return an addRelationshipCommand with the necessary arguments
     * @throws ParseException
     */
    public AddRelationshipCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        String[] parts = userInput.split(" ", 3);
        try {
            String originUuid = ParserUtil.parseUuid(parts[0]);
            String targetUuid = ParserUtil.parseUuid(parts[1]);
            String relationshipDescriptor = parts[2];
            return new AddRelationshipCommand(originUuid, targetUuid, relationshipDescriptor);
        } catch (ParseException pe) {
            throw new ParseException(Messages.MESSAGE_INVALID_PERSON_UUID);
        }
    }
}
