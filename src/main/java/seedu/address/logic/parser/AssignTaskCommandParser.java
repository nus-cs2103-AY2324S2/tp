package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.commands.AssignTaskCommand;

import java.lang.reflect.InvocationTargetException;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;


public class AssignTaskCommandParser implements Parser<AssignTaskCommand> {

    public AssignTaskCommand parse(String args) throws ParseException {
        try {
            args = args.trim();
            String[] parts = args.split(" ");

            int arg1 = Integer.parseInt(parts[0]);
            int arg2 = Integer.parseInt(parts[1]);

            return new AssignTaskCommand(arg1,arg2);
        } catch (NumberFormatException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignTaskCommand.MESSAGE_USAGE));
        }
    }
}
