package seedu.address.logic.parser;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.commands.DeleteAptCommand;

public class DeleteAptCommandParser implements Parser<DeleteAptCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteAppointmentCommand
     * and returns a DeleteAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public DeleteAptCommand parse(String args) throws ParseException {
        try {
            String trimmedArgs = args.trim();
            if (!trimmedArgs.matches("\\d+ /name .+")) {
                throw new ParseException(DeleteAptCommand.MESSAGE_USAGE);
            }

            String[] parts = trimmedArgs.split("/name");
            if (parts.length != 2) {
                throw new ParseException(DeleteAptCommand.MESSAGE_USAGE);
            }

            int index = Integer.parseInt(parts[0].trim());
            String patientName = parts[1].trim();

            if (index <= 0) {
                throw new ParseException("Index must be a positive integer.");
            }

            return new DeleteAptCommand(index, patientName);
        } catch (NumberFormatException e) {
            throw new ParseException("The index provided is not a valid integer.");
        }
    }
}