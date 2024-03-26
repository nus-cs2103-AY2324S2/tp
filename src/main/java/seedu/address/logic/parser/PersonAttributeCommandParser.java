package seedu.address.logic.parser;

import seedu.address.logic.commands.AddAttributeCommand;
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
        String[] parts = userInput.trim().split(" ", 4);

        // Validate the basic command structure
        if (parts.length < 2) {
            throw new ParseException("Invalid command format.");
        }

        String commandType = parts[0];

        if ("addAttribute".equals(commandType)) {
            return parseAddCommand(parts);
        } else {
            throw new ParseException("Unknown command or operation.");
        }
    }

    private Command parseAddCommand(String[] parts) throws ParseException {
        if (parts.length < 4) {
            throw new ParseException("Incomplete command for adding an attribute.");
        }
        String uuid = parts[1];
        String attributeName = parts[2];
        String attributeValue = parts[3];

        return new AddAttributeCommand(uuid, attributeName, attributeValue);
    }
}
