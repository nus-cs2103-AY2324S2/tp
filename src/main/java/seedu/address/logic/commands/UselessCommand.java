package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * This is a useless command that does nothing but helps me finish tutorial 2
 */
public class UselessCommand extends Command{
   public static final String Nothing = "I've done nothing, I'm a useless command";

    public static final String COMMAND_WORD = "useless";
    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(Nothing, true, false);
    }
}
