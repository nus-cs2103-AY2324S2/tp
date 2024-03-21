package seedu.address.logic.relationship;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses user input into a DeleteRelationshipCommand.
 */
public class DeleteRelationshipCommandParser implements Parser<DeleteRelationshipCommand> {
    /**
     * Parses the given user input and returns a DeleteRelationshipCommand.
     *
     * @param userInput The user input to parse.
     * @return The DeleteRelationshipCommand corresponding to the user input.
     * @throws IllegalArgumentException If the user input is invalid.
     */
    public DeleteRelationshipCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        String[] parts = userInput.split(" ", 3);
        try {
            String originUuid = ParserUtil.parseUuid(parts[0]);
            String targetUuid = ParserUtil.parseUuid(parts[1]);
            String relationshipDescriptor = parts[2];
            return new DeleteRelationshipCommand(originUuid, targetUuid, relationshipDescriptor);
        } catch (ParseException pe) {
            throw new ParseException(Messages.MESSAGE_INVALID_PERSON_UUID);
        }
    }
}
