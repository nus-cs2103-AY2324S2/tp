package seedu.address.logic.parser;

import seedu.address.logic.commands.CreateAptCommand;
import seedu.address.logic.parser.exceptions.ParseException;


public class CreateAptCommandParser {
    public CreateAptCommand parse(String args) throws ParseException {
        final String[] nameAndDateTime = args.trim().split("/time", 2);

        if (nameAndDateTime.length < 2) {
            throw new ParseException(CreateAptCommand.MESSAGE_USAGE);
        }

        String name = nameAndDateTime[0].trim();
        String dateTimeStr = nameAndDateTime[1].trim();


        return new CreateAptCommand(name, dateTimeStr);
    }
}
