package seedu.findvisor.logic.parser;

import seedu.findvisor.logic.commands.RemarkCommand;
import seedu.findvisor.logic.parser.exceptions.ParseException;

public class RemarkCommandParser implements Parser<RemarkCommand> {

    public RemarkCommand parse(String args) throws ParseException {
        return new RemarkCommand();
    }
}
