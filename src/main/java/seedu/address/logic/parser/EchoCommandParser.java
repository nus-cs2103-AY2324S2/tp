package seedu.address.logic.parser;

import seedu.address.logic.commands.EchoCommand;

public class EchoCommandParser implements Parser<EchoCommand> {
    @Override
    public EchoCommand parse(String userInput) {
        return new EchoCommand(userInput);
    }
}
