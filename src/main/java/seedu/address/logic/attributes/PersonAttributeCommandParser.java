package seedu.address.logic.attributes;

import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input into commands for managing person attributes.
 * This parser handles the conversion of user input strings into specific Command objects
 * that can add or delete attributes associated with persons in the address book.
 */
public class PersonAttributeCommandParser {

    /**
     * Parses the given user input string into an executable command.
     *
     * @param userInput The user input string representing a command.
     * @return A Command object ready for execution.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    public Command parse(String userInput) throws ParseException {
        String[] parts = userInput.trim().split("\\s+", 5);

        // Validate the basic command structure
        if (parts.length < 3) {
            throw new ParseException("Invalid command format.");
        }

        String commandType = parts[0];

        if ("addAttribute".equals(commandType)) {
            return parseAddCommand(parts);
        } else if ("deleteAttribute".equals(commandType)) {
            return parseDeleteCommand(parts);
        } else {
            throw new ParseException("Unknown command or operation.");
        }
    }

    private Command parseAddCommand(String[] parts) throws ParseException {
        if (parts.length < 5) {
            throw new ParseException("Incomplete command for adding an attribute.");
        }
        String uuid = parts[2];
        String attributeName = parts[3].replaceFirst("^\\\\", "");
        String attributeValue = parts[4];

        return new AddAttributeCommand(uuid, attributeName, attributeValue);
    }

    private Command parseDeleteCommand(String[] parts) throws ParseException {
        if (parts.length < 4) {
            throw new ParseException("Incomplete command for deleting an attribute.");
        }
        String uuid = parts[2];
        String attributeName = parts[3].replaceFirst("^\\\\", "");

        // The DeleteAttributeCommand constructor accepts the entire command parts for further processing.
        return new DeleteAttributeCommand(uuid, attributeName);
    }
}
