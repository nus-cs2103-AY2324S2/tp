package seedu.teachstack.logic.parser;

import seedu.teachstack.logic.commands.AddCommand;
import seedu.teachstack.logic.commands.SetWeakThresholdCommand;
import seedu.teachstack.logic.parser.exceptions.ParseException;
import seedu.teachstack.model.person.Grade;

import static seedu.teachstack.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.teachstack.logic.parser.CliSyntax.PREFIX_GRADE;

public class SetWeakThresholdCommandParser implements Parser<SetWeakThresholdCommand> {
    public SetWeakThresholdCommand parse(String args) throws ParseException{
        try {
            System.out.println(args);
            Grade g = new Grade(args.trim());
            System.out.println("okay created2");
            return new SetWeakThresholdCommand(g);

        } catch (Exception e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetWeakThresholdCommand.MESSAGE_SET_THRESHOLD_FAIL + args));
        }

    }

}
