package seedu.address.logic.relationship;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input into an EditRelationshipCommand.
 */
public class EditRelationshipCommandParser {
    /**
     * Parses the given user input and returns an EditRelationshipCommand.
     *
     * @param userInput The user input to parse.
     * @return The EditRelationshipCommand corresponding to the user input.
     * @throws IllegalArgumentException If the user input is invalid.
     */
    public EditRelationshipCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        String[] parts = userInput.split(" ", 4);
        if (parts.length != 4) {
            throw new ParseException(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }
        try {
            String originUuid = ParserUtil.parseUuid(parts[0]);
            String targetUuid = ParserUtil.parseUuid(parts[1]);
            String oldRelationshipDescriptor = parts[2];
            String newRelationshipDescriptor = parts[3];
            return new EditRelationshipCommand(originUuid, targetUuid,
                    oldRelationshipDescriptor, newRelationshipDescriptor);
        } catch (ParseException pe) {
            throw new ParseException(Messages.MESSAGE_INVALID_PERSON_UUID);
        }
    }
}
