package seedu.address.logic.parser;

import java.util.UUID;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.EditPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.EditPersonDescriptor;
import seedu.address.model.person.attribute.Attribute;

/**
 * This class is responsible for parsing user input strings into {@code EditPersonCommand} objects.
 * It supports parsing commands for two primary operations:
 * adding new attributes to a person and deleting attributes from a person.
 * The expected format for the user input is as follows:
 * Attributes are specified by their names, prefixed with a hyphen.
 * For additions, each attribute name must be followed by its corresponding value.
 * The UUID must be in a valid format, and the parser will throw an error if the format
 * is incorrect or if required parts of the command are missing.
 */
public class EditPersonCommandParser implements Parser<Command> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditPersonCommand
     * and returns an EditPersonCommand object for execution.
     *
     * @param userInput The user input to parse.
     * @return The parsed command.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    @Override
    public Command parse(String userInput) throws ParseException {
        String trimmedInput = userInput.trim();
        String[] parts = trimmedInput.split("\\s+");

        // Basic validation
        if (parts.length < 4) {
            throw new ParseException("Incorrect command format.");
        }

        String action = parts[0]; // /add or /delete
        String uuidString = parts[1];

        UUID uuid;
        try {
            uuid = UUID.fromString(uuidString);
        } catch (IllegalArgumentException e) {
            throw new ParseException("Invalid UUID format.");
        }

        EditPersonDescriptor descriptor = new EditPersonDescriptor();

        if ("/add".equals(action)) {
            // Start from index 2 to skip action and UUID
            for (int i = 2; i < parts.length; i += 2) {
                String attributeName = parts[i].substring(1); // Assuming attribute names are prefixed
                if (i + 1 >= parts.length) {
                    throw new ParseException("Missing value for attribute: " + attributeName);
                }
                String attributeValue = parts[i + 1];

                // Assuming Attribute.fromString can parse the attribute value into the correct Attribute subclass
                Attribute attribute = Attribute.fromString(attributeName, attributeValue);
                descriptor.setAttribute(attributeName, attribute);
            }
        } else if ("/delete".equals(action)) {
            // Handle deletion similarly, focusing on attribute names without values
        } else {
            throw new ParseException("Unknown action: " + action);
        }

        // Assuming EditPersonCommand takes a UUID and an EditPersonDescriptor
        return new EditPersonCommand(uuid, descriptor);
    }
}
